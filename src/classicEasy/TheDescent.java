package classicEasy;

import java.util.Scanner;

//https://www.codingame.com/training/easy/the-descent
public class TheDescent {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int highestMountain;
        int mountainIndex;
        // game loop
        while (true) {
            highestMountain = 0;
            mountainIndex = 0;
            for (int i = 0; i < 8; i++) {
                int mountainH = in.nextInt(); // represents the height of one mountain.
                if(highestMountain < mountainH) {
                    highestMountain = mountainH;
                    mountainIndex = i;
                }
            }
            System.out.println(mountainIndex); // The index of the mountain to fire on.
        }
    }
}