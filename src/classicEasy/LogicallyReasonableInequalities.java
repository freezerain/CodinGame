package classicEasy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/logically-reasonable-inequalities
public class LogicallyReasonableInequalities {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        if (in.hasNextLine()) in.nextLine();
        List<String> list = new ArrayList<>();
        boolean isValid = true;
        for (int i = 0; i < n; i++){
            String[] splitedString = in.nextLine().split(">");
            String min = splitedString[0].trim();
            String max = splitedString[1].trim();
            if (list.contains(max)) {
                if (list.contains(min)) if (!(list.indexOf(min) < list.indexOf(max))) {
                    isValid = false;
                    break;
                } else list.add(list.indexOf(max), min);
            } else {
                if (!list.contains(min)) list.add(min);
                list.add(max);
            }
        }
        System.out.println(isValid ? "consistent" : "contradiction");
    }
}