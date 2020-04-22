package classicEasy;

import java.util.Arrays;
import java.util.Scanner;
//https://www.codingame.com/training/easy/horse-racing-duals/solution
public class HorseRacingDuals {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        if (N < 2) return; //not enough data exit
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = in.nextInt();
        }
        Arrays.sort(arr);
        int closestPower = Integer.MAX_VALUE;
        int tempPower = 0;
        for (int i = 0; i < N - 1; i++) {
            tempPower = arr[i] - arr[i + 1];
            if (Math.abs(tempPower) < closestPower) closestPower = Math.abs(tempPower);
        }
        System.out.println(closestPower);
    }
}
