package classicEasy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

//https://www.codingame.com/ide/puzzle/gold-packing
public class GoldPacking {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int length = in.nextInt();
        in.nextLine();
        in.nextLine();
        List<Integer> goldBarList = Arrays.stream(in.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(toList());
        List<Integer> answer = findGold(length, goldBarList);
        System.out.println(answer.toString().replaceAll("\\,|\\]|\\[", ""));
    }

    private static List<Integer> findGold(int lengthLeft, List<Integer> goldBars) {
        if (lengthLeft == 0) return new ArrayList<Integer>();
        if (lengthLeft < 0) return null;
        List<Integer> path = new ArrayList<>();
        int oldLength = 0;
        for (int y = 0; y < goldBars.size(); y++){
            List<Integer> copyArray = goldBars.subList(y + 1, goldBars.size());
            List<Integer> newPath = findGold(lengthLeft - goldBars.get(y), copyArray);
            if (newPath == null) break;
            newPath.add(0, goldBars.get(y));
            int newLength = newPath.stream().mapToInt(Integer::intValue).sum();
            if (oldLength < newLength) {
                path = newPath;
                oldLength = newLength;
            } else if (oldLength == newLength) {
                if (path.size() > newPath.size()) {
                    path = newPath;
                    oldLength = newLength;
                } else if (path.size() == newPath.size()) for (int j = 0; j < path.size(); j++){
                    if (path.get(j) > newPath.get(j)) {
                        path = newPath;
                        oldLength = newLength;
                        break;
                    } else if (path.get(j) < newPath.get(j)) break;
                }
            }
        }
        return path;
    }
}