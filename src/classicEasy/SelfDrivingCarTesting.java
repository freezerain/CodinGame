package classicEasy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
//https://www.codingame.com/ide/puzzle/self-driving-car-testing
public class SelfDrivingCarTesting {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        if (in.hasNextLine()) in.nextLine();
        // Replace letter to integer offset format
        String xthenCommands = in.nextLine()
                .replace("L", " -1")
                .replace("R", " 1")
                .replace("S", " 0");
        //Pairs of "command counter" and "command offset" with "start position" in index 0
        List<Integer> commandList = Arrays.stream(xthenCommands.split("\\;|\\s"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        ArrayList<String> roadPatterns = new ArrayList<>();
        for (int i = 0; i < N; i++) roadPatterns.add(in.nextLine());
        int carPosition = commandList.get(0) - 1;
        StringBuilder sb = new StringBuilder();
        //For each road pattern...
        for (String s: roadPatterns){
            //... road pattern repetition counter
            for (int j = 0; j < Integer.parseInt(s.substring(0, s.indexOf(";"))); j++){
                //clear previous road buffer
                sb.setLength(0);
                //add current road patter to buffer
                sb.append(s.substring(s.indexOf(";") + 1));
                int commandIndex = 1;
                //search for next command to execute which counter is not already at 0 
                for (int k = commandIndex; k < commandList.size(); k += 2)
                    if (commandList.get(k) > 0) {
                        commandIndex = k;
                        break;
                    }
                // decrement command counter by 1
                commandList.set(commandIndex, commandList.get(commandIndex) - 1);
                // update car position by command offset
                carPosition += commandList.get(commandIndex + 1);
                //update string buffer with car position
                sb.setCharAt(carPosition, '#');
                System.out.println(sb.toString());
            }
        }
    }
}