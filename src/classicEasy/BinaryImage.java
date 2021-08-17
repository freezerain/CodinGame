package classicEasy;

import java.util.Arrays;
import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/binary-image
class BinaryImage {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int h = Integer.parseInt(in.nextLine());
        int[][] image = new int[h][];
        int length = -1;
        for (int i = 0; i < h; i++){
            image[i] = Arrays.stream(in.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int rowSum = Arrays.stream(image[i]).sum();
            if (length == -1) length = rowSum;
            if (rowSum != length) {
                System.out.println("INVALID");
                return;
            }
        }
        for (int i = 0; i < h; i++){
            for (int j = 0; j < image[i].length; j++)
                System.out.print(
                        j % 2 == 0 ? ".".repeat(image[i][j]) : "O".repeat(image[i][j]));
            System.out.println();
        }
    }
}