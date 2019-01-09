/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author Maciek
 */
public class Pong implements ActionListener {
    
    
    Timer tm = new Timer(1,this);
    JFrame pongFrame = new JFrame();
    RenderPanel renderPanel = new RenderPanel();
    
    Pong()
    {
        
        pongFrame.setBounds(300,300,600,600);
        pongFrame.setDefaultCloseOperation(3);
        pongFrame.getContentPane().add(renderPanel);
        pongFrame.setVisible(true);
        pongFrame.addKeyListener(new Controls());
        startGame();
        
    
    }
    
    
    void startGame()
    {
    
        renderPanel.ball.firstMove();
        tm.start();
    
    }
    
    public static void main(String[] args) {
        new Pong();
    }

    int ticks=0;
    @Override
    public void actionPerformed(ActionEvent ae) {
        ticks++;
        System.out.println("YE");
        checkCollision();
        renderPanel.paddle.move();
        
        if(ticks%2==0)
        {
            renderPanel.ball.moveX();
           
        }
        
        if(ticks%8==0)
            renderPanel.ball.moveY();
        
        
        renderPanel.repaint();
            
            
    }
    
    void checkCollision()
    {
    
       if(renderPanel.ballRectangle.intersects(renderPanel.paddleRectangle))
            System.out.println("YESSS");
       
    
    }
    
    
    class Controls extends KeyAdapter
    {
      
        public void keyPressed(KeyEvent ke)
        {
            if(ke.getKeyCode()==KeyEvent.VK_UP)
            { 
                renderPanel.paddle.up=true;
             
                
            }   
            if(ke.getKeyCode()==KeyEvent.VK_DOWN)
            {
              
                renderPanel.paddle.down=true;
            
            }
        }
        
        @Override
        public void keyReleased(KeyEvent ke)
        {
                renderPanel.paddle.up=false;
                renderPanel.paddle.down=false;
            
        
        }
        
    }
    
}



class Paddle
{
    boolean up=false;
    boolean down=false;
    int x=560;
    int y=300;
    double dy=1;
    
    
    void move()
    {
    
        if(up)
        {
            moveUp();      
        }
        if(down)
        {
            moveDown();      
        }
    
    }
    
    void moveUp()
    {
    
       y-=dy;
        
        
    }
    
    void moveDown()
    {
    
       y+=dy;
     
    }
}


class Ball extends Point
{
   Random rnd = new Random(); 
   double dx;
   double dy;

    Ball()
    {
        this.x=300;
        this.y=300;   
    }

   void firstMove()
   {
       dx=1;
       dy=1;

   }
   
   void moveX()
   {

       x+=dx;
      
   
   }
   
   void moveY()
   {

      
       y+=dy;
   
   }
        
   void bounce()
   {
   
       dx*=-1;
   
   }
}