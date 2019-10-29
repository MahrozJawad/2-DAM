import java.io.*;
import java.util.*;
import java.text.*;

public class UD2Ejer504
{
   public static void main(String[] args)
   {
      String linea = "";
      Scanner entrada = new Scanner(System.in);
      String fichero = "frases4.txt";
      BufferedWriter w = null;

      try
      {
         w = new BufferedWriter(new FileWriter(fichero,true));
         do
         {
            System.out.print("Introduce cadenas (Cadena Vacía para terminar): ");
            linea = entrada.nextLine();
            //Guardando la hora y la fecha
            Date fechaActual = new Date();
            DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
            DateFormat formatoFecha = new SimpleDateFormat("dd:MM:yyyy");
            String hora = formatoHora.format(fechaActual);
            String fecha = formatoFecha.format(fechaActual);
            //  Guardando las frases
            if(!linea.equals(""))
               w.write("En la fecha: " + fecha + "A la(s)"+ hora + "se ha escrito esta linea de abajo:" + "\n"+ linea + "\n");
         } while(!linea.equals(""));
      
         w.close();
         System.out.print("Se ha guardado el fichero 'frases4.txt'");
      }
      catch (IOException e)
      {
         System.out.println(e.getMessage());
      }
   }
}