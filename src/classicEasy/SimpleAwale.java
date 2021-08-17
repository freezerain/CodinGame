package classicEasy;

import java.util.Arrays;
import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/simple-awale
class SimpleAwale {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int[] opArr = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] myArr = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int num = in.nextInt();
        int grains = myArr[num];
        myArr[num] = 0;
        int lastIndex = grains % 13 + num;
        int divider = grains / 13;
        int[] modArr;
        for (int i = 0; i < 13; i++){
            if (i > 6) modArr = opArr;
            else modArr = myArr;
            modArr[i % 7] = modArr[i % 7] + divider + (i > num && i <= lastIndex ? 1 : 0);
        }
        for (int i = 1; i <= 14; i++){
            if(i<=7) modArr =opArr;
            else modArr = myArr;
            if(i%7 == 0) System.out.println("["+modArr[6] + "]");
            else System.out.print(modArr[i % 7-1] + " ");
        }
        if (lastIndex==6) System.out.println("REPLAY");
    }
}