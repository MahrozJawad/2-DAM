/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.util.*;

public class Ejercicio1 {
   public static void main(String[] args) throws FileNotFoundException, IOException 
    {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Introduce el fichero: ");
        File fichero = new File(entrada.nextLine());
        
        if(fichero.exists())
        {
            FileInputStream fis = new FileInputStream(fichero);
            int primerByte = fis.read();
            int segundoByte = fis.read();
           
            if(primerByte == 0x4D && segundoByte == 0x5A )
                System.out.println("El fichero es válido.");
            else
                System.out.print("El fichero no es válido.");
            
            //E:/2DAM/AD/UD2/Parte2/write.exe
        }
        else
            System.out.println("El fichero no existe.");
    }
}