package drawing1;

import java.awt.Graphics;
import javax.swing.*;


public class Main extends JFrame {
private Painter painter = new Painter();
           
   Main()
   {
       
       this.setTitle("Drawing");
       this.setBounds(300,300,300,300);
       this.setDefaultCloseOperation(3);
       this.getContentPane().add(painter);
       
   
   
   }
    
    public static void main(String[] args) {
       
        new Main().setVisible(true);
        
    }
    
}

class Painter extends JPanel
{

    
    
    public static int i=0;
    
    public Painter()
    {
        this.setName("Panel");
        this.add(new JButton("test")
        { 
            @Override
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
               // System.out.println(i++);
                
            }
        
        
        });
        
    }
    
    @Override
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.drawString("Test", 40, 40);
                g.drawRect(60, 60, 10, 20);
                g.drawLine(80,100, 23, 65);
                g.drawImage(new ImageIcon("empire.png").getImage(), 100, 110, null);
            }
}