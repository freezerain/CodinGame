import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Player {
    static final String ACTION_WAIT = "WAIT";
    static final String ACTION_MOVE = "MOVE";
    static final int HERO_MOVE_RANGE = 800;
    static final int HERO_ATTACK_RANGE = 800;
    static final int HERO_DAMAGE = 2;
    static final int MONSTER_MOVE_RANGE = 400;
    static final int MONSTER_AGRO_RANGE = 5000;
    static final int MONSTER_DAMAGE_RANGE = 300;
    static final int MONSTER_DAMAGE = 1;
    static final double L_DEF_CENTER_X = 3535.53;
    static final double L_DEF_CENTER_Y = 3535.53;
    static final double R_DEF_CENTER_X = 14094.46;
    static final double R_DEF_CENTER_Y = 5464.47;

    public static void main(String args[]) {
        int currentBaseLife = 3;
        Scanner in = new Scanner(System.in);
        int baseX = in.nextInt(); // The corner of the map representing your base
        int baseY = in.nextInt();
        boolean isBaseTopLeft = baseX < 1;
        int heroesPerPlayer = in.nextInt();
        while (true) {
            GameState currentGameState = new GameState(currentBaseLife, in);
            simpleHeroSend(currentGameState, isBaseTopLeft);
        }
    }

    static void simpleHeroSend(GameState state, boolean isTopLeft) {
        List<Entity> monsterList = getMonsterThreatList(state);
        if (monsterList.isEmpty()){
            int moveX = (int) (isTopLeft ? L_DEF_CENTER_X : R_DEF_CENTER_X);
            int moveY = (int) (isTopLeft ? L_DEF_CENTER_Y : R_DEF_CENTER_Y);
            for (int i = 0; i < 3; i++){
                System.out.println(ACTION_MOVE + " " + moveX + " " + moveY);
            }
        } else for (Entity hero : state.myHeroList){
            Entity nearestMonster = monsterList.get(0);
            double distance = hero.distanceToSqrt(nearestMonster);
            for (int i = 1; i < monsterList.size(); i++){
                Entity newMonster = monsterList.get(i);
                double newDistance = hero.distanceToSqrt(newMonster);
                if (newDistance < distance){
                    nearestMonster = newMonster;
                    distance       = newDistance;
                }
            }
            System.out.println(ACTION_MOVE + " " + nearestMonster.x + " " + nearestMonster.y);
        }
    }


    static List<Entity> getMonsterThreatList(GameState state) {
        List<Entity> monsterList = state.monsterList;
        List<Entity> heroLost = state.myHeroList;
        List<Entity> newMonsterList = new ArrayList<>(monsterList.size());
        System.err.println("monsterList size: " + state.monsterList.size());
        for (Entity err : monsterList){
            System.err.println(err);
        }
        for (Entity e : monsterList){
            if (e.threat == Entity.ThreatEnum.MY_THREAT){
                newMonsterList.add(e);
            }
        }
        System.err.println("new monsterlist size: " + newMonsterList.size());
        return newMonsterList;
    }

    static class GameState {
        public int baseLife;
        public int myHealth;
        public int myMana;
        public int enemyHealth;
        public int enemyMana;
        public int entityCount;
        List<Entity> monsterList;
        List<Entity> myHeroList;
        List<Entity> enemyHeroList;

        public GameState(int currentBaseLife, Scanner scn) {
            baseLife      = currentBaseLife;
            myHealth      = scn.nextInt();
            myMana        = scn.nextInt();
            enemyHealth   = scn.nextInt();
            enemyMana     = scn.nextInt();
            entityCount   = scn.nextInt();
            monsterList   = new ArrayList<>(entityCount);
            myHeroList    = new ArrayList<>(entityCount);
            enemyHeroList = new ArrayList<>(entityCount);
            for (int i = 0; i < entityCount; i++){
                Entity e = new Entity(scn.nextInt(), scn.nextInt(), scn.nextInt(), scn.nextInt(),
                        scn.nextInt(), scn.nextInt(), scn.nextInt(), scn.nextInt(), scn.nextInt(),
                        scn.nextInt(), scn.nextInt());
                List<Entity> listToAdd = e.type == Entity.EntityType.MONSTER ? monsterList :
                        e.type == Entity.EntityType.MY_HEROES ? myHeroList : enemyHeroList;
                listToAdd.add(e);
            }
        }
    }

    static class Entity {
        int id;
        EntityType type;
        int x;
        int y;
        int shieldLife;
        boolean isControlled;
        int health;
        int vx;
        int vy;
        boolean isNearBase;
        ThreatEnum threat;

        public Entity(int id, int type, int x, int y, int shieldLife, int isControlled,
                      int health, int vx, int vy, int isNearBase, int threat) {
            this.id           = id;
            this.type         = type == 0 ? EntityType.MONSTER :
                    type == 1 ? EntityType.MY_HEROES : EntityType.ENEMY_HEROES;
            this.x            = x;
            this.y            = y;
            this.shieldLife   = shieldLife;
            this.isControlled = isControlled > 0;
            this.health       = health;
            this.vx           = vx;
            this.vy           = vy;
            this.isNearBase   = isNearBase > 0;
            this.threat       = threat == 0 ? ThreatEnum.NONE :
                    threat == 1 ? ThreatEnum.MY_THREAT : ThreatEnum.ENEMY_THREAT;
        }

        double distanceToSqrt(Entity target) {
            return Math.pow(this.x - target.x, 2) + Math.pow(this.y - target.y, 2);
        }

        @Override
        public String toString() {
            return "Entity{" + "id=" + id + ", type=" + type + ", x=" + x + ", y=" + y +
                   ", shieldLife=" + shieldLife + ", isControlled=" + isControlled + ", health=" +
                   health + ", vx=" + vx + ", vy=" + vy + ", isNearBase=" + isNearBase +
                   ", threat=" + threat + '}';
        }

        enum ThreatEnum {
            NONE, MY_THREAT, ENEMY_THREAT
        }

        enum EntityType {
            MONSTER, MY_HEROES, ENEMY_HEROES
        }
    }
}