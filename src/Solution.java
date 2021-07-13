import java.util.*;
import java.io.*;
import java.math.*;

//https://www.codingame.com/ide/puzzle/custom-game-of-life
class Solution {
    public static void main(String args[]) {
        //Receive data 
        Scanner in = new Scanner(System.in);
        int height = in.nextInt();
        int width = in.nextInt();
        int turns = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        String alive = in.nextLine();
        String dead = in.nextLine();
        //Store cell state as boolean map
        boolean[][] mapArray = new boolean[height][width];
        for (int i = 0; i < height; i++){
            String line = in.nextLine();
            for (int j = 0; j < line.length(); j++){
                char c = line.charAt(j);
                mapArray[i][j] = (c == 'O');
            }
        }
        //Game Simulation
        boolean[][] simulationArray = new boolean[height][width];
        for (int k = 0; k < turns; k++){
            for (int i = 0; i < mapArray.length; i++){
                for (int j = 0; j < mapArray[i].length; j++){
                    int neighbours = countNeighbours(mapArray, i, j);
                    simulationArray[i][j] = isAlive(mapArray[i][j] ? alive : dead, neighbours);
                }
            }
            mapArray = simulationArray;
            simulationArray = new boolean[height][width];
        }
        //Print answer
        printMap(mapArray);
    }

    //Utility function to check if cell should be alive or dead depending from
    //input game rules
    public static boolean isAlive(String lookup, int neighboursCount) {
        return lookup.charAt(neighboursCount) == '1';
    }

    //Utility function to count gow many alive neighbours have target cell 
    public static int countNeighbours(boolean[][] mapArr, int height, int width) {
        int counter = 0;
        for (int i = Math.max(height - 1, 0); i <= Math.min(height + 1, mapArr.length - 1); i++){
            for (int j = Math.max(width - 1, 0); j <= Math.min(width + 1, mapArr[i].length - 1);
                 j++){
                if (i == height && j == width) continue;
                if (mapArr[i][j]) counter++;
            }
        }
        return counter;
    }

    //Utility function to format boolean map to printable strings of "0" and "."
    public static void printMap(boolean[][] mapArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mapArr.length; i++){
            sb.setLength(0);
            for (int j = 0; j < mapArr[i].length; j++){
                sb.append(mapArr[i][j] ? "O" : ".");
            }
            System.out.println(sb.toString());
        }
    }
}

