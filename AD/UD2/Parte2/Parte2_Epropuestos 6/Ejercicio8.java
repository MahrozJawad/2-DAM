
import java.io.*;
import java.util.*;

public class Ejercicio8
{
    public static void main(String[] args) throws IOException
    {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Introduce un fichero existente: ");
        File fichero = new File(entrada.nextLine());
        
        if(fichero.exists())
        {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(fichero),"utf-8");
            BufferedReader br = new BufferedReader(isr);
            String linea = "";
            ArrayList<String> al = new ArrayList<>(1000);
            
            while((linea=br.readLine()) != null)
                al.add(linea);
            br.close();
            
            String cadena = "";
            do 
            {   System.out.print("Introduce una cadena para buscar. (Vacío para terminar la búsqueda): ");
                cadena = entrada.nextLine();
                linea = "";
                int index = 0;
                for (int i = 0; i < al.size(); i++) 
                {
                    if(al.get(i).contains(cadena))
                    {
                        linea += ++index + ") " + al.get(i) + "\n";
                    }
                }
                if(linea.equals(""))
                {
                    System.out.println("El texto no Encontardo");
                }
                else if(cadena.equals(""))
                {
                     System.out.print("Terminado");
                }
                else
                    System.out.println("El resultado de la búsqueda es: \n" + linea);
                
            } while (!cadena.equals(""));
            
        }
        else
            System.out.println("El fichero no existe.");
    }
  
}
