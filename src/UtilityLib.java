import java.util.Arrays;

public class UtilityLib {
    private void print2dArray(int[][] map){
        System.out.println("\n\t" + Arrays.deepToString(map).
                replaceAll("\\s|\\[|\\](?!,)", "").
                replace(",", "\t").
                replace("]", "\n"));
    }
}
