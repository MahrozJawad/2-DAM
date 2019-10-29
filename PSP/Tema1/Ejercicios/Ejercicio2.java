import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Ejercicio2 {
   public static void main(String[] command) 
   {
      try {
            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec("mspaint.exe");
            System.out.println("Se ha abierto el programa.");
            p.waitFor();
            System.out.println("Se ha Cerrado el programa.");
        } catch (IOException | InterruptedException ex) {     
            System.out.println("Error al ejecutar el programa del sistema windows: " + ex);
       }
   }
}