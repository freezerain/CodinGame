package classicEasy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Scanner;

//https://www.codingame.com/training/easy/plague-jr
public class PlagueJr {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        HashMap<Integer, ArrayList<Integer>> graphMap = new HashMap<>();// <padID <ConnectionList>>
        for(int i = 0; i <= N ; i++){//Filling graph
            graphMap.put(i, new ArrayList<>());
        }
        for(int i = 0; i < N; i++){//Filling connections in graph
            int aIndex = in.nextInt();
            int bIndex = in.nextInt();
            graphMap.get(aIndex).add(bIndex);
            graphMap.get(bIndex).add(aIndex);
        }
        int answer = Integer.MAX_VALUE;
        for(int root = 0; root < graphMap.size(); root++){//Test for every pad being the start position
            int nightCount = -1;// -1 to offset the last empty iteration
            LinkedHashSet<Integer> infectedList = new LinkedHashSet<>();//Circular link protection
            ArrayList<Integer> currentNight = new ArrayList<>();//List to infect this night
            currentNight.add(root);//Root is starting point
            while(!currentNight.isEmpty()){
                ArrayList<Integer> nextNight = new ArrayList<>();//List to infect next night(from valid connections)
                for(int padIndex : currentNight){
                    infectedList.add(padIndex);//Mark pad as already visited/infected
                    //Add connections to next night list
                    for (int connectedIndex : graphMap.get(padIndex))
                        if (!infectedList.contains(connectedIndex))
                            nextNight.add(connectedIndex);
                }
                nightCount++;//Call it a day
                currentNight = nextNight;
            }
            answer = Math.min(answer, nightCount);//If this root was more efficient - update our answer
        }
        System.out.println(answer);
    }
}
