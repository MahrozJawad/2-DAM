import java.io.*;
import java.util.*;

public class Ejercicio4 {
   public static void main(String args[])
   {
      try
      {
         Runtime rt = Runtime.getRuntime();
         Process p = rt.exec("C:/Program Files/BlueJ/BlueJ.exe");
         p.waitFor();
         System.out.println ("Aplicación finalizada.");
      } 
      catch (IOException | InterruptedException ex) 
      {
      	System.out.println("Error al ejecutar el programa del sistema windows: " + ex);
      }
   }
}