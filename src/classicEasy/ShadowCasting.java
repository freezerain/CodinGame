package classicEasy;

import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/shadow-casting
class ShadowCasting {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = Integer.parseInt(in.nextLine());
        String previousRow = "";
        for (int i = 0; i < N+2; i++){
            StringBuilder row = new StringBuilder(i<N? in.nextLine() : "");
            if (previousRow.isEmpty()) {
                previousRow = row.toString();
                System.out.println(previousRow);
                continue;
            }
            if (row.length() < previousRow.length() + 1)
                row.append(" ".repeat(previousRow.length() + 1 - row.length()));
            for (int j = 0; j < previousRow.length(); j++)
                if (previousRow.charAt(j) != ' ' && previousRow.charAt(j) != '¤' && row.charAt(j+1) == ' ')
                    row.setCharAt(j + 1, previousRow.charAt(j) == '⁂' ? '¤':'⁂');
            System.out.println(row.toString().stripTrailing().replace('⁂', '-').replace('¤', '`'));
            previousRow = row.toString().stripTrailing();
        }
    }
}
