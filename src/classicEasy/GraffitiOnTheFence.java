package classicEasy;

import java.util.*;

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
        for (int i = 0; i < N; i++) {
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
        while(it1.hasNext()){
            Range r1 = it1.next();
            it2=it1;
            if(it2.hasNext()) {
                Range r2 = it2.next();
                if(r1.isCollide(r2)){
                    rangeList.set(rangeList.indexOf(r1), r1.merge(r2));
                    it2.remove();
                    it1 = rangeList.iterator();
                }
            } else break;
        }
        if(rangeList.size()>0 && rangeList.get(0).equals(new Range(0, L)))
            System.out.println("All painted");
        else if(rangeList.size() ==0) System.out.println("0 " + L);
        else{
            if(rangeList.get(0).start != 0) unpainted.add(0);
            for (Range range : rangeList) {
                unpainted.add(range.start);
                unpainted.add(range.end);
            }
            if(unpainted.get(unpainted.size()-1)!=L) unpainted.add(L);
            for(int i = 0; i<unpainted.size();i+=2){
                System.out.println(unpainted.get(i) + " " + unpainted.get(i+1));
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

    public Range merge(Range r) {
        return new Range(Math.min(start, r.start), Math.max(end, r.end));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Range range = (Range) o;
        return start == range.start &&
                end == range.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}