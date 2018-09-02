/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawingtimer;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Maciek
 */
public class Snake implements ActionListener {
    
    public static Snake snake;
    Apple apple;
    JFrame jFrame = new JFrame("Window");
    RenderPanel renderPanel = new RenderPanel();
    Timer timer = new Timer(85,this);
    
   
    Snake(){
      
        snake=this;
        jFrame.add(renderPanel);
        jFrame.setBounds(300,300,300,300);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setVisible(true);
        jFrame.addKeyListener(new Controls());
        renderPanel.repaint();
        startGame();
        
       
    }

    
    public static void main(String[] args) {
       new Snake();
    }

    ArrayList<Point> snakeParts = new ArrayList<Point>();
    Point head;
    int ticks=0,direction;
    final int UP=0,DOWN=1,LEFT=2,RIGHT=3;
    
    void startGame()
    {
        direction=1;
        head= new Point(10,10);
        timer.start();
        snakeParts.add(new Point(head.x,head.y));
        snakeParts.add(new Point(head.x,head.y));
        snakeParts.add(new Point(head.x,head.y));
        apple=new Apple();
    
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
       ticks++;
       snakeParts.remove(0); 
       checkApple();
       snakeParts.add(new Point(head.x,head.y));
       renderPanel.repaint();
       CheckColision();
       
       scored=false;
  
       if(direction==RIGHT)
       {
          head.x+=10; 
       }
       if(direction==LEFT)
       {
          head.x-=10; 
       }
       if(direction==UP)
       {
          head.y-=10;  
       }
       if(direction==DOWN)
       {
          head.y+=10;
       }
       
    }
    
 
    boolean scored=false;
    class Controls extends KeyAdapter
    {
      
        public void keyPressed(KeyEvent ke) {
            if(ke.getKeyCode()==KeyEvent.VK_UP)
                direction=0;
            if(ke.getKeyCode()==KeyEvent.VK_DOWN)
                direction=1;
            if(ke.getKeyCode()==KeyEvent.VK_LEFT)
                direction=2;
            if(ke.getKeyCode()==KeyEvent.VK_RIGHT)
                direction=3;
            if(ke.getKeyCode()==KeyEvent.VK_ENTER)
            {
                snakeParts.add(new Point(head.x,head.y));
                scored=true;             
            }
        }
        
    }
    
    void checkApple()
    {
          Point headf = snakeParts.get(snakeParts.size()-1);

          System.out.println("apple x= " + apple.x+" apple y= "+ apple.y);
          System.out.println("head x= " + headf.x+" head y= "+ headf.y);
           if (headf.equals(new Point(apple.x,apple.y)))
           {
               snakeParts.add(new Point(head.x,head.y));
               scored=true;
               apple.Eat();
           }    
 
    }
    
    
    
    void gameOver()
    {
    
        timer.stop();
        int choice = JOptionPane.showConfirmDialog(jFrame,"Again?", "Game Over", JOptionPane.YES_NO_OPTION);
        if(choice==0)
        {
        
            snakeParts.clear();
            startGame();
        
        }
        
    
    }
    
    void CheckColision()
    {
    
        Point headf = snakeParts.get(snakeParts.size()-1);
        
        for(int i=0; i<snakeParts.size()-1;i++)
        {
        
           if (headf.equals(snakeParts.get(i))&&ticks>1&&scored==false)
                gameOver();
    
  
        }
        if(head.x>=280||head.x<=-10||head.y>=260||head.y<=-10)
        {
                gameOver();
          
        }
       
    
    }
    
}

class Apple 
{
    Random rand = new Random();
    Apple()
    {
        
        x=rand.nextInt(27)*10;
        y=rand.nextInt(25)*10;
        
    
    }
    int x;
    int y;
    static boolean eaten=false;
    
    void Eat()
    {
        
        x=rand.nextInt(27)*10;
        y=rand.nextInt(25)*10;
        playSound();
    
    }

    void playSound()
    {
    
        String soundName = "point.wav";    
        AudioInputStream audioInputStream;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            try {
                Thread.sleep(clip.getMicrosecondLength()/100000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Snake.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("played");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    
}