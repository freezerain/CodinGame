package classicEasy;

import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/number-derivation
public class NumberDerivation {

    public static void main(String args[]) {
        System.out.println(deriveRecursive(new Scanner(System.in).nextInt()));
    }

    private static int deriveRecursive(int n){
        int a = 1;
        for (int i = 2; i <=n/2 ; i++)
            if(n%i==0) {
                a = i;
                break;
            }
        if(a == 1) return 1;
        int b = n / a;
        return (deriveRecursive(a) * b) + (a * deriveRecursive(b));
    }
}