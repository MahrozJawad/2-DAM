import java.io.*;

   public class PrintWriter3 {
      public static void main(String[] args) {
         PrintWriter printWriter = null;
      try 
      {
         printWriter = new PrintWriter(new BufferedWriter(
         new FileWriter("ejemplo3.txt", true)));
         printWriter.println("Hola otra vez!");
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
         {
            printWriter.close();
         }
      }
   }
}