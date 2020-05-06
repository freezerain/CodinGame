import java.util.*;

class Player {
    public static final int MAX_SPEED = 100;
    public static Boolean haveBoost = true;
    public static CheckpointManager cpManager = new CheckpointManager();
    public static final Point mapCenter = new Point(8000, 4500);

    public static void main(String args[]) {
        standardMainFlow();
        //testMainFlow();
    }

    static void testMainFlow() {
        long startTime;
        Pod myPod = new Pod(0, 0, 0);
        //cpManager.addNewCheckpoint(1500, 0);
        //cpManager.addNewCheckpoint(0,3500);
        //cpManager.addNewCheckpoint(-1500,-1500);
        // cpManager.addNewCheckpoint(1500, 0);
        cpManager.updateCurrentTarget(new Point(0, 3500));
        //cpManager.updateCurrentTarget(new Point(-1500,-1500));
        //cpManager.updateCurrentTarget(new Point(1500,0));

        ArrayList<Pod> pods = new ArrayList<>();
        pods.add(myPod);
        myPod.angle = 0;
        // game loop
        while (true) {
            startTime = System.nanoTime();

            //test(pods);
            evolution(startTime,myPod);

            System.err.println("Iteration time: " + (System.nanoTime() - startTime) / 1000000);
        }
    }

    static void standardMainFlow() {
        Scanner in = new Scanner(System.in);
        long startTime;
        Pod myPod = new Pod(0, 0, 0);
        // game loop
        while (true) {
            startTime = System.nanoTime();
            myPod.x = in.nextInt(); // x position of your pod
            myPod.y = in.nextInt(); // y position of your pod
            Point currentCheckpoint = new Point(in.nextInt(), in.nextInt());
            int nextCheckpointDist = in.nextInt();
            int nextCheckpointAngle = in.nextInt();
            if (in.hasNextLine()) in.nextLine();
            if (in.hasNextLine()) in.nextLine();
            /*if(cpManager.cPointArr.isEmpty()){
                cpManager.addNewCheckpoint(myPod.x,myPod.y);
                myPod.cpIndex++;
            }*/
            cpManager.updateCurrentTarget(currentCheckpoint);
            myPod.cpIndex = cpManager.getTargetIndex(currentCheckpoint);
            myPod.setCurrentAngle(currentCheckpoint, nextCheckpointAngle);
/*            if (!cpManager.isAllCheckPoints)
                simpleMove((int) currentCheckpoint.x, (int) currentCheckpoint.y, nextCheckpointAngle, nextCheckpointDist);
            else evolution(startTime, myPod);*/

            if(haveBoost && Math.abs(nextCheckpointAngle)<10 && nextCheckpointDist>3000) {
                print((int)currentCheckpoint.x, (int)currentCheckpoint.y, 300);
                haveBoost = false;
            }else{
                System.err.println("input angle " +nextCheckpointAngle);
                evolution(startTime, myPod);
                //myPod.printMove(new Move(myPod.diffAngle(currentCheckpoint), angleDiff > 60 ? 0 : 100));
                //print((int)currentCheckpoint.x,(int)currentCheckpoint.y,angleDiff > 60 ? 0 : 100);
                System.err.println("Iteration time: " + (System.nanoTime() - startTime) / 1000000);
            }

        }
    }

    static void simpleMove(int x, int y, int angleDiff, int cpDistance) {
        if (Math.abs(angleDiff) > 60) print(x, y, 0);
        else if (haveBoost && cpDistance > 3000 && Math.abs(angleDiff) < 15) {
            print(x, y, 300);
            haveBoost = false;
        } else print(x, y, 100);
    }

    public static void print(int x, int y, int thrust) {
        System.out.println(x + " " + y + " " + (thrust > 200 ? "BOOST" : thrust < 0 ? 0 : thrust));
    }

    static void test(ArrayList<Pod> podArr) {
        for(int i = 0; i < podArr.size(); ++i){
            podArr.get(i).rotate(cpManager.cPointArr.get(podArr.get(i).getCpIndex()));
            podArr.get(i).boost(100);
        }

        simulateTurn(podArr);
    }

    //Симуляция хода
    static void simulateTurn(ArrayList<Pod> podArr) {
        // This tracks the time during the turn. The goal is to reach 1.0
        double t = 0.0;
        HashSet<Collision> collisionHistory = new HashSet<>();
        while (t < 1.0) {
            Collision firstCollision = null;
            // We look for all the collisions that are going to occur during the turn
            for(int i = 0; i < podArr.size(); ++i){
                // Collision with another pod?
                for(int j = i + 1; j < podArr.size(); ++j){
                    Collision col = podArr.get(i).collision(podArr.get(j));
                    // If the collision occurs earlier than the one we currently have we keep it
                    if (col != null && !collisionHistory.contains(col) && col.t + t < 1.0 && (firstCollision == null || col.t < firstCollision.t)) {
                        firstCollision = col;
                    }
                }
                // Collision with another checkpoint?
                // It is unnecessary to check all checkpoints here. We only test the pod's next checkpoint.
                // We could look for the collisions of the pod with all the checkpoints, but if such a collision happens it wouldn't impact the game in any way
                Collision col = podArr.get(i).collision(cpManager.cPointArr.get(podArr.get(i).getCpIndex()));

                // If the collision happens earlier than the current one we keep it
                if (col != null && !collisionHistory.contains(col) && col.t + t < 1.0 && (firstCollision == null || col.t < firstCollision.t)) {
                    firstCollision = col;
                }
            }

            if (firstCollision == null) {
                // No collision, we can move the pods until the end of the turn
                for(int i = 0; i < podArr.size(); ++i){
                    podArr.get(i).move(1.0 - t);
                }

                // End of the turn
                t = 1.0;
            } else {
                // Move the pods to reach the time `t` of the collision
                for(int i = 0; i < podArr.size(); ++i){
                    podArr.get(i).move(firstCollision.t);
                }

                // Play out the collision
                firstCollision.a.bounce(firstCollision.b);
                collisionHistory.add(firstCollision);
                t += firstCollision.t;
            }
        }

        for(int i = 0; i < podArr.size(); ++i){
            podArr.get(i).end();
        }
    }

    static void evolution(long startTime, Pod myPod) {
        TreeSet<MySolution> solutionTree = getStartingSolutions(myPod);
        int amountOfEvolutions = 0;

        while (System.nanoTime() - startTime < 70000000) {
            amountOfEvolutions++;
            MySolution solution = new MySolution(solutionTree.first());
            solution.mutateMoves(1.0);
            solution.score(myPod);
            if (solution.score > solutionTree.first().score) {
                solutionTree.pollFirst();
                solutionTree.add(solution);
            }
        }

        System.err.println("Amount of evolutions: " + amountOfEvolutions);
        MySolution best = solutionTree.last();

/*
        myPod.rotate(best.moves1.get(0).angle);
        myPod.boost(best.moves1.get(0).thrust);
        simulateTurn(new ArrayList<>(Arrays.asList(myPod)));
*/

        System.err.println(best);
        Pod copyPod = new Pod(myPod);
        copyPod.rotate(best.moves1.get(0).angle);
        copyPod.boost(best.moves1.get(0).thrust);
        simulateTurn(new ArrayList<>(Arrays.asList(copyPod)));
        myPod = copyPod;

        myPod.printMove(best.moves1.get(0));
    }

    static TreeSet<MySolution> getStartingSolutions(Pod ogPod) {
        TreeSet<MySolution> set = new TreeSet<>();
        //while (set.size() < 5) {
            Pod pod = new Pod(ogPod);
            MySolution newSolution = new MySolution();
            for(int j = 0; j < 6; j++){
                Move move = new Move();
                double angle =  pod.diffAngle(cpManager.cPointArr.get(pod.getCpIndex()));
                move.angle = angle;
                move.thrust = Math.abs(angle) > 60 ? 0 : 100;
                pod.rotate(angle);
                pod.boost(move.thrust);
                ArrayList<Pod> tempArr = new ArrayList<>();
                tempArr.add(pod);
                simulateTurn(tempArr);
                newSolution.moves1.add(move);
            }
            newSolution.score = pod.score();
        set.add(newSolution);
        for(int i = 0; i < 5; i++){
            pod = new Pod(ogPod);
            MySolution nextS = new MySolution(newSolution);
            nextS.mutateMoves(1.0);
            nextS.score(pod);
            set.add(nextS);
        }

        //  }
        return set;
    }

}

class MySolution implements Comparable {
    ArrayList<Move> moves1 = new ArrayList<>();
    ArrayList<Move> moves2;
    public double score = Double.MIN_VALUE;
    public Point finalLocation = new Point(0,0);
    public double finalAngle = 0;

    public MySolution() {
    }
    public MySolution(MySolution s) {
        s.moves1.forEach(m->this.moves1.add(new Move(m)));
        this.finalLocation = new Point(s.finalLocation.x, s.finalLocation.y);
        this.finalAngle = s.finalAngle;
        this.moves2 = s.moves2;
        this.score = s.score;
    }

    @Override
    public String toString() {
        return "Solution: Final location: " + finalLocation.x + " " + finalLocation.y + " Final angle: "+(int)finalAngle;
    }

    void mutateMoves(double amplitude) {
        for(Move m: moves1){
            m.mutate(amplitude);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MySolution that = (MySolution) o;
        return Math.abs(that.score - score) < 1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }

    double score(Pod ogPod) {
        Pod clonedPod = new Pod(ogPod);
        // Play out the turns
        ArrayList<Pod> tempArr = new ArrayList<>();
        tempArr.add(clonedPod);
        for (Move m : moves1) {
            clonedPod.rotate(m.angle);
            clonedPod.boost(m.thrust);
            Player.simulateTurn(tempArr);
        }
        finalLocation = new Point(clonedPod.x, clonedPod.y);
        finalAngle = clonedPod.angle;
        score = clonedPod.score();
        return score;
    }

    @Override
    public int compareTo(Object o) {
        return Double.compare(score, ((MySolution) o).score);
    }
}

class Move {
    double angle; // Between -18.0 and +18.0
    int thrust; // Between -1 and 200

    public Move() {
    }
    public Move(Move m){
        this.angle = m.angle;
        this.thrust = m.thrust;
    }

    public Move(double angle, int thrust) {
        this.angle = angle;
        this.thrust = thrust;
    }

    void mutate(double amplitude) {
        double ramin = this.angle - 36.0 * amplitude;
        double ramax = this.angle + 36.0 * amplitude;

        if (ramin < -18.0) {
            ramin = -18.0;
        }

        if (ramax > 18.0) {
            ramax = 18.0;
        }

        angle = (new Random().nextDouble() * (ramax - ramin)) + ramin;

        //Мутация щита отключена
        /*if (!this.shield && random(0, 100) < SHIELD_PROB) {
            this.shield = true;
        } else {*/
        int pmin = (int) (this.thrust - 100 * amplitude);
        int pmax = (int) (this.thrust + 100 * amplitude);

        if (pmin < 0) {
            pmin = 0;
        }

        if (pmax > 0) {
            pmax = 100;
        }

        this.thrust = new Random().nextInt(pmax - pmin) + pmin;

        // this.shield = false;
        //}
    }
}

class CheckpointManager {
    ArrayList<Checkpoint> cPointArr = new ArrayList<>();
    boolean isAllCheckPoints = false;
    Checkpoint lastAddedCheckpoint;

    public void addNewCheckpoint(double x, double y) {
        cPointArr.add(new Checkpoint(x, y, cPointArr.size()));
    }

    public void addNewCheckpoint(Point p) {
        cPointArr.add(new Checkpoint(p.x, p.y, cPointArr.size()));
    }

    public Checkpoint pointToCheckpoint(Point p) {
        return new Checkpoint(p.x, p.y, cPointArr.size());
    }

    public Checkpoint getNextCheckpoint(int id) {
        return cPointArr.get((id + 1) % cPointArr.size());
    }
    public int getTargetIndex(Point p){
        return cPointArr.indexOf(p);
    }

    public void updateCurrentTarget(Point point) {
        if (!isAllCheckPoints) {
            if (cPointArr.contains(point)) {
                if (!point.equals(lastAddedCheckpoint) && point.equals(cPointArr.get(0))) isAllCheckPoints = true;
            } else {
                addNewCheckpoint(point);
                lastAddedCheckpoint = pointToCheckpoint(point);
            }
        }
    }

    public void debugPrint() {
        for(int i = 0; i < cPointArr.size(); i++){
            System.err.println("P" + i + ": " + cPointArr.get(i).x + " " + cPointArr.get(i).y);
        }
    }
}

class Pod extends Unit {
    double angle = 0;
    boolean shield = false;
    public int timeout = 100;
    public int checked = 0;
    public int cpIndex = 0;

    public Pod(double x, double y, int id) {
        super(x, y, id);
    }

    public Pod(Pod p) {
        super(p.x, p.y, p.id);
        this.angle = p.angle;
        this.shield = p.shield;
        this.timeout = p.timeout;
        this.checked = p.checked;
        this.cpIndex = p.cpIndex;
    }

    //TODO Заменить эту функцию на получаемый угол когда его начнут давать
    //Эта функция находит фронтальный угол Пода от разницы с чекпоинтом
    public void setCurrentAngle(Point nextCp, int angleDifference) {
        double cpAngle = getAngle(nextCp);
        angle = cpAngle - angleDifference;
    }

    //Это угол на который Под должен смотреть, что бы смотреть на цель
    double getAngle(Point point) {
        double d = this.getDistanceSqrt(point);
        double dx = (point.x - this.x) / d;
        double dy = (point.y - this.y) / d;
        // Simple trigonometry. We multiply by 180.0 / PI to convert radiants to degrees.
        double ang = Math.acos(dx) * 180.0 / Math.PI;
        // If the point I want is below me, I have to shift the angle for it to be correct
        if (dy < 0) ang = 360.0 - ang;
        return ang;
    }

    //Это угол на который под должен повернутся что бы смотреть на цель
    double diffAngle(Point p) {
        double a = this.getAngle(p);

        // To know whether we should turn clockwise or not we look at the two ways and keep the smallest
        // The ternary operators replace the use of a modulo operator which would be slower
        double right = this.angle <= a ? a - this.angle : 360.0 - this.angle + a;
        double left = this.angle >= a ? this.angle - a : this.angle + 360.0 - a;

        if (right < left) {
            return right;
        } else {
            // We return a negative angle if we must rotate to left
            return -left;
        }
    }

    //Метод для поворота Пода
    void rotate(Point p) {
        double a = this.diffAngle(p);

        // Can't turn by more than 18° in one turn
        if (a > 18.0) {
            a = 18.0;
        } else if (a < -18.0) {
            a = -18.0;
        }

        this.angle += a;

        // The % operator is slow. If we can avoid it, it's better.
        if (this.angle >= 360.0) {
            this.angle = this.angle - 360.0;
        } else if (this.angle < 0.0) {
            this.angle += 360.0;
        }
    }

    public int getCpIndex() {
        return cpIndex % Player.cpManager.cPointArr.size();
    }

    void rotate(double a) {

        // Can't turn by more than 18° in one turn
        if (a > 18.0) {
            a = 18.0;
        } else if (a < -18.0) {
            a = -18.0;
        }

        this.angle += a;

        // The % operator is slow. If we can avoid it, it's better.
        if (this.angle >= 360.0) {
            this.angle = this.angle - 360.0;
        } else if (this.angle < 0.0) {
            this.angle += 360.0;
        }
    }

    //Добавить Поду Ускорение
    void boost(int thrust) {
        // Don't forget that a pod which has activated its shield cannot accelerate for 3 turns
        if (this.shield) {
            return;
        }

        // Conversion of the angle to radiants
        double ra = Math.toRadians(angle);

        // Trigonometry
        this.vx += Math.cos(ra) * thrust;
        this.vy += Math.sin(ra) * thrust;
    }

    //Перепестить Под от ускорения, т-контролирует влияние ускорения
    void move(double t) {
        this.x += this.vx * t;
        this.y += this.vy * t;
    }

    double score() {
        Checkpoint cp = Player.cpManager.cPointArr.get(getCpIndex());
        double distance = getDistanceSqrt(cp);
        double v = Math.abs(diffAngle(cp)) * 50.0;
        double v1 = checked * 50000.0;
        double v2 = v1 - v - distance;
        return timeout == 0 ? Double.MIN_VALUE : v2;
    }

    //Завершающие округления
    void end() {
        this.x = Math.round(this.x);
        this.y = Math.round(this.y);
        this.vx = (int) (this.vx * 0.85);
        this.vy = (int) (this.vy * 0.85);

        // Don't forget that the timeout goes down by 1 each turn. It is reset to 100 when you pass a checkpoint
        this.timeout -= 1;
    }

    //Симулировать ход
    void play(double angle, int thrust) {
        this.rotate(angle);
        this.boost(thrust);
        this.move(1.0);
        this.end();
    }

    @Override
    void bounce(Unit u) {
        super.bounce(u);
        if (u instanceof Checkpoint) {
            // Collision with a checkpoint
            //this.bounceWithCheckpoint((Checkpoint) u);
            timeout = 100;
            checked++;
            cpIndex++;
        } else {
            // If a pod has its shield active its mass is 10 otherwise it's 1
            double m1 = this.shield ? 10 : 1;
            double m2 = ((Pod) u).shield ? 10 : 1;
            double mcoeff = (m1 + m2) / (m1 * m2);

            double nx = this.x - u.x;
            double ny = this.y - u.y;

            // Square of the distance between the 2 pods. This value could be hardcoded because it is always 800²
            double nxnysquare = nx * nx + ny * ny;

            double dvx = this.vx - u.vx;
            double dvy = this.vy - u.vy;

            // fx and fy are the components of the impact vector. product is just there for optimisation purposes
            double product = nx * dvx + ny * dvy;
            double fx = (nx * product) / (nxnysquare * mcoeff);
            double fy = (ny * product) / (nxnysquare * mcoeff);

            // We apply the impact vector once
            this.vx -= fx / m1;
            this.vy -= fy / m1;
            u.vx += fx / m2;
            u.vy += fy / m2;

            // If the norm of the impact vector is less than 120, we normalize it to 120
            double impulse = Math.sqrt(fx * fx + fy * fy);
            if (impulse < 120.0) {
                fx = fx * 120.0 / impulse;
                fy = fy * 120.0 / impulse;
            }

            // We apply the impact vector a second time
            this.vx -= fx / m1;
            this.vy -= fy / m1;
            u.vx += fx / m2;
            u.vy += fy / m2;

            // This is one of the rare places where a Vector class would have made the code more readable.
            // But this place is called so often that I can't pay a performance price to make it more readable.
        }

    }

    void printMove(Move move) {
        double a = this.angle + move.angle;

        if (a >= 360.0) {
            a = a - 360.0;
        } else if (a < 0.0) {
            a += 360.0;
        }

        // Look for a point corresponding to the angle we want
        // Multiply by 10000.0 to limit rounding errors
        a = Math.toRadians(a);
        double px = this.x + Math.cos(a) * 10000.0;
        double py = this.y + Math.sin(a) * 10000.0;
        //Щиты отключены
        /*if (move.shield) {
            print(round(px), round(py), "SHIELD");
            activateShield();
        } else {*/
        Player.print((int) Math.round(px), (int) Math.round(py), move.thrust);
        //}
    }
}

class Checkpoint extends Unit {
    public Checkpoint(double x, double y, int id) {
        super(x, y, id);
        this.r = 550.0;
    }

    @Override
    void bounce(Unit u) {
        super.bounce(u);
    }
}


//Class is Done
class Unit extends Point {
    int id;
    double vx;
    double vy;
    double r;

    public Unit(double x, double y, int id) {
        super(x, y);
        this.id = id;
        this.r = 350.0;
    }

    //Возвращяет результат столкновения этого юнита с U
    Collision collision(Unit u) {
        // Square of the distance
        double dist = this.getDistance(u);

        // Sum of the radii squared
        double sr = (this.r + u.r) * (this.r + u.r);

        // We take everything squared to avoid calling sqrt uselessly. It is better for performances

        if (dist < sr) {
            // Objects are already touching each other. We have an immediate collision.
            return new Collision(this, u, 0.0);
        }

        // Optimisation. Objects with the same speed will never collide
        if (this.vx == u.vx && this.vy == u.vy) {
            return null;
        }

        // We place ourselves in the reference frame of u. u is therefore stationary and is at (0,0)
        double x = this.x - u.x;
        double y = this.y - u.y;
        Point myp = new Point(x, y);
        double vx = this.vx - u.vx;
        double vy = this.vy - u.vy;
        Point up = new Point(0, 0);

        // We look for the closest point to u (which is in (0,0)) on the line described by our speed vector
        Point p = up.closest(myp, new Point(x + vx, y + vy));

        // Square of the distance between u and the closest point to u on the line described by our speed vector
        double pdist = up.getDistance(p);

        // Square of the distance between us and that point
        double mypdist = myp.getDistance(p);

        // If the distance between u and this line is less than the sum of the radii, there might be a collision
        if (pdist < sr) {
            // Our speed on the line
            double length = Math.sqrt(vx * vx + vy * vy);

            // We move along the line to find the point of impact
            double backdist = Math.sqrt(sr - pdist);
            p.x = p.x - backdist * (vx / length);
            p.y = p.y - backdist * (vy / length);

            // If the point is now further away it means we are not going the right way, therefore the collision won't happen
            if (myp.getDistance(p) > mypdist) {
                return null;
            }

            pdist = p.getDistanceSqrt(myp);

            // The point of impact is further than what we can travel in one turn
            if (pdist > length) {
                return null;
            }

            // Time needed to reach the impact point
            double t = pdist / length;

            return new Collision(this, u, t);
        }

        return null;
    }

    void bounce(Unit u) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Unit)) return false;
        if (!super.equals(o)) return false;
        Unit unit = (Unit) o;
        return id == unit.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}

//Class id Done
class Collision {
    Unit a;
    Unit b;
    double t;

    public Collision(Unit unit, Unit u, double t) {
        this.a = unit;
        this.b = u;
        this.t = t;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collision collision = (Collision) o;
        return a.equals(collision.a) && b.equals(collision.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}

//Clas id Done
class Point {
    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point point = (Point) o;
        return Math.abs(point.x - x) < 1 && Math.abs(point.y - y) < 1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    double getDistance(Point p) {
        return (this.x - p.x) * (this.x - p.x) + (this.y - p.y) * (this.y - p.y);
    }

    double getDistanceSqrt(Point p) {
        return Math.sqrt(this.getDistance(p));
    }

    Point closest(Point a, Point b) {
        double da = b.y - a.y;
        double db = a.x - b.x;
        double c1 = da * a.x + db * a.y;
        double c2 = -db * this.x + da * this.y;
        double det = da * da + db * db;
        double cx = 0;
        double cy = 0;

        if (det != 0) {
            cx = (da * c1 - db * c2) / det;
            cy = (da * c2 + db * c1) / det;
        } else {
            // The point is already on the line
            cx = this.x;
            cy = this.y;
        }

        return new Point(cx, cy);
    }
}
