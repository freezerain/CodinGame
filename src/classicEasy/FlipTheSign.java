package classicEasy;

import java.util.Arrays;
import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/flip-the-sign
class FlipTheSign {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int height = in.nextInt();
        int width = in.nextInt();
        in.nextLine();
        Boolean[][]isPositive = new Boolean[height][width];
        for (int i = 0; i < height; i++) 
            isPositive[i] = Arrays.stream(in.nextLine().split(" "))
                    .map(s -> !s.contains("-"))
                    .toArray(Boolean[]::new);
        Boolean lastPos = null;
        for (int i = 0; i < height; i++) {
            String row = in.nextLine();
            if(!row.contains("X")) continue;
            String[] chars = row.split(" ");
            for (int j = 0; j < chars.length; j++)
                if(chars[j].contains("X")){
                    if(lastPos==null || lastPos != isPositive[i][j])
                        lastPos = isPositive[i][j];
                    else{
                        System.out.println("false");
                        return;
                    }
                }
        }
        System.out.println("true");
    }
}