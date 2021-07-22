package classicEasy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/count-as-i-count
public class CountAsICount {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int target = 50 - in.nextInt();
        List<Integer> pinLookup = new ArrayList<>();
        pinLookup.add(1);
        for (int i = 2; i <= 12; i++){
            pinLookup.add(i);
            pinLookup.add(i);
        }
        System.out.println(countRec(pinLookup, target, 0, 0));
    }

    static int countRec(List<Integer> lookup, int target, int preScore, int deepness) {
        if (deepness > 3) return 0;
        int counter = 0;
        for (int i = 0; i < lookup.size(); i++){
            int score = lookup.get(i) + preScore;
            if (score == target) counter++;
            else if (score >= target) return counter;
            else counter += countRec(lookup, target, score, deepness + 1);
        }
        return counter;
    }
}