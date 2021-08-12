package classicEasy;

import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/number-derivation
public class NumberDerivation {

    public static void main(String args[]) {
        System.out.println(deriveRecursive(new Scanner(System.in).nextInt()));
    }

    private static int deriveRecursive(int n){
        for (int i = 2; i <=n/2 ; i++)
            if(n%i==0) {
                return (deriveRecursive(n / i) * i) + n / i;
            }
        return 1;
    }
}