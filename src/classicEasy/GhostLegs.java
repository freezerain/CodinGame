package classicEasy;

import java.util.ArrayList;
import java.util.Scanner;
//https://www.codingame.com/training/easy/ghost-legs
public class GhostLegs {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int column = in.nextInt();
        int row = in.nextInt();
        if (in.hasNextLine()) { in.nextLine(); }
        char[][] arr = new char[row][column];
        for (int i = 0; i < row; i++) { //fill 2d array with data
            String line = in.nextLine();
            arr[i] = line.toCharArray();
        }
        ArrayList<String> answerList = new ArrayList<>();
        for (int i = 0; i < column; i++) {//Iterate through columns with letters in head
            if (arr[0][i] == ' ') continue;
            String answer = String.valueOf(arr[0][i]);
            answerList.add(answer + findEnd(arr, 1, i));//Start recursive call
        }
        for (String s : answerList){//print answer list
            System.out.println(s);
        }
    }
    //This recursive method should be reconstructed with better logic
    static char findEnd(char[][] arr, int row, int column) {
        char c = arr[row][column];
        if (c != '|' && c != '-') return c;//Exit if char is not connection
        else if (column > 0 && arr[row][column - 1] == '-') {//check if left side has connection
            for (int i = column - 1; i >= 0; i--) {//Move to left until valid column
                if (arr[row][i] == '|') return findEnd(arr, row + 1, i);//Recurse deeper
            }
            return findEnd(arr, row + 1, column); //Emergency recursive exit
        } else if (column < arr[row].length-1 && arr[row][column + 1] == '-') {//check if right side has connection
            for (int i = column + 1; i < arr[row].length; i++) {//Move to right until valid column
                if (arr[row][i] == '|') return findEnd(arr, row + 1, i);//Recurse deeper
            }
            return findEnd(arr, row + 1, column);//Emergency recursive exit
        } else return findEnd(arr, row + 1, column);//If no side connection - recurse deeper in next row
    }
}