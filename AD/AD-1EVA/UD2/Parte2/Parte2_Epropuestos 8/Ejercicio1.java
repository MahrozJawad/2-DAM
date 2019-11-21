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
        final int b = (int)'B';
        final int m = (int)'M';
        final int BUFFER_SIZE = 54;
        Scanner entrada = new Scanner(System.in);
        System.out.print("Introduce el fichero bmp: ");
        String nomfichero = entrada.nextLine();
        File fichero = new File(nomfichero);
        
        
        if(fichero.exists())
        {            
            InputStream ficheroEntrada = new FileInputStream(fichero);
            byte[] buffer = new byte[BUFFER_SIZE];
            
            ficheroEntrada.read(buffer, 0, BUFFER_SIZE);
            
            int primerByte= buffer[0];
            int segundoByte= buffer[1];
            
            boolean cierto = false;
            
            if(primerByte == b && segundoByte == m)
                cierto = true;
              
            if(cierto)
                System.out.println("El fichero parece un BMP Válido");
            else
                System.out.println("El fichero un BMP no Válido");
        }
        else
            System.out.println("El fichero no existe.");
    }
}