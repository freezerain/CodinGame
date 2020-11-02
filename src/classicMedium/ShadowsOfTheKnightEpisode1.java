package classicMedium;
import java.util.Scanner;
public class ShadowsOfTheKnightEpisode1 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int maxW = in.nextInt();
        int maxH = in.nextInt();
        int N = in.nextInt();
        int x = in.nextInt();
        int y = in.nextInt();
        int minW = -1;
        int minH = -1;
        while (true) {
            String bombDir = in.next();
            if (bombDir.contains("U")) {
                maxH = y;
                y = y - ((maxH - minH) / 2);
            } else if (bombDir.contains("D")) {
                minH = y;
                y = y + ((maxH - minH) / 2);
            }
            if (bombDir.contains("R")) {
                minW = x;
                x = x + ((maxW - minW) / 2);
            } else if (bombDir.contains("L")) {
                maxW = x;
                x = x - ((maxW - minW) / 2);
            }
            System.out.println(x + " " + y);
        }
    }
}
