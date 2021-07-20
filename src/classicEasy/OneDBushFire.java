package classicEasy;

import java.util.Scanner;

//https://www.codingame.com/ide/puzzle/1d-bush-fire
public class OneDBushFire {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < N; i++) {
            String line = in.nextLine();
            int counter= 0;
            for (int j = 0; j < line.length(); j++)
                if(line.charAt(j)=='f'){
                    counter++;
                    j=j+2;
                }
            System.out.println(counter);
        }
    }
}