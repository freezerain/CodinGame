package classicEasy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//https://www.codingame.com/ide/puzzle/sudoku-validator
public class SudokuValidator {

    public static void main(String args[]) {
        //Receive data 
        Scanner in = new Scanner(System.in);
        //Lists of sequences to check for validness
        List<String> rowList = new ArrayList<>();
        List<String> columnList = new ArrayList<>();
        List<String> cellList = new ArrayList<>();
        for (int i = 0; i < 9; i++){
            String row = in.nextLine().replace(" ", "");
            rowList.add(row);
            for (int j = 0; j < 9; j++){
                String currentChar = row.substring(j, j + 1);
                if (columnList.size() > j) columnList.set(j, columnList.get(j) + currentChar);
                else columnList.add(currentChar);
                //Choose in which cell a number should be putted by its coordinates
                int cellIndex = i / 3 * 3 + j / 3;
                if (cellList.size() > cellIndex)
                    cellList.set(cellIndex, cellList.get(cellIndex) + currentChar);
                else cellList.add(currentChar);
            }
        }
        in.close();
        System.out.println(
                checkIsValid(rowList) && checkIsValid(columnList) && checkIsValid(cellList));
    }

    //If any string will not contain 123456789 then sudoku is not valid
    static boolean checkIsValid(List<String> listToCheck) {
        for (String s: listToCheck)
            for (int i = 1; i < 10; i++)
                if (!(s.contains(String.valueOf(i)))) return false;
        return true;
    }
}