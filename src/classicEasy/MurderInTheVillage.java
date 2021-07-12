package classicEasy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/murder-in-the-village
public class MurderInTheVillage { public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    int N = in.nextInt();
    if (in.hasNextLine()) {
        in.nextLine();
    }
    List<Villager> villagerList = new ArrayList<>();
    ArrayList<VillagerPair> pairList = new ArrayList<>();
    for (int i = 0; i < N; i++){
        String s = in.nextLine();
        villagerList.add(new Villager(s));

        List<String> wordList = Arrays.asList(
                s.replaceAll("[^a-zA-Z ]", "").split("\\s+"));
        String nameA = wordList.get(0);
        String location = wordList.get(5);
        if (s.contains("alone")) {
            pairList.add(new VillagerPair(location, nameA, ""));
            continue;
        }
        ArrayList<String> friendList = new ArrayList<>();
        for (int j = 6; j < wordList.size(); j++){
            if (wordList.get(j).equals("with") || wordList.get(j).equals("and")) continue;
            friendList.add(wordList.get(j));
        }
        for (String friendName: friendList){
            pairList.add(new VillagerPair(location, nameA, friendName));
        }
    }

    String answer = "It was me!";
    mainLoop:
    for (int i = 0; i < pairList.size(); i++){
        VillagerPair p = pairList.get(i);
        if (p.villagerB.equals("")) {
            int counter = 0;
            for (VillagerPair testingPair: pairList){
                if (testingPair.villagerA.equals(p.villagerA)) continue;
                if (testingPair.location.equals(p.location)) {
                    counter++;
                    if (counter > 1) {
                        answer = p.villagerA + " did it!";
                        break mainLoop;
                    }
                }
            }
            p.isTrue = true;
            continue ; //?????
        }

        if (p.isTrue) continue;

        for (int j = i; j < pairList.size(); j++){
            VillagerPair testPair = pairList.get(j);
            if (testPair.villagerA.equals(p.villagerA)) continue;
            if (testPair.isTrue) continue;
            if (p.comparePair(testPair)) {
                p.isTrue = true;
                testPair.isTrue = true;
                continue mainLoop;
            }
        }
        answer = p.villagerA + " did it!";
        break;
    }

       /* for (VillagerPair p: pairList){
            if (!(p.isTrue)) {
                answer = p.villagerA + " did it!";
                break;
            }
        }*/
    System.out.println(answer);



        /*    ListIterator<VillagerPair> iter = pairList.listIterator();
        mainLoop:
        while(iter.hasNext()){
            VillagerPair pair = iter.next();
            if(pair.villagerB.equals("")){
                int counter = 0;
                for (VillagerPair p : pairList) {
                    if(p.villagerA.equals(pair.villagerA)) continue;
                    if(p.location.equals(pair.location)) {
                        counter++;
                        if (counter>1){
                            answer = pair.villagerA + " did it!";
                            break mainLoop;
                        }
                    }
                }
                iter.remove();
            }
        }
        iter = pairList.listIterator();
        while(iter.hasNext()){
            VillagerPair pair = iter.next();
            
            
        }
        System.out.println(answer);*/


}
    // String finalStatement = "It was me!";
        /*mainLoop:
        for (int i = 0; i < villagerList.size(); i++){
            Villager currentVillager = villagerList.get(i);
            if(currentVillager.statement.contains("alone")){
                finalStatement = currentVillager.name + " did it!";
                for (int j = 0; j < villagerList.size(); j++){
                    if(j == i) continue ;
                    int counterValidation = 0;
                    Villager testVillager = villagerList.get(j);
                    if(testVillager.location.equals(currentVillager.location)){
                        counterValidation++;
                        if(counterValidation>1){
                            break mainLoop;
                        }
                    }
                }
                continue;
            }
            for (int j = 0; j < villagerList.size(); j++){
                if(j == i) continue;
                Villager testVillager = villagerList.get(j);
                if(testVillager.statement.contains(currentVillager.name)){
                    continue mainLoop;
                }
            }
            finalStatement = currentVillager.name + " did it!";;
            break;
        }*/
    //System.out.println(finalStatement);
}


class VillagerPair {
    String location;
    String villagerA;
    String villagerB;
    boolean isTrue = false;

    public VillagerPair(String location, String villagerA, String villagerB) {
        this.location = location;
        this.villagerA = villagerA;
        this.villagerB = villagerB;
    }

    public boolean compareLocation(String location) {
        return location.equals(this.location);
    }

    public boolean comparePair(VillagerPair pair) {
        if (villagerB.isEmpty()) return false;
        return pair.location.equals(location) &&
               (pair.villagerA.equals(villagerA) || pair.villagerB.equals(villagerA)) &&
               (pair.villagerB.equals(villagerA) || pair.villagerB.equals(villagerB));
    }
}

class Villager {
    String name;
    String location;
    String statement;

    public Villager(String statement) {
        this.statement = statement;
        List<String> wordList = Arrays.asList(statement.split(" "));
        name = wordList.get(0).substring(0, wordList.get(0).length() - 1);
        location = wordList.get(4).equals("the") ? wordList.get(5) : wordList.get(4);
    }
}