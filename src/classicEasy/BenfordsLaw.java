package classicEasy;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//https://www.codingame.com/training/easy/benfords-law
public class BenfordsLaw {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        //N is also total number count
        int N = in.nextInt();
        if (in.hasNextLine()) in.nextLine();
        //Number counters
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < N; i++){
            //Search first 1-9 number
            Matcher matcher = Pattern.compile("[1-9]").matcher(in.nextLine());
            matcher.find();
            int firstNumber = Integer.parseInt(matcher.group(0));
            //If map already contain number - increment, else add 
            countMap.put(firstNumber, countMap.getOrDefault(firstNumber, 0) + 1);
        }
        //Lookup percentages from wikipedia
        Map<Integer, Double> lookupMap = new HashMap<>();
        lookupMap.put(1, 30.1);
        lookupMap.put(2, 17.6);
        lookupMap.put(3, 12.5);
        lookupMap.put(4, 9.7);
        lookupMap.put(5, 7.9);
        lookupMap.put(6, 6.7);
        lookupMap.put(7, 5.8);
        lookupMap.put(8, 5.1);
        lookupMap.put(9, 4.6);
        boolean isFraud = false;
        for (int i = 1; i < 10; i++){
            double numberPercent = countMap.get(i) / (double) N * 100.0;
            double lookupPercent = lookupMap.get(i);
            //If percentage is in valid range then proceed
            if (numberPercent > Math.max(lookupPercent - 10.0, 0.0) &&
                numberPercent < Math.min(lookupPercent + 10.0, 100.0)) continue;
            isFraud = true;
            break;
        }
        System.out.println(isFraud);
    }
}