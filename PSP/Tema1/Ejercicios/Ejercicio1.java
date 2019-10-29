import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
public class Ejercicio1 {
   public static void main(String args[]) {
      
      try
      {
	      String cmd = "ipconfig";
	      Runtime.getRuntime().exec(cmd);
         System.out.println ();
      } 
      catch (IOException ioe) 
      {
      	System.out.println (ioe);
      }
     
   }
}