package classicEasy;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//https://www.codingame.com/ide/puzzle/text-formatting
public class TextFormatting {

    public static void main(String args[]) {
        String intext = new Scanner(System.in).nextLine();
        //Match NOT_WORDS between WORD and WORD_OR_ENDLINE
        Matcher matcher = Pattern.compile("(?<=\\w)\\W+(?=\\w|$)").matcher(intext);
        while (matcher.find()) {
            String match = matcher.group();
            //Remove all whitespaces
            String result = match.replaceAll(" ", "");
            //If match contain non_space symbol, put back that symbol, else put whitespace
            intext = intext.replace(match, result.length() > 0 ? (result).charAt(0) + " " : " ");
        }
        //replace all multi_whitespaces with 1 space
        intext = intext.replaceAll("\\s+", " ");
        //first symbol toUpperCase(), everything else toLowerCase()
        intext = intext.substring(0, 1).toUpperCase() + intext.substring(1).toLowerCase();
        //match dot->space->any_letter
        matcher = Pattern.compile("\\. \\w").matcher(intext);
        while (matcher.find())
            //replace match with uppercase
            intext = intext.replaceFirst("\\. \\w", matcher.group().toUpperCase());
        //Trim space in the end of the line
        System.out.println(intext.trim());
    }
}