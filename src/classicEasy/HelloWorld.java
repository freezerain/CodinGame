package classicEasy;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
//https://www.codingame.com/ide/puzzle/hello-world
class HelloWorld {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int capitalN = Integer.parseInt(in.nextLine());
        int locationN = Integer.parseInt(in.nextLine());
        double[] lat = new double[capitalN];
        double[] lon = new double[capitalN];
        for (int i = 0; i < capitalN; i++){
            String[] s = in.nextLine().split(" ");
            lat[i] = getDecimalDegree(s[1]);
            lon[i] = getDecimalDegree(s[2]);
        }
        String[] message = new String[capitalN];
        for (int i = 0; i < capitalN; i++) message[i] = in.nextLine();
        for (int i = 0; i < locationN; i++){
            String[] s = in.nextLine().split(" ");
            double currentLat = getDecimalDegree(s[0]);
            double currentLong = getDecimalDegree(s[1]);
            int[] distances = new int[capitalN];
            for (int j = 0; j < capitalN; j++)
                distances[j] = getDistanceFromLatLonInKm(lat[j], lon[j], currentLat, currentLong);
            int minDist = Arrays.stream(distances)
                    .min()
                    .orElse(0);
            System.out.println(IntStream.range(0, distances.length)
                    .filter(i1 -> distances[i1] == minDist)
                    .mapToObj(i1 -> message[i1])
                    .collect(Collectors.joining(" ")));
        }
    }

    private static double getDecimalDegree(String s) {
        int offset = s.matches("[NS].*") ? 0 : 1;
        return (Integer.parseInt(s.substring(3 + offset, 5 + offset)) / 60.0 +
                Integer.parseInt(s.substring(5 + offset)) / 3600.0 +
                Integer.parseInt(s.substring(1, 3 + offset))) * (s.matches("[NE].*") ? 1 : -1);
    }


    private static int getDistanceFromLatLonInKm(double lat1, double lon1, double lat2,
                                                 double lon2) {
        return (int) Math.round(6371 * Math.acos(
                Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                Math.cos(Math.abs(Math.toRadians(lon1) - Math.toRadians(lon2)))));
    }
}