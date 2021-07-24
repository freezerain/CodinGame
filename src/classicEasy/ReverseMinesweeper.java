package classicEasy;

import java.util.Arrays;
import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/reverse-minesweeper
public class ReverseMinesweeper {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int w = in.nextInt();
        int h = in.nextInt();
        if (in.hasNextLine()) in.nextLine();
        char[][] mapArr = new char[h][w];
        for (int i = 0; i < h; i++)
            mapArr[i] = in.nextLine().toCharArray();
        for (int i = 0; i < h; i++)
            for (int j = 0; j < w; j++)
                if (mapArr[i][j] == 'x')
                    for (int k = Math.max(0, i - 1); k < Math.min(h, i + 2); k++)
                        for (int l = Math.max(0, j - 1); l < Math.min(w, j + 2); l++){
                            if (mapArr[k][l] == 'x') continue;
                            mapArr[k][l] = (char) (49 + (mapArr[k][l] == '.' ?
                                    0 : Character.getNumericValue(mapArr[k][l])));
                        }
        System.out.println(Arrays.deepToString(mapArr).
                replaceAll("\\s|\\[|\\](?!,)|\\,", "").
                replace("x", ".").
                replace("]", "\n"));
    }
}