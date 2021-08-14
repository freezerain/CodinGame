package classicEasy;

import java.util.Arrays;
import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/blowing-fuse
class BlowingFuse {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        int maxAmp = Integer.parseInt(input.substring(input.lastIndexOf(" ")+1));
        int[] consumer = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        boolean[] isOnArr = new boolean[consumer.length];
        int[] switchId = Arrays.stream(in.nextLine().split(" ")).mapToInt(s-> Integer.parseInt(s)-1).toArray();
        int totalConsume = 0;
        int maxConsume = 0;
        for (int i = 0; i < switchId.length; i++) {
            if(isOnArr[switchId[i]]) totalConsume-=consumer[switchId[i]];
            else {
                totalConsume+= consumer[switchId[i]];
                maxConsume = Math.max(maxConsume, totalConsume);
            }
            if(totalConsume > maxAmp) {
                System.out.println("Fuse was blown.");
                return;
            }
            isOnArr[switchId[i]] = !isOnArr[switchId[i]];
        }
        System.out.println("Fuse was not blown.");
        System.out.printf("Maximal consumed current was %d A.\n", maxConsume);
    }
}