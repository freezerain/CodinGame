package classicEasy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/rugby-score
class RugbyScore {

    public static void main(String args[]) {
        int score = new Scanner(System.in).nextInt();
        for (int tries = 0; tries * 5 <= score; tries++)
            for (int trans = 0; tries * 5 + trans * 2 <= score && trans <= tries; trans++)
                for (int drop = 0; tries * 5 + trans * 2 + drop * 3 <= score; drop++)
                    if (tries * 5 + trans * 2 + drop * 3 == score)
                        System.out.println(tries + " " + trans + " " + drop);
    }
}