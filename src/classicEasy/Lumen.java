package classicEasy;

import java.util.Scanner;

//https://www.codingame.com/training/easy/lumen
public class Lumen {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int L = in.nextInt();
        int[][] lightArr = new int[N][N];
        char[][] symbolArr = new char[N][N];
        if (in.hasNextLine()) { //End line
            in.nextLine();
        }
        for (int i = 0; i < N; i++) { //Filling cells with input symbols
            for (int k = 0; k < N; k++) {
                symbolArr[i][k] = in.next().charAt(0);
            }
        }
        int lightedCells = 0;
        for (int row = 0; row < N; row++) {//Travers through every ROW
            for (int column = 0; column < N; column++) {//Travers through every COLUMN
                if (symbolArr[row][column] == 'C') {//If we have found a CANDLE
                    for (int light = 0; light < L; light++) {// For every light power
                        for (int x = row - light; x <= row + light; x++) {//Travers through every LIGHTED ROW
                            for (int y = column - light; y <= column + light; y++) {//Travers through every LIGHT COLUMN
                                if (x >= 0 && y >= 0 && x < N && y < N) {//If cell not out of bounds and darker then should be
                                    if (L - light > lightArr[x][y]) {
                                        if (lightArr[x][y] == 0) lightedCells++;//Increment counter;
                                        lightArr[x][y] = L - light;//Set cell light power
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println((N * N) - lightedCells);//All cells - lighted cells
    }
}