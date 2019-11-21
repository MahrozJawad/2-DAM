import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class PrintWriter1 {
   public static void main(String[] args) {
      try {
         PrintWriter printWriter = new PrintWriter("ejemplo.txt");
         printWriter.println("Hola!");
         printWriter.close ();
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
   }
}