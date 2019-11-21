/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.util.*;

public class Ejercicio3 {
   public static void main(String[] args) throws FileNotFoundException, IOException 
    {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Introduce el fichero: ");
        String nomfichero = entrada.nextLine();
        File fichero = new File(nomfichero);
        
        if(fichero.exists())
        {
            FileInputStream fis = new FileInputStream(fichero);
            int dato = Integer.MIN_VALUE;
            String datos= "";
            
            while ((dato=fis.read()) != -1)
                if(dato >= 32 && dato <= 127)
                    datos += ((char)dato);
            
            PrintWriter pw = new PrintWriter(new File(nomfichero + ".txt"));
            pw.write(datos);
            pw.close();
            
            //E:/2DAM/AD/UD2/Parte2/write.exe
        }
        else
            System.out.println("El fichero no existe.");
    }
}