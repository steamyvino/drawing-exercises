package bottlemachine;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {

  
    public static void main(String[] args) {
        
        BottleCase bottleCase = new BottleCase();
        BottleMachine machine1 = new BottleMachine(bottleCase);
        ChangingCaseMachine machine2 = new ChangingCaseMachine(bottleCase);
        
        Thread production = new Thread(machine1,"production");
        Thread changing = new Thread(machine2,"changing");
        
        production.start();
        changing.start();
        
        
    }
    
}

class ChangingCaseMachine implements Runnable
{
    
BottleCase bottleCase;
    
    ChangingCaseMachine(BottleCase bottleCase)
    {
    
        this.bottleCase=bottleCase;
    
    }
    
    @Override
    public void run() {
      
        synchronized(bottleCase)
        {
           System.out.println(Thread.currentThread().getName()+ " Ready to change case");
           
            
            while(true)
            {
                while(!bottleCase.isFull())
                {
                
                    try 
                    {
                        System.out.println(Thread.currentThread().getName()+ " Case is switched");
                        bottleCase.wait();
                        System.out.println(Thread.currentThread().getName()+ " Starting to switch");
                    } catch (InterruptedException ex) {
                       ex.printStackTrace();
                    }
                    
                }
                bottleCase.getBottleCount();
                bottleCase.changeCase();
                bottleCase.getBottleCount();
                bottleCase.notifyAll();
                
            }
        }
    }
}

class BottleMachine implements Runnable
{
    
private BottleCase bottleCase;
private int i=0;
    
    BottleMachine(BottleCase bottleCase)
    {
    
        this.bottleCase=bottleCase;
    
    }
    
    @Override
    public void run() {
      
        synchronized(bottleCase)
        {
            System.out.println(Thread.currentThread().getName()+ " Starting to produce bottles");
            while(true)
            {
               
                while(bottleCase.isFull())
                {
                    try
                    {
                        System.out.println(Thread.currentThread().getName()+ " BottleCase is full");
                        bottleCase.wait();                      
                        i=0;
                        System.out.println(Thread.currentThread().getName()+ "Back to production");
                    } catch (InterruptedException ex)
                    {
                        ex.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName()+ " Adding Bottle "+(++i));
                bottleCase.addBottle(new Bottle());
                bottleCase.notifyAll();
             }
 
        }
        
    }
}

class BottleCase
{
    private final int CASE_CAPACITY = 10;
    ArrayList<Bottle> bottleCase = new ArrayList<Bottle>(CASE_CAPACITY);

    public synchronized int getBottleCount()
    {
        System.out.println(Thread.currentThread().getName()+ ": Bottle case has "+bottleCase.size()+" bottles");
        return bottleCase.size();
    }
    
    public synchronized boolean isFull()
    {
        if(bottleCase.size()==CASE_CAPACITY)
            return true;

        return false;
    }
    
    public synchronized void addBottle(Bottle bottle)
    {
    
        bottleCase.add(bottle);
    
    }
    
    public synchronized void changeCase()
    {
         System.out.println(Thread.currentThread().getName()+ ": Switching Cases");
         bottleCase.clear();
       
    
    }
    
    
  

}

class Bottle
{

}

