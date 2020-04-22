package classicEasy;

import java.util.Scanner;

//https://www.codingame.com/training/easy/ascii-art
public class AsciiArt {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int L = in.nextInt();
        int H = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        String T = in.nextLine();
        String answer = "";
        for (int i = 0; i < H; i++) {
            if(i!=0) answer += "\n";
            String ROW = in.nextLine();
            for (int k = 0; k<T.length(); k++){
                String letter = T.substring(k, k+1);
                answer += getLetterSlice(ROW, findInAlphabet(letter), L);
            }
        }
        System.out.println(answer);
    }
    //looking for substring of letter by index starting from 0
    static String getLetterSlice(String string, int index, int width){
        return string.substring(width*index, width*index+width);
    }
    static int findInAlphabet(String letter){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        if (alphabet.contains(letter.toLowerCase())) return alphabet.indexOf(letter.toLowerCase());
        else return 26;
    }
}