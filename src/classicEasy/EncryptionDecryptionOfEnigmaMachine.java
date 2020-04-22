package classicEasy;

import java.util.ArrayList;
import java.util.Scanner;
//https://www.codingame.com/training/easy/encryptiondecryption-of-enigma-machine
public class EncryptionDecryptionOfEnigmaMachine {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        final String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        boolean isEncode = in.nextLine().equals("ENCODE"); //Check Encode / Decode
        int pseudoRandomNumber = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        } //End line skip
        ArrayList<String> rotorList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            rotorList.add(in.nextLine());
        }
        String message = in.nextLine();
        String answer = "";
        if (isEncode) { // Message encdoing in forward direction
            answer = caesarCrypt(alph, message, pseudoRandomNumber);
            for (String rotor : rotorList) {
                answer = rotorPass(alph, answer, rotor);
            }
        } else {//Message decoding in backward direction
            answer = message;
            for (int i = rotorList.size() - 1; i >= 0; i--) {
                //For backward direction swap alphabet with rotor stings
                answer = rotorPass(rotorList.get(i), answer, alph);
            }//For backward direction call caesarCrypt with -pseudoRandomNumber
            answer = caesarCrypt(alph, answer, pseudoRandomNumber * -1);
        }
        System.out.println(answer);
    }

    static String rotorPass(String alph, String message, String rotor) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            int index = alph.indexOf(message.charAt(i));
            sb.append(rotor.charAt(index));
        }
        return sb.toString();
    }

    static String caesarCrypt(String alph, String message, int shift) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            int finalShift = shift;
            int index = alph.indexOf(message.charAt(i));
            if (shift < 0) finalShift = 26 - Math.abs(shift) - i;
            else finalShift += i;
            sb.append(caesarShift(alph, finalShift).charAt(index));
        }
        return sb.toString();
    }

    static String caesarShift(String s, int shift) {
        if (shift < 0) {
            shift += 26;
            return caesarShift(s, shift);
        }
        int i = shift % s.length();
        return s.substring(i) + s.substring(0, i);
    }
}