package classicEasy;

import java.util.Scanner;
import java.util.stream.IntStream;
//https://www.codingame.com/ide/puzzle/whats-so-complex-about-mandelbrot
class WhatsSoComplexAboutMandelbrot {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String c = in.nextLine();
        int delimeterIndex = IntStream.range(1, c.length())
                .filter(i -> c.charAt(i) == '+' || c.charAt(i) == '-')
                .findFirst()
                .getAsInt();
        System.out.println(getIter(Double.parseDouble(c.substring(0, delimeterIndex)),
                Double.parseDouble(c.substring(delimeterIndex, c.length() - 1)), in.nextInt()));
    }

    private static int getIter(double r, double i, int max) {
        double nR = 0.0;
        double nI = 0.0;
        for (int j = 0; j < max; j++){
            double tempR = nR * nR - nI * nI + r;
            nI = 2 * nR * nI + i;
            nR = tempR;
            if (nR * nR + nI * nI > 4.0) return j + 1;
        }
        return max;
    }
}