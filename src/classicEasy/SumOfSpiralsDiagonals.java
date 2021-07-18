package classicEasy;

import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/sum-of-spirals-diagonals
public class SumOfSpiralsDiagonals {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        long val = 1;
        int startRow = 0;
        int endRow = size;
        int startCol = 0;
        int endCol = size;
        long sum = 0;
        while (startRow < endRow && startCol < endCol) {
            //First row left-to-right
            for (int i = startCol; i < endCol; i++){
                if (i == startCol || i == endCol - 1) sum += val;
                val++;
            }
            startRow++;
            //Last column top-to-down
            for (int i = startRow; i < endRow; i++){
                if (i == endRow - 1) sum += val;
                val++;
            }
            endCol--;
            //Last row right-to-left
            if (startRow < endRow) {
                for (int i = endCol - 1; i >= startCol; i--){
                    if (i == startCol) sum += val;
                    val++;
                }
                endRow--;
            }
            //First column down-to-top
            if (startCol < endCol) {
                for (int i = endRow - 1; i >= startRow; i--)
                    val++;
                startCol++;
            }
        }
        System.out.println(sum);
    }
}