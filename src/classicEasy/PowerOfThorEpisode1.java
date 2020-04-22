package classicEasy;

import java.util.Scanner;

//https://www.codingame.com/training/easy/power-of-thor-episode-1
public class PowerOfThorEpisode1 {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int lightX = in.nextInt(); // the X position of the light of power
        int lightY = in.nextInt(); // the Y position of the light of power
        int initialTx = in.nextInt(); // Thor's starting X position
        int initialTy = in.nextInt(); // Thor's starting Y position
        int currentX = initialTx;
        int currentY = initialTy;
        // game loop
        while (true) {
            int remainingTurns = in.nextInt();
            // The remaining amount of turns Thor can move. Do not remove this line.
            if (currentX>lightX && currentY>lightY &&
                    checkMove(currentX-1,currentY-1)){
                System.out.println("NW");
                currentX -= 1;
                currentY -= 1;
            }else if (currentX<lightX && currentY>lightY &&
                    checkMove(currentX+1,currentY-1)){
                System.out.println("NE");
                currentX += 1;
                currentY -= 1;
            }else if (currentX<lightX && currentY<lightY &&
                    checkMove(currentX+1,currentY+1)){
                System.out.println("SE");
                currentX += 1;
                currentY += 1;
            }else if (currentX>lightX && currentY<lightY &&
                    checkMove(currentX-1,currentY+1)){
                System.out.println("SW");
                currentX -= 1;
                currentY += 1;
            }else if(currentX > lightX && checkMove(currentX-1, currentY)){
                System.out.println("W");
                currentX -= 1;
            }else if(currentX < lightX && checkMove(currentX+1, currentY)){
                System.out.println("E");
                currentX += 1;
            }else if(currentY > lightY && checkMove(currentX, currentY-1)){
                System.out.println("N");
                currentY -= 1;
            }else if(currentY < lightY && checkMove(currentX, currentY+1)){
                System.out.println("S");
                currentY += 1;
            }else System.out.println ("Error founding next move");
        }
    }

    static boolean checkMove (int x, int y){
        if (x>=0 && x<40 && y>=0 && y<18) return true;
        else return false;
    }
}