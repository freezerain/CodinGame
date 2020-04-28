package classicEasy;

import java.util.Scanner;
//https://www.codingame.com/training/easy/the-river-i-
public class TheRiverI {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        long r1 = in.nextLong();
        long r2 = in.nextLong();
        long meetPoint = -1;

        while (meetPoint < 0) {
            if (r1 == r2) meetPoint = r1;//meet point found
            else if (r1 < r2) r1 = riverStep(r1);//choose which river to increment
            else r2 = riverStep(r2);
        }
        System.out.println(meetPoint);
    }

    static long riverStep(long river) {
        long startLong = river;
        while (startLong > 0) {//get sum of all digits of number
            river = river + (startLong % 10);
            startLong = startLong / 10;
        }
        return river;
    }
}