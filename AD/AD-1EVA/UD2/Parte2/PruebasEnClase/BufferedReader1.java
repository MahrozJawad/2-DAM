import java.io.*;
public class BufferedReader1 {
   public static void main(String[] args) {
      // Primero vemos si el fichero existe
      if (! (new File("ejemplo.txt")).exists() ) 
      {
         System.out.println("No he encontrado ejemplo.txt");
         return;
      }
      // En caso de que exista, intentamos leer
      System.out.println("Leyendo fichero...");
      try 
      {
         BufferedReader ficheroEntrada = new BufferedReader(
         new FileReader(new File("ejemplo.txt")));
         String linea = null;
         linea = ficheroEntrada.readLine();
         while (linea != null) 
         {
            System.out.println(linea);
            linea = ficheroEntrada.readLine();
         }
         ficheroEntrada.close();
      } 
      catch (IOException e) 
      {
         System.out.println( "Ha habido problemas: " + e.getMessage() );
      }
      System.out.println("Fin de la lectura.");
   }
}