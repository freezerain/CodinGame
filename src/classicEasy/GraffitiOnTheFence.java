package classicEasy;

import java.util.*;
import java.util.stream.Collectors;

//https://www.codingame.com/training/easy/graffiti-on-the-fence
//TODO This is not solved yet
public class GraffitiOnTheFence {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int L = in.nextInt();
        int N = in.nextInt();
        ArrayList<Integer> unpainted = new ArrayList<>();
        ArrayList<Range> rangeList = new ArrayList<>();
        outerLoop:
        for(int i = 0; i < N; i++){
            rangeList.add(new Range(in.nextInt(), in.nextInt()));
        }
        rangeList.sort(new Comparator<Range>() {
            @Override
            public int compare(Range o1, Range o2) {
                return Integer.compare(o1.start, o2.start);
            }
        });
        Iterator<Range> it1 = rangeList.iterator();
        Iterator<Range> it2;
        while (it1.hasNext()) {
            Range r1 = it1.next();
            it2 = it1;
            if (it2.hasNext()) {
                Range r2 = it2.next();
                if (r1.isCollide(r2)) {
                    rangeList.set(rangeList.indexOf(r1), r1.merge(r2));
                    it2.remove();
                    it1 = rangeList.iterator();
                }
            } else break;
        }
        if (rangeList.size() > 0 && rangeList.get(0).equals(new Range(0, L))) System.out.println("All painted");
        else if (rangeList.size() == 0) System.out.println("0 " + L);
        else {
            ArrayList<Range> unpaintedRanges = new ArrayList<>();
            unpaintedRanges.add(new Range(0, L));
            ArrayList<Range> paintedRanges = rangeList;
            while (!paintedRanges.isEmpty()) {
                ArrayList<Range> tempUnpaintedRanges = new ArrayList<>();
                for(int i = 0; i < unpaintedRanges.size(); i++){
                    Range r = unpaintedRanges.get(i);
                    Iterator<Range> paintedIt = paintedRanges.iterator();
                    while (paintedIt.hasNext()) {
                        Range painted = paintedIt.next();
                        if (r.isCollide(painted)) {
                            tempUnpaintedRanges.addAll(r.split(painted));
                            paintedIt.remove();
                            break;
                        }
                    }
                }
                unpaintedRanges = tempUnpaintedRanges;
            }
            Iterator<Range> unIt1 = unpaintedRanges.iterator();
            Iterator<Range> unIt2;
            while (unIt1.hasNext()) {
                Range r1 = unIt1.next();
                unIt2 = unIt1;
                if (unIt2.hasNext()) {
                    Range r2 = unIt2.next();
                    if (r1.isCollide(r2)) {
                        unpaintedRanges.set(unpaintedRanges.indexOf(r1), r1.merge(r2));
                        unIt2.remove();
                        unIt1 = unpaintedRanges.iterator();
                    }
                } else break;
            }
            for (Range r : unpaintedRanges) {
                System.out.println(r);
            }
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

    public ArrayList<Range> split(Range splitter) {
        ArrayList<Range> splittedArr = new ArrayList<>();
        if (!isCollide(splitter)) {
            splittedArr.add(this);
            return splittedArr;
        }
        ArrayList<Integer> tempArr = new ArrayList<>();
        tempArr.add(Math.min(start, splitter.start));
        tempArr.add(Math.max(start, splitter.start));
        tempArr.add(Math.min(end, splitter.end));
        tempArr.add(Math.max(end, splitter.end));
        tempArr = tempArr.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
        for(int i = 0; i < tempArr.size() - 1; i++){
            splittedArr.add(new Range(tempArr.get(i), tempArr.get(i + 1)));
        }
        return splittedArr;
    }


    @Override
    public String toString() {
        return start + " " + end;
    }

    public Range merge(Range r) {
        return new Range(Math.min(start, r.start), Math.max(end, r.end));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Range range = (Range) o;
        return start == range.start && end == range.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}