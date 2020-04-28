package classicEasy;

import java.util.LinkedList;
import java.util.Scanner;
//https://www.codingame.com/training/easy/brackets-extreme-edition
public class BracketsExtremeEdition {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String expression = in.next();
        expression = expression.replaceAll("[^()\\[\\]{}]", "");//Leave only bracers to check
        LinkedList<Character> charList = new LinkedList<>();
        for(char c: expression.toCharArray()){
            char last = charList.peekLast() != null ? charList.peekLast() : 0;
            if (last == '(' && c == ')' || last == '[' && c == ']' || last == '{' && c == '}') charList.removeLast();
            else charList.add(c);
        }
        System.out.println(charList.isEmpty());
    }
}