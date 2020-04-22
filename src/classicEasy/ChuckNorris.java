package classicEasy;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

//https://www.codingame.com/training/easy/chuck-norris
class ChuckNorris {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String MESSAGE = in.nextLine(); // Recieving input data
        if (MESSAGE.isEmpty()) { //No data exit
            System.out.println("");
            return;
        }
        StringBuilder sb = new StringBuilder();
        ArrayList<String> bitArr = new ArrayList<>();
        for (char c : MESSAGE.toCharArray()) { //Converting char to bit sequence
            String bin = new BigInteger(String.valueOf(c).getBytes()).toString(2);
            while (bin.length()<7){ //ASCII 7-bit format
                bin = "0"+bin;
            }
            bitArr.add(bin);
        }
        String bin = "";
        for (String bit : bitArr) { //Building one sequence of bits
            bin += bit;
        }
        int counter = 0;
        char tempChar = '0';
        for (int i = 0; i < bin.length(); i++) { //converting bits to zero-format
            if (counter == 0 || tempChar == bin.charAt(i)) {
                tempChar = bin.charAt(i);
                counter++;
            } else {
                if (sb.length() != 0) sb.append(" ");
                sb.append(buildZero(tempChar, counter));
                counter = 1;
                tempChar = bin.charAt(i);
            }
        }
        if (counter > 0) {
            if (sb.length() != 0) sb.append(" ");
            sb.append(buildZero(tempChar, counter));
        }
        System.out.println(sb.toString());
    }

    static String buildZero(char c, int amount) {
        StringBuilder sb = new StringBuilder();
        if (c == '1') sb.append("0 ");
        else sb.append("00 ");
        for (int i = 0; i < amount; i++) {
            sb.append("0");
        }
        return sb.toString();
    }
}