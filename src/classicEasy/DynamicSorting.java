package classicEasy;

import java.util.*;
import java.util.stream.Collectors;
//https://www.codingame.com/training/medium/dynamic-sorting
public class DynamicSorting {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        LinkedHashMap<String, Boolean> filterList = new LinkedHashMap<>();
        String[] filterStringArr = in.nextLine().split("(?=[\\+\\-])");
        Arrays.stream(filterStringArr).forEach(s -> {
            boolean b = s.contains("+");
            filterList.put(s.replaceAll("([\\+\\-])", ""), b);
        });
        String types = in.nextLine();
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        ArrayList<Procedural> proceduralList = new ArrayList<>();
        for(int i = 0; i < N; i++){
            String[] ROW = in.nextLine().split(",");
            Map<String, Object> properties = Arrays.stream(ROW)
                    .map(s -> s.split(":"))
                    .collect(Collectors.toMap(a -> a[0], a -> a[1].matches("^[0-9]+$") ? Integer.parseInt(a[1]) : a[1]));
            proceduralList.add(new Procedural(properties, filterList));
        }
        Collections.sort(proceduralList);
        proceduralList.forEach(p -> System.out.println(p.properties.get("id")));
    }
}

class Procedural implements Comparable<Procedural> {
    Map<String, Object> properties;
    LinkedHashMap<String, Boolean> filterList;

    public Procedural(Map<String, Object> properties, LinkedHashMap<String, Boolean> filterList) {
        this.properties = properties;
        this.filterList = filterList;
    }

    @Override
    public int compareTo(Procedural o) {
        int compareResult = 0;
        for(Map.Entry<String, Boolean> e: filterList.entrySet()){
            Object property1 = this.properties.get(e.getKey());
            Object property2 = o.properties.get(e.getKey());
            if (property1 instanceof Integer) {
                compareResult = ((Integer) property1).compareTo((Integer) property2);
            } else if (property1 instanceof String) {
                compareResult = ((String) property1).compareTo((String) property2);
            }
            if (!e.getValue()) compareResult *= -1;
            if (compareResult != 0) return compareResult;
        }
        return compareResult;
    }
}