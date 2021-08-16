package classicEasy;

import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/is-the-king-in-check-part-1
class IsTheKingInCheckPart1 {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int kingX = 0;
        int kingY = 0;
        int pieceX = 0;
        int pieceY = 0;
        char piece = ' ';
        for (int i = 0; i < 8; i++){
            String s = in.nextLine().replace(" ", "");
            for (int j = 0; j < 8; j++){
                if (s.charAt(j) == 'K') {
                    kingX = i;
                    kingY = j;
                } else if (s.charAt(j) != '_') {
                    piece = s.charAt(j);
                    pieceX = i;
                    pieceY = j;
                }
            }
        }
        boolean isCheck = false;
        int deltaX = Math.abs(kingX - pieceX);
        int deltaY = Math.abs(kingY - pieceY);
        if (piece == 'B') isCheck = deltaX == deltaY;
        else if (piece == 'R') isCheck = kingX == pieceX || kingY == pieceY;
        else if (piece == 'Q') isCheck = deltaX == deltaY || kingX == pieceX || kingY == pieceY;
        else if (piece == 'N')
            isCheck = (deltaX == 1 && deltaY == 2) || (deltaY == 1 || deltaX == 2);
        System.out.println(isCheck ? "Check" : "No Check");
    }
}