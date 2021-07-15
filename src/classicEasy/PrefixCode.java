package classicEasy;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/prefix-code
public class PrefixCode {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Map<String, Character> prefixMap = new HashMap<>();
        for (int i = 0; i < n; i++){
            String s = in.next();
            int c = in.nextInt();
            prefixMap.put(s, (char) c);
        }
        String s = in.next();
        StringBuilder sb = new StringBuilder();
        int lastCharIndex = 0;
        String answer = "";
        for (int i = 1; i <= s.length(); i++){
            int occurrences = 0;
            for (String keyString: prefixMap.keySet()){
                if (keyString.startsWith(s.substring(lastCharIndex, i))) {
                    occurrences++;
                    if (keyString.equals(s.substring(lastCharIndex, i))) {
                        sb.append(prefixMap.get(s.substring(lastCharIndex, i)));
                        lastCharIndex = i;
                        break;
                    }
                }
            }
            if (occurrences == 0) {
                answer = "DECODE FAIL AT INDEX " + lastCharIndex;
                break;
            }
        }
        if (answer.equals("")) answer = lastCharIndex >= s.length() ? sb.toString() :
                "DECODE FAIL AT INDEX " + (s.length() - 1);
        System.out.println(answer);
    }
}