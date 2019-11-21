
import java.io.*;
import java.util.*;

public class Ejercicio5
{
    public static void main(String[] args) throws IOException
    {
        Scanner entrada = new Scanner(System.in);
    
        File fichero = new File("../anotaciones.txt");
        if(fichero.exists())
        {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(fichero),"utf-8");
            BufferedReader br = new BufferedReader(isr);
            String linea = "";
            String lineaParaGuardar = null;
            while((lineaParaGuardar=br.readLine()) != null)
            {
                linea += lineaParaGuardar.toUpperCase() + "\n";
            }
            br.close();
            if(linea != null)
            {
                File nuevoFichero = new File("");
                Boolean existe = false;
                do
                {
                    System.out.print("Introduce el nombre del fichero en el que quieres guardar los datos: ");
                    nuevoFichero = new File(entrada.nextLine());
                    
                  if(nuevoFichero.exists())
                        System.out.print("El fichero ya existe, vuelve a introducir el nombre.");
                  else
                  {
                       PrintWriter pw = new PrintWriter(nuevoFichero);
                       pw.write(linea);
                       pw.close();
                       existe=true;
                       System.out.println("---------------------------------------");  
                       System.out.println("Se ha guardado el fichero correctamente");
                       System.out.println("---------------------------------------");                       
                   }
                } while(!existe);
                
            }
        }
        else
            System.out.println("El fichero no existe.");
    }
    
}
