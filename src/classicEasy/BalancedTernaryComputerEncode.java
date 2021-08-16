package classicEasy;

import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/balanced-ternary-computer-encode
class BalancedTernaryComputerEncode {

    public static void main(String args[]) {
        int N = new Scanner(System.in).nextInt();
        System.out.println(N == 0 ? 0 : N > 0 ? toTernary(N) : toTernary(-N)
                .replace('1', '2')
                .replace('T', '1')
                .replace('2', 'T'));
    }
    private static String toTernary(int i) {
        if (i == 0) return "";
        if (i % 3 == 0) return toTernary(i / 3) + "0";
        else if (i % 3 == 1) return toTernary(i / 3) + "1";
        else return toTernary((i + 1) / 3) + "T";
    }
}