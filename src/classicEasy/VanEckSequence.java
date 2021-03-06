package classicEasy;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
//https://www.codingame.com/training/easy/van-ecks-sequence
public class VanEckSequence {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int start = in.nextInt();
        int N = in.nextInt();
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        AtomicInteger i = new AtomicInteger();
        Stream.iterate(start, n -> {
            int e = i.get() - map.getOrDefault(n, i.get());
            map.put(n, i.get());
            i.getAndIncrement();
            return e;
        }).limit(N).skip(N - 1).findFirst().ifPresent(System.out::println);
    }
}