package classicEasy;

import java.util.*;
import java.util.stream.Collectors;

public class TheMichelangeloCode {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String TEXT = in.nextLine().replaceAll("(\\W|\\d)", "").toLowerCase();
        int N = in.nextInt();
        if (in.hasNextLine()) in.nextLine();
        ArrayList<String> wordList = new ArrayList<>();
        for (int i = 0; i < N; i++) wordList.add(in.nextLine());
        System.out.println(findSolution(TEXT, wordList));
    }

    private static String findSolution(String text, ArrayList<String> wordList) {
        String answer = "";
        Map<Character, String> letMap = buildLetterMap(text);
        for (int i = 0; i < wordList.size(); i++){
            String word = wordList.get(i);
            String recu = getWord(text, word, 0);
            //String recu = getSolution(text, word);
            //String recu = searchInMap(text, word, letMap);
            if (recu.length() > answer.length()) answer = recu;
        }
        return answer;
    }

    private static String searchInMap(String text, String word, Map<Character, String> letMap) {
        int maxPeriod = text.length() / word.length();
        if(!letMap.containsKey(word.charAt(0))) return "";
        String indexes = letMap.get(word.charAt(0));
        List<Integer> indexList = Arrays.stream(indexes.split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        String ans = "";
        mainLoop:
        for (int i = maxPeriod; i > 0; i--){
            numericLoop:
            for (Integer k: indexList){
                boolean isFoud = true;
                for (int j = 1; j < word.length(); j++){
                    if (letMap.containsKey(word.charAt(j))) {
                        int indexToCheck = k + (j * i);
                        String value = letMap.get(word.charAt(j));
                        if (value.contains(indexToCheck + " ")) {
                            continue;
                        }
                    }
                    isFoud = false;
                    break;
                }
                if (isFoud) {
                    //ans = text.substring(k, k + i * (word.length()-1)+1);
                    //break mainLoop;
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < word.length(); j++){
                        char c = Character.toUpperCase(text.charAt(k + (i * j)));
                        String substring = text.substring((k+1) + (i * j), k + (i * (j+1)));
                        sb.append(c).append(substring);
                    }

                    sb.setLength(sb.length() - i + 1);
                    ans = sb.toString();
                    break mainLoop;
                }
            }
        }
        return ans;
    }


    private static Map<Character, String> buildLetterMap(String text) {
        HashMap<Character, String> letMap = new HashMap<>();
        for (int i = 0; i < text.length(); i++){
            letMap.put(text.charAt(i), letMap.getOrDefault(text.charAt(i), "") + i + " ");
        }
        return letMap;
    }


    private static String getSolution(String text, String word) {
        int length = word.length();
        String ans = "";
        mainLoop:
        for (int i = text.length() / length; i >= 1; i--){
            int start = text.indexOf(word.charAt(0));
            while (start >= 0 && start < length * (i + 1)) {
                boolean isFound = true;
                for (int j = 1; j < length; j++){
                    int checkIndex = start + (i * j);
                    if (checkIndex < text.length() && word.charAt(j) == text.charAt(checkIndex)) {
                        continue;
                    } else {
                        isFound = false;
                        break;
                    }
                }
                if (!isFound) {
                    start = text.indexOf(word.charAt(0), start + 1);
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < length; j++){
                        sb.append(Character.toUpperCase(text.charAt(start + (i * j))))
                                .append(text.substring(start + 1 + (i * j), start + (i * (j + 1))));
                    }
                    sb.setLength(sb.length() - i + 1);
                    ans = sb.toString();
                    break mainLoop;
                }
            }
            /*if(ans.isEmpty()) return ans;
            else{
                return ans;
            }*/
        }
        return ans;
    }


    private static String getWord(String text, String word, int period) {
        if (word.length() < 2) return word.toUpperCase();
        if ((!text.contains(word.charAt(0) + "")) || (!text.contains(word.charAt(1) + "")) || period*word.length()>text.length())
            return "";
        if(period != 0){
            if(word.charAt(1) == text.charAt(period)){
                String afterWord = getWord(text.substring(period), word.substring(1), period);
                if(afterWord.isEmpty()) return "";
                else return text.substring(0, 1).toUpperCase() + text.substring(1, period) +
                            afterWord;
            }else return "";
        }
        String answer = "";
        int start = text.indexOf(word.charAt(0));
        while (start >= 0) {
            int end = text.indexOf(word.charAt(1), start + 1);
            while (end >= 0) {

                String result = getWord(text.substring(end), word.substring(1), end - start);
                if ((!result.isEmpty()) && result.length() + end - start > answer.length()) {
                    String startString = text.substring(start, start + 1).toUpperCase();
                    String endString = text.substring(start + 1, end);
                    answer = startString + endString + result;
                }
                end = text.indexOf(word.charAt(1), end + 1);
            }
            start = text.indexOf(word.charAt(0), start + 1);
        }
        return answer;
        //if (answer.isEmpty()) return answer;
        //return Character.toUpperCase(answer.charAt(0)) + answer.substring(1);
    }
}