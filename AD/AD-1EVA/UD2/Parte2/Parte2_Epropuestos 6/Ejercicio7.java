
import java.io.*;
import java.util.*;

public class Ejercicio7
{
   public static void main(String[] args) throws IOException
    {
        File fichero = new File("C:/Users/Mahroz/Desktop/2DAM/AD/UD2/Parte2/Ejercicios/rectangulo.txt");
        
        if(fichero.exists())
        {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(fichero),"utf-8");
            BufferedReader br = new BufferedReader(isr);
            String linea = "";
            String ultimaLinea ="";
            int altura = 0;
            int anchura = 0;
            
            while((linea=br.readLine()) != null)
            {
                altura = altura + 1;
                ultimaLinea = linea;
            }    
            for (int i = 0; i < ultimaLinea.length(); i++)
                anchura = anchura + 1;
            
            br.close();
            
            System.out.print("La altura es de " + altura + " y la anchura es de " + anchura);
        }
        else
            System.out.println("El fichero no existe.");
    }    
}
