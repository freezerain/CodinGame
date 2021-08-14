package classicEasy;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class AMountainOfAMoleHill {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int totalO = 0;
        String [][] map = new String[16][16];
        for (int i = 0; i < 16; i++){
            map[i] = in.nextLine().split("");
        }
        boolean[][] horizontalValitdator = new boolean[16][16];
        boolean[][] verticalValidator = new boolean[16][16];
        for (int i = 0; i < 16; i++) {
            Matcher m = Pattern.compile("((\\||\\+)(?!(\\||\\+)))(.+?)((?<!(\\||\\+))(\\||\\+))").matcher(String.join("",map[i]));
            while (m.find()) {
                int start = m.start();
                int end = m.end();
                Arrays.fill(horizontalValitdator[i],start, end, true);
                String match = m.group();
                int length = match.length();
                int newLength = match.replaceAll("o", "").length();
                totalO += length-newLength;
            }
        }
        String[][] rotatedMap = rotate90Clockwise(map);
        for (int i = 0; i < 16; i++) {
            Matcher m = Pattern.compile("((\\||\\+)(?!(\\||\\+)))(.+?)((?<!(\\||\\+))(\\||\\+))").matcher(String.join("",rotatedMap[i]));
            while (m.find()) {
                int start = m.start();
                int end = m.end();
                Arrays.fill(verticalValidator[i],start, end, true);
                String match = m.group();
                int length = match.length();
                int newLength = match.replaceAll("o", "").length();
                totalO += length-newLength;
            }
        }
        int counter = 0;
        for (int i = 0; i < 16; i++){
            for (int j = 0; j < 16; j++){
                if((horizontalValitdator[i][j] || verticalValidator[j][15-i]) && map[i][j].equals("o")){
                    counter++;
                }
            }
            
        }
        
        System.out.println(counter);
    }
    static String[][] rotate90Clockwise(String inputArr[][]) {
        String[][] a = Arrays.stream(inputArr).map(String[]::clone).toArray(String[][]::new);
        for (int i = 0; i < 16; i++){
            for (int j = 0; j < 16; j++){
                if(a[i][j].contains("|")) a[i][j] = a[i][j].replace("|", "-");
                else if(a[i][j].contains("-")) a[i][j] = a[i][j].replace("-", "|");
            }
        }
        int N = a.length;
        // Traverse each cycle
        for (int i = 0; i < N/2; i++){
            for (int j = i; j < N - i - 1; j++){
                // Swap elements of each cycle
                // in clockwise direction
                String temp = a[i][j];
                a[i][j] = a[N - 1 - j][i];
                a[N - 1 - j][i] = a[N - 1 - i][N - 1 - j];
                a[N - 1 - i][N - 1 - j] = a[j][N - 1 - i];
                a[j][N - 1 - i] = temp;
            }
        }
        return a;
    }
}