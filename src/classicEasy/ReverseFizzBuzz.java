package classicEasy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
//https://www.codingame.com/ide/puzzle/reverse-fizzbuzz
class ReverseFizzBuzz {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        List<Integer> fizz = new ArrayList<>();
        List<Integer> buzz = new ArrayList<>();
        int firstIndex = 0;
        for (int i = 1; i <= n; i++){
            String line = in.nextLine();
            if (line.equals("FizzBuzz")) {
                fizz.add(i);
                buzz.add(i);
            } else if (line.equals("Buzz")) buzz.add(i);
            else if (line.equals("Fizz")) fizz.add(i);
            else if (firstIndex == 0) firstIndex = Integer.parseInt(line) - i;
        }
        int offset = firstIndex;
        if (offset != 0) {
            fizz = fizz.stream().map(i -> i + offset).collect(Collectors.toList());
            buzz = buzz.stream().map(i -> i + offset).collect(Collectors.toList());
        }
        System.out.println(commonDivisor(fizz) + " " + commonDivisor(buzz));
    }

    private static int commonDivisor(List<Integer> list){
        List<Integer> candidates = getDividers(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            if(candidates.size()==1) return candidates.get(0);
            Integer n = list.get(i);
            candidates = candidates.stream().filter(cand -> n % cand == 0).collect(Collectors.toList());
        }
        return candidates.get(candidates.size()-1);
    }

    private static List<Integer> getDividers(int a) {
        List<Integer> dividers = new ArrayList<>();
        for (int i = 1; i <= a; i ++) if (a % i == 0) dividers.add(i);
        return dividers;
    }
}