package classicEasy;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
//https://www.codingame.com/training/easy/the-travelling-salesman-problem
public class TheTravellingSalesmanProblem {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        ArrayList<City> cityList = new ArrayList<>();
        for(int i = 0; i < N; i++)
            cityList.add(new City(i, in.nextInt(), in.nextInt()));
        in.close();
        cityList.forEach(city -> city.calculateDistance(cityList));
        System.out.println(Math.round(visitCity(cityList.get(0), cityList, 0.0)));
    }

    static double visitCity(City currentCity, ArrayList<City> cityList, double distance) {
        cityList.get(currentCity.id).isVisited = true;
        currentCity.sortDistances();
        int nextId = currentCity.distances.keySet()
                .stream()
                .filter(k -> !cityList.get(k).isVisited)
                .findFirst()
                .orElse(0);
        //Check if its time to return home, else recursive call to next city
        if (nextId == 0) return distance + currentCity.distances.get(0);
        else return visitCity(cityList.get(nextId), cityList, distance + currentCity.distances.get(nextId));
    }
}

class City {
    boolean isVisited = false;
    public int x, y, id;
    LinkedHashMap<Integer, Double> distances = new LinkedHashMap<>();//<Id, distance from THIS>

    public City(int i, int x, int y) {
        this.id = i;
        this.x = x;
        this.y = y;
    }

    public void calculateDistance(ArrayList<City> neighborList) {
        for(City neighbor: neighborList){
            if (this.id != neighbor.id)
                this.distances.put(neighbor.id, Math.sqrt(Math.pow(this.x - neighbor.x, 2) + Math.pow(this.y - neighbor.y, 2)));
        }
    }

    public void sortDistances() {//Sort map by value
        distances = distances.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (c1, c2) -> c1, LinkedHashMap::new));
    }
}