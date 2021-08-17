package classicEasy;

import java.util.Arrays;
import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/feature-extraction
class FeatureExtraction {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int r = in.nextInt();
        int c = in.nextInt();
        int[][] inputMatrix = new int[r][c];
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                inputMatrix[i][j] = in.nextInt();
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] conveyMatrix = new int[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                conveyMatrix[i][j] = in.nextInt();
        int[][] result = new int[r - m + 1][c - n + 1];
        for (int i = 0; i <= r - m; i++)
            for (int j = 0; j <= c - n; j++){
                int sum = 0;
                for (int k = 0; k < m; k++)
                    for (int l = 0; l < n; l++)
                        sum += conveyMatrix[k][l] * inputMatrix[i + k][j + l];
                result[i][j] = sum;
            }
        System.out.println(Arrays.deepToString(result)
                                   .replace("],", "\n")
                                   .replaceAll("[ \\[\\]]", "")
                                   .replace(",", " "));
    }
}