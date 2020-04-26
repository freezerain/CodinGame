package classicEasy;

import java.util.Scanner;
//https://www.codingame.com/training/easy/a-childs-play
public class AChildsPlay {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int col = in.nextInt();
        int row = in.nextInt();
        long n = in.nextLong();
        if (in.hasNextLine()) {in.nextLine(); }//End line
        char[][] mapArr = new char[row][col];
        int robotRow = 0; //This will be robot position coordinates
        int robotCol = 0;
        int obstacleCount = 0;
        for(int i = 0; i < row; i++){
            String line = in.nextLine();
            for(int j = 0; j < line.length(); j++){
                char c = line.charAt(j);
                mapArr[i][j] = c;//Add symbol to 2d array = "map"
                if (c == 'O') { //if we found robot - update current coordinates
                    robotRow = i;
                    robotCol = j;
                } else if (c == '#') obstacleCount++; } }
        in.close();// C l o s e
        int loopRow = 0; int loopCol = 0;//Coordinates of loop start
        int loopThreshold = row * col - obstacleCount; //If robot moved > then possible moves, then robot guaranteed in the loop
        int direction = 0;// 0-up, 1-right, 2-down, 3-left. Robot turns clockwise
        for(long move = 1; move <= n; move++){
            if (direction == 0 && mapArr[robotRow - 1][robotCol] != '#') robotRow -= 1;
            else if (direction == 1 && mapArr[robotRow][robotCol + 1] != '#') robotCol += 1;
            else if (direction == 2 && mapArr[robotRow + 1][robotCol] != '#') robotRow += 1;
            else if (direction == 3 && mapArr[robotRow][robotCol - 1] != '#') robotCol -= 1;
            else {
                direction = (direction + 1) % 4;//Change direction of movement
                move -= 1;//Undo iteration if no move was made
            }
            if (move == loopThreshold) {//If we guaranteed in the loop, set point to count loop size
                loopRow = robotRow;
                loopCol = robotCol;
            }
            if (move > loopThreshold && robotRow == loopRow && robotCol == loopCol)//After full loop is made
                move = n - (((n - loopThreshold) % (move - loopThreshold)));//Jump over all rest loops
        }
        System.out.println(robotCol + " " + robotRow);
    }
}