package classicEasy;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
//https://www.codingame.com/ide/puzzle/offset-arrays
public class OffsetArrays {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        //Input list name and list variables
        Map<String, List<Integer>> mainList = new HashMap<>();
        for (int i = 0; i < n; i++){
            String assignment = in.nextLine();
            //Match all uppercase letters
            Matcher matcher = Pattern.compile("[A-Z]\\w*").matcher(assignment);
            matcher.find();
            String listName = matcher.group(0);
            //Match everything between [ and .
            matcher = Pattern.compile("(?<=\\[)(.*?)(?=\\.)").matcher(assignment);
            matcher.find();
            //Find array offset
            int offset = Integer.parseInt(matcher.group(0));
            String[] split = assignment.split("=");
            List<Integer> values = Arrays.stream(split[1].trim().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            //Threat array offset as header and insert in index 0
            values.add(0, offset);
            mainList.put(listName, values);
        }
        //Call recursion
        System.out.println(recursiveSearch(in.nextLine(), mainList));
    }

    static String recursiveSearch(String s, Map<String, List<Integer>> mainList) {
        //Find everything between [ and ]
        Matcher matcher = Pattern.compile("(?<=\\[)(.*)(?=\\])").matcher(s);
        matcher.find();
        String matchedString = matcher.group(0);
        //If match contains deeper level of [ do recursion
        if (matchedString.contains("[")) matchedString = recursiveSearch(matchedString, mainList);
        //Find array name
        matcher = Pattern.compile("[A-Z]\\w*").matcher(s);
        matcher.find();
        String name = matcher.group(0);
        List<Integer> integers = mainList.get(name);
        //Offset real index with "header" in index 0 AND +1
        return String.valueOf(integers.get
                (Integer.parseInt(matchedString) - integers.get(0) + 1));
    }
}