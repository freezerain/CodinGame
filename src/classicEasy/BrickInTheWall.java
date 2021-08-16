package classicEasy;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;
//https://www.codingame.com/ide/puzzle/brick-in-the-wall
class BrickInTheWall {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int maxBricksInRow = in.nextInt();
        in.nextLine();
        in.nextLine();
        List<Integer> bricks = Arrays.stream(in.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .boxed()
                .sorted((i, y) -> Integer.compare(y, i))
                .collect(Collectors.toList());
        double work = 0.0;
        int currentRow = 0;
        for (int i = 0; i < bricks.size(); i += maxBricksInRow){
            for (int j = i; j < i + maxBricksInRow && j < bricks.size(); j++)
                work += currentRow * 6.5 / 100 * 10 * bricks.get(j);
            currentRow++;
        }
        System.out.printf(Locale.US, "%.3f", work);
    }
}