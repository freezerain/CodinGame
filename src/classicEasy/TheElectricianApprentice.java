package classicEasy;

import java.util.*;
//https://www.codingame.com/ide/puzzle/the-electrician-apprentice
class TheElectricianApprentice {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int C = in.nextInt();
        if (in.hasNextLine()) in.nextLine();
        List<Equipment> equipmentList = new ArrayList<>();
        for (int i = 0; i < C; i++) equipmentList.add(new Equipment(in.nextLine()));
        int A = in.nextInt();
        if (in.hasNextLine()) in.nextLine();
        Map<String, Boolean> switchMap = new HashMap<>();
        for (int i = 0; i < A; i++){
            String s = in.nextLine();
            switchMap.put(s, !switchMap.getOrDefault(s, false));
        }
        for (Equipment e: equipmentList){
            boolean equipmentIsOn = false;
            for (List<String> switchList: e.dependencies){
                boolean isSerial = switchList.get(0).equals("-");
                boolean isOn = false;
                for (int i = 1; i < switchList.size(); i++){
                    isOn = switchMap.getOrDefault(switchList.get(i), false);
                    if ((isSerial && !isOn) || (isOn && !isSerial)) break;
                }
                equipmentIsOn = isOn;
                if (!isOn) break;
            }
            System.out.println(e.name + (equipmentIsOn ? " is ON" : " is OFF"));
        }
    }

    static class Equipment {
        String name;
        List<ArrayList<String>> dependencies = new ArrayList<>();

        public Equipment(String statement) {
            String[] statementArr = statement.split(" ");
            name = statementArr[0];
            int listIndex = -1;
            for (int i = 1; i < statementArr.length; i++){
                String s = statementArr[i];
                if (s.equals("-") || s.equals("=")) {
                    dependencies.add(new ArrayList<>(Arrays.asList(s)));
                    listIndex++;
                } else if (listIndex > -1) dependencies.get(listIndex).add(s);
            }
        }
    }
}