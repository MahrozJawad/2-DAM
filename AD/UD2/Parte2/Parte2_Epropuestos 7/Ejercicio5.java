/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.util.*;

public class Ejercicio5 {
   public static void main(String[] args) throws FileNotFoundException, IOException 
    {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Introduce el fichero: ");
        String nomfichero = entrada.nextLine();
        File fichero = new File(nomfichero);
        
        System.out.print("Introduce la palabra para buscar en el fichero: ");
        String cadena = entrada.nextLine();
        
       if(fichero.exists())
        {            
            FileInputStream fis = new FileInputStream(fichero);
            String bytes = "";
            int tempByte;
            String palabra = "";
            String mensaje = "";
            
            //Lectura
            while ((tempByte = fis.read()) != -1)
                bytes += (char)tempByte;      
            
            fis.close();
            
            //Búsqueda
            for (int j = 0; j < bytes.length() - cadena.length(); j++)
            {
                for (int k = j; k < cadena.length() + j; k++)
                    palabra += bytes.charAt(k);
                    
                if(palabra.equals(cadena))
                {
                    mensaje = "La palabra " + cadena + ", si que existe en el fichero " + nomfichero + "";
                    break;
                }
                else
                    mensaje = "No se ha encontrado la palabra introducida";
                palabra = "";
            }
            //Salida del mensaje
            System.out.println(mensaje);
        }
        else
            System.out.println("El fichero no existe.");
    }
}