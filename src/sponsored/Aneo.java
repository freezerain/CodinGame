package sponsored;

import java.util.Scanner;

//https://www.codingame.com/open-challenge-apply/20475484
public class Aneo {

    public static void main(String args[]) {
        //Receive data from console
        Scanner in = new Scanner(System.in);
        int speed = in.nextInt();
        int lightCount = in.nextInt();
        //Array to store (i, i+1 = distance, period) pairs 
        int[] lightArr = new int[lightCount * 2];
        for (int i = 0; i < lightCount * 2; i += 2){
            lightArr[i] = in.nextInt();
            lightArr[i + 1] = in.nextInt();
        }
        //Lower speed until answer is found
        mainLoop:
        for (int i = speed; i > 0; i--){
            for (int j = 0; j < lightCount * 2; j += 2){
                //Get time needed to reach current light with current speed
                double time = (double) lightArr[j] / i * 3.6;
                //If that time is not in "green" period of light = lower speed
                if (!((int) (time / lightArr[j + 1] % 2) == 0)) continue mainLoop;
            }
            //If loop has finished with success = print and return
            System.out.println(i);
            return;
        }
        //if no answer is found print error message
        System.out.println("Cruise speed not found");
    }
}