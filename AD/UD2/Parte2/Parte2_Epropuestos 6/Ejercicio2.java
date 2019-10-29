
import java.io.*;

public class Ejercicio2
{
    public static void main(String[] args) throws IOException
    {
        File fichero = new File("C:\\Users\\Mahroz\\Desktop\\2DAM\\AD\\UD2\\Parte2\\anotaciones.txt");
        if(fichero.exists())
        {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(fichero),"utf-8");
            BufferedReader br = new BufferedReader(isr);
            String linea = null;
            while(br.readLine() != null)
            {
                linea = br.readLine();
                System.out.println(linea);
            }
            br.close();
        }
        else
            System.out.println("El fichero no existe.");
    }
    
}
