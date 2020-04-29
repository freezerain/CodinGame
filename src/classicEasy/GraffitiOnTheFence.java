package classicEasy;

import java.util.*;
import java.util.stream.Collectors;

//https://www.codingame.com/training/easy/graffiti-on-the-fence
public class GraffitiOnTheFence {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int L = in.nextInt();
        int N = in.nextInt();
        ArrayList<Range> rangeList = new ArrayList<>();
        LinkedList<Integer> ran = new LinkedList<>();
        for(int i = 0; i < N; i++)
            rangeList.add(new Range(in.nextInt(), in.nextInt()));
        outerLoop:
        while (true) {
            Iterator<Range> it1 = rangeList.iterator();
            Iterator<Range> it2;
            mergeLoop:
            while (it1.hasNext()) {
                int index = rangeList.indexOf(it1.next());
                it2 = rangeList.listIterator(index + 1);
                while (it2.hasNext()) {
                    Range r2 = it2.next();
                    if (rangeList.get(index).isCollide(r2) || r2.isCollide(rangeList.get(index))) {
                        rangeList.set(index, rangeList.get(index).merge(r2));
                        it2.remove();
                        break mergeLoop;
                    }
                }
            }
            for(int i = 0; i < rangeList.size() - 1; i++){
                for(int j = i + 1; j < rangeList.size(); j++){
                    if (rangeList.get(i).isCollide(rangeList.get(j))) {
                        continue outerLoop;
                    }
                }
            }
            break;
        }
        for(Range r: rangeList){
            ran.add(r.start);
            ran.add(r.end);
        }
        ran.sort(Integer::compare);
        if (ran.isEmpty() || ran.size() == 2 && ran.getFirst() == 0 && ran.getLast() == L) {
            System.out.println("All painted");
            return;
        }
        if (ran.getFirst() != 0) ran.addFirst(0);
        else if (ran.getFirst() == 0) ran.removeFirst();
        if (ran.getLast() != L) ran.add(L);
        else if (ran.getLast() == L) ran.removeLast();
        while (!ran.isEmpty()) {
            if (ran.size() < 2) break;
            System.out.println(ran.poll() + " " + ran.poll());
        }
    }
}

class Range {
    int start, end;

    public Range(int x, int y) {
        this.start = x;
        this.end = y;
    }

    public boolean isCollide(Range r) {
        return (r.start >= start && r.start <= end) || (r.end >= start && r.end <= end);
    }

    public Range merge(Range r) {
        return new Range(Math.min(Math.min(start, r.start), Math.min(end, r.end)), Math.max(Math.max(end, r.end), Math.max(start, r.start)));
    }
}