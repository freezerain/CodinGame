package classicEasy;

import java.util.Arrays;
import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/rectangular-block-spinner
class RectangularBlockSpinner {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int size = Integer.parseInt(in.nextLine());
        int angle = (8 - (Integer.parseInt(in.nextLine()) / 45 % 8)) / 2;
        String[][] matrix = new String[size][size];
        for (int i = 0; i < size; i++)
            matrix[i] = in.nextLine().split(" ");
        String[][] rotatedMatrix = new String[size * 2 - 1][size * 2 - 1];
        Arrays.stream(rotatedMatrix).forEach(m -> Arrays.fill(m, " "));
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                rotatedMatrix[i + j][-i + j + size - 1] = matrix[i][j];
        int rotSize = rotatedMatrix.length;
        for (int k = 0; k < angle; k++)
            for (int i = 0; i < rotSize / 2; i++)
                for (int j = i; j < rotSize - i - 1; j++){
                    String temp = rotatedMatrix[i][j];
                    rotatedMatrix[i][j] = rotatedMatrix[rotSize - 1 - j][i];
                    rotatedMatrix[rotSize - 1 - j][i] = rotatedMatrix[rotSize - 1 - i][rotSize - 1 - j];
                    rotatedMatrix[rotSize - 1 - i][rotSize - 1 - j] = rotatedMatrix[j][rotSize - 1 - i];
                    rotatedMatrix[j][rotSize - 1 - i] = temp;
                }
        for (int i = 0; i < rotatedMatrix.length; i++){
            for (int j = 0; j < rotatedMatrix.length; j++)
                System.out.print(rotatedMatrix[i][j]);
            System.out.println();
        }
    }
}