/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.*;


/**
 *
 * @author Maciek
 */
public class RenderPanel extends JPanel {

    Ball ball=new Ball();
    Paddle paddle = new Paddle();
    Rectangle ballRectangle;
    Rectangle paddleRectangle;
   
    
    RenderPanel()
    {
        this.setBackground(Color.BLACK);
    }
    @Override
    
    
    protected void paintComponent(Graphics g) {
    
        ballRectangle=new Rectangle(ball.x,ball.y,10,10);
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(ballRectangle.x, ballRectangle.y, 10, 10);
        
        /*
        PADDLE
        */
        
        drawPaddle(g);
        
        
        
    }
    
   
    protected void drawPaddle(Graphics g) {
        paddleRectangle=new Rectangle(paddle.x,paddle.y,10,10);
        g.fillRect(paddleRectangle.x, paddleRectangle.y, 10, 80);

    }
    
    
    
   
    
}
