package classicEasy;

import java.util.*;
import java.util.stream.Collectors;
//https://www.codingame.com/ide/puzzle/the-michelangelo-code
public class TheMichelangeloCode {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String TEXT = in.nextLine().replaceAll("[\\W_\\d]", "").toLowerCase();
        int N = in.nextInt();
        if (in.hasNextLine()) in.nextLine();
        ArrayList<String> wordList = new ArrayList<>();
        for (int i = 0; i < N; i++) wordList.add(in.nextLine());
        wordList.sort(Comparator.comparingInt(String::length).reversed());
        for (String word: wordList){
            char first = word.charAt(0);
            char second = word.charAt(1);
            int indexFirst = TEXT.indexOf(first);
            while (indexFirst >= 0) {
                int indexSecond = TEXT.indexOf(second, indexFirst);
                while (indexSecond >= 0) {
                    int indexPeriod = indexSecond - indexFirst;
                    int letterToCheck = 2;
                    for (int i = indexSecond + indexPeriod;
                         i < TEXT.length() && letterToCheck < word.length(); i += indexPeriod){
                        if (TEXT.charAt(i) == word.charAt(letterToCheck)) letterToCheck++;
                        else break;
                    }
                    if (letterToCheck >= word.length()) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(TEXT, indexFirst,
                                  indexFirst + (indexPeriod * (word.length() - 1)) + 1);
                        for (int i = 0; i < sb.length(); i += indexPeriod)
                            sb.setCharAt(i, Character.toUpperCase(sb.charAt(i)));
                        System.out.println(sb.toString());
                        return;
                    }
                    indexSecond = TEXT.indexOf(second, indexSecond + 1);
                }
                indexFirst = TEXT.indexOf(first, indexFirst + 1);
            }
        }
    }
}