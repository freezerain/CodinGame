package classicEasy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/hooch-clash
class HoochClash {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int minD = in.nextInt();
        int maxD = in.nextInt();
        Orb gOrb = new Orb(in.nextInt(), in.nextInt());
        List<Orb> oList = new ArrayList<>();
        for (int i = minD; i <= maxD; i++)
            for (int j = i; j <= maxD; j++){
                Orb o = new Orb(i, j);
                if (o.totalVolume == gOrb.totalVolume) oList.add(o);
            }
        System.out.println(oList.size() == 0 ? "NOT VALID" : oList.size() == 1 ? "VALID" :
                String.valueOf(oList.stream()
                        .max((o1, o2) ->
                                o1.diameterA == gOrb.diameterA || o1.diameterB == gOrb.diameterB ||
                                o1.diameterA == o1.diameterB ? -1 :
                                        Long.compare(o1.surfaceAreaDiff, o2.surfaceAreaDiff))

                        .get()));
    }

    static class Orb {
        int diameterA;
        int diameterB;
        long surfaceAreaDiff;
        long totalVolume;

        public Orb(int diameterA, int diameterB) {
            this.diameterA = diameterA;
            this.diameterB = diameterB;
            surfaceAreaDiff = (long) (4 * Math.PI * Math.pow(diameterB / 2.0, 2) -
                                      4 * Math.PI * Math.pow(diameterA / 2.0, 2));
            totalVolume = (long) (4 / 3.0 * Math.PI * Math.pow(diameterA / 2.0, 3) +
                                  4 / 3.0 * Math.PI * Math.pow(diameterB / 2.0, 3));
        }

        @Override
        public String toString() {
            return diameterA + " " + diameterB;
        }

    }
}