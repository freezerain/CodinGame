package classicEasy;

import java.util.Arrays;
import java.util.Scanner;
//https://www.codingame.com/training/easy/object-insertion
//Solve it with String.substring(), not with char-to-char iteration
public class ObjectInsertion {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int figureRow = in.nextInt();
        int figureCol = in.nextInt();
        char[][] figureMap = new char[figureRow][figureCol];
        if (in.hasNextLine()) in.nextLine();
        for (int i = 0; i < figureRow; i++)
            figureMap[i] = in.nextLine().toCharArray();
        int mapRow = in.nextInt();
        int mapCol = in.nextInt();
        if (in.hasNextLine()) in.nextLine();
        char[][] map = new char[mapRow][mapCol];
        char[][] clonedMap = new char[mapRow][mapCol];
        int figureCounter = 0;
        for (int i = 0; i < mapRow; i++)
            map[i] = in.nextLine().toCharArray();
        for (int currentMapRow = 0; currentMapRow < mapRow; currentMapRow++)
            mapColLoop:
                    for (int currentMapCol = 0; currentMapCol < mapCol; currentMapCol++){
                        for (int currentFigRow = 0; currentFigRow < figureRow; currentFigRow++)
                            for (int currentFigCol = 0; currentFigCol < figureCol; currentFigCol++){
                                if (currentFigRow + currentMapRow >= mapRow ||
                                    currentFigCol + currentMapCol >= mapCol) continue mapColLoop;
                                char mapChar = map[currentMapRow + currentFigRow][currentMapCol +
                                                                                  currentFigCol];
                                char figureChar = figureMap[currentFigRow][currentFigCol];
                                if (figureChar == '*' && mapChar == '#') continue mapColLoop;
                            }
                        if (figureCounter == 0) {
                            clonedMap = Arrays.stream(map)
                                    .map(char[]::clone)
                                    .toArray(char[][]::new);
                            for (int i = 0; i < figureRow; i++)
                                for (int j = 0; j < figureCol; j++){
                                    char figChar = figureMap[i][j];
                                    if (figChar == '.') continue;
                                    clonedMap[i + currentMapRow][j + currentMapCol] = figChar;
                                }
                        }
                        figureCounter++;
                    }
        System.out.println(figureCounter);
        if (figureCounter == 1) System.out.println(Arrays.deepToString(clonedMap).
                replaceAll("\\s|\\[|\\](?!,)|,", "").
                replace("]", "\n"));
    }
}