package classicEasy;

import java.util.*;
import java.util.stream.Collectors;
//https://www.codingame.com/ide/puzzle/personal-best
class PersonalBest {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        Map<String, List<Double>> person = Arrays.stream(in.nextLine().split(","))
                .collect(Collectors.toMap(s -> s,
                                          s -> new ArrayList<>(Arrays.asList(-1.0, -1.0, -1.0)),
                                          (e1, e2) -> e1, LinkedHashMap::new));
        String cat = in.nextLine();
        boolean[] catArr = new boolean[]{cat.contains("bars"), cat.contains("beam"), cat.contains(
                "floor")};
        int N = Integer.parseInt(in.nextLine());
        for (int i = 0; i < N; i++){
            String[] record = in.nextLine().split(",");
            if (person.containsKey(record[0])) for (int j = 0; j < 3; j++)
                if (catArr[j]) person.get(record[0])
                        .set(j, Math.max(person.get(record[0]).get(j),
                                         Double.parseDouble(record[j + 1])));
        }
        person.values()
                .forEach(r -> System.out.println(r.toString()
                                                         .replaceAll("[\\[ \\]]", "")
                                                         .replaceAll("\\,?-1.0\\,?", "")
                                                         .replaceAll("(?<=\\d)\\.0+\\b", "")));
    }
}