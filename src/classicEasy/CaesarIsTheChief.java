package classicEasy;

import java.util.Scanner;

//https://www.codingame.com/training/easy/caesar-is-the-chief
class CaesarIsTheChief {

    public static void main(String args[]) {
        String[] originalArray = new Scanner(System.in).nextLine().split(" ");

        StringBuilder sb = new StringBuilder();
        boolean isFound = false;
        for (int i = 0; i < 27; i++){
            String[] decrypted = new String[originalArray.length];
            for (int j = 0; j < originalArray.length; j++){
                sb.setLength(0);
                char[] encrypted = originalArray[j].toCharArray();
                for (int k = 0; k < encrypted.length; k++){
                    sb.append((char) ((encrypted[k] - 65 + i) % 26 + 65));
                }
                decrypted[j] = sb.toString();
                if (sb.toString().equals("CHIEF")) isFound = true;
            }
            if (isFound){
                System.out.println(String.join(" ", decrypted));
                return;
            }
        }
        System.out.println("WRONG MESSAGE");
    }
}