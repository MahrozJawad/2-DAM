/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.util.*;

public class Ejercicio2 {
   public static void main(String[] args) throws FileNotFoundException, IOException 
    {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Introduce el fichero: ");
        File fichero = new File(entrada.nextLine());
        
        if(fichero.exists())
        {
            FileInputStream fis = new FileInputStream(fichero);
            int dato = Integer.MIN_VALUE;
            
            while ((dato=fis.read()) != -1)
            {
                if(dato >= 32 && dato <= 127)
                {
                    System.out.print(((char)dato) + " ");
                }
            }
            
            //E:/2DAM/AD/UD2/Parte2/write.exe
        }
        else
            System.out.println("El fichero no existe.");
    }   
}