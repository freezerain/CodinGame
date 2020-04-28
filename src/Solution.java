import java.util.*;
import java.util.stream.Collectors;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for(int k = 0; k < n; k++){
            int validator = 0;
            List<Integer> intArr = in.nextLine()
                    .replaceAll(" ", "")
                    .chars()
                    .mapToObj(c -> Character.getNumericValue((char) c))
                    .collect(Collectors.toList());
            for(int i = 0; i < intArr.size(); i++){
                int digit = intArr.get(i);
                validator += i % 2 != 0 ? digit : digit * 2 < 9 ? digit * 2 : digit * 2 - 9;
            }
            System.out.println(validator % 10 == 0 ? "YES" : "NO");
        }
    }
}