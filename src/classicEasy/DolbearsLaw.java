package classicEasy;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/dolbears-law
class DolbearsLaw {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int M = Integer.parseInt(in.nextLine());
        int[][] chirps = new int[M][15];
        for (int i = 0; i < M; i++)
            chirps[i] = Arrays.stream(in.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        double avg60 = Arrays.stream(chirps)
                .mapToDouble(s -> 10 + (Arrays.stream(s).sum() - 40) / 7.0)
                .sum() / (double) M;
        System.out.format(Locale.US, "%.1f\n", avg60);
        if (avg60 < 5.0 || avg60 > 30.0) return;
        System.out.format(Locale.US, "%.1f\n", (Arrays.stream(chirps)
                .flatMapToInt(Arrays::stream)
                .limit(M * 15 - (M * 15 % 2 == 0 ? 0 : 1))
                .mapToDouble(i -> i + 2.5)
                .sum() / ((M * 15 - (M * 15 % 2 == 0 ? 0 : 1)) / 2.0)));
    }
}
