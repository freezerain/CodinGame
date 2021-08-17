package classicEasy;

import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/horse-racing-hyperduals
class HorseRacingHyperduals {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] velocity = new int[N];
        int[] elegance = new int[N];
        for (int i = 0; i < N; i++) {
            velocity[i] = in.nextInt();
            elegance[i] = in.nextInt();
        }
        int distance = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++)
            for (int j = i+1; j < N; j++)
                 distance = Math.min(distance, Math.abs(velocity[i] - velocity[j]) + Math.abs(elegance[i] - elegance[j]));
        System.out.println(distance);
    }
}