package classicEasy;

import java.util.Scanner;

//https://www.codingame.com/ide/puzzle/decode-the-message
class DecodeTheMessage {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        long m = Long.parseLong(in.nextLine());
        String alph = in.nextLine();
        StringBuilder result = new StringBuilder();
        for (int i = 0; m > 0; i++){
            double pow = Math.pow(alph.length(), i + 1);
            result.append(alph.charAt((int) (m % pow / Math.pow(alph.length(), i))));
            m -= pow;
        }
        System.out.println(result.toString());
    }
}