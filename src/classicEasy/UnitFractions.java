package classicEasy;

import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/unit-fractions
//All credits goes to @MericLuc 
public class UnitFractions {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        long n = in.nextInt();
        long N = n * n;
        for (long i = 1; i < N / 2 + 1; i++){
            long x = n + N / i;
            long y = n + i;
            if (x < y) break;
            if (N % i == 0) System.out.println(String.format("1/%d = 1/%d + 1/%d", n, x, y));
        }
    }
}