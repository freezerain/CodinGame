package classicEasy;

import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/fax-machine
class FaxMachine {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = Integer.parseInt(in.nextLine());
        in.nextLine();
        String[] pixels = in.nextLine().split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pixels.length; i++)
            sb.append(i % 2 == 0 ? "*".repeat(Integer.parseInt(pixels[i])) : " ".repeat(
                    Integer.parseInt(pixels[i])));
        for (int i = W; i < sb.length(); i += W + 3) sb.insert(i, "|\n|");
        sb.insert(0, "|").append("|");
        System.out.println(sb.toString());
    }
}