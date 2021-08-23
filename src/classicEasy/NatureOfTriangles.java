package classicEasy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/nature-of-triangles
class NatureOfTriangles {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++){
            String name = "";
            List<Integer> coord = new ArrayList<>();
            for (int j = 0; j < 3; j++){
                name += in.next();
                coord.add(in.nextInt());
                coord.add(in.nextInt());
            }
            System.out.println(name + sideNature(name, coord) + angleNature(name, coord));
        }
    }

    private static String angleNature(String name, List<Integer> coord) {
        int AB = getLengthSquare(coord.get(0), coord.get(2), coord.get(1), coord.get(3));
        int BC = getLengthSquare(coord.get(2), coord.get(4), coord.get(3), coord.get(5));
        int CA = getLengthSquare(coord.get(4), coord.get(0), coord.get(5), coord.get(1));
        double c = Math.acos((BC + CA - AB) / (2 * Math.sqrt(BC) * Math.sqrt(CA))) * 180 / Math.PI;
        double a = Math.acos((AB + CA - BC) / (2 * Math.sqrt(AB) * Math.sqrt(CA))) * 180 / Math.PI;
        double b = Math.acos((AB + BC - CA) / (2 * Math.sqrt(AB) * Math.sqrt(BC))) * 180 / Math.PI;
        if (a < 90 && b < 90 && c < 90) return "an acute triangle.";
        else if (a == 90 || b == 90 || c == 90) return "a right in " +
                                                       (a == 90 ? name.substring(0, 1) :
                                                        b == 90 ? name.substring(1, 2) :
                                                        name.substring(2)) + " triangle.";
        else return "an obtuse in " + (a > 90 ? name.substring(0, 1) :
                                       b > 90 ? name.substring(1, 2) : name.substring(2)) + " (" +
                    (a > 90 ? Math.round(a) : b > 90 ? Math.round(b) : Math.round(c)) +
                    "°) triangle.";
    }

    private static String sideNature(String name, List<Integer> coord) {
        int AB = getLengthSquare(coord.get(0), coord.get(2), coord.get(1), coord.get(3));
        int BC = getLengthSquare(coord.get(2), coord.get(4), coord.get(3), coord.get(5));
        int CA = getLengthSquare(coord.get(4), coord.get(0), coord.get(5), coord.get(1));
        if (AB != BC && BC != CA && CA != AB) return " is a scalene and ";
        else if (AB == CA) return " is an isosceles in " + name.substring(0, 1) + " and ";
        else if (AB == BC) return " is an isosceles in " + name.substring(1, 2) + " and ";
        else return " is an isosceles in " + name.substring(2) + " and ";
    }

    private static int getLengthSquare(int x1, int x2, int y1, int y2) {
        return (int) (Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

}