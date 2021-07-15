package classicEasy;

import java.util.Scanner;

//https://www.codingame.com/training/easy/dungeons-and-maps
public class DungeonsAndMaps {
    public static void main(String args[]) {
        //Receive data
        Scanner in = new Scanner(System.in);
        int w = in.nextInt();
        int h = in.nextInt();
        int startRow = in.nextInt();
        int startCol = in.nextInt();
        int n = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        // 3d array of all maps
        char[][][] mapArrays = new char[n][h][w];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < h; j++){
                mapArrays[i][j] = in.nextLine().toCharArray();
            }
        }
        //Find map with shortest path
        int shortestPathLength = -1;
        int shortestMapIndex = -1;
        for (int i = 0; i < n; i++){
            int mapLength = traverseMap(mapArrays[i], startRow, startCol);
            if (mapLength == -1) continue;
            if (shortestPathLength == -1) {
                shortestPathLength = mapLength;
                shortestMapIndex = i;
            } else if (mapLength < shortestPathLength) {
                shortestPathLength = mapLength;
                shortestMapIndex = i;
            }
        }
        System.out.println(shortestMapIndex >= 0 ? shortestMapIndex : "TRAP");
    }

    //If return -1 then map is not valid
    static int traverseMap(char[][] map, int startRow, int startCol) {
        int currentRow = startRow;
        int currentCol = startCol;
        int pathLength = 0;
        while (true) {
            //Protection against cycled map
            if (pathLength > 0 && currentRow == startRow && currentCol == startCol) return -1;
            char cellChar = map[currentRow][currentCol];
            switch (cellChar) {
                case '<':
                    pathLength++;
                    currentCol -= 1;
                    if (currentCol >= 0) continue;
                    else return -1;
                case '>':
                    pathLength++;
                    currentCol += 1;
                    if (currentCol < map[0].length) continue;
                    else return -1;
                case '^':
                    pathLength++;
                    currentRow -= 1;
                    if (currentRow >= 0) continue;
                    else return -1;
                case 'v':
                    pathLength++;
                    currentRow += 1;
                    if (currentRow < map.length) continue;
                    else return -1;
                case 'T':
                    return ++pathLength;
                default:
                    return -1;
            }
        }
    }
}