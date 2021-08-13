package classicEasy;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;
//https://www.codingame.com/ide/puzzle/next-growing-number
class NextGrowingNumber {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String n = in.nextLine();
        int[] arr = IntStream.range(0, n.length())
                .map(i -> Character.getNumericValue(n.charAt(i)))
                .toArray();
        for (int i = arr.length - 1; i >= 0; i--){
            arr[i] = arr[i] + 1;
            if (arr[i] >= 10) {
                arr[i] = 0;
            } else break;
        }
        if(arr[0] == 0){
            int[] newArr = new int[arr.length+1];
            newArr[0] = 1;
            System.arraycopy(arr,0,newArr,1,arr.length);
            arr = newArr;
        }
        int value = -1;
        for (int i = 1; i < arr.length; i++){
            if (value == -1 && arr[i - 1] > arr[i]) {
                value = arr[i - 1];
                arr[i] = value;
            }
            else if (value != -1) arr[i] = value;
        }
        System.out.println(Arrays.toString(arr).replaceAll("[\\]\\[\\, ]", ""));
    }
}