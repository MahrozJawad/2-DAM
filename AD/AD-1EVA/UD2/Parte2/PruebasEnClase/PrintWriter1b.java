import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class PrintWriter1b {
   public static void main(String[] args) throws FileNotFoundException {
      PrintWriter printWriter = new PrintWriter ("ejemplo.txt");
      printWriter.println ("Hola!");
      printWriter.close ();
   }
}