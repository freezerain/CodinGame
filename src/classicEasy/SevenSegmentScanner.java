package classicEasy;

import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/7-segment-scanner
public class SevenSegmentScanner {

    public static void main(String args[]) {
        final String lookup =
                " _ | ||_|     |  | _  _||_  _  _| _|   |_|  | _ |_  _| _ |_ |_| _   |  | _ |_||_| _ |_| _|";
        Scanner in = new Scanner(System.in);
        String line1 = in.nextLine();
        String line2 = in.nextLine();
        String line3 = in.nextLine();
        for (int i = 0; i < line1.length(); i+=3){
            String s = "";
            s+= line1.substring(i,i+3);
            s+= line2.substring(i,i+3);
            s+= line3.substring(i,i+3);
            System.out.print(lookup.indexOf(s)/9);
        }
    }
}