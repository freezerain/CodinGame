package classicEasy;

import java.util.*;
//https://www.codingame.com/ide/puzzle/hunger-games
class HungerGames {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int t = Integer.parseInt(in.nextLine());
        Map<String, List<String>> tributes = new TreeMap<>();
        for (int i = 0; i < t; i++) tributes.put(in.nextLine(), new ArrayList<>(List.of("Winner")));
        int turns = Integer.parseInt(in.nextLine());
        for (int i = 0; i < turns; i++) {
            String[] info = in.nextLine().split(" killed |, ");
            for (int j = 1; j < info.length; j++) {
                tributes.get(info[0]).add(info[j]);
                tributes.get(info[j]).set(0, info[0]);
            }
        }
        boolean isFirst = true;
        for (String s : tributes.keySet()){
            if(!isFirst) System.out.println();
            isFirst = false;
            System.out.println("Name: " + s);
            System.out.print("Killed: ");
            List<String> strings = tributes.get(s).subList(1, tributes.get(s).size());
            if(strings.size() == 0) System.out.println("None");
            else {
                strings.sort(String::compareTo);
                System.out.println(strings.toString()
                                           .replaceAll("[]\\[]", ""));
            }
            System.out.println("Killer: " + tributes.get(s).get(0));
        }
    }
}