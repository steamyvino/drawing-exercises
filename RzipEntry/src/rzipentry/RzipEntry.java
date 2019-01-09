/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rzipentry;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.*;

 

public class RzipEntry {

    
    
    public final static int BUFFER=1024;
   
    void zip()
    {
    
        String[] tab = new String[]{"photo.jpg","test.txt","manifest.mf"};
        byte tmpData[] = new byte[BUFFER];
        
        try {
            ZipOutputStream zoutS = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream("myArchive.zip"),BUFFER));
            
            for(int i=0;i<tab.length;i++)
            {

                BufferedInputStream zInS = new BufferedInputStream(new FileInputStream(tab[i]),BUFFER);

                zoutS.putNextEntry(new ZipEntry(tab[i]));
                int counter;

                while((counter = zInS.read(tmpData, 0, BUFFER))!=-1)
                {


                    zoutS.write(tmpData, 0, counter);


                }
                zoutS.closeEntry();
                zInS.close();
            }
            zoutS.close();
        } catch (IOException ex) {
            
            System.out.println(ex.getMessage());
        }
    
    }
    
    static void unzip()
    {
        byte tmpData[] = new byte[BUFFER];
        File unpackedFolder = new File(System.getProperty("user.dir")+File.separator+"myArchive");
        unpackedFolder.mkdir();
        
        ZipEntry entry=null;
        
        
        try{
            
            ZipInputStream zInS = new ZipInputStream(new BufferedInputStream(new FileInputStream("myArchive.zip")));
            
            while((entry=zInS.getNextEntry())!=null)
            {
                BufferedOutputStream fOutS = new BufferedOutputStream(new FileOutputStream(unpackedFolder+File.separator+entry.getName()));
                
                int counter;
                
                while((counter=zInS.read(tmpData, 0, BUFFER))!=-1)
                {
                     fOutS.write(tmpData, 0, counter);
                }
                
                fOutS.close();
                zInS.closeEntry();
            }
            
            zInS.close();
        }
        catch(IOException ex)
        {
        
            System.out.println(ex.getMessage());
            
        }
        
        
    
    }
    
    public static void main(String[] args){
      
       RzipEntry.unzip();
        
        
        
    }
    
}
