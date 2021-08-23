package classicEasy;

import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/low-resolution-whats-the-shape
class LowResolutionWhatsTheShape {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt();
        int H = Integer.parseInt(in.nextLine().trim());
        int [] sideCounter = new int[]{0, 0, 0, 0};
        for (int i = 0; i < H; i++){
            String row = in.nextLine();
            if (i == 0) 
                sideCounter[0] = row.replaceAll("[^X]", "").length();
            else if (i == H - 1) 
                sideCounter[3] = row.replaceAll("[^X]", "").length();
            if(row.charAt(0) == 'X') sideCounter[1] = sideCounter[1] +1;
            if(row.charAt(row.length() - 1) == 'X') sideCounter[2] = sideCounter[2] +1;
        }
        if (sideCounter[0] == W && sideCounter[3] == W && sideCounter[1] == H && sideCounter[2] == H) System.out.println("Rectangle");
        else if (sideCounter[0] > 0 && sideCounter[1] > 0 && sideCounter[2] > 0 && sideCounter[3] > 0 
                 && sideCounter[0] < W && sideCounter[3] < W && sideCounter[1] < H && sideCounter[2] < H) System.out.println("Ellipse");
        else System.out.println("Triangle");
    }
}