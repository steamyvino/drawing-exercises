/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Main {

  
    public static void main(String[] args) throws InterruptedException {
      
      
      //  Display display = new Display();
       // Object lock = new Object();
        Lock lock = new ReentrantLock();
      
        Thread thread1 = new Thread(new Display(lock),"thread1");
        Thread thread2 = new Thread(new Display(lock),"thread2");
        
        thread1.start();
        //thread1.join();
        thread2.start();
       // thread2.join();
        
        Counter counter =new Counter();
        
        Thread thread3 = new Thread(new CounterRunnable(counter,false),"Increase");
        Thread thread4 = new Thread(new CounterRunnable(counter,true),"Decrease");
        
//        thread3.start();
//        thread3.join();
//        System.out.println("TEST");
//        thread4.start();
    }
 
}

class Display implements Runnable
{
    
    Lock lock;
    
    Display(Lock lock)
    {
    
        this.lock=lock;
    
    }

    String msg[] = {"This","is","test","of","threads"};
    
    @Override
    public void run() {
       display(Thread.currentThread().getName());
    }


     void display(String threadName)
    {
    
            lock.lock();
            try{
            
                for(int i=0;i<msg.length;i++)
                {

                    System.out.println(threadName+": "+msg[i]);

                    try
                    {
                        Thread.sleep(10);

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

    static int number = 50;
    
    public void increase()
    {
        number++;
    
        System.out.println(Thread.currentThread()+": "+number);
    
    }
    
    public void decrease()
    {
    
        number--;
        System.out.println(Thread.currentThread()+": "+number);
    
    }


}

class CounterRunnable implements Runnable
{
    boolean increase;
    Counter counter;

    CounterRunnable(Counter counter, boolean increase)
    {
    
        this.counter=counter;
        this.increase=increase;
    
    }

    @Override
    public void run() {
       
        synchronized(counter)
        {
            for(int i=0; i<49;i++)
            {

                if(increase)
                    counter.increase();
                else
                    counter.decrease();
                try
                {

                    Thread.sleep(50);

                }
                catch(InterruptedException ex)
                {

                    System.out.println(ex.getMessage());

                }
            }
        }
    }
    
    
    
}