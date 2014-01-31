/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package biolifegenerations;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 *
 * @author Angel Preciado
 */
public class Environment extends JFrame {
    
    private JButton startButton, stopButton, exitButton;
    private int cornerX = 30, cornerY = 50, beingSize = 10;
    private Container container;
    private World world;
    private int days;
    private int mapSize;
    
    public Environment(World world){
        super();
        this.mapSize = world.getMapSize();
        this.days = 0;
        this.world = world;
        this.setSize(800, 800);
        this.setResizable(true);
        this.setTitle("Bio Life Generations");
        this.setVisible(true);
        this.setBackground(Color.gray);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.run();
    }
    
    public void run(){
        while(true){
            try {
                Thread.sleep(100);
            } catch (Exception e) {
            }
            world.incrementDays();
            world.runWorld();
            this.paintComponent(this.getGraphics());
            days++;
            System.out.println(days);
            //System.out.println(world.calculateAvgFoodEatenByPrey());
            //System.out.println(world.getTotalPlantsEaten());
        }
    }
    
    public int convertXCoord(int x){
        if(x == 0){
            return this.cornerX+1+(beingSize*x);
        }
        return this.cornerX+(beingSize*x);
    }
    public int convertYCoord(int y){
        return this.cornerY+(beingSize*y);
    }
    
    public void setUpGUI(){
    }
    
    @Override
    public void paint(Graphics g){
    g.setColor(Color.white);
    g.fillRect(30, 50, mapSize*beingSize, mapSize*beingSize);
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                 Being temp = world.getBeing(i, j);
                 String type = temp.getType();
                 if(type.equalsIgnoreCase("food")){
                     g.setColor(Color.green);
                     g.fill3DRect(this.convertXCoord(i), this.convertYCoord(j), beingSize, beingSize, true);
                     //g.fillOval(this.convertXCoord(i), this.convertYCoord(j), beingSize, beingSize);
                 } else if(type.equalsIgnoreCase("predator")){
                     g.setColor(Color.red);
                     g.fillRect(this.convertXCoord(i), this.convertYCoord(j), beingSize-1, beingSize-1);
                 } else if(type.equalsIgnoreCase("prey")){
                     g.setColor(Color.cyan);
                     g.fill3DRect(this.convertXCoord(i), this.convertYCoord(j), beingSize-1, beingSize-1, true);
                     //g.fillOval(this.convertXCoord(i), this.convertYCoord(j), beingSize, beingSize);
                 }
            }
    }
    g.setColor(Color.black);
    g.drawRect(30, 50, mapSize*beingSize, mapSize*beingSize);
    //TEST BLOCK
  }
    protected void paintComponent(Graphics g) 
{
    BufferedImage bufferedImage = new BufferedImage(cornerX+mapSize*beingSize, cornerY+mapSize*beingSize, BufferedImage.TYPE_4BYTE_ABGR);
    Graphics2D g2d = bufferedImage.createGraphics();
    //paint using g2d ...////////////////
    
    g2d.setColor(Color.white);
    g2d.fillRect(30, 50, mapSize*beingSize, mapSize*beingSize);
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                 Being temp = world.getBeing(i, j);
                 String type = temp.getType();
                 if(type.equalsIgnoreCase("food")){
                     g2d.setColor(Color.green);
                     g2d.fill3DRect(this.convertXCoord(i), this.convertYCoord(j), beingSize, beingSize, true);
                     //g.fillOval(this.convertXCoord(i), this.convertYCoord(j), beingSize, beingSize);
                 } else if(type.equalsIgnoreCase("predator")){
                     g2d.setColor(Color.red);
                     g2d.fillRect(this.convertXCoord(i), this.convertYCoord(j), beingSize-1, beingSize-1);
                 } else if(type.equalsIgnoreCase("prey")){
                     g2d.setColor(Color.cyan);
                     g2d.fill3DRect(this.convertXCoord(i), this.convertYCoord(j), beingSize-1, beingSize-1, true);
                     //g.fillOval(this.convertXCoord(i), this.convertYCoord(j), beingSize, beingSize);
                 }
            }
    }
    g2d.setColor(Color.black);
    g2d.drawRect(30, 50, mapSize*beingSize, mapSize*beingSize);
    /////////////////////////////////////

    Graphics2D g2dComponent = (Graphics2D) g;
    g2dComponent.drawImage(bufferedImage, null, 0, 0);  
}
}
