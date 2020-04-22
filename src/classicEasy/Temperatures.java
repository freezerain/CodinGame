package classicEasy;

import java.util.Scanner;

//https://www.codingame.com/training/easy/temperatures
public class Temperatures {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // the number of temperatures to analyse
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            int t = in.nextInt(); // a temperature expressed as an integer ranging from -273 to 5526
            arr[i] = t;
        }
        if(n == 0) System.out.println(0);
        else if(n == 1) System.out.println(arr[0]);
        else {
            int closest = arr[0];
            for (int i = 1; i<n; i++){
                if(Math.abs(closest) >= Math.abs(arr[i])){
                    if(Math.abs(closest) > Math.abs(arr[i])){
                        closest = arr[i];
                    }else closest = Math.max (closest, arr[i]);
                }
            }
            System.out.println(closest);
        }
    }
}