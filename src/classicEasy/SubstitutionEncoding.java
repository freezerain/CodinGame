package classicEasy;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/substitution-encoding
class SubstitutionEncoding {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int rows = Integer.parseInt(in.nextLine());
        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < rows; i++){
            String[] row = in.nextLine().split(" ");
            for (int j = 0; j < row.length; j++)
                map.put(row[j].charAt(0), i + "" + j);
        }
        for (Character c: in.nextLine().toCharArray())
            System.out.print(map.get(c));
    }
}