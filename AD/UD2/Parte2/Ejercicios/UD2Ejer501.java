import java.io.*;
import java.util.Scanner;

public class UD2Ejer501 
{
   public static void main(String[] args)
   {
      Scanner entrada = new Scanner(System.in);
      String fichero = "frases1.txt";
      System.out.print("Introduce Primera cadena: ");
      String pfrase = entrada.nextLine();

      System.out.print("Introduce Segunda cadena: ");
      String sfrase = entrada.nextLine();
      try
      {
      //  Guardando las frases
         PrintWriter pw = new PrintWriter(fichero);
         pw.println(pfrase);
         pw.println(sfrase);
         pw.close();
         System.out.print("Se ha guardado el fichero 'frases1.txt'");
      }
      catch (IOException e)
      {
         System.out.println(e.getMessage());
      }
   }
}