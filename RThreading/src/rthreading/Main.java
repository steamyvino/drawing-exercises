/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rthreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Maciek
 */
public class Main {

   
    public static void main(String[] args) {
     
        Lock lock = new ReentrantLock();
       // WypisanieRunnable wypisanie = new WypisanieRunnable();
        Thread watek1 = new Thread(new WypisanieRunnable(lock),"watek 1");
        Thread watek2 = new Thread(new WypisanieRunnable(lock),"watek 2");
        
        watek1.start();
        watek2.start();

//          Counter licznik=new Counter();
//          Thread watek3 = new Thread(new CounterRunnable(licznik,true),"rosnie");
//          Thread watek4 = new Thread(new CounterRunnable(licznik,false),"maleje");
//          
//          watek3.start();
//          watek4.start();

    }
    
}


class WypisanieRunnable implements Runnable
{
    
    Lock lock;
    WypisanieRunnable(Lock lock)
    {
    
        this.lock = lock;
    
    }

    String[] msg = new String[]{"To","jest","synchronicznie","wyslana","wiadomosc"};
    
    @Override
    public void run() {
        
        display(Thread.currentThread().getName());
        
    }
    
    public void display(String threadName)
    {
    
        lock.lock();
        try{
           
                for(int i=0;i<msg.length;i++)
                {

                    System.out.println(threadName+": "+msg[i]);
                    try
                    {

                        Thread.sleep(100);

                    }
                     catch(InterruptedException ex)
                    {
                        System.out.println(ex.getMessage());

                    }

                }
            
        }
        finally
        {
            lock.unlock();
        }

    }


}


class Counter
{

    int counter =50;
    
    void increaseCounter()
    {
    
        counter++;
        
    }
        
    void decreaseCounter()
    {
    
        counter--;
        
    }

}

class CounterRunnable implements Runnable
{

    Counter licznik;
    boolean increase;
    
    CounterRunnable(Counter licznik,boolean increase)
    {
    
        this.licznik=licznik;
        this.increase=increase;
        
    }
    
    public void run() {
        
        synchronized(licznik)
        {
            for(int i=0;i<50;i++)
            {
                if(increase)    
                    licznik.increaseCounter();
                else
                    licznik.decreaseCounter();

                 System.out.println(Thread.currentThread().getName()+" "+licznik.counter);

                 try
                 {
                     Thread.sleep(100);
                 }
                 catch(InterruptedException ex)
                 {
                     System.out.println(ex.getMessage());
                 }

            }
        }
        
        
        
    }


}