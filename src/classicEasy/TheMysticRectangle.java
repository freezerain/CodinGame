package classicEasy;

import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/the-mystic-rectangle
public class TheMysticRectangle {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int x1 = in.nextInt();
        int y1 = in.nextInt();
        int x2 = in.nextInt();
        int y2 = in.nextInt();
        int totalPrice = 0;
        //Loop until we on target
        while ((!(x1 == x2)) || (!(y1 == y2))) {
            //Calculate absolute difference between points
            int dx = Math.abs(x2 - x1);
            int dy = Math.abs(y2 - y1);
            //Set price * 10 to evade rounding error
            int price = dx != 0 && dy != 0 ? 5 : dx != 0 ? 3 : 4;
            //Increment or decrement coordinate
            int directionX = dx == 0 ? 0 : x1 < x2 ? 1 : -1;
            int directionY = dy == 0 ? 0 : y1 < y2 ? 1 : -1;
            //Check opposite direction
            x1 = dx > 100 ? x1 + (directionX * -1) : x1 + (directionX);
            y1 = dy > 75 ? y1 + (directionY * -1) : y1 + (directionY);
            //Wrap around coordinate
            x1 = x1 ==-1 ? 199 : x1 == 200? 0 : x1;
            y1 = y1 ==-1 ? 149 : y1 == 150? 0 : y1;
            totalPrice += price;
        }
        //format int to float
        System.out.println((totalPrice / 10) + "." + (totalPrice % 10));
    }
}