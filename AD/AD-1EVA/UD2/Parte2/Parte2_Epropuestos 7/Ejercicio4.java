/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.util.*;

public class Ejercicio4 {
   public static void main(String[] args) throws FileNotFoundException, IOException 
    {
        final int g = ((int)'G');
        final int i = ((int)'I');
        final int f = ((int)'F');
        
        Scanner entrada = new Scanner(System.in);
        System.out.print("Introduce el fichero gif: ");
        String nomfichero = entrada.nextLine();
        File fichero = new File(nomfichero);
        
       if(fichero.exists())
        {
            FileInputStream fis = new FileInputStream(fichero);
            int primerByte = fis.read();
            int segundoByte = fis.read();
            int tercerByte = fis.read();
           
            if(primerByte == g && segundoByte == i && tercerByte == f)
                System.out.println("El fichero es válido.");
            else
                System.out.print("El fichero no es válido.");
            
            //E:/2DAM/AD/UD2/Parte2/
        }
        else
            System.out.println("El fichero no existe.");
    }}