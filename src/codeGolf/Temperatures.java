package codeGolf;

import java.util.*;

class Temperature {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        if (in.nextLine().equals("0")) System.out.println("0");
        Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).reduce(
                (s, y) -> Math.abs(s) < Math.abs(y) ? s : Math.abs(s) == Math.abs(y) ? Math.max(s, y) : y).ifPresent(
                System.out::println);
    }
}