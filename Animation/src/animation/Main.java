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
    
    }
    
    public void startAnimation()
    {
        
        animationPanel.addBall();
    
    }

    class AnimationPanel extends JPanel
    {
      
        ArrayList<Ball> ballList = new ArrayList<Ball> ();
        
        public void addBall()
        {
        
            ballList.add(new Ball());
            for(int i=0;i<41;i++)
            {
            
               ballList.get(0).x++;
               ballList.get(0).y++;
               this.paint(this.getGraphics());
                try 
                {
                    Thread.sleep(100);
                } 
                catch (InterruptedException ex) 
                {
                    System.out.println(ex.getMessage());
                }
            
            }
            
        
        }
        public void paintComponent(Graphics g)
        {
            
           super.paintComponent(g);
           
           for(int i=0;i<ballList.size();i++)
           {
           
               g.drawImage(Ball.getImg(), ballList.get(0).x, ballList.get(0).y, rootPane);
           
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
    
    
    public static Image getImg()
    {
    
        return ballImage;
    
    }

    private static Image ballImage = new ImageIcon("pokeball.png").getImage();


}

