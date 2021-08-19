package classicEasy;

import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/auto-pickup
class AutoPickup {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        in.nextLine();
        String s = in.nextLine();
        int i = 0;
        while (i < s.length()) {
            int length = Integer.parseInt(s.substring(i + 3, i + 7), 2);
            if (s.startsWith("101", i))
                System.out.print("001" + s.substring(i + 3, i + 7 + length));
            i += 7 + length;
        }
    }
}