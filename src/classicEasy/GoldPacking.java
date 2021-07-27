package classicEasy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//https://www.codingame.com/ide/puzzle/gold-packing
public class GoldPacking {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int length = in.nextInt();
        int n = in.nextInt();
        in.nextLine();
        //Stream scanner line directly to list
        List<Integer> goldBarList = Arrays.stream(in.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        //Put 0 in 0 index of start array as "length" for comparing
        List<Integer> answer = goldRecursive(new ArrayList<>(Arrays.asList(0)), 0, goldBarList,
                                             length);
        //Remove "length" from final answer
        answer.remove(0);
        System.out.println(answer.toString().replaceAll("\\,|\\]|\\[", ""));
    }

    private static List<Integer> goldRecursive(List<Integer> currentSolution, int goldStartIndex,
                                               List<Integer> goldBarList, int length) {
        //Default solution to compare
        List<Integer> answerList = new ArrayList<>(currentSolution);
        for (int i = goldStartIndex; i < goldBarList.size(); i++){
            int goldBar = goldBarList.get(i);
            //goldBarList is sorted, if goldBar > length then all next goldBars also > length
            if (goldBar > length) break;
            //Add to solution current goldBar and add goldBar length to index 0
            List<Integer> solutionCopy = new ArrayList<>(currentSolution);
            solutionCopy.set(0, currentSolution.get(0) + goldBar);
            solutionCopy.add(goldBar);
            List<Integer> comparedSolution = solutionCopy;
            //If length is not 0 then we can go deeper
            if (goldBar < length) comparedSolution = goldRecursive(solutionCopy, i + 1, goldBarList,
                                                                   length - goldBar);
            //If length in index 0 of current solution is bigger then in one we receive from 
            // recursion
            if (answerList.get(0) < comparedSolution.get(0)) answerList = comparedSolution;
                //If length of both solutions is the same
            else if (answerList.get(0) == comparedSolution.get(0)) {
                //If currentSolution contains more goldBars then comparedSolution
                if (answerList.size() > comparedSolution.size()) answerList = comparedSolution;
                    //If goldBar amount is the same
                else if (answerList.size() == comparedSolution.size())
                    //Check bars one by one
                    for (int j = 0; j < answerList.size(); j++){
                        if (answerList.get(j) > comparedSolution.get(j)) {
                            answerList = comparedSolution;
                            break;
                        } else if (answerList.get(j) < comparedSolution.get(j)) break;
                    }
            }
        }
        return answerList;
    }
}