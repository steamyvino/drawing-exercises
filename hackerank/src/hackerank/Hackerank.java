package hackerank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Hackerank {



  

    public static void main(String []argh){
        Scanner in = new Scanner(System.in);
        int t=in.nextInt();
        for(int i=0;i<t;i++){
            try
            {
            long a = in.nextLong();
            
            System.out.println(a+" can be fitted in:");  
            if(a<=Byte.MAX_VALUE&&a>=Byte.MIN_VALUE)
                  System.out.println("* byte");   
            if(a<=Short.MAX_VALUE&&a>=Short.MIN_VALUE)
                System.out.println("* short");   
            if(a<=Integer.MAX_VALUE&&a>=Integer.MIN_VALUE)
                 System.out.println("* integer");   
            if(a<=Long.MAX_VALUE&&a>=Long.MIN_VALUE)
                System.out.println("* long");   
            }
            catch(Exception e)
            {
            
                System.out.println(in.next()+" can't be fitted anywhere.");
            
            }
        } 
        in.close();
       
    }
}
    
