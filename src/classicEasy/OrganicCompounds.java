package classicEasy;

import java.util.Scanner;

//https://www.codingame.com/ide/puzzle/organic-compounds
class OrganicCompounds {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = Integer.parseInt(in.nextLine());
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++){
            String COMPOUND = in.nextLine();
            int[] row = new int[COMPOUND.length() / 3];
            for (int j = 0; j < COMPOUND.length(); j += 3){
                String s = COMPOUND.substring(j, j + 3);
                row[j / 3] = s.contains(" ") ? -1 : Integer.parseInt(s.replaceAll("[^0-9]", ""));
            }
            map[i] = row;
        }
        for (int row = 0; row < map.length; row += 2)
            for (int col = 0; col < map[row].length; col += 2){
                if (map[row][col] == -1) continue;
                int bounds = 4 - map[row][col];
                if (row > 0 && col < map[row - 1].length && map[row - 1][col] != -1)
                    bounds -= map[row - 1][col];
                if (row < map.length - 1 && col < map[row + 1].length && map[row + 1][col] != -1)
                    bounds -= map[row + 1][col];
                if (col > 0 && map[row][col - 1] != -1) bounds -= map[row][col - 1];
                if (col < map[row].length - 1 && map[row][col + 1] != -1)
                    bounds -= map[row][col + 1];
                if (bounds != 0) {
                    System.out.println("INVALID");
                    return;
                }
            }
        System.out.println("VALID");
    }
}