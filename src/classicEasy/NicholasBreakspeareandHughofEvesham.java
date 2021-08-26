package classicEasy;

import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/nicholas-breakspeare-and-hugh-of-evesham
class NicholasBreakspeareandHughofEvesham {

    private static final String[] TRIPLE_EX = {"hundred", "thousand", "million", "billion", 
            "trillion", "quadrillion", "quintillion", "sextillion", "septillion"};
    private static final String[] DIGITS = {"", "one", "two", "three", "four", "five", "six", 
            "seven", "eight", "nine",};
    private static final String[] TEENS = {"", "eleven", "twelve", "thirteen", "fourteen", 
            "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    private static final String[] DECADES = {"ten", "twenty", "thirty", "forty", "fifty", "sixty"
            , "seventy", "eighty", "ninety"};


    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++){
            String x = in.next();
            System.out.println(x.equals("0")? "zero" : getCardinalRec(x, 0));
        }
    }

    private static String getCardinalRec(String n, int exp) {
        if (n.contains("-")) return "negative " + getCardinalRec(n.substring(1), exp);
        else if (n.length() > 3) {
            int r = n.length() % 3;
            String cardinal = getCardingal(Integer.parseInt(n.substring(0, r == 0 ? 3 : r)));
            String cardinalRec = getCardinalRec(n.substring(r == 0 ? 3 : r), exp);
            return cardinal + (cardinal.length() > 0 ? " " + TRIPLE_EX[(n.length() - 1) / 3] : "") +
                   (cardinalRec.length() > 0 ? " " + cardinalRec : "");
        } else return getCardingal(Integer.parseInt(n));
    }

    private static String getCardingal(int n) {
        if (n > 99) return getCardingal(n / 100) + " hundred" +
                           (n % 100 != 0 ? " " + getCardingal(n % 100) : "");
        if (n < 10) return DIGITS[n];
        else if (n > 10 && n < 20) return TEENS[n % 10];
        else return DECADES[n / 10 - 1] + (n % 10 > 0 ? "-" + DIGITS[n % 10] : "");
    }

}