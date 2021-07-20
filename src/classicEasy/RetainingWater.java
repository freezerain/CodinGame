package classicEasy;

import java.util.Arrays;
import java.util.Scanner;
//Unfinished
public class RetainingWater {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        int sum = 0;
        int[][] poolMap = new int[N][N];
        for (int i = 0; i < N; i++){
            String line = in.nextLine();
            for (int j = 0; j < line.length(); j++){
                char c = line.charAt(j);
                int cellValue = 25 - ((int)c -65);
                poolMap[i][j] = cellValue;
            }
        }
        int minLevel = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if(i==0 || i==N-1 ||j==0 || j==N-1){
                    minLevel = Math.max(minLevel, poolMap[i][j]-1);
                    poolMap[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                int cellValue = poolMap[i][j];
                /*if(cellValue<7) poolMap[i][j] = 0;*/
                if(i==0 || i==N-1 ||j==0 || j==N-1){continue;}
                poolMap[i][j] = Math.max(cellValue - minLevel  , 0);
            }
        }
        int sumIntStream = Arrays.stream(poolMap).flatMapToInt(Arrays::stream).sum();
        //long sumStream = Arrays.deepToString(poolMap).chars().mapToLong((int)i-> (int)i).sum();
        //System.err.println(Arrays.deepToString());
        System.err.println("\t" + Arrays.deepToString(poolMap).
                replaceAll("\\s|\\[|\\](?!,)", "").
                replace(",", "\t").
                replace("]", "\n"));
        System.out.println(sumIntStream);
    }
}