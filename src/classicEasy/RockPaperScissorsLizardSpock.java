package classicEasy;

import java.util.ArrayList;
import java.util.Scanner;
//https://www.codingame.com/training/easy/rock-paper-scissors-lizard-spock
public class RockPaperScissorsLizardSpock {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        ArrayList<Player> playerList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            playerList.add(new Player(in.nextInt(), in.next()));
        }
        while(playerList.size()>1){
            ArrayList<Player> winnerList = new ArrayList<>();
            for(int i = 1; i< playerList.size(); i+=2){
                Player player1 = playerList.get(i);
                Player player2 = playerList.get(i-1);
                if(player1.sign.equals(player2.sign)){
                    if(player1.id<player2.id) winnerList.add(setOponent(player1,player2));
                    else winnerList.add(setOponent(player2,player1));
                }else if (winCheck(player1.sign, player2.sign))
                    winnerList.add(setOponent(player1, player2));
                else winnerList.add(setOponent(player2, player1));
            }
            playerList = winnerList;
        }
        System.out.println(playerList.get(0).id);
        System.out.println(playerList.get(0).oponents.trim());
    }
    static Player setOponent(Player p1, Player p2){
        p1.oponents += p2.id + " ";
        return p1;
    }
    static boolean winCheck(String a, String b){
        switch (a){
            case "R" :
                return b.equals("L") || b.equals("C");
            case "P" :
                return b.equals("R") || b.equals("S");
            case "C" :
                return b.equals("P") || b.equals("L");
            case "L" :
                return b.equals("S") || b.equals("P");
            case "S" :
                return b.equals("C") || b.equals("R");
            default: return false;
        }
    }
}
class Player{
    int id; String sign; String oponents;
    public Player (int i, String s){
        id = i; sign = s; oponents = "";
    }
}