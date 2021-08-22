package classicEasy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
//https://www.codingame.com/ide/puzzle/mountain-map-convergence
class MountainMapConvergence {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<Integer> points = new ArrayList<>();
        points.add(0);
        for (int i = 0; i < n; i++){
            int height = in.nextInt();
            if (points.get(points.size() - 1) >= height) points.add(height - 1);
            else if (i != 0 && points.get(points.size() - 1) < height)
                points.add(points.get(points.size() - 1) - 1);
            points.add(height);
        }
        if (points.get(points.size() - 1) < 0) points.add(points.get(points.size() - 1) - 1);
        points.add(0);
        int minHeight = points.stream().min(Integer::compare).orElse(0);
        int maxHeight = points.stream().max(Integer::compare).orElse(0);
        points = points.stream().map(i -> i + Math.abs(minHeight)).collect(Collectors.toList());
        List<List<Character>> heightList = new ArrayList<>();
        IntStream.range(0, maxHeight - minHeight).forEach(str -> heightList.add(new ArrayList<>()));
        int currentHeight = Math.abs(minHeight);
        for (int i = 0; i < points.size(); i++){
            int currentMountain = points.get(i);
            boolean isRising = currentMountain >= currentHeight;
            while (currentHeight != currentMountain) {
                if (!isRising) currentHeight--;
                for (int j = 0; j < heightList.size(); j++)
                    heightList.get(j).add(currentHeight == j ? isRising ? '/' : '\\' : '.');
                if (isRising) currentHeight++;
            }
        }
        Collections.reverse(heightList);
        heightList.forEach(s -> System.out.println(
                s.toString().replaceAll("[^\\/\\\\\\.]", "").replace(".", " ").stripTrailing()));
    }
}