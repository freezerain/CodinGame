import java.util.*;
import java.io.*;
import java.math.*;
import java.util.stream.Stream;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        Stream.iterate(in.nextLine(), n -> in.nextLine())
                .limit(N)
                .forEach(s -> System.out.println(s + appendFriendly(s)));
    }

    static String appendFriendly(String s) {
        HashSet<String> history = new HashSet<>();
        while (true) {
            s = getSumOfSquares(s);
            if (s.equals("1")) return " :)";
            if (history.contains(s)) return " :(";
            history.add(s);
        }
    }

    static String getSumOfSquares(String s) {
        BigInteger n = new BigInteger(s);
        BigInteger sum = BigInteger.valueOf(0);
        while (n.compareTo(BigInteger.valueOf(0)) > 0) {
            sum = sum.add(n.remainder(BigInteger.valueOf(10)).multiply(n.remainder(BigInteger.valueOf(10))));
            n = n.divide(BigInteger.valueOf(10));
        }
        return sum.toString();
    }
}