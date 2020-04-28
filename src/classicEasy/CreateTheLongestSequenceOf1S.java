package classicEasy;

import java.util.Scanner;

//https://www.codingame.com/training/easy/create-the-longest-sequence-of-1s
public class CreateTheLongestSequenceOf1S {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String sequenceString = in.nextLine();
        int longestSeq = 0;
        for(int i = 0; i < sequenceString.length(); i++)
            if (sequenceString.charAt(i) == '0') { //for every zero we find
                int prefixCounter = 1;//including zero itself
                for(int prefix = i - 1; prefix >= 0; prefix--){ // calculate prefix sequence of 1
                    if (sequenceString.charAt(prefix) == '0') break;
                    prefixCounter++;
                }
                int suffixCounter = 0;
                for(int suffix = i + 1; suffix < sequenceString.length(); suffix++){ // calculate suffix sequence of 1
                    if (sequenceString.charAt(suffix) == '0') break;
                    suffixCounter++;
                }
                if (longestSeq < suffixCounter + prefixCounter) longestSeq = suffixCounter + prefixCounter;
            }
        System.out.println(longestSeq);
    }
}