package classicEasy;

import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/are-the-clumps-normal
class AreTheClumpsNormal {

    public static void main(String args[]) {
        String N = new Scanner(System.in).nextLine();
        int counter = 1;
        for (int i = 1; i <= 9; i++){
            int localCounter = 0;
            int module = -1;
            for (int j = 0; j < N.length(); j++){
                int digit = Character.getNumericValue(N.charAt(j)) % i;
                if(digit != module) {
                    module = digit;
                    localCounter++;
                }
            }
            if(localCounter<counter) {
                System.out.println(i);
                return;
            }
            counter = localCounter;
        }
        System.out.println("Normal");
    }
}