package classicEasy;

import java.util.Scanner;
//https://www.codingame.com/training/easy/robot-show
public class RobotShow {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int L = in.nextInt();
        int N = in.nextInt();
        int answer = -1;
        for (int i = 0; i < N; i++){
            int b = in.nextInt();
            int maxB = Math.max(b, Math.abs(L - b));
            if (answer == -1) answer = maxB;
            else answer = Math.max(answer, maxB);
        }
        System.out.println(answer);
    }
}