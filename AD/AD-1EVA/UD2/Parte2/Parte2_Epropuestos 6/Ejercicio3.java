
import java.io.*;
import java.util.*;

public class Ejercicio3
{
   public static void main(String[] args) throws IOException
    {
        File fichero = new File("C:\\Users\\Mahroz\\Desktop\\2DAM\\AD\\UD2\\Parte2\\anotaciones.txt");
        if(fichero.exists())
        {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(fichero),"utf-8");
            BufferedReader br = new BufferedReader(isr);
            String linea = null;
            for(int i=1; (linea=br.readLine()) != null;i++)
            {
                if(i%11 != 0)
                {
                    System.out.println(linea);       
                }
                else
                {
                    System.out.print("Pulse intro para seguir mostrando: ");
                    Scanner entrada = new Scanner(System.in);
                    entrada.nextLine();
                }
            }
            br.close();
        }
        else
            System.out.println("El fichero no existe.");
    }    
}
