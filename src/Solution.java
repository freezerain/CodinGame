import java.util.*;
import java.io.*;
import java.math.*;
import java.util.jar.JarEntry;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int L = in.nextInt();
        boolean[][] lightArr = new boolean[N][N];
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
        for (int row = 0; row < N; row++)                              //Travers through every ROW
            for (int column = 0; column < N; column++)                  //Travers through every COLUMN
                if (symbolArr[row][column] == 'C')                       //If we have found a CANDLE
                    for (int x = row - (L - 1); x < row + L; x++)         //Travers through every LIGHTED ROW
                        for (int y = column - (L - 1); y < column + L; y++)//Travers through every LIGHT COLUMN
                            if (x >= 0 && y >= 0 && x < N && y < N)         //If cell is not out of bounds
                                if (!lightArr[x][y]) {                       //if cell not already lighted
                                    lightedCells++;                           //Light and increment
                                    lightArr[x][y] = true;
                                }
        System.out.println((N * N) - lightedCells);//All cells - lighted cells
    }
}