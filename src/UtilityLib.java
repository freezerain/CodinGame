import java.util.Arrays;

public class UtilityLib {
    private void print2dArray(int[][] map){
        System.out.println("\t" + Arrays.deepToString(map).
                replaceAll("\\s|\\[|\\](?!,)", "").
                replace(",", "\t").
                replace("]", "\n"));
    }
}
