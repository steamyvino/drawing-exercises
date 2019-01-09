/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdrawing;

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


/**
 *
 * @author Maciek
 */
public class Main extends JFrame{

    
    Main()
    {
    
        this.setDefaultCloseOperation(3);
        this.setBounds(300,300,350,300);
        
        initComponents();
        
    }
    
    void initComponents()
    {
        AnimationPanel animationPanel = new AnimationPanel();
        JPanel buttonPanel = new JPanel();
        this.getContentPane().add(animationPanel);
        this.getContentPane().add(buttonPanel,BorderLayout.SOUTH);
        
        JButton startButton = new JButton("Start");
                
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                animationPanel.start();
            }
        });
        
        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               animationPanel.stop();
            }
        });
        
         JButton dodajButton = new JButton("Dodaj");
        dodajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
              
                animationPanel.dodaj();
                
            }
        });
        JButton przyspieszButton = new JButton("+");
        przyspieszButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
              
                animationPanel.przyspiesz();
                
            }
        });
        JButton zwolnijButton = new JButton("-");
        zwolnijButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
              
                animationPanel.zwolnij();
                
            }
        });
                
        
        
        
        
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(dodajButton);
        buttonPanel.add(przyspieszButton);
        buttonPanel.add(zwolnijButton);
        animationPanel.setBackground(Color.yellow);
    }
    
    
    
   
    
    
    public static void main(String[] args) {
     
        new Main().setVisible(true);
       
    }
    
}
  
class AnimationPanel extends JPanel
{
    private int speed=10;
    private volatile boolean zatrzymany=false;
    private Object lock = new Object();
    ArrayList kropelki = new ArrayList();
    JPanel ten = this;
    Thread watek;
    ThreadGroup grupaWatkow = new ThreadGroup("Grupa kropelek");
    
    void stop()
    {
      
        
        zatrzymany=true;
        
    }
    
    void start()
    {
      
        if(zatrzymany)
        {
            zatrzymany=false;
            synchronized(lock)
            {
                lock.notifyAll();
            }
        }

    }
    
    void dodaj()
    {
        
      kropelki.add(new Kropelka());
      watek = new Thread(grupaWatkow,new KropelkaRunnable(((Kropelka)kropelki.get(kropelki.size()-1))));  
      watek.start();
      grupaWatkow.list();
    
    }
    
    void przyspiesz()
    {
    
        if(speed-1>0)
            speed-=1;
        System.out.println("przyspieszam");
        System.out.println("speed "+speed);
    
       
    
    }
    
     void zwolnij()
    {
            
        speed+=1;
        System.out.println("zwalniam");
        System.out.println("speed "+speed);
        
    }
    
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for(int i=0;i<kropelki.size();i++)
        {
            g.drawImage(((Kropelka)kropelki.get(i)).getImg(), ((Kropelka)kropelki.get(i)).x, ((Kropelka)kropelki.get(i)).y, null);
        
        }
    }
    
  
    
    class KropelkaRunnable implements Runnable
    {
        Kropelka kropelka;
        
        KropelkaRunnable(Kropelka kropelka)
        {
        
            this.kropelka = kropelka;
        
        }

        @Override
        public void run() {
            
                 
                while(true)
                {
                        synchronized(lock)
                        {
                            while(zatrzymany)
                               
                                try
                                {
                                    lock.wait();
                                }
                                catch(InterruptedException ex)
                                {
                                    ex.printStackTrace();
                                }
                                
                        }
                        kropelka.ruszKropelka(ten);
                       
                        repaint();   
                        try
                        {
                            
                            Thread.sleep(speed);
                        }
                        catch(InterruptedException ex)
                        {
                            ex.printStackTrace();
                        }
                }
                
                
                
                
        }
            
     }
    
        
        
    }
    


class Kropelka
{

    int x=0;
    int y=0;
    int dx=1;
    int dy=1;
    int xkropelki = kropelkaImg.getWidth(null);
    int ykropelki = kropelkaImg.getHeight(null);
    
    Image getImg()
    {
        
        return this.kropelkaImg;
    
    }
    
    void ruszKropelka(JPanel panel)
    {
        Rectangle borders = panel.getBounds();
        
        
        x+=dx;
        y+=dy;
        
        if(y+ykropelki>=borders.getMaxY())
        {
             y= (int)(borders.getMaxY()-ykropelki);
             dy=-dy;
               
        }
        if(x+xkropelki>=borders.getMaxX())
        {
             x= (int)(borders.getMaxX()-xkropelki);
             dx=-dx;    
        }
        if(y<=borders.getMinY())
        {        
             dy=-dy;   
        }
        if(x<=borders.getMinX())
        {     
             dx=-dx;         
        }
       
         
          
    }
    
    public static Image kropelkaImg = new ImageIcon("pokeball.png").getImage();

}