package classicEasy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/cosmic-love
public class CosmicLove {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        List<Planet> planetList = new ArrayList<>();
        for (int i = 0; i < N; i++){
            String name = in.next();
            planetList.add(new Planet(name, Double.parseDouble(in.next()),
                                               Double.parseDouble(in.next()),
                                               Double.parseDouble(in.next())));
        }
        Collections.sort(planetList);
        Planet alicePlanet = planetList.get(0);
        for (Planet p: planetList){
            if (p.name.equals("Alice")) continue;
            double rocheLimit =
                    alicePlanet.radii * Math.cbrt(2 * (alicePlanet.density / p.density));
            if (p.distance > rocheLimit) {
                System.out.println(p.name);
                break;
            }
        }
    }

    private static class Planet implements Comparable<Planet> {
        String name;
        double radii;
        double volume;
        double density;
        double mass;
        double distance;

        public Planet(String name, double radii, double mass, double distance) {
            this.name = name;
            this.radii = radii;
            this.mass = mass;
            this.distance = distance;
            volume = (4.0 / 3.0) * Math.PI * (Math.pow(radii, 3));
            density = this.mass / volume;
        }

        @Override
        public int compareTo(Planet o) {
            return distance > o.distance ? 1 : -1;
        }
    }
}
