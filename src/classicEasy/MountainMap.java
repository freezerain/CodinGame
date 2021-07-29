package classicEasy;


import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;

//https://www.codingame.com/ide/puzzle/mountain-map
public class MountainMap {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        ArrayList<Integer> heightList = new ArrayList<>();
        int maxHeight = 0;
        for (int i = 0; i < n; i++) {
            int height = in.nextInt();
            maxHeight = Math.max(maxHeight, height);
            heightList.add(height);
        }
        in.close();
        StringBuilder sb = new StringBuilder();
        for (int i = maxHeight; i >0; i--){
            sb.setLength(0);
            for (int j = 0; j < n; j++){
                if(i>heightList.get(j)) sb.append(repeatString(" ", heightList.get(j)*2));
                else sb.append(" ".repeat(i-1))
                        .append("/")
                        .append(repeatString(" ", (heightList.get(j) - i) * 2))
                        .append("\\")
                        .append(repeatString(" ", i - 1));
            }
            String answer = sb.toString();
            answer = answer.stripTrailing();
            System.out.println(answer);
        }
    }

    private static String repeatString(String s, int n){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(s);
        return sb.toString();
    }

}