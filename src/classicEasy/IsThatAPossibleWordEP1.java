package classicEasy;

import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
//https://www.codingame.com/ide/puzzle/is-that-a-possible-word-ep1
class IsThatAPossibleWordEP1 {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        Set<Character> alphabet = in.nextLine().chars().mapToObj(c->(char)c).collect(Collectors.toSet());
        String states = in.nextLine();
        int numberOfTransitions = in.nextInt();
        if (in.hasNextLine()) in.nextLine();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfTransitions; i++) sb.append(in.nextLine()).append(" ");
        String transitions = sb.toString();
        String startState = in.nextLine();
        String endStates = in.nextLine();
        int numberOfWords = in.nextInt();
        if (in.hasNextLine()) in.nextLine();
        wordLoop:
        for (int i = 0; i < numberOfWords; i++){
            String word = in.nextLine();
            if(!alphabet.containsAll(word.chars().mapToObj(c->(char)c).collect(Collectors.toSet()))){
                System.out.println("false");
                continue;
            }
            String currentState = startState;
            for (int j = 0; j < word.length(); j++){
                int beginIndex = transitions.indexOf(currentState + " " + word.charAt(j));
                if(beginIndex == -1) {
                    System.out.println("false");
                    continue wordLoop;
                }
                currentState = transitions.substring(beginIndex+4, beginIndex + 5);
            }
            System.out.println(endStates.contains(currentState)? "true" : "false");
        }
    }
}