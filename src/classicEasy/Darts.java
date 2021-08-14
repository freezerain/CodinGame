package classicEasy;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/darts
class Darts {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        int N = in.nextInt();
        if (in.hasNextLine()) in.nextLine();
        Map<String, Integer> scoreMap = new HashMap<>();
        for (int i = 0; i < N; i++) scoreMap.put(in.nextLine(), 0);
        int T = in.nextInt();
        for (int i = 0; i < T; i++){
            String throwName = in.next();
            int x = in.nextInt();
            int y = in.nextInt();
            int score = 0;
            if (Math.abs(x) + Math.abs(y) <= size / 2) score = 15;
            else if (Math.abs(x * x) + Math.abs(y * y) <= (size / 2) * (size / 2)) score = 10;
            else if (Math.abs(x) <= (size / 2) && Math.abs(y) <= (size / 2)) score = 5;
            scoreMap.put(throwName, scoreMap.get(throwName) + score);
        }
        scoreMap.entrySet()
                .stream()
                .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                .forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));
    }
}