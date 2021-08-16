package classicEasy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//https://www.codingame.com/training/easy/unique-prefixes
class UniquePrefixes {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        List<String> words = new ArrayList<>();
        for (int i = 0; i < N; i++) words.add(in.next());
        for (int i = 0; i < N; i++){
            String word = words.get(i);
            for (int j = 1; j <= word.length(); j++){
                String prefix = word.substring(0, j);
                if (j == word.length()) System.out.println(word);
                else if (words.stream().noneMatch(w -> (!w.equals(word)) && w.startsWith(prefix))) {
                    System.out.println(prefix);
                    break;
                }
            }
        }
    }
}