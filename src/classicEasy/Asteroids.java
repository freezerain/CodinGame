package classicEasy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Asteroids {
    public static void main(String args[]) {
        //Recieve data
        Scanner in = new Scanner(System.in);
        int w = in.nextInt();
        int h = in.nextInt();
        int T1 = in.nextInt();
        int T2 = in.nextInt();
        int T3 = in.nextInt();
        int deltaT1 = T2 - T1;
        int deltaT2 = T3 - T2;
        char[][] t1Map = new char[w][h];
        char[][] t2Map = new char[w][h];
        char[][] t3Map = new char[w][h];
        for (int i = 0; i < h; i++){
            t1Map[i] = in.next().toCharArray();
            t2Map[i] = in.next().toCharArray();
        }
        List<Asteroid> asteroidList = new ArrayList<>();
        //Find asteroids in T1 map
        for (int i = 0; i < w; i++)
            for (int j = 0; j < h; j++)
                if (t1Map[i][j] != '.') {
                    char name = t1Map[i][j];
                    //Find same asteroid in T2 map
                    innerLoop:
                    for (int k = 0; k < w; k++)
                        for (int l = 0; l < h; l++)
                            if (t2Map[k][l] == name) {
                                asteroidList.add(new Asteroid(name, i, j, k, l));
                                break innerLoop;
                            }
                }
        //Generate T3 map and print
        fillMap(t3Map, asteroidList, deltaT1, deltaT2);
        System.out.println(Arrays.deepToString(t3Map).
                replaceAll("\\s|\\[|\\](?!,)|,", "").
                replace("]", "\n"));
    }

    public static void fillMap(char[][] map, List<Asteroid> asteroidList, int deltaT1,
                               int deltaT2) {
        for (Asteroid a: asteroidList){
            //Get T3 asteroid coordinates
            int t3w = a.t2w + (int) Math.floor(((a.t2w - a.t1w) / (double) deltaT1) * deltaT2);
            int t3h = a.t2h + (int) Math.floor(((a.t2h - a.t1h) / (double) deltaT1) * deltaT2);
            //If asteroid out of bounds - ignore
            if (!(t3w >= 0 && t3h >= 0 && t3w < map.length && t3h < map[0].length)) continue;
            if (map[t3w][t3h] == 0) map[t3w][t3h] = a.name;
                //Check asteroid collision with ASCII numeric value
            else map[t3w][t3h] = (char) Math.min(a.name, map[t3w][t3h]);
        }
        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[i].length; j++)
                if (map[i][j] == 0) map[i][j] = '.';
    }
}

class Asteroid {
    char name;
    int t1w;
    int t1h;
    int t2w;
    int t2h;

    public Asteroid(char name, int t1w, int t1h, int t2w, int t2h) {
        this.name = name;
        this.t1w = t1w;
        this.t1h = t1h;
        this.t2w = t2w;
        this.t2h = t2h;
    }
}