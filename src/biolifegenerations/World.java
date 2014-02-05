/*
 * Will store the locations of life on the world map and handle operations
 * dealing with positions in the world
 */
package biolifegenerations;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Angel Preciado
 */
public class World {

    private int mapSize, 
            days, //days that have passed since simulation began
            stamina;  //stamina = how many days can pass before beings lose health
    private Being[][] map;
    //private ArrayList<Being> animals;

    public World(int mapSize) {
        this.stamina = 25;
        this.mapSize = mapSize;
        this.map = new Being[mapSize][mapSize];
        //this.animals = new ArrayList<Being>();
        this.createRandomWorld();
    }

    public void createRandomWorld() {
        double rand = 0;
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                rand = Math.random();
                if (rand < .80) {
                    map[i][j] = new Being("empty", i, j,stamina, 1);
                } else if (rand < .946) {
                    map[i][j] = new Being("food", i, j,stamina,1);
                    map[i][j].assignAttributes();
                    map[i][j].setActionsPerDay();
                } else if (rand < .95) {
                    map[i][j] = new Being("predator", i, j,stamina,1);
                    map[i][j].assignAttributes();
                    map[i][j].setActionsPerDay();
                    //this.animals.add(map[i][j]);
                } else if (rand < .98) {
                    map[i][j] = new Being("prey", i, j,stamina,1);
                    map[i][j].assignAttributes();
                    map[i][j].setActionsPerDay();
                    //this.animals.add(map[i][j]);
                } else {
                    map[i][j] = new Being("empty", i, j,stamina,1);
                }
            }
        }
    
    }

    /**
     * Should handle all the events that need to take place within one day in this simulation
     * This should be the main logic that controls what happens and all methods should probably
     * be called from within this loop
     */
    public void runWorld() {
        for (int z = 1; z < 5; z++) { // does this from 1 - 4 because that is the highest number of actions that can be taken by any being at this point
        //go through each square determine if there is a being to move
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if(map[i][j].actionsPerDay == z){
                //determine where they will move
                //check spot they "chose"
                if (map[i][j].getType().equalsIgnoreCase("prey")
                        || map[i][j].getType().equalsIgnoreCase("predator")) {
                    int direction = this.chooseDirection(map[i][j]);
                    String inPath = this.lookAhead(map[i][j], direction);
                    if (!inPath.equalsIgnoreCase("outOfBounds")) {
                        //choose approrpiate action
                        deathNears(map[i][j]);
                        doAction(map[i][j], inPath, direction);
                        //move on to next being
                    }
                }
            }}
        }}
    }

    /**
     *  Another place where things may take place, right now this method decides
     * what action to take depending on direction the being is headed and what is
     * currently in its path
     * @param cur the being we are currently working with
     * @param inPath String containing the type of object in its path
     * @param dir the direction this being is headed
     */
    public void doAction(Being cur, String inPath, int dir) {
        if (inPath.equalsIgnoreCase("empty")) { //being just moves
            //System.out.println("being has moved");
            if (dir == 0) {
                this.replaceBeing(cur, cur.getX() - 1, cur.getY());
            } else if (dir == 1) {
                this.replaceBeing(cur, cur.getX() + 1, cur.getY());
            } else if (dir == 2) {
                this.replaceBeing(cur, cur.getX(), cur.getY() - 1);
            } else if (dir == 3) {
                this.replaceBeing(cur, cur.getX(), cur.getY() + 1);
            }
        } else if (inPath.equalsIgnoreCase("food")) {
            if (cur.getType().equalsIgnoreCase("prey")) {
                //eats food
                //System.out.println("prey ate food");
                if (dir == 0) {
                    cur.atePlant();
                    this.replaceBeing(cur, cur.getX() - 1, cur.getY());
                } else if (dir == 1) {
                    cur.atePlant();
                    this.replaceBeing(cur, cur.getX() + 1, cur.getY());
                } else if (dir == 2) {
                    cur.atePlant();
                    this.replaceBeing(cur, cur.getX(), cur.getY() - 1);
                } else if (dir == 3) {
                    cur.atePlant();
                    this.replaceBeing(cur, cur.getX(), cur.getY() + 1);
                }
            } else {
                //predator, ignore food dont move
                //System.out.println("predator did not move");
            }

        } else if (inPath.equalsIgnoreCase("prey")) {
            if (cur.getType().equalsIgnoreCase("predator")) {
                if (dir == 0) {
                    cur.atePrey();
                    this.replaceBeing(cur, cur.getX() - 1, cur.getY());
                } else if (dir == 1) {
                    cur.atePrey();
                    this.replaceBeing(cur, cur.getX() + 1, cur.getY());
                } else if (dir == 2) {
                    cur.atePrey();
                    this.replaceBeing(cur, cur.getX(), cur.getY() - 1);
                } else if (dir == 3) {
                    cur.atePrey();
                    this.replaceBeing(cur, cur.getX(), cur.getY() + 1);
                }
            }
        }
    }

    public void incrementBeingDaysLived(Being be){
        be.incrementDaysLived();
    }
    
    public void incrementDays() {
        this.days++;
    }

    /**
     * Method currently decreases the health of beings after a certain amount of
     * days have passed, called stamina for now.  We may want to change this
     * @param be being currently being worked with
     */
    public void deathNears(Being be) {
        if (be.getDaysLived() % be.getStamina() == 0) {
            if (be.getType().equalsIgnoreCase("prey")) {
                be.healthDown(3);
                if (be.getHealth() <= 0) {
                    System.out.println("A prey being has died, it lived "+be.getDaysLived()
                            +" days and died on the "+this.days+" day.");
                    this.killBeing(be);
                }
            } else if (be.getType().equalsIgnoreCase("predator")) {
                be.healthDown(1);
                if (be.getHealth() <= 0) {
                    System.out.println("A predator being has died, it lived "+be.getDaysLived()
                    +" days and died on the "+this.days+" day.");
                    this.killBeing(be);
                }
            }

        }
    }

    /**
     * Kills the being that is passed in and replaces it with an empty being object,
     * essentially removing them from the game.
     * @param be Being to be killed
     */
    public void killBeing(Being be) {
        map[be.getX()][be.getY()] = new Being("empty", be.getX(), be.getY(),stamina,0);
    }

    public int getTotalPlantsEaten() {
        int total = 0;
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                total += map[i][j].score;
            }
        }
        return total;
    }

    public double calculatePlantsEaten() {
        double totalEaten = 0;
        double totalEaters = 0;
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if (map[i][j].getType().equalsIgnoreCase("prey")) {
                    totalEaten += map[i][j].getScore();
                    totalEaters++;
                }
            }
        }
        return totalEaten / totalEaters;
    }

    public void senseSurroundings(Being be){
        
    }
    /**
     * Choose a random direction
     * @param be
     * @return 
     */
    public int chooseDirection(Being be) {
        //add code so direction isnt always random
        Random rand = new Random();
        int dir = rand.nextInt(4);
        return dir;
    }

    /**
     * Method will look into the beings path and return whatever is located in
     * that path, if it cannot travel there an appropriate message should be
     * returned.
     *
     * @param dir random int
     * @return String containing description of object in the path
     */
    public String lookAhead(Being cur, int dir) {
        if (dir == 0) { //move up
            if (inBounds(cur, dir)) {
                return this.getBeing(cur.getX() - 1, cur.getY()).getType();
            }
        } else if (dir == 1) { //move down
            if (inBounds(cur, dir)) {
                return this.getBeing(cur.getX() + 1, cur.getY()).getType();
            }
        } else if (dir == 2) { //move left
            if (inBounds(cur, dir)) {
                return this.getBeing(cur.getX(), cur.getY() - 1).getType();
            }
        } else { //move right
            if (inBounds(cur, dir)) {
                return this.getBeing(cur.getX(), cur.getY() + 1).getType();
            }
        }
        return "outOfBounds";
    }

    /**
     * Checks that the being's next move is actually within bounds.
     * @param cur
     * @param dir
     * @return 
     */
    public boolean inBounds(Being cur, int dir) {
        if (cur.getX() == 0) { //left
            if (dir == 0) {
                return false;
            }
        }
        if (cur.getX() == mapSize - 1) {
            if (dir == 1) {
                return false;
            }
        }
        if (cur.getY() == 0) {
            if (dir == 2) {
                return false;
            }
        }
        if (cur.getY() == mapSize - 1) {
            if (dir == 3) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the being currently at this location
     * @param x
     * @param y
     * @return 
     */
    public Being getBeing(int x, int y) {
        return this.map[x][y];
    }

    /**
     * Replaces the passed in being to the coordinates given
     * @param cur
     * @param x
     * @param y 
     */
    public void replaceBeing(Being cur, int x, int y) {
        map[x][y] = cur;
        int xx = cur.getX();
        int yy = cur.getY();
        map[xx][yy] = new Being("empty", xx, yy,stamina,0);
        cur.changeLocation(x, y);
    }

    public int getMapSize() {
        return mapSize;
    }

    public void printMap() {
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 40; j++) {
                System.out.println(map[i][j].getType());
            }
        }
    }
}
