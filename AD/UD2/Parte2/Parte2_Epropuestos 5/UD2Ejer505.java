import java.io.*;
import java.util.*;
import java.text.*;
//Crea un programa que pida al usuario una anchura y una altura, y cree un
//fichero llamado "rectangulo.txt" que contenga un rectángulo de
//asteriscos de esa anchura y altura.
// Por ejemplo, si el usuario indica anchura 5 y altura 3, el fichero
//contendría:
//*****
//*****
//*****
public class UD2Ejer505
{
   public static void main(String[] args)
   {
      int anchura = 0;
      int altura = 0;
      Scanner entrada = new Scanner(System.in);
      String fichero = "rectangulo.txt";
      BufferedWriter w = null;

      try
      {
         w = new BufferedWriter(new FileWriter(fichero,false));
         do
         {
            System.out.print("Introduce Anchura del rectangulo: ");
            anchura = entrada.nextInt();
            System.out.print("Introduce Altura del rectangulo: ");
            altura = entrada.nextInt();
            
            //Escribiendo el rectangulo
            for(int i=0; i < altura; i++)
            {
               for(int j=0; j < anchura; j++)
                  w.write("*");
               if(!(i==altura-1))
                  w.write("\n");
            }
            
            if(anchura <= 0 || altura <= 0)
               System.out.println("Anchura y altura debede ser mayor que 0");
         } while(anchura <= 0 || altura <= 0);
      
         w.close();
         System.out.print("Se ha guardado el fichero 'rectangulo.txt'");
      }
      catch (IOException e)
      {
         System.out.println(e.getMessage());
      }
   }
}