package classicEasy;

import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/hidden-messages-in-images
public class HiddenMessagesInImages {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int w = in.nextInt();
        int h = in.nextInt();
        if(in.hasNextLine()) in.nextLine();
        StringBuilder binaryString = new StringBuilder();
        for (int i = 0; i < h; i++)
            for (String s : in.nextLine().split(" ")) {
                String binary = Integer.toBinaryString(Integer.parseInt(s));
                binaryString.append(binary.substring(binary.length() - 1));
            }
        StringBuilder answer = new StringBuilder();
        for(int i = 0; i <= binaryString.length()-8; i += 8)
            answer.append((char) Integer.parseInt(binaryString.substring(i, i + 8), 2));;
        System.out.println(answer);
    }
}