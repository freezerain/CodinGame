package classicEasy;

import java.util.Scanner;
//https://www.codingame.com/training/easy/the-river-ii-
public class TheRiverII {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int r1 = in.nextInt();
        String s = "NO";
        for (int i = r1-1; i >Math.max(r1-46, 0); i--){
            if(r1 == riverSum(i)) {
                s = "YES";
                break;
            }
        }
        System.out.println(s);
    }

    private static int riverSum(int n){
        String s = String.valueOf(n);
        int sum = n;
        for (char c : s.toCharArray()) {
            sum += Character.getNumericValue(c);
        }
        return sum;
    }
}