package classicEasy;

import java.util.*;

//https://www.codingame.com/ide/puzzle/murder-in-the-village
public class MurderInTheVillage {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        if (in.hasNextLine()) in.nextLine();
        List<Villager> villagerList = new ArrayList<>();
        List<Villager> suspectList = new ArrayList<>();
        for (int i = 0; i < N; i++)
            villagerList.add(new Villager(in.nextLine()));
        mainLoop:
        //loop villagers
        for (int i = 0; i < villagerList.size(); i++){
            Villager potentialSus = villagerList.get(i);
            //if villager was alone put him in suspect list
            if (potentialSus.witnessSet.size() == 0) {
                suspectList.add(potentialSus);
                continue;
            }
            //Ask all villagers who he was with
            for (String witnessName: potentialSus.witnessSet){
                //Skip him self
                if (witnessName.equals(potentialSus.name)) continue;
                //find villager object by name
                for (Villager witness: villagerList)
                    if (witnessName.equals(witness.name))
                        //ask witness 
                        //if at least 1 witness will return true then alibi confirmed 
                        if (potentialSus.confirmAlibi(witness)) {
                            suspectList.remove(potentialSus);
                            suspectList.remove(witness);
                            continue mainLoop;
                        }
            }
            //if not witnesses can confirm alibi
            suspectList.add(potentialSus);
        }
        String answer;
        //If multiple suspects (loners or liars) filter out loners
        if (suspectList.size() > 1) {
            String name = "";
            mainLoop:
            //Loop loners in suspect
            for (Villager sus: suspectList){
                //if we find a liar - hes a killer
                if (sus.witnessSet.size() > 0) {
                    name = sus.name;
                    break;
                }
                //Loop through all villagers
                for (Villager witness: villagerList)
                    //Skip suspects
                    if (!suspectList.contains(witness))
                        //If suspect was "alone" in same location as honest villagers
                        //then suspect is a liar
                        if (sus.location.equals(witness.location)) {
                            name = sus.name;
                            break mainLoop;
                        }
            }
            answer = name + " did it!";
        } else if (suspectList.size() == 1) answer = suspectList.get(0).name + " did it!";
        else answer = "It was me!";
        System.out.println(answer);
    }

    private static class Villager {
        String name;
        String location;
        Set<String> witnessSet = new HashSet<>();

        public Villager(String statement) {
            //Remove dot in the end of statement
            statement = statement.replaceAll("\\.", "");
            name = statement.substring(0, statement.indexOf(":"));
            location = statement.substring(statement.indexOf("the") + 4,
                                           statement.contains("alone") ?
                                                   statement.indexOf("alone") - 2 :
                                                   statement.indexOf("with") - 1);
            if (statement.contains("with")) {
                witnessSet = new HashSet<>(Arrays.asList(
                        statement.substring(statement.indexOf("with") + 5).split(" and ")));
                //Add this to witnesses for easier alibi confirmation
                witnessSet.add(name);
            }
        }

        public boolean confirmAlibi(Villager witness) {
            //Location is wrong
            if (!location.equals(witness.location)) return false;
            //This villager is not in witness.nameSet
            if (!witness.witnessSet.contains(name)) return false;
            //witness.name is not in this.nameSet
            if (!witnessSet.contains(witness.name)) return false;
            // nameSets are different
            // this test is obsolete
            if (!(witnessSet.containsAll(witness.witnessSet)) &&
                !(witness.witnessSet.containsAll(witnessSet))) return false;
            return true;
        }
    }
}
