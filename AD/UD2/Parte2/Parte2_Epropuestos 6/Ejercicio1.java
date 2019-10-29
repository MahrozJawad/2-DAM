
import java.io.*;

public class Ejercicio1 
{
    public static void main(String[] args) throws IOException 
    {
        File fichero = new File("../anotaciones.txt");
        if(fichero.exists())
        {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(fichero),"utf-8");
            BufferedReader br = new BufferedReader(isr);
            String linea = null;
            if((linea = br.readLine()) != null)
            {
                System.out.print(linea);
            }
            else
                System.out.println("");
            
            br.close();
        }
        else
            System.out.println("El fichero no existe.");
    }
    
}
