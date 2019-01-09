/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filerepetition;


import java.io.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maciek
 */
public class FileRepetition {
        

    public static void main(String[] args) throws IOException {
    
       PrintWriter drukarz = new PrintWriter(new FileWriter("drukarka.txt"));
       
       drukarz.print("lalala");
       
       drukarz.close();
       
       drukarz = new PrintWriter(new FileWriter("drukarka.txt",true));
       
       drukarz.println();
       drukarz.append("1234");
       drukarz.println();
       drukarz.printf("on ma %d kg i %.2f %s wzrostu",50,167.2,"cm");
       
       drukarz.close();
       
       BufferedReader reader = new BufferedReader(new FileReader("drukarka.txt"));
       BufferedWriter writer = new BufferedWriter(new FileWriter("nowy.txt"));
        
       
       String tresc="";
       while((tresc=reader.readLine())!=null)
       {  
           writer.write(tresc);
           writer.newLine();
       }
       writer.close();
       reader.close();
       
    
       
       
           
       
    }
    
  
    
}
