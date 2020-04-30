package classicEasy;

import java.util.Scanner;
//https://www.codingame.com/training/easy/detective-pikaptcha-ep2
public class DetectivePikaptchaEp2 {

    public static void main(String args[]) {
        //Receiving and parsing data
        Scanner in = new Scanner(System.in);
        int col = in.nextInt();
        int row = in.nextInt();
        //Map of maze
        char[][] mapArr = new char[row][col];
        //Start position of pikachu
        int startRow = 0;
        int startCol = 0;
        //Building maze from String char by char and determine start position
        for(int i = 0; i < row; i++){
            String line = in.next();
            for(int j = 0; j < col; j++){
                mapArr[i][j] = line.charAt(j);
                //Start symbol check
                if (line.charAt(j) != '0' && line.charAt(j) != '#') {
                    startRow = i;
                    startCol = j;
                }
            }
        }
        //Parsing data to variables to work with
        //Turn direction will be opposite to wall to follow
        boolean isClockwise = in.next().contains("L");
        int currentRow = startRow;
        int currentCol = startCol;
        //Direction of movement will be based on angle variable
        char c = mapArr[startRow][startCol];
        double angle = c == '^' ? Math.PI : c == '>' ? Math.PI + Math.PI / 2 : c == '<' ? Math.PI / 2 : 0.0;
        //We dont want start symbol in final result
        mapArr[startRow][startCol] = '0';
        //Moving in maze
        outerLoop:
        while (true) {
            //First try is go to desired wall opposite to turn direction
            if (isClockwise) angle -= Math.PI / 2;
            else angle += Math.PI / 2;
            int nextRow = (int) Math.cos(angle);
            int nextCol = (int) Math.sin(-angle);
            int turnsCounter = 0;
            //While next move is not valid - turn direction of movement
            while ((isOutOfBounds(currentRow + nextRow, row) || isOutOfBounds(currentCol + nextCol, col))
                   || mapArr[currentRow + nextRow][currentCol + nextCol] == '#') {
                if (!isClockwise) angle -= Math.PI / 2;
                else angle += Math.PI / 2;
                nextRow = (int) Math.cos(angle);
                nextCol = (int) Math.sin(-angle);
                //Emergency exit if we are trapped
                turnsCounter++;
                if (turnsCounter > 4) break outerLoop;
            }
            currentRow += nextRow;
            currentCol += nextCol;
            mapArr[currentRow][currentCol]++;
            //if we on start - break the loop
            if (currentRow == startRow && currentCol == startCol) break;
        }
        //Print result
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                System.out.print(mapArr[i][j]);
            }
            System.out.println();
        }
    }
    //Simple utility function
    static boolean isOutOfBounds(int val, int lessThen) {
        return val < 0 || val >= lessThen;
    }
}