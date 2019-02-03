/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author DAW
 */
public class Datos {
    
    public static String cadena(){
        
        String sdato="";
        
        try{
            InputStreamReader isr=new InputStreamReader(System.in);
            BufferedReader flujoE=new BufferedReader(isr);
            
            sdato=flujoE.readLine();
        }catch(IOException e){
            System.err.println("Error: "+e.getMessage());
        }
        return sdato;
        
    }
    
    
    public static int entero(){
        boolean sw = true;
        int x=0;
        while(sw){
            try{
                x=Integer.parseInt(cadena());
                sw=false;
            }catch(Exception ex){
                
                System.out.println("Vuelva a introducir el dato");
                
            }
        }
        
        return x;
        
    }
    
    
        public static double real(){
        boolean sw = true;
        double x=0;
        while(sw){
            try{
                x=Double.parseDouble(cadena());
                sw=false;
            }catch(Exception ex){
                
                System.out.println("Vuelva a introducir el dato");
                
            }
        }
        
        return x;
        
    }
        
        
        public static char caracter(){
        boolean sw = true;
        char x=' ';
        while(sw){
            try{
                x=cadena().charAt(0);
                sw=false;
            }catch(Exception ex){
                
                System.out.println("Vuelva a introducir el dato");
                
            }
        }
        
        return x;
        
    }
    
    

    
    
}
