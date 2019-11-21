import java.io.*;
import java.util.Scanner;

public class UD2Ejer502
{
   public static void main(String[] args)
   {
      String linea = "";
      Scanner entrada = new Scanner(System.in);
      String fichero = "frases2.txt";
      PrintWriter pw = null;

      try
      {
         pw = new PrintWriter(fichero);
         do
         {
            System.out.print("Introduce cadenas (Cadena Vacía para terminar): ");
            linea = entrada.nextLine();
            //  Guardando las frases
            if(!linea.equals(""))
               pw.println(linea + "\n");
         } while(!linea.equals(""));
      
         pw.close();
         System.out.print("Se ha guardado el fichero 'frases2.txt'");
      }
      catch (IOException e)
      {
         System.out.println(e.getMessage());
      }
   }
}