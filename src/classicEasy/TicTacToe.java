package classicEasy;

import java.util.Arrays;
import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/tictactoe
public class TicTacToe {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        char[][] boardArr = new char[3][3];
        for (int i = 0; i < 3; i++)
            boardArr[i] = in.nextLine().toCharArray();
        boolean isWin = false;
        mainLoop:
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (boardArr[i][j] == '.') {
                    boardArr[i][j] = 'O';
                    if ((boardArr[0][0] == 'O' && boardArr[0][1] == 'O' && boardArr[0][2] == 'O') ||
                        (boardArr[1][0] == 'O' && boardArr[1][1] == 'O' && boardArr[1][2] == 'O') ||
                        (boardArr[2][0] == 'O' && boardArr[2][1] == 'O' && boardArr[2][2] == 'O') ||
                        (boardArr[0][0] == 'O' && boardArr[1][0] == 'O' && boardArr[2][0] == 'O') ||
                        (boardArr[0][1] == 'O' && boardArr[1][1] == 'O' && boardArr[2][1] == 'O') ||
                        (boardArr[0][2] == 'O' && boardArr[1][2] == 'O' && boardArr[2][2] == 'O') ||
                        (boardArr[0][0] == 'O' && boardArr[1][1] == 'O' && boardArr[2][2] == 'O') ||
                        (boardArr[0][2] == 'O' && boardArr[1][1] == 'O' && boardArr[2][0] == 'O')) {
                        isWin = true;
                        break mainLoop;
                    } else boardArr[i][j] = '.';
                }
        System.out.println(!isWin ? isWin : Arrays.deepToString(boardArr).
                replaceAll("\\s|\\[|\\](?!,)|\\,", "").
                replace("]", "\n"));
    }
}