package classicEasy;

import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
//https://www.codingame.com/ide/puzzle/ipv6-shortener
class IPv6Shortener {
    public static void main(String args[]) {
        String ip = new Scanner(System.in).nextLine();
        System.out.println(Pattern.compile("((:|)0000(:|)){2,}")
                                   .matcher(ip)
                                   .results()
                                   .map(MatchResult::group)
                                   .max(Comparator.naturalOrder())
                                   .map(s -> ip.replaceFirst(s, "::"))
                                   .orElse(ip)
                                   .replaceAll("(?<=:|\\b)0{1,3}", ""));
    }
}