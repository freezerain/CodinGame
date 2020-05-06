package CodersStrikeBack;

import java.util.*;

class Player {
    public static final int MAX_SPEED = 100;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        Boolean haveBoost = true;
        // game loop
        while (true) {
            int x = in.nextInt(); // x position of your pod
            int y = in.nextInt(); // y position of your pod
            int nextCheckpointX = in.nextInt(); // x position of the next check point
            int nextCheckpointY = in.nextInt(); // y position of the next check point
            int nextCheckpointDist = in.nextInt();
            int nextCheckpointAngle = in.nextInt();
            if (in.hasNextLine()) in.nextLine();
            if (in.hasNextLine()) in.nextLine();
            //Pod pod1 = new Pod(x, y, 0, 400.0);
            HashSet<Checkpoint> checkpointsSet = new HashSet<>();
            /*if(Math.abs(nextCheckpointAngle)>90) print(nextCheckpointX, nextCheckpointY, 0);
            else*/
            if (Math.abs(nextCheckpointAngle) > 45) print(nextCheckpointX, nextCheckpointY, 0);
                //else if (nextCheckpointAngle>45 || nextCheckpointAngle<-45) print(nextCheckpointX, nextCheckpointY, 25);
            else if (haveBoost && nextCheckpointDist > 2500 && Math.abs(nextCheckpointAngle) < 10) {
                print(nextCheckpointX, nextCheckpointY, 200);
                haveBoost = false;
                System.err.println("BOOSTED");
            } else print(nextCheckpointX, nextCheckpointY, 100);
        }
    }

    static void print(int x, int y, int thrust) {
        System.out.println(x + " " + y + " " + (thrust > 100 ? "BOOST" : thrust));
    }

    //TODO
    static void play(Pod[] pods, Checkpoint[] checkpoints) {
        // This tracks the time during the turn. The goal is to reach 1.0
        double t = 0.0;

        while (t < 1.0) {
            Collision firstCollision = null;

            // We look for all the collisions that are going to occur during the turn
            for(int i = 0; i < pods.length; ++i){
                // Collision with another pod?
                for(int j = i + 1; j < pods.length; ++j){
                    Collision col = pods[i].collision(pods[j]);

                    // If the collision occurs earlier than the one we currently have we keep it
                    if (col != null && col.t + t < 1.0 && (firstCollision == null || col.t < firstCollision.t)) {
                        firstCollision = col;
                    }
                }

                // Collision with another checkpoint?
                // It is unnecessary to check all checkpoints here. We only test the pod's next checkpoint.
                // We could look for the collisions of the pod with all the checkpoints, but if such a collision happens it wouldn't impact the game in any way
                Collision col = pods[i].collision(checkpoints[pods[i].nextCheckpointId]);

                // If the collision happens earlier than the current one we keep it
                if (col != null && col.t + t < 1.0 && (firstCollision == null || col.t < firstCollision.t)) {
                    firstCollision = col;
                }
            }

            if (firstCollision == null) {
                // No collision, we can move the pods until the end of the turn
                for(int i = 0; i < pods.length; ++i){
                    pods[i].move(1.0 - t);
                }

                // End of the turn
                t = 1.0;
            } else {
                // Move the pods to reach the time `t` of the collision
                for(int i = 0; i < pods.length; ++i){
                    pods[i].move(firstCollision.t);
                }

                // Play out the collision
                firstCollision.a.bounce(firstCollision.b);

                t += firstCollision.t;
            }
        }

        for(int i = 0; i < pods.length; ++i){
            pods[i].end();
        }
    }
    static void test(Pod[] pods, Checkpoint[] checkpoints) {
        for (int i = 0; i < pods.length; ++i) {
            pods[i].rotate(new Point(8000, 4500));
            pods[i].boost(200);
        }

        play(pods, checkpoints);
    }
}
class CheckpointManager{
    ArrayList<Point> cPointArr = new ArrayList<>();
    Point targetPoint;
    Point nextTargetPoint;



}

class Simulation{
    Pod myPod;
    Pod enemyPod;
}



//TODO
class Pod extends Unit {
    double angle = 0;
    int nextCheckpointId = 0;
    int checked = 0;
    boolean shield = false;
    Pod partner;
    int timeout = 100;

    public Pod(double x, double y, int id, double radius) {
        super(x, y, id, radius);
    }

    double getAngle(Point p) {
        double distance = this.getDistanceSqrt(p);
        double dx = (p.x - this.x) / distance;
        double dy = (p.y - this.y) / distance;

        // Simple trigonometry. We multiply by 180.0 / PI to convert radiants to degrees.
        double a = Math.acos(dx) * 180.0 / Math.PI;

        // If the point I want is below me, I have to shift the a for it to be correct
        if (dy < 0) {
            a = 360.0 - a;
        }

        return a;
    }

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

    void boost(int thrust) {
        // Don't forget that a pod which has activated its shield cannot accelerate for 3 turns
        if (this.shield) {
            return;
        }

        // Conversion of the angle to radiants
        double ra = this.angle * Math.PI / 180.0;

        // Trigonometry
        this.vx += Math.cos(ra) * thrust;
        this.vy += Math.sin(ra) * thrust;
    }

    void move(double t) {
        this.x += this.vx * t;
        this.y += this.vy * t;
    }

    void end() {
        this.x = Math.round(this.x);
        this.y = Math.round(this.y);
        this.vx = (int) (this.vx * 0.85);
        this.vy = (int) (this.vy * 0.85);


        // Don't forget that the timeout goes down by 1 each turn. It is reset to 100 when you pass a checkpoint
        this.timeout -= 1;
    }

    void play(Point p, int thrust) {
        this.rotate(p);
        this.boost(thrust);
        this.move(1.0);
        this.end();
    }

    void bounce(Unit u) {
        if (u instanceof Checkpoint) {
            // Collision with a checkpoint
            //MY comment this was this.bounceWithCheckpoint((Checkpoint) u);
            /*this.bounceWithCheckpoint((Checkpoint) u);*/
        } else {
            // If a pod has its shield active its mass is 10 otherwise it's 1
            double m1 = this.shield ? 10 : 1;
            double m2 = /*u.shield ? 10 :*/ 1;
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
    /*float score() {
        return checked*50000 - this.distance(this.checkpoint());
    }*/
    void output(Move move) {
        double a = angle + move.angle;

        if (a >= 360.0) {
            a = a - 360.0;
        } else if (a < 0.0) {
            a += 360.0;
        }

        // Look for a point corresponding to the angle we want
        // Multiply by 10000.0 to limit rounding errors
        a = a * Math.PI / 180.0;
        double px = this.x + Math.cos(a) * 10000.0;
        double py = this.y + Math.sin(a) * 10000.0;

        /*if (move.shield) {
            print(round(px), round(py), "SHIELD");
            activateShield();
        } else
            print(Math.round(px), Math.round(py), move.power);*/

    }
}

class Checkpoint extends Unit {
    public Checkpoint(double x, double y, int id, double radius) {
        super(x, y, id, radius);
    }

    @Override
    void bounce(Unit u) {

    }
}

class Unit extends Point {
    int id;
    double r;
    double vx;
    double vy;


    public Unit(double x, double y, int id, double radius) {
        super(x, y);
        this.r = radius;
        this.id = id;
    }

    //TODO
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

    //TODO
    void bounce(Unit u) {

    }
}

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
        return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
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

//TODO
class Collision {
    Unit a;
    Unit b;
    double t;

    public Collision(Unit a, Unit b, double t) {
        this.a = a;
        this.b = b;
        this.t = t;
    }
}

//TODO
class TurnSolution {
    Move[] moves1;
    Move[] moves2;
    void randomize() {

    }
/*    double score() {
        // Play out the turns
        for (int i = 0; i < moves1.length; ++i) {
            // Apply the moves to the pods before playing
            myPod1.apply(moves1[i]);
            myPod2.apply(moves2[i]);

            play();
        }

        // Compute the score
        float result = evaluation();

        // Reset everyone to their initial states
        load();

        return result;
    }*/
}

//TODO

class Move {
    double angle; // Between -18.0 and +18.0
    int thrust; // Between -1 and 200
   /* void mutate(double amplitude) {
        double ramin = this.angle - 36.0 * amplitude;
        double ramax = this.angle + 36.0 * amplitude;

        if (ramin < -18.0) {
            ramin = -18.0;
        }

        if (ramax > 18.0) {
            ramax = 18.0;
        }

        angle = new Random().nextDouble() * (ramax - ramin);

        if (!this.shield && random(0, 100) < SHIELD_PROB) {
            this.shield = true;
        } else {
            int pmin = this.thrust - 200 * amplitude;
            int pmax = this.thrust + 200 * amplitude;

            if (pmin < 0) {
                pmin = 0;
            }

            if (pmax > 0) {
                pmax = 200;
            }

            this.thrust = new Random().nextInt(pmax) - pmin;

            this.shield = false;
        }
    }*/
}
