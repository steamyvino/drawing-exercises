/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package priority;

/**
 *
 * @author Maciek
 */
public class Main {

    
    public static void main(String[] args) {
    
        System.out.println(Thread.currentThread().getPriority());
        Thread thread1 = new Thread(new Thread1(),"small priority");
        Thread thread2 = new Thread(new Thread2(),"big priority");
        
        thread1.setPriority(1);
        thread1.start();
        thread2.start();
        
        
    }
    
   
    
}

class Thread1 implements Runnable
    {

        @Override
        public void run() {
           System.out.println(Thread.currentThread().getName()+" "+Thread.currentThread().getPriority());
        }
    
    
    }
    class Thread2 implements Runnable
    {

        @Override
        public void run() {
           System.out.println(Thread.currentThread().getName()+" "+Thread.currentThread().getPriority());
        }
    
    
    }