
import java.io.*;
import java.util.*;

public class Ejercicio4
{
    public static void main(String[] args) throws IOException
    {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Introduce el nombre del fichero: ");
        String nombre = entrada.nextLine();
    
        File fichero = new File(nombre);
        if(fichero.exists())
        {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(fichero),"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String linea = null;
            while((linea=br.readLine()) != null)
            {
                System.out.println(linea);
            }
            br.close();
        }
        else
            System.out.println("El fichero no existe.");
    }
    
}
