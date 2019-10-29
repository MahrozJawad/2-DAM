/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actividad3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Actividad3 {

    public static void main(String[] args) {           
       
        try
        {       
            Scanner entrada = new Scanner(System.in);                           
            String txt = "";
             
            do
            {
                System.out.print("Introduce para Obtener letras en Mayuscula: ");  
                txt = entrada.nextLine();   
               
                if(!txt.equals("fin")){
                    procesoHijo( txt);
                }
            }
            while(!txt.equals("fin"));
           
            System.out.println("Terminado.");       
        }
        catch(Exception ex)
        {       
            ex.printStackTrace();
        }       
    }
    private static void procesoHijo( String txt) {

        try {
            Process p = Runtime.getRuntime().exec("java -jar E:\\2DAM\\PSP\\Tema2\\Proyectos\\Actividad3\\src\\actividad3\\Mayusculas.jar");
            OutputStream outputStream = p.getOutputStream ();
            InputStream inputStream = p.getInputStream ();
            
            BufferedReader reader = new BufferedReader (new InputStreamReader(inputStream));
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(outputStream));
            
            pw.println(txt);
            pw.flush();
            
            String linea = reader.readLine();
            
            if( linea != null){
                System.out.println(linea);
            }
        } catch (IOException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }   

}