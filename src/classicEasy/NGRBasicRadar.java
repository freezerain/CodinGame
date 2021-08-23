package classicEasy;

import java.util.*;

//https://www.codingame.com/ide/puzzle/1--ngr---basic-radar
class NGRBasicRadar {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        Map<String, Long> carsOnRoad = new HashMap<>();
        List<String> report = new ArrayList<>();
        for (int i = 0; i < N; i++){
            String plate = in.next();
            in.next();
            long timestamp = in.nextLong();
            if (carsOnRoad.containsKey(plate)) {
                if (timestamp - carsOnRoad.get(plate) < 360000) {
                    report.add(plate + " " + ((int) (13 /
                            ((timestamp - carsOnRoad.get(plate)) / 3600000.0))));
                    carsOnRoad.remove(plate);
                }
            } else carsOnRoad.put(plate, timestamp);
        }
        report.sort(String::compareTo);
        report.forEach(System.out::println);
    }
}