/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordscanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maciek
 */
public class Main {

   
    public static void main(String[] args) {
       
        BlockingQueue<File> kolejkaBlokujaca = new ArrayBlockingQueue<File>(5);
        new Thread(new PoszukiwaczSciezek(kolejkaBlokujaca, sciezkaGlowna)).start();
        
        for (int i=0; i<50;i++)
        {
            new Thread(new kolejka(kolejkaBlokujaca,slowoSzukane)).start();
           
        }

    }
    
      final static private File sciezkaGlowna = new File(System.getProperty("user.dir"));
      final static private String slowoSzukane = new String("Vino");
    
      
}

class PoszukiwaczSciezek implements Runnable
{
    BlockingQueue<File> kolejka;
    File sciezkaGlowna;
    
    
    PoszukiwaczSciezek(BlockingQueue<File> kolejka,File sciezkaGlowna)
    {
        this.sciezkaGlowna=sciezkaGlowna;
        this.kolejka=kolejka;
    }

    @Override
    public void run() {
         
        try {
            szukajSciezek(sciezkaGlowna);
            kolejka.put(new File("pusty"));
        } 
        catch (InterruptedException ex) 
        {
           ex.printStackTrace();
        }
    }
    
    public void szukajSciezek(File sciezka) throws InterruptedException
    {
        File[] sciezki = sciezka.listFiles();
        
        for(int i=0;i<sciezki.length;i++)
            if(sciezki[i].isDirectory())
                szukajSciezek(sciezki[i]);
            else
               
                    kolejka.put(sciezki[i]);
           
        
    }

}

class kolejka implements Runnable
{

    BlockingQueue<File> kolejka;
    String szukaneSlowo;
    
    kolejka(BlockingQueue<File> kolejka, String szukaneSlowo)
    {
    
        this.kolejka=kolejka;
        this.szukaneSlowo=szukaneSlowo;
        
    }
    
    @Override
    public void run() {
      
        boolean skonczone = false;
        
        while(!skonczone)
        {
           
            try {
                File przeszukiwanyPlik=kolejka.take();
                if(przeszukiwanyPlik.equals(new File("pusty")))
                {
                    kolejka.put(przeszukiwanyPlik);
                    skonczone=true;
                
                }
                else
              
                    szukajSlowa(przeszukiwanyPlik);

            } 
            catch (Exception ex) {
                ex.printStackTrace();
            }
            
            
        }
        
    }

    public void szukajSlowa(File searchedFile) throws FileNotFoundException
    {
        Scanner reader = new Scanner(new BufferedReader(new FileReader(searchedFile)));
        
        int lineNumber = 0;
        
        while(reader.hasNextLine())
        {
            lineNumber++;
            if(reader.nextLine().contains(szukaneSlowo))
                 
                System.out.println(szukaneSlowo+" found in "+ searchedFile.getPath()+" line " + lineNumber);
            
            
        }
      
        reader.close();
    }

}