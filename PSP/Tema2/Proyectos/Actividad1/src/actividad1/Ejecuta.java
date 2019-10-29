
package actividad1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ejecuta {
   
    public static void main(String[] args) {
        String cmd = "";
       
        for(int i=0; i<args.length; i++)
            cmd += args[i];
       
        if(!cmd.equals(""))
            new Ejecuta(cmd);
        else
            System.out.println("Debes de introducir el  argumento.");
    }
    
public Ejecuta(String cmd) {
    try
       {
           Process p = Runtime.getRuntime().exec(cmd);
           p.waitFor();
           BufferedReader br = new BufferedReader (new InputStreamReader(p.getInputStream()));
           String linea = br.readLine();
           while (linea!=null)
           {
               System.out.println (linea);
               linea = br.readLine();
           }
       }
       catch (Exception e)
       {
           System.out.println("Error al ejecutar el comando:" + e.getMessage());
       }
   }
}

