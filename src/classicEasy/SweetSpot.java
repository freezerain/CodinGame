package classicEasy;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

//https://www.codingame.com/ide/puzzle/sweet-spot
class SweetSpot {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = Integer.parseInt(in.nextLine());
        char[][] startMap = new char[N][];
        for (int j = 0; j < N; j++)
             startMap[j] = in.nextLine().toCharArray();

        for (int row = 0; row < startMap.length; row++){
            for (int col = 0; col < startMap[0].length; col++){
                if (startMap[row][col] == 'A' || startMap[row][col] == 'H'){
                    explode(startMap, row, col, startMap[row][col] == 'H', true);
                }
            }
        }
        System.out.println(Arrays.stream(startMap).map(str -> new String(str).replace("b", "B")).
                collect(Collectors.joining("\n")));
    }

    private static void explode(char[][] map, int expRow, int expCol, boolean isFive,
                                boolean isInclusive) {
        int startRow = Math.max(0, expRow - 3);
        int endRow = Math.min(map.length - 1, expRow + 3);
        for (int row = startRow; row <= endRow; row++){
            int startCol = isInclusive || row == expRow ? Math.max(0, expCol - 3) : expCol;
            int endCol = isInclusive || row == expRow ? Math.min(map[row].length - 1, expCol + 3) :
                    expCol;
            for (int col = startCol; col <= endCol; col++){
                char c = map[row][col];
                if (c == 'B'){
                    map[row][col] = 'b';
                    explode(map, row, col, false, false);
                } else if (c == 'A' || c == 'H' || c == 'b') continue;
                else {
                    map[row][col] = (char) (Math.max(map[row][col] - 48, (isFive ? 5 :
                            (4 - Math.max(Math.abs(expRow - row), Math.abs(expCol - col))))) + 48);
                }
            }
        }
    }
}