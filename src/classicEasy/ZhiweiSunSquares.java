package classicEasy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/zhiwei-sun-squares
class ZhiweiSunSquares {

    public static void main(String args[]) {
        int n = new Scanner(System.in).nextInt();
        int sqrt = (int) Math.sqrt(n);
        int count = 0;
        List<Integer> secondEquation = new ArrayList<>();
        for (int b = 0; b <= sqrt; b++)
            for (int c = 0; c <= sqrt; c++)
                for (int d = 0; d <= sqrt; d++){
                    double eSquare = Math.sqrt(b + c * 3 + d * 5);
                    int e = b * b + c * c + d * d;
                    if (e <= n && eSquare - Math.floor(eSquare) == 0) secondEquation.add(e);
                }
        for (int a = 0; a <= sqrt; a++)
            for (int i: secondEquation)
                if (a * a + i == n) count++;
        System.out.println(count);
    }
}