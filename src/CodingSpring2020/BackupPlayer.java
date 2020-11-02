package CodingSpring2020;

import java.util.*;
import java.util.stream.Collectors;

//CodingSpring2020.Player / MyAgent
class Player {
    public static int turnsLeft = 200;
    public static int widthX = 0;
    public static int heightY = 0;
    public static int[][] startingMapArr;
    public static Graph<Cell> graph;
    public static HashMap<CellPair, Integer> distanceMap;
    public static int myScore;
    public static int opponentScore;
    public static int visiblePacCount;
    public static int myPacsAmount;
    public static int enemyPacsAmount;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        widthX = in.nextInt(); // size of the grid
        long startTime = System.nanoTime();
        heightY = in.nextInt(); // top left corner is (x=0, y=0)
        if (in.hasNextLine()) in.nextLine();
        startingMapArr = new int[heightY][widthX];
        for(int i = 0; i < heightY; i++){
            String row = in.nextLine(); // one line of the grid: space " " is floor, pound "#" is wall
            for(int j = 0; j < widthX; j++) startingMapArr[i][j] = row.charAt(j) == '#' ? -1 : 0;
        }
        graph = GraphBuilder.buildGraph(startingMapArr);
        distanceMap = GraphBuilder.calculateCellDistances(graph);
        System.err.println("Initialisation time: " + (System.nanoTime() - startTime) / 1000000.0);
        // game loop
        while (true) {
            myScore = in.nextInt();
            startTime = System.nanoTime();
            opponentScore = in.nextInt();
            visiblePacCount = in.nextInt(); // all your pacs and enemy pacs in sight
            ArrayList<Pac> pacArrayList = new ArrayList<>();
            for(int i = 0; i < visiblePacCount; i++){
                pacArrayList.add(new Pac(in.nextInt(), in.nextInt() != 0, in.nextInt(), in.nextInt()));
                String typeId = in.next(); // unused in wood leagues
                int speedTurnsLeft = in.nextInt(); // unused in wood leagues
                int abilityCooldown = in.nextInt(); // unused in wood leagues
            }
            List<Pac> myPacArr = pacArrayList.stream().filter(pac -> pac.isMine).collect(Collectors.toList());
            int visiblePelletCount = in.nextInt(); // all pellets in sight
            int[][] mapArr = Arrays.stream(startingMapArr).map(int[]::clone).toArray(int[][]::new);
            MapGraph mapGraph = new MapGraph();
            TreeSet<Cell> cellSet = new TreeSet<>();
            for(int i = 0; i < visiblePelletCount; i++){
                int x = in.nextInt();
                int y = in.nextInt();
                int score = in.nextInt();
                mapArr[y][x] = score;
                mapGraph.addCell(new Point(x, y), score);
                cellSet.add(new Cell(new Point(x, y), score));
            }
            List<Cell> targetCellArr = new ArrayList<>();
            for(Pac myPac: myPacArr){
                Cell targetCell = new Cell(new Point(0, 0), Integer.MIN_VALUE);
                int lastDistance = Integer.MAX_VALUE;
                for(Cell c: cellSet){
                    if (c.score > targetCell.score || (c.getDistance(myPac) < lastDistance
                                                       && c.score >= targetCell.score)) {
                        targetCell = c;
                        lastDistance = c.getDistance(myPac);
                    }
                }
                targetCellArr.add(targetCell);
                cellSet.remove(targetCell);
            }

            ArrayList<Pac> pacTemp = new ArrayList<>();
            for(int i = 0; i < targetCellArr.size(); i++){
                Pac bestPac = myPacArr.get(0);
                for(int j = 0; j < myPacArr.size(); j++){
                    if (myPacArr.get(j).getDistance(targetCellArr.get(i)) < bestPac.getDistance(targetCellArr.get(i)))
                        bestPac = myPacArr.get(j);
                }
                pacTemp.add(bestPac);
                myPacArr.remove(bestPac);
            }
            myPacArr = pacTemp;
            graph = GraphBuilder.buildGraph(mapArr);
            RouteFinder<Cell> routeFinder = new RouteFinder<>(graph, new PreCalculatedScorer(distanceMap), new PreCalculatedScorer(distanceMap));
            List<Cell> routeList = routeFinder.findRoute(graph.getNode(new Cell(new Point(pacArrayList.get(0).x, pacArrayList
                    .get(0).y))), mapGraph.map.lastKey());
            System.err.println("Itteration time: " + (System.nanoTime() - startTime) / 1000000.0);
            //debugMap(mapArr);
            for(int i = 0; i < myPacArr.size(); i++){
                System.out.print("MOVE "
                                 + myPacArr.get(i).id
                                 + " "
                                 + targetCellArr.get(i).x
                                 + " "
                                 + targetCellArr.get(i).y
                                 + " WAKA");
                if (i < myPacArr.size()) System.out.print(" | ");
            }
            System.out.println();
        }
    }

    static void debugMap(int[][] mapArr) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < mapArr.length; i++){
            for(int j = 0; j < mapArr[i].length; j++){
                int cell = mapArr[i][j];
                if (cell == -1) sb.append("#");
                else if (cell == 10) sb.append("*");
                else if (cell > 0) sb.append("·");
                else sb.append(" ");
            }
            sb.append("\n");
        }
        System.err.println(sb.toString());
    }



}


class GraphBuilder {
    public static Graph<Cell> buildGraph(int[][] mapArr) {
        Set<Cell> nodes = new HashSet<Cell>();
        Map<Cell, Set<Cell>> connections;
        for(int i = 0; i < mapArr.length; i++){
            for(int j = 0; j < mapArr[i].length; j++){
                if (mapArr[i][j] >= 0) nodes.add(new Cell(new Point(j, i), mapArr[i][j]));
            }
        }
        connections = nodes.stream()
                .collect(Collectors.toMap(k -> k, v -> new HashSet<>(v.getConnectedFloorCells(mapArr))));
        return new Graph<>(nodes, connections);
    }

    public static HashMap<CellPair, Integer> calculateCellDistances(Graph<Cell> graph) {
        Map<Cell, Set<Cell>> graphMap = graph.connections;
        HashMap<CellPair, Integer> distanceMap = new HashMap<>();
        for(Cell cell: graphMap.keySet()){
            Set<Cell> visitedConnection = new HashSet<>();
            visitedConnection.add(cell);
            Set<Cell> connectionToAdd = graphMap.get(cell);
            int distance = 1;
            while (!connectionToAdd.isEmpty()){
                visitedConnection.addAll(connectionToAdd);
                HashSet<Cell> nextConnectionSet = new HashSet<>();
                for (Cell connection : connectionToAdd) {
                    distanceMap.putIfAbsent(new CellPair(cell, connection),distance);
                    nextConnectionSet.addAll(graphMap.get(connection));
                }
                nextConnectionSet.removeAll(visitedConnection);
                connectionToAdd = nextConnectionSet;
                distance++;
            }
        }
        return distanceMap;
    }
}

class RouteFinder<T extends Cell> {
    final Graph<T> graph;
    final Scorer<T> nextNodeScorer;
    final Scorer<T> targetScorer;

    RouteFinder(Graph<T> graph, Scorer<T> nextNodeScorer, Scorer<T> targetScorer) {
        this.graph = graph;
        this.nextNodeScorer = nextNodeScorer;
        this.targetScorer = targetScorer;
    }

    public List<T> findRoute(T from, T to) {
        Queue<RouteNode<T>> openSet = new PriorityQueue<>();
        Map<T, RouteNode<T>> allNodes = new HashMap<>();

        RouteNode<T> start = new RouteNode<T>(from, null, 0, targetScorer.computeCost(from, to));
        openSet.add(start);
        allNodes.put(from, start);
        while (!openSet.isEmpty()) {
            RouteNode<T> next = openSet.poll();
            if (next.current.equals(to)) {

                List<T> route = new ArrayList<>();
                RouteNode<T> current = next;
                do {
                    route.add(0, current.current);
                    current = allNodes.get(current.previous);
                } while (current != null);

                return route;
            }

            graph.getConnections(next.current).forEach(connection -> {
                double newScore = next.routeScore + nextNodeScorer.computeCost(next.current, connection);
                RouteNode<T> nextNode = allNodes.getOrDefault(connection, new RouteNode<>(connection));
                allNodes.put(connection, nextNode);

                if (nextNode.routeScore > newScore) {
                    nextNode.previous = next.current;
                    nextNode.routeScore = newScore;
                    nextNode.estimatedScore = newScore + targetScorer.computeCost(connection, to);
                    openSet.add(nextNode);
                }
            });
        }

        throw new IllegalStateException("No route found");
    }
}


class ManhattanScorer implements Scorer<Cell> {
    @Override
    public double computeCost(Cell from, Cell to) {
        return from.getDistance(to);
    }
}

class PreCalculatedScorer implements Scorer<Cell> {
    public HashMap<CellPair, Integer> distanceMap;

    public PreCalculatedScorer(HashMap<CellPair, Integer> distanceMap) {
        this.distanceMap = distanceMap;
    }

    @Override
    public double computeCost(Cell from, Cell to) {
        return distanceMap.getOrDefault(new CellPair(from, to), Integer.MAX_VALUE);
    }
}

interface Scorer<T extends Cell> {
    double computeCost(T from, T to);
}

class RouteNode<T extends Cell> implements Comparable<RouteNode<T>> {
    final T current;
    T previous;
    double routeScore;
    double estimatedScore;

    RouteNode(T current) {
        this(current, null, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    RouteNode(T current, T previous, double routeScore, double estimatedScore) {
        this.current = current;
        this.previous = previous;
        this.routeScore = routeScore;
        this.estimatedScore = estimatedScore;
    }

    @Override
    public int compareTo(RouteNode other) {
        if (this.estimatedScore > other.estimatedScore) {
            return 1;
        } else if (this.estimatedScore < other.estimatedScore) {
            return -1;
        } else {
            return 0;
        }
    }
}

class Graph<T extends Cell> {
    final Set<T> nodes;
    final Map<T, Set<T>> connections;

    Graph(Set<T> nodes, Map<T, Set<T>> connections) {
        this.nodes = nodes;
        this.connections = connections;
    }

    public T getNode(T t) {
        return nodes.stream()
                .filter(node -> node.equals(t))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No node found with ID"));
    }

    public Set<T> getConnections(T node) {
        return connections.get(node).stream().map(this::getNode).collect(Collectors.toSet());
    }
}

class MapGraph {
    public TreeMap<Cell, TreeSet<Cell>> map = new TreeMap<>();

    public void addCell(Point p, int score) {
        map.put(new Cell(p, score), new TreeSet<>());
    }

    public int breadthFirstSearch(Cell root, Set<Cell> visitedSet, int deepness) {

        for(Cell c: map.get(root)){

        }
        return 0;
    }

    public void buildConnections(int[][] mapArr) {
        for(Cell c: map.keySet()){
            int y = c.y;
            int x = c.x;
            ArrayList<Point> pointArr = c.getConnectedFloorPoints(mapArr);
            for(Point p: pointArr)
                if (map.containsKey(new Cell(p, mapArr[p.y][p.x]))) connect(c, new Cell(p, mapArr[p.y][p.x]));
        }
    }

    public void connect(Cell c1, Cell c2) {
        if (!map.get(c1).contains(c2)) map.get(c1).add(c2);
        if (!map.get(c2).contains(c1)) map.get(c2).add(c1);
    }

    public TreeSet<Cell> getConnected(Cell c) {
        return map.get(c);
    }
}

class CellPair {
    Cell cell1;
    Cell cell2;

    public CellPair(Cell cell1, Cell cell2) {
        this.cell1 = cell1;
        this.cell2 = cell2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellPair cellPair = (CellPair) o;
        return cell1.equals(cellPair.cell1) && cell2.equals(cellPair.cell2)
               || cell1.equals(cellPair.cell2) && cell2.equals(cellPair.cell1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cell1, cell2);
    }

    @Override
    public String toString() {
        return "CodingSpring2020.CellPair 1: " + cell1 + " 2: " + cell2;
    }
}

class Crossroad extends Cell {
    TreeMap<Integer, Crossroad> connectionTree = new TreeMap<>();

    public Crossroad(Point p, int score) {
        super(p, score);
    }

    public Crossroad(Point p) {
        super(p);
    }
}

class Tunnel {
    Crossroad crossroad1;
    Crossroad crossroad2;

    int score;
    int length;

}

class Pac extends Point {
    int id;
    boolean isMine;
    //typeId - unused
    //speedTurnsLeft - unused
    //abilityCooldown - unused

    public Pac(int id, boolean isMine, int x, int y) {
        super(x, y);
        this.id = id;
        this.isMine = isMine;
    }
}

class Cell extends Point implements Comparable<Cell> {
    int score = 0;

    public Cell(Point p, int score) {
        super(p);
        this.score = score;
    }

    public Cell(Point p) {
        super(p);
    }

    @Override
    public String toString() {
        return "CodingSpring2020.Cell x: " + x + " y: " + y + " score: " + score;
    }


    @Override
    public int compareTo(Cell o) {
        int comparator = Integer.compare(this.score, o.score);
        if (comparator == 0) return this.equals(o) ? 0 : 1;
        else return comparator;
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    //Manhattan distance
    public int getDistance(Point p) {
        return Math.abs(this.x - p.x) + Math.abs(this.y - p.y);
    }

    public ArrayList<Point> getConnectedPoints(int[][] mapArr) {
        ArrayList<Point> pointArr = new ArrayList<>();
        if (x > 0) pointArr.add(new Point(x - 1, y));
        if (x < mapArr[0].length - 1) pointArr.add(new Point(x + 1, y));
        if (y > 0) pointArr.add(new Point(x, y - 1));
        if (y < mapArr.length - 1) pointArr.add(new Point(x, y + 1));
        return pointArr;
    }

    public ArrayList<Point> getConnectedFloorPoints(int[][] mapArr) {
        ArrayList<Point> cellArr = getConnectedPoints(mapArr).stream()
                .filter(p -> mapArr[p.y][p.x] >= 0)
                .collect(Collectors.toCollection(ArrayList::new));
        return cellArr;
    }

    public ArrayList<Cell> getConnectedFloorCells(int[][] mapArr) {
        ArrayList<Cell> cellArr = getConnectedPoints(mapArr).stream()
                .filter(p -> mapArr[p.y][p.x] >= 0)
                .map(p -> new Cell(p, mapArr[p.y][p.x]))
                .collect(Collectors.toCollection(ArrayList::new));
        return cellArr;
    }

    public int calculateDistance(Point point, int mapWidth) {
        int dv = Math.abs(this.y - point.y);
        int dh = Math.min(Math.abs(this.x - point.x), Math.min(this.x + mapWidth - point.x, point.x + mapWidth
                                                                                            - this.x));
        return dv + dh;
    }

    @Override
    public String toString() {
        return "CodingSpring2020.Point x:" + x + " y: " + y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}