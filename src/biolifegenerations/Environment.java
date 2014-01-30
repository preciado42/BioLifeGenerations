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
    
    public Environment(){
        super();
        this.setSize(600, 600);
        this.setResizable(false);
        this.setTitle("Bio Life Generations");
        this.setVisible(true);
        this.setBackground(Color.gray);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.run();
    }
    
    public void run(){
        world = new World();
        while(true){
            try {
                Thread.sleep(500);
            } catch (Exception e) {
            }
            world.runWorld();
            this.paint(this.getGraphics());
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
    g.fillRect(30, 50, 400, 400);
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 40; j++) {
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
    g.drawRect(30, 50, 400, 400);
    //TEST BLOCK
  }
    protected void paintComponent(Graphics g) 
{
    BufferedImage bufferedImage = new BufferedImage(500, 500, BufferedImage.TYPE_4BYTE_ABGR);
    Graphics2D g2d = bufferedImage.createGraphics();
    //paint using g2d ...

    Graphics2D g2dComponent = (Graphics2D) g;
    g2dComponent.drawImage(bufferedImage, null, 0, 0);  
}
}