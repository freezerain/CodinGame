import java.util.*;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String TEXT = in.nextLine().replaceAll("(\\W|\\d)", "").toLowerCase();
        int N = in.nextInt();
        if (in.hasNextLine()) in.nextLine();
        ArrayList<String> wordList = new ArrayList<>();
        for (int i = 0; i < N; i++) wordList.add(in.nextLine());
        System.out.println(findSolution(TEXT, wordList));
    }
    
    private static String findSolution(String text, ArrayList<String> wordList){
        String answer = "";
        for (int i = 0; i < wordList.size(); i++){
            String word = wordList.get(i);
            //String recu = getWord(text, word, 0);
            String recu = getSolution(text, word);
            if(recu.length() > answer.length()) answer = recu;
        }
        return answer;
    }
    
    private static String getSolution(String text, String word){
        int length = word.length();
        String ans = "";
        mainLoop:
        for (int i = text.length()/length; i >= 1; i--){
            int start = text.indexOf(word.charAt(0));
            while (start>=0){
                boolean isFound = true;
                for (int j = 0; j < length; j++){
                    if(word.charAt(j) == text.charAt(start+(i*j))){
                        continue;
                    }else{
                        isFound = false;
                        break;
                    }
                }
                if(!isFound){
                    start = text.indexOf(word.charAt(0), start+1);
                }else{
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < length; j++){
                        sb.append(  Character.toUpperCase(  text.charAt(start +(i*j)))    ).append(text.substring(start+1+(i*j), start+(i*(j+1))));
                    }
                    sb.setLength(sb.length() - i+1);
                    ans = sb.toString();
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
        if(word.length()<2) return word.toUpperCase();
        if((!text.contains(word.charAt(0) + "")) || (!text.contains(word.charAt(1) + ""))) return "";
        String answer = "";
        int start = text.indexOf(word.charAt(0));
        while (start >= 0) {
            int end = text.indexOf(word.charAt(1), start+1);
            while (end>=0){
                if(period==0 || end - start == period){
                    String result = getWord(text.substring(end), word.substring(1), end-start);
                    if((!result.isEmpty()) && result.length() + end - start > answer.length()) answer = text.substring(start, end) + result;
                }
                end = text.indexOf(word.charAt(1), end+1);
            }
            start = text.indexOf(word.charAt(0), start+1);
        }
        if(answer.isEmpty()) return answer;
        return Character.toUpperCase(answer.charAt(0)) + answer.substring(1);
    }
        
        
        
    
    
    
    /*private String solutionRecursive(String text, String word, Set<LetterPair> letterSet){
        if(word.length()<2) return word;
        
        String s = word.substring(0, 2);
        for (LetterPair letterPair : letterSet) {
            if(s.equals(letterPair.letters)){
                
            }
        }
        
        
        for (int j = 0; j < word.length()-1; j++){
            String pair = "" + word.charAt(j) + word.charAt(j+1);
            for (LetterPair letterPair : letterSet){
                if(letterPair.letters.equals(pair)){
                    if()
                }
            }
        }
        
    }*/
    
    private static Set<LetterPair> buildPairs(String text){
        HashSet<LetterPair> pairSet = new HashSet<>();
        for (int i = 0; i < text.length(); i++){
            for (int j = i+1; j < text.length(); j++){
                pairSet.add(new LetterPair("" + text.charAt(i) + text.charAt(j) , i, j));
            }
        }
        return pairSet;
    }
    
    private static class LetterPair {
        String letters;
        int beginIndex;
        int endIndex;
        int length;

        public LetterPair(String letters, int beginIndex, int endIndex) {
            this.letters = letters;
            this.beginIndex = beginIndex;
            this.endIndex = endIndex;
            this.length = endIndex-beginIndex;
        }
    }
}