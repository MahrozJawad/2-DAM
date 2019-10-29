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
        final int BUFFERED_SIZE = 512 * 1024;
        
        Scanner entrada = new Scanner(System.in);
        System.out.print("Introduce el fichero: ");
        String nomfichero = entrada.nextLine();
        File fichero = new File(nomfichero);
        
        if(fichero.exists())
        {
            InputStream is = new FileInputStream(fichero);
            OutputStream os = new FileOutputStream(fichero + ".txt");
            
            byte[] datos = new byte[BUFFERED_SIZE];
            int contenidoLeido;
            
            while ((contenidoLeido = is.read(datos, 0, BUFFERED_SIZE)) > 0)
            {
                for (int i = 0; i < contenidoLeido; i++) 
                {
                    if(datos[i] >= 32 && datos[i] <= 127)
                        os.write((char)datos[i]);
                }
            }
            is.close();
            os.close();
            
            //C:\Users\Mahroz\Desktop\UD2\Parte2/fichero.bin
        }
        else
            System.out.println("El fichero no existe.");
    }
}