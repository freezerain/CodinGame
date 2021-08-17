import java.util.Arrays;

public class UtilityLib {
    private void print2dArrayWithTabs(int[][] arr){
        System.out.println("\n\t" + Arrays.deepToString(arr).
                replaceAll("\\s|\\[|\\](?!,)", "").
                replace(",", "\t").
                replace("]", "\n"));
    }
    private void print2dArray(int[][] arr){
        System.out.println(Arrays.deepToString(arr)
                                   .replace("],", "\n")
                                   .replaceAll("[ \\[\\]]", "")
                                   .replace(",", " "));
    }
}
