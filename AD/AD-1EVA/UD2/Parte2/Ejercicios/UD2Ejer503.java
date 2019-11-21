import java.io.*;
import java.util.Scanner;

public class UD2Ejer503
{
   public static void main(String[] args)
   {
      String linea = "";
      Scanner entrada = new Scanner(System.in);
      String fichero = "frases3.txt";
      BufferedWriter w = null;

      try
      {
         w = new BufferedWriter(new FileWriter(fichero,true));
         do
         {
            System.out.print("Introduce cadenas (Cadena Vacía para terminar): ");
            linea = entrada.nextLine();
            //  Guardando las frases
            if(!linea.equals(""))
               w.write(linea + "\n");
         } while(!linea.equals(""));
      
         w.close();
         System.out.print("Se ha guardado el fichero 'frases3.txt'");
      }
      catch (IOException e)
      {
         System.out.println(e.getMessage());
      }
   }
}