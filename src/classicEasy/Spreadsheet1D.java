package classicEasy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
//https://www.codingame.com/training/easy/1d-spreadsheet
public class Spreadsheet1D {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        ArrayList<String> operationList = new ArrayList<>();
        ArrayList<String> arg1List = new ArrayList<>();
        ArrayList<String> arg2List = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            operationList.add(in.next());
            arg1List.add(in.next());
            arg2List.add(in.next());
        }
        int[] cellArr = new int[N];
        Arrays.fill(cellArr, Integer.MIN_VALUE);//Fill with MIN for isSolved check
        boolean isNotDone = true;
        while(isNotDone){
            for (int i = 0; i<operationList.size(); i++ ){
                if(cellArr[i] != Integer.MIN_VALUE) continue;//If cell already solved
                int a = 0, b = 0;
                String arg1 = arg1List.get(i), arg2 = arg2List.get(i);
                if(arg1.contains("$")){ //If arg1 is a reference
                    if(cellArr[parseInt(arg1)] == Integer.MIN_VALUE) //Check if that reference is not solved
                        continue;
                    else a = cellArr[parseInt(arg1)];
                }else if(parseInt(arg1) != Integer.MIN_VALUE) //Check if arg1 can be parsed
                    a = parseInt(arg1);
                if(arg2.contains("$")){
                    if(cellArr[parseInt(arg2)] == Integer.MIN_VALUE)
                        continue;
                    else b = cellArr[parseInt(arg2)];
                }else if(parseInt(arg2) != Integer.MIN_VALUE)
                    b = parseInt(arg2);
                cellArr[i] = operation(operationList.get(i), a, b);
            }
            for (int i: cellArr) { //Check if cellArr contain unsolved cells
                isNotDone = false;
                if(i == Integer.MIN_VALUE){
                    isNotDone = true;
                    break;
                }
            }
        }
        for(int i : cellArr){
            System.out.println(i);
        }
    }
    static int parseInt(String s){
        try { //Filter only digits, dot and minus sign from string to parse
            if(s.replaceAll("[^\\-.0123456789]","").isEmpty()) return Integer.MIN_VALUE;
            else return Integer.parseInt(s.replaceAll("[^\\-.0123456789]",""));
        } catch (NumberFormatException e) {
            return Integer.MIN_VALUE;
        }
    }
    static int operation(String operation, int a, int b){
        switch (operation) {
            case "VALUE" :
                return a;
            case "ADD" :
                return a+b;
            case "SUB" :
                return a - b;
            case "MULT" :
                return a * b;
            default:
                return 0;
        }
    }
}
