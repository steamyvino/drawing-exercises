/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rthreadbottles;

import java.util.ArrayList;

/**
 *
 * @author Maciek
 */
public class Main {

   
    
    public static void main(String[] args) {
        
        Skrzynka skrzynka = new Skrzynka();
        
        MaszynaProdukujacaButelki maszyna1 = new MaszynaProdukujacaButelki(skrzynka);
        MaszynaZmieniajacaButelki maszyna2 = new MaszynaZmieniajacaButelki(skrzynka);
        
        Thread produkcja = new Thread(maszyna1,"Produkcja");
        Thread zmieniacz = new Thread(maszyna2,"Zmieniacz");
        
        produkcja.start();
        zmieniacz.start();
        
        
        
    }
    
}

class MaszynaProdukujacaButelki implements Runnable
{
    int i = 0;
    Skrzynka skrzynka = new Skrzynka();
    MaszynaProdukujacaButelki(Skrzynka skrzynka)
    {
    
        this.skrzynka=skrzynka;
    
    }

    @Override
    public void run() {
      
            synchronized(skrzynka)
            {
                System.out.println(Thread.currentThread().getName()+": Zaczynam produkowac butelki");
                while(true)
                {
                   
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException ex)
                    {
                    
                        System.out.println(ex.getMessage());
                        
                    }
                    while(skrzynka.jestPelna())
                    {
                        try
                        {
                            System.out.println(Thread.currentThread().getName()+": SKRZYNA PEŁNA");
                            skrzynka.wait();
                            System.out.println(Thread.currentThread().getName()+": Produkcja wznowiona");
                        }
                        catch(InterruptedException ex)
                        {
                            ex.printStackTrace();
                        }
                    }    
                    System.out.println(Thread.currentThread().getName()+": Wyprodukowano "+(++i)+" butelkę" );
                    skrzynka.dodaj(new Butelka());            
                    skrzynka.notify();
                }
            
            }
    }
    
   
}

class MaszynaZmieniajacaButelki implements Runnable
{

    Skrzynka skrzynka = new Skrzynka();
    MaszynaZmieniajacaButelki(Skrzynka skrzynka)
    {
    
        this.skrzynka=skrzynka;
    
    }

    @Override
    public void run() {
       
         synchronized(skrzynka)
            {
                System.out.println(Thread.currentThread().getName()+": Przygotowanie do zmiany skrzynki");
                 while(true)
                {
                    
                      try
                    {
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException ex)
                    {
                    
                        System.out.println(ex.getMessage());
                        
                    }
                    
                    while(!skrzynka.jestPelna())
                    {
                        try
                        {   
                            System.out.println(Thread.currentThread().getName()+": Koniec zamiany");
                            skrzynka.wait();
                            System.out.println(Thread.currentThread().getName()+": zamiana");
                            
                            
                        }
                        catch(InterruptedException ex)
                        {
                        
                            ex.printStackTrace();
                        
                        }
                    }
                    skrzynka.pobierzIloscButelek();
                    skrzynka.zamiana();
                    skrzynka.pobierzIloscButelek();
                    
                    skrzynka.notifyAll();
                
                }
            
            }
        
    }
    
}

class Skrzynka
{

    public synchronized boolean jestPelna()
    { 
        if(listaButelek.size()==POJEMNOSC)
            return true;
        
        return false;
    }
    
    public synchronized int pobierzIloscButelek()
    {
    
        System.out.println(Thread.currentThread().getName()+": Aktualnie w skrzynce jest "+listaButelek.size()+" butelek");
        return listaButelek.size();
    
    }
    
     public synchronized void dodaj(Butelka butelka)
    {
        listaButelek.add(butelka);
    }
     public synchronized void zamiana()
    {
        System.out.println(Thread.currentThread().getName()+": ZAMIANA BUTELEK");
        listaButelek.clear();
    }
    
     
    private final int POJEMNOSC = 10;
    private ArrayList listaButelek = new ArrayList(POJEMNOSC);

}

class Butelka
{

    
    
}