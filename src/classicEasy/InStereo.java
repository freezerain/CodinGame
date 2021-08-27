package classicEasy;

import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/in-stereo
class InStereo {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int P = Integer.parseInt(in.nextLine());
        String pattern = in.nextLine();
        int S = Integer.parseInt(in.nextLine());
        String stock = in.nextLine();
        int H = in.nextInt();
        int W = in.nextInt();
        in.nextLine();
        for (int i = 0; i < H; i++){
            int[] depthmap = in.nextLine().chars().map(c -> c - 48).toArray();
            int currentDepth = 0;
            int index = 0;
            StringBuilder result = new StringBuilder();
            StringBuilder newPattern = new StringBuilder(pattern);
            StringBuilder newStock = new StringBuilder(stock);
            for (int nextDepth : depthmap){
                for (int k = 0; k < Math.abs(nextDepth - currentDepth); k++){
                    if (nextDepth > currentDepth) {
                        newPattern.deleteCharAt(index % newPattern.length());
                        index = index % newPattern.length();
                    }
                    else {
                        newPattern.insert(index+k, newStock.charAt(0));
                        newStock.deleteCharAt(0);
                    }
                }
                result.append(newPattern.charAt(index % newPattern.length()));
                currentDepth = nextDepth;
                index = (index+1) % newPattern.length();
            }
            System.out.println(result.toString());
        }
    }
}