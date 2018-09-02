/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawingtimer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;
import javax.swing.*;



public class RenderPanel extends JPanel {

    
   
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        
        Snake snake = Snake.snake;
        
        g.setColor(Color.gray);
        g.fillRect(0, 0, 300, 300);
        
        g.setColor(Color.GREEN);
        g.fillRect(snake.apple.x, snake.apple.y, 10, 10);
        
        for(Point point :snake.snakeParts)
        {
            g.setColor(Color.RED);
            g.fillRect(point.x, point.y, 10, 10);
        
        }
        
       
        
       
 
    }
   
   
    
}
