package classicEasy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/addem-up
public class AddemUp {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        List<Integer> cardList = new ArrayList<>();
        for (int i = 0; i < N; i++)
            cardList.add(in.nextInt());
        Collections.sort(cardList);
        int sum = 0;
        while (cardList.size() > 1) {
            int a = Collections.min(cardList);
            cardList.remove((Integer) a);
            int b = Collections.min(cardList);
            cardList.remove((Integer) b);
            sum += a + b;
            cardList.add(a + b);
        }
        System.out.println(sum);
    }
}