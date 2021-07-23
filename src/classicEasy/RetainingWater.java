package classicEasy;

import java.util.Arrays;
import java.util.Scanner;

//https://www.codingame.com/training/easy/retaining-water
public class RetainingWater {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        if (in.hasNextLine()) in.nextLine();
        int[][] cellHeightArr = new int[N][N];
        for (int i = 0; i < N; i++){
            String line = in.nextLine();
            for (int j = 0; j < line.length(); j++){
                char c = line.charAt(j);
                int cellValue = ((int) c - 64);
                cellHeightArr[i][j] = cellValue;
            }
        }
        int[][] waterArr = waterDrainingCycle(cellHeightArr);
        int sum = 0;
        for (int i = 0; i < waterArr.length; i++){
            for (int j = 0; j < waterArr.length; j++){
                sum += waterArr[i][j] - cellHeightArr[i][j];
            }
        }
        System.out.println(sum);
    }

    static int[][] waterDrainingCycle(int[][] cellHeightArr) {
        int[][] waterLevel = new int[cellHeightArr.length][cellHeightArr.length];
        Arrays.stream(waterLevel).forEach(arr -> Arrays.fill(arr, 26));
        boolean isDrainingLastTurn = true;
        while (isDrainingLastTurn) {
            isDrainingLastTurn = false;
            for (int row = 0; row < waterLevel.length; row++)
                for (int col = 0; col < waterLevel.length; col++){
                    int startWaterLevel = waterLevel[row][col];
                    if (row == 0 || row == waterLevel.length - 1 || col == 0 ||
                        col == waterLevel.length - 1) {
                        if (startWaterLevel != cellHeightArr[row][col]) {
                            isDrainingLastTurn = true;
                            waterLevel[row][col] = cellHeightArr[row][col];
                        }
                    } else {
                        int top = waterLevel[row - 1][col];
                        int bot = waterLevel[row + 1][col];
                        int left = waterLevel[row][col - 1];
                        int right = waterLevel[row][col + 1];
                        int maxWaterLevel = Math.min(Math.min(top, bot), Math.min(left, right));
                        int currentWaterLevel = Math.max(cellHeightArr[row][col], maxWaterLevel);
                        if (startWaterLevel != currentWaterLevel) {
                            isDrainingLastTurn = true;
                            waterLevel[row][col] = currentWaterLevel;
                        }
                    }
                }
        }
        return waterLevel;
    }
}