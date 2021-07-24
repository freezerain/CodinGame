package classicEasy;

import java.util.Scanner;

//https://www.codingame.com/ide/puzzle/1000000000d-world
public class BillionDWorld {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String[] v1 = in.nextLine().split(" ");
        String[] v2 = in.nextLine().split(" ");
        long dotProd = 0;
        mainLoop:
        for (int i = 0; i < v1.length; i+=2){
            long amount = Integer.parseInt(v1[i]);
            long value = Integer.parseInt(v1[i+1]);
            if(amount<=0) continue;
            for (int j = 0; j < v2.length; j+=2){
                long amount2 = Integer.parseInt(v2[j]);
                long value2 = Integer.parseInt(v2[j+1]);
                if(amount2<=0) continue;
                dotProd+= value*value2 * Math.min(amount, amount2);
                v1[i]= String.valueOf(amount-amount2);
                v2[j]= String.valueOf(amount2-amount);
                amount-= amount2;
                if(amount<=0) continue mainLoop;
            }
        }
        System.out.println(dotProd);
    }
}