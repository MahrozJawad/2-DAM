import java.io.*;

public class PrintWriter2 {
   public static void main(String[] args) {
      PrintWriter printWriter = null;
      try 
      {
         printWriter = new PrintWriter("ejemplo.txt");
         printWriter.println("Hola!");
         printWriter.println("y...");
         printWriter.println("hasta luego!");
      } 
      catch (IOException e)
      {
         e.printStackTrace();
      } 
      finally 
      {
         if ( printWriter != null ) 
            printWriter.close();
      }
   }
}