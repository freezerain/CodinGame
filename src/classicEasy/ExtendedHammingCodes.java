package classicEasy;

import java.util.Arrays;
import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/extended-hamming-codes
class ExtendedHammingCodes {

    public static void main(String args[]) {
        String bitString = new Scanner(System.in).nextLine();
        boolean[][] bits = new boolean[4][4];
        for (int i = 0; i < bitString.length(); i++)
            bits[i / 4][i % 4] = bitString.charAt(i) == '1';
        boolean isGlobalEven = true;
        boolean isOddColEven = true;
        boolean isLastColEven = true;
        boolean isOddRowEven = true;
        boolean isLastRowEven = true;
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++){
                boolean bit = bits[i][j];
                isGlobalEven = bit != isGlobalEven;
                if (j == 1 || j == 3) isOddColEven = bit != isOddColEven;
                if (j > 1) isLastColEven = bit != isLastColEven;
                if (i == 1 || i == 3) isOddRowEven = bit != isOddRowEven;
                if (i > 1) isLastRowEven = bit != isLastRowEven;
            }
        int errorRow =
                isOddRowEven && isLastRowEven ? -1 : isOddRowEven ? 2 : isLastRowEven ? 1 : 3;
        int errorCol =
                isOddColEven || isLastColEven ? isOddColEven ? isLastColEven ? -1 : 2 : 1 : 3;
        if ((errorRow >= 0 || errorCol >= 0) && isGlobalEven) System.out.println("TWO ERRORS");
        else {
            if (errorRow >= 0 || errorCol >= 0) {
                errorRow = Math.max(errorRow, 0);
                errorCol = Math.max(errorCol, 0);
                bits[errorRow][errorCol] = !bits[errorRow][errorCol];
            }
            System.out.println(Arrays.deepToString(bits)
                                       .replaceAll("true", "1")
                                       .replaceAll("false", "0")
                                       .replaceAll("[]\\[, ]", ""));
        }
    }
}