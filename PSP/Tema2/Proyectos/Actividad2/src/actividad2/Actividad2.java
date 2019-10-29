/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actividad2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Actividad2 {
   
    public static void main(String[]args){
   
        Process p = null;
        Scanner entrada = new Scanner(System.in);
        String txt = "";
        do
        {           
            System.out.print("Introduce: ");           
            txt = entrada.nextLine();

            if(!txt.equals("fin") )
            {
                LeerLinea(p);
            }
        } while(!txt.equals("fin"));
        System.out.println("Finalizado.");
        if( p != null)
            p.destroy();
       
    }
    private static void LeerLinea(Process p) {       
       
        try
        {       
            p = Runtime.getRuntime().exec("java -jar E:\\2DAM\\PSP\\Tema2\\Proyectos\\Actividad2\\src\\actividad2\\Aleatorios.java.jar");   
             BufferedReader buf = new BufferedReader (new InputStreamReader (p.getInputStream()));
             String txt = buf.readLine();
             if (txt !=null){
                 System.out.println(txt);
           
         }       
        }catch(Exception e)
        {
            System.err.println("Error: " + e.getMessage());
        }
       
    }
}
