package bottlemachine;

import java.util.ArrayList;


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
            
        }
    }
}

class BottleMachine implements Runnable
{
    
BottleCase bottleCase;
    
    BottleMachine(BottleCase bottleCase)
    {
    
        this.bottleCase=bottleCase;
    
    }
    
    @Override
    public void run() {
      
        synchronized(bottleCase)
        {
        
        }
        
    }
}

class BottleCase
{

    public int getBottleCount()
    {
        System.out.println(Thread.currentThread().getName()+ ": Bottle case has "+bottleCase.size()+" bottles");
        return bottleCase.size();
    }
    
    public boolean isFull()
    {
        if(bottleCase.size()==CASE_CAPACITY)
            return true;

        return false;
    }
    
    private final int CASE_CAPACITY = 10;
    ArrayList<Bottle> bottleCase = new ArrayList<Bottle>(CASE_CAPACITY);

}

class Bottle
{

}

