package classicEasy;

import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/may-the-triforce-be-with-you
public class MayTheTriforceBeWithYou {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        for (int i = 0; i < N*2; i++){
            if(i==0) System.out.print("." + repeatString(" ",N*2-2)+repeatString("*", (i%N)*2+1));
            else System.out.print(repeatString(" ",N*2-i-1)+repeatString("*", (i%N)*2+1));
            if(i>N-1) System.out.print(repeatString(" ", (N-(i%N))*2-1)+repeatString("*", (i%N)*2+1));
            System.out.println();
        }
    }
    private static String repeatString(String s, int amount){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < amount; i++) sb.append(s);
        return sb.toString();
    }
}