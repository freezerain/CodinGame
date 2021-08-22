package classicEasy;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
//https://www.codingame.com/ide/puzzle/stall-tilt
class StallTilt {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int v = in.nextInt();
        Map<Integer, Integer> speedMap = new LinkedHashMap<>();
        for (int i = 0; i < n; i++)
            speedMap.put(i, in.nextInt());
        speedMap = speedMap.entrySet()
                .stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1,
                                          LinkedHashMap::new));
        int max = Integer.MAX_VALUE;
        for (int i = 0; i < v; i++){
            int maxSpeed = (int) Math.sqrt(Math.tan(Math.toRadians(60)) * (in.nextInt() * 9.81));
            max = Math.min(max, maxSpeed);
            for (int j = 0; j < n; j++)
                if (speedMap.get(j) > maxSpeed) speedMap.put(j, i - v);
        }
        System.out.println(max + "\ny");
        speedMap.entrySet()
                .stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .forEach(e -> System.out.println((char) (e.getKey() + 97)));
    }
}