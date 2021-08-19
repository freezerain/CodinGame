package classicEasy;

import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/jack-silver-the-casino
class JackSilverTheCasino {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int rounds = Integer.parseInt(in.nextLine());
        int cash = Integer.parseInt(in.nextLine());
        for (int i = 0; i < rounds; i++){
            String[] round = in.nextLine().split(" ");
            int number = Integer.parseInt(round[0]);
            int multiplier = -1;
            if (!round[1].equals("PLAIN")) {
                if ((round[1].equals("ODD") && number % 2 == 1) ||
                    (round[1].equals("EVEN") && number % 2 == 0 && number != 0)) multiplier = 1;
            } else if (number == Integer.parseInt(round[2])) multiplier = 35;
            cash += (int) Math.ceil(cash / 4.0) * multiplier;
        }
        System.out.println(cash);
    }
}