package classicEasy;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/xml-mdf-2016
public class XMLMDF2016 {

    public static void main(String args[]) {
        String s = new Scanner(System.in).nextLine();
        Map<Character, Double> map = new HashMap<>();
        int counter = 0;
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) != '-') {
                counter++;
                map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0.0) + 1.0 / counter);
            } else {
                counter--;
                i += 1;
            }
        }
        System.out.println(
                map.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey());
    }
}