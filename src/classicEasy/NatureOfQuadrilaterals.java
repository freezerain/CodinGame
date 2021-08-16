package classicEasy;

import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/nature-of-quadrilaterals
class NatureOfQuadrilaterals {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++){
            String name = "";
            int[] coordX = new int[4];
            int[] coordY = new int[4];
            for (int j = 0; j < 4; j++){
                name += in.next();
                coordX[j] = in.nextInt();
                coordY[j] = in.nextInt();
            }
            boolean isRectangle = isRectangle(coordX, coordY);
            boolean isRhombus = isRhombus(coordX, coordY);
            boolean isParallelogram = isParallelogram(coordX, coordY);
            if (isRectangle && isRhombus) System.out.println(name + " is a square.");
            else if (isParallelogram || isRhombus)
                System.out.println(name + " is a " + (isRhombus ? "rhombus." : "parallelogram."));
            else if (isRectangle) System.out.println(name + " is a rectangle.");
            else System.out.println(name + " is a quadrilateral.");
        }
    }

    static int dotProd(int x1, int y1, int x2, int y2, int x3, int y3) {
        return ((x1 - x2) * (x2 - x3) + ((y1 - y2) * (y2 - y3)));
    }

    static boolean isRectangle(int[] coordX, int[] coordY) {
        for (int i = 0; i < 4; i++){
            if (dotProd(coordX[i], coordY[i], coordX[(i + 1) % 4], coordY[(i + 1) % 4],
                        coordX[(i + 2) % 4], coordY[(i + 2) % 4]) != 0) return false;
        }
        return true;
    }

    static int getVectorLength(int x1, int y1, int x2, int y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }

    static boolean isParallelogram(int[] coordX, int[] coordY) {
        int[] vectorLength = getAllLength(coordX, coordY);
        return vectorLength[0] == vectorLength[2] && vectorLength[1] == vectorLength[3];
    }

    static boolean isRhombus(int[] coordX, int[] coordY) {
        int[] vectorLength = getAllLength(coordX, coordY);
        for (int i = 0; i < vectorLength.length; i++)
            if (vectorLength[i] != vectorLength[(i + 1) % vectorLength.length]) return false;
        return true;
    }

    static int[] getAllLength(int[] coordX, int[] coordY) {
        int[] vectorLength = new int[4];
        for (int i = 0; i < 4; i++)
            vectorLength[i] = getVectorLength(coordX[i], coordY[i], coordX[(i + 1) % 4],
                                              coordY[(i + 1) % 4]);
        return vectorLength;
    }
}