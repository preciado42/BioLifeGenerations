/*
 * The Being class will be the model for a creature that seeks to survive.  They
 * will have certain attributes that will help determine wether they succeed at
 * finding food.  These attributes may be passed down to future generations.
 * 
 * Attributes: Total attributes will add up to 100. Therefore a being can only increase
 *             one attribute by decreasing another.  I did this to see which attributes
 *             would be favored.
 * 
 *      Health - determines how far they can go without food and how long
 *               they can survive being hunted.
 *      Speed - determines how fast they can move through the world
 *      Sense - beings move randomly looking for food, if they have high
 *              spatial awareness they will tend to move towards food more often.
 *      Awareness - beings move randomly, if they have a high awareness
 *                  they will be more likely to avoid predators if they are prey
 *                  and will increase a predators effectiveness chasing prey.
 */
package biolifegenerations;

import java.util.Random;

/**
 *
 * @author Angel Preciado
 */
public class Being {
    protected int health, speed, sense, awareness;
    protected int x, y;
    protected String type;
    protected int score;
    
    public Being(){
        this.type = "empty";
    }
    
    public Being(String typ, int xx, int yy){
        this.type = typ;
        this.x = xx;
        this.y = yy;
    }
    
    public Being(int h, int s){
        this.health = h;
        this.speed = s;
    }
    
    public Being(int h, int s, int xx, int yy){
        this.health = h;
        this.speed = s;
        this.x = xx;
        this.y = yy;
    }
    
    public void assignAttributes(){
        double assign = Math.random();
        if(assign < .20){
            //avg
            this.health = 25;
            this.speed = 25;
            this.sense = 25;
            this.awareness = 25;
        } else if(assign < .40){
            //healthy
            this.health = 40;
            this.speed = 20;
            this.sense = 20;
            this.awareness = 20;
        } else if(assign < .60){
            //speedy
            this.health = 20;
            this.speed = 40;
            this.sense = 20;
            this.awareness = 20;
        } else if(assign < .80){
            //senses
            this.health = 20;
            this.speed = 20;
            this.sense = 40;
            this.awareness = 20;
        } else {
            //aware
            this.health = 20;
            this.speed = 20;
            this.sense = 20;
            this.awareness = 40;
        }
    }
    
    public void scoreUp(){
        this.score++;
    }
    
    public int getScore(){
        return this.score;
    }
    
    public void atePlant(){
        this.scoreUp();
        this.health += 2;
    }
    
    public void atePrey(){
        this.scoreUp();
        this.health += 1;
    }
    
    public void changeLocation(int xx, int yy){
        this.x = xx;
        this.y = yy;
    }
    
    public void healthUp(int i){
        this.health += i;
    }
    
    public void healthDown(int i){
        this.health -= i;
    }
    
    public void speedUp(int i){
        this.speed += i;
    }
    
    public void speedDown(int i){
        this.speed -= i;
    }
    ////////////////getters setters incoming
    public void setHealth(int d){
        this.health = d;
    }
    public int getHealth(){
        return this.health;
    }
    public void setSpeed(int d){
        this.speed = d;
    }
    public int getSpeed(int d){
        return this.speed;
    }
    public String getType(){
        return this.type;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public void setSense(int i){
        this.sense = i;
    }
    public int getSense(){
        return this.sense;
    }
    public void setAwareness(int i){
        this.awareness = i;
    }
    public int getAwareness(){
        return this.awareness;
    }
}
