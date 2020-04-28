package classicEasy;

import java.util.ArrayList;
import java.util.Scanner;
//https://www.codingame.com/training/easy/dead-mens-shot
public class DeadMensShot {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        Point[] rectangle = new Point[in.nextInt()];
        for(int i = 0; i < rectangle.length; i++)
            rectangle[i] = new Point(in.nextInt(), in.nextInt());
        int M = in.nextInt();
        ArrayList<Point> shotList = new ArrayList<>();
        for(int i = 0; i < M; i++){
            shotList.add(new Point(in.nextInt(), in.nextInt()));
        }
        shotList.forEach(s -> System.out.println(hitOrMiss(rectangle, s) ? "hit" : "miss"));
    }

    static boolean hitOrMiss(Point[] rectangle, Point hitPoint) {
        //https://math.stackexchange.com/questions/190111/how-to-check-if-a-point-is-inside-a-rectangle
        int rectangleSquare = getArea(rectangle);
        int pointSquare = 0;
        for(int i = 0; i < rectangle.length; i++){
            pointSquare += getArea(new Point[]{hitPoint, rectangle[i], rectangle[(i + 1) % rectangle.length]});
        }
        return pointSquare <= rectangleSquare;
    }

    static int getArea(Point[] pointArr) {
        //Gauss area formula
        int l = pointArr.length;
        if (l < 2) return 0;
        int S2 = 0;
        for(int i = 0; i < l; i++){
            S2 += pointArr[i].x * pointArr[(i + 1) % l].y;
            S2 -= pointArr[i].y * pointArr[(i + 1) % l].x;
        }
        return Math.abs(S2 / 2);
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}