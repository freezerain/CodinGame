package classicEasy;

import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/the-dart-101
class TheDart101 {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = Integer.parseInt(in.nextLine());
        String[] name = new String[N];
        for (int i = 0; i < N; i++) name[i] = in.nextLine();
        String winner = "";
        int lastBest = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            String[] shots = in.nextLine().split(" ");
            int total = 0;
            int turns = 0;
            int shotCounter = 0;
            int lastTurnTotal = 0;
            int missCounter = 0;
            for (int j = 0; j < shots.length; j++){
                if(shots[j].contains("X")) {
                    total -= missCounter>1? total : Math.min(total, missCounter>0?30:20);
                    lastTurnTotal = total;
                    missCounter++;
                }else if(shots[j].contains("*")){
                    total += Integer.parseInt(shots[j].substring(0, shots[j].indexOf("*"))) * Integer.parseInt(shots[j].substring(shots[j].indexOf("*")+1));
                    missCounter = 0;
                }else{
                    total += Integer.parseInt(shots[j]);
                    missCounter = 0;
                }
                shotCounter++;
                if(total> 101){
                    total = lastTurnTotal;
                    shotCounter = 0;
                    turns++;
                }else if(total == 101){
                    turns++;
                    break;
                }else{
                    if(shotCounter==3){
                        shotCounter = 0;
                        missCounter = 0;
                        turns++;
                        lastTurnTotal = total;
                    }
                }
            }
            if(total==101 && turns<lastBest){
                lastBest = turns;
                winner = name[i];
            }
        }

        
        
        
        System.out.println(winner);
    }
}