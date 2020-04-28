package classicEasy;

import java.util.Scanner;

//https://www.codingame.com/training/easy/detective-pikaptcha-ep1
public class DetectivePikaptchaEp1 {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int cols = in.nextInt();
        int rows = in.nextInt();
        int[][] mapArr = new int[rows][cols];
        for(int i = 0; i < rows; i++){
            String line = in.next();
            for(int j = 0; j < cols; j++){
                mapArr[i][j] = line.charAt(j) == '0' ? 0 : -1;
            }
        }
        in.close();
        StringBuilder sb = new StringBuilder();
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                if (mapArr[row][col] == 0) {
                    int counter = 0;
                    if (row > 0 && mapArr[row - 1][col] != -1) counter++;
                    if (row < rows - 1 && mapArr[row + 1][col] != -1) counter++;
                    if (col > 0 && mapArr[row][col - 1] != -1) counter++;
                    if (col < cols - 1 && mapArr[row][col + 1] != -1) counter++;
                    sb.append(counter);
                } else sb.append('#');
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}