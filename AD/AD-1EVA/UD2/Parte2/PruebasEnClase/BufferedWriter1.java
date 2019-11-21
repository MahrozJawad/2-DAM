import java.io.*;
public class BufferedWriter1 {
   public static void main(String[] args) throws IOException {
      BufferedWriter ficheroSalida = null;
      try 
      {
         ficheroSalida = new BufferedWriter(
         new FileWriter(new File("ejemplo2.txt")));
         ficheroSalida.write("Línea 1");
         ficheroSalida.newLine();
         ficheroSalida.write("Línea 2");
         ficheroSalida.newLine();
      } 
      catch (IOException e) 
      {
         System.out.println("Ha habido problemas: " + e.getMessage() );
      } 
      finally 
      {
         if ( ficheroSalida != null ) 
         {
            ficheroSalida.close();
         }
      }
   }
}