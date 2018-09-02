/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;


/**
 *
 * @author Maciek
 */
 class GameFrame extends JFrame {
    
    GameFrame()
    {
    
        super("snake");
        this.setBounds(300,300,300,300);
        this.setDefaultCloseOperation(3);
        this.setResizable(false);
        initComponents();
    
    }
    
    JButton startButton;
    GameFrame mainPanel;
    SnakePanel snakePanel;
    void initComponents()
    {
    
      mainPanel=this;
      startButton  = new JButton("Start");
      this.getContentPane().add(startButton);
      
      startButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent ae) {
              mainPanel.remove(startButton);
            
              startGame();
              revalidate();
              repaint();
          }
      });
    
    
    }
    
    
   void startGame()
   {
       snakePanel = new SnakePanel();
       this.getContentPane().add(snakePanel);
       snakePanel.requestFocus();
       
   
   }
    
    
}

class SnakePanel extends JPanel
{
    
    Snake snake = new Snake();
    
    SnakePanel()
    {
        this.setBackground(Color.GRAY);
        this.addKeyListener(new Controls());
  
    }

    @Override
    public void paint(Graphics g)
        {
            
           super.paintComponent(g);
            System.out.println("REPAINT");
           g.drawImage(snake.getImage(), snake.x, snake.y, this);
         
        
        }

    class Controls extends KeyAdapter
    {

        Controls()
        {
        
            System.out.println("ControlADDED");
            
        }
        @Override
        public void keyPressed(KeyEvent ke) {

          int key = ke.getKeyCode();
 
            if (key == KeyEvent.VK_RIGHT) 
            {
              
                System.out.println("MOVED");
                snake.right=true;
                snake.move();


            }
            if (key == KeyEvent.VK_LEFT) 
            {
               
                System.out.println("MOVED");
                snake.right=false;
                snake.move();


            }


        }

    }

    class Snake 
{

    boolean right = false;
    private Image ballImage = new ImageIcon("pokeball.png").getImage();
    int x=0;
    int y=0;
    int moveX=5;
    int moveY=5;
    
    Image getImage()
    {
    
        return ballImage;
    
    }

    void move()
    {
            System.out.println("move snake");
            x+=moveX;
            System.out.println(x);
            repaint();

    }

}
    
}






