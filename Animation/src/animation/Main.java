/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


public class Main extends JFrame {

       
    private JPanel buttonPanel = new JPanel();
    private AnimationPanel animationPanel = new AnimationPanel();
    
    
    Main()
    {    
        this.setBounds(300,300,300,300);
        this.setDefaultCloseOperation(3);
        this.add(buttonPanel,BorderLayout.SOUTH);
        this.add(animationPanel);
        animationPanel.setBackground(Color.GRAY);
        JButton startButton = (JButton)buttonPanel.add(new JButton("Start"));
        ImageIcon btnIcon = new ImageIcon("pokeball.png");
        startButton.setIcon(btnIcon);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               startAnimation();
            }
        });
        JButton stopButton = (JButton)buttonPanel.add(new JButton("Stop"));
        stopButton.setIcon(btnIcon);
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               stopAnimation();
            }
        });
    
    }
    
    public void startAnimation()
    {
        
        animationPanel.addBall();
    
    }
    public void stopAnimation()
    {
        
       animationPanel.stop();
    
    }

    class AnimationPanel extends JPanel
    {
      
        ArrayList<Ball> ballList = new ArrayList<Ball> ();
        Thread animThread;
        ThreadGroup threadGroup = new ThreadGroup("Ball");
        
       
        
        public void addBall()
        {
            ballList.add(new Ball());
            animThread = new Thread(threadGroup,new BallThread(ballList.get(ballList.size()-1)));
            animThread.start();
            threadGroup.list();
        }
        
        public void stop()
        {
        
          threadGroup.interrupt();
        
        }
        
        public void paintComponent(Graphics g)
        {
           
           super.paintComponent(g);
           
           for(int i=0;i<ballList.size();i++)
           {
           
               g.drawImage(Ball.getImg(), ballList.get(i).x, ballList.get(i).y, rootPane);
           
           }
        
        }
      
        class BallThread implements Runnable
        {

            Ball ball;
            
            BallThread(Ball ball)
            {
             this.ball=ball;
            }
            
            @Override
            public void run() 
            {
  
                try 
                {    
                    while (!Thread.currentThread().isInterrupted())
                    {
                        repaint();

                           ball.moveBall(animationPanel);


                                Thread.sleep(5);
                    }
                }   
                catch (InterruptedException ex) 
                {
                    System.out.println(ex.getMessage());
                    ballList.clear();
                    repaint();
                   
                }
     
            }
        }
     

        
    }
    
    
    public static void main(String[] args) {
        new Main().setVisible(true);
        
    }


    
}
class Ball
{
    int x=0;
    int y=0;
    int dx=1;
    int dy=1;
    int ballWidth = ballImage.getWidth(null);
    int ballHeight = ballImage.getHeight(null);
    
    public void moveBall(JPanel container)
    {
    
        Rectangle panelBorder = new Rectangle(container.getBounds());
        double borderMaxX=panelBorder.getMaxX();
        double borderMinX=panelBorder.getMinX();
        double borderMaxY=panelBorder.getMaxY();
        double borderMinY=panelBorder.getMinY();
       
   
        x+=dx;
        y+=dy;
        
        if(x+ballWidth>=borderMaxX)
        {
            x = ((int)borderMaxX-ballWidth);
            dx=-dx;
        }
        if(y+ballHeight>=borderMaxY)
        {
            y=((int)borderMaxY-ballHeight);
            dy=-dy;
        }
        if(x<=borderMinX)
        {  
            x=(int)borderMinX;
            dx=-dx;
        }
        if(y<=borderMinY)
        {
            y=(int)borderMinY;
            dy=-dy;
        }
    }
    
    public static Image getImg()
    {
    
        return ballImage;
    
    }

    private static Image ballImage = new ImageIcon("pokeball.png").getImage();


}

