/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rserializacja;

import java.io.*;


class Towar implements Serializable
{

    Towar()
    {
    
        this.cena=9;
        this.nazwa="default name";
    
    }
    
    Towar(int cena,String nazwa)
    {
    
        this.cena=cena;
        this.nazwa=nazwa;
    
    }
    
    int getCena()
    {
        return cena;
    }
    
    String getNazwa()
    {
        
        return nazwa;
        
    }
    
    void setCena()
    {
        
        this.cena=cena;
         
    }
    
     void setNazwa()
    {
        
        this.nazwa=nazwa;
         
    }
    private int cena;
    private String nazwa;
    
    


}


public class Rserializacja {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        Towar[] towary = new Towar[3];
        
        towary[0] = new Towar();
        towary[1] = new Towar(5,"jablko");
        towary[2] = new Towar(3,"chlep");
        
   
        
       
        try{
            ObjectOutputStream outS = new ObjectOutputStream(new FileOutputStream("towary.txt"));
            
            outS.writeObject(towary);
            
            outS.close();
            
            ObjectInputStream inS = new ObjectInputStream(new FileInputStream("towary.txt"));
            
            Towar[] towaryNowe = (Towar[])inS.readObject();
            
            System.out.println(towaryNowe.length);
            System.out.println(towaryNowe[2].getNazwa());
            
            inS.close();
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }  
        catch(ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        
        
        
        
    }
    
}
