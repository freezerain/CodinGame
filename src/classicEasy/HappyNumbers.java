package classicEasy;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.stream.Stream;
//https://www.codingame.com/training/easy/happy-numbers
//TODO Transfer BigInteger to full String solution
public class HappyNumbers {
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

    public static void mainBigInteger(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        LinkedHashMap<BigInteger, Boolean> map = new LinkedHashMap<>();
        for (int i = 0; i < N; i++) {
            BigInteger n = new BigInteger(in.nextLine());
            map.put(n, isFriendlyNumber(n));
        }
        map.forEach((k,v)-> System.out.println(k+ " " + (v ? ":)" : ":(")));
    }
    static boolean isFriendlyNumber(BigInteger n){
        HashSet<BigInteger> history = new HashSet<>();
        BigInteger sum = getSumOfSquaresBigInteger(n);
        while (true){
            if(sum.compareTo(BigInteger.valueOf(1)) == 0) return true;
            else if(history.contains(sum)) return false;
            history.add(sum);
            sum = getSumOfSquaresBigInteger(sum);
        }
    }
    static BigInteger getSumOfSquaresBigInteger(BigInteger n){
        BigInteger sum = BigInteger.valueOf(0);
        while (n.compareTo(BigInteger.valueOf(0)) > 0){
            sum = sum.add(n.remainder(BigInteger.valueOf(10)).multiply(n.remainder(BigInteger.valueOf(10))));
            n = n.divide(BigInteger.valueOf(10));
        }
        return sum;
    }
}