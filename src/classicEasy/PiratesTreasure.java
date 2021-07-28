package classicEasy;

import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/pirates-treasure
public class PiratesTreasure {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt();
        int H = in.nextInt();
        int[][] treasureMap = new int[H][W];
        for (int i = 0; i < H; i++)
            for (int j = 0; j < W; j++)
                treasureMap[i][j] = in.nextInt();
        mainLoop:
        for (int curH = 0; curH < H; curH++)
            innerLoop:
                    for (int curW = 0; curW < W; curW++)
                        if (treasureMap[curH][curW] == 0) {
                            for (int obstH = Math.max(0, curH - 1); obstH < Math.min(H, curH + 2);
                                 obstH++)
                                for (int obstW = Math.max(0, curW - 1);
                                     obstW < Math.min(W, curW + 2); obstW++){
                                    if (obstH == curH && obstW == curW) continue;
                                    if (treasureMap[obstH][obstW] == 0) continue innerLoop;
                                }
                            System.out.println(curW + " " + curH);
                            break mainLoop;
                        }
    }
}