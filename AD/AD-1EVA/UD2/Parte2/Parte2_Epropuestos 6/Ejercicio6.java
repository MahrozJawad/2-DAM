
import java.io.*;
import java.util.*;

public class Ejercicio6
{
    public static void main(String[] args) throws IOException
    {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Introduce el nombre del fichero: ");
        File fichero = new File(entrada.nextLine());
        
        System.out.print("Introduce la palabra para buscar en el fichero: ");
        String palabra = entrada.nextLine();
        
        if(fichero.exists())
        {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(fichero),"utf-8");
            BufferedReader br = new BufferedReader(isr);
            String linea = "";
            String lineaParaGuardar = null;
            int i = 0;
            while((lineaParaGuardar=br.readLine()) != null)
                if(lineaParaGuardar.contains(palabra))
                    linea += "" + ++i + ") " + lineaParaGuardar + "\n";
            br.close();
            
            System.out.println(linea);
        }
        else
            System.out.println("El fichero no existe.");
    }
    
}
