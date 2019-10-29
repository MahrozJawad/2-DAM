import java.util.ArrayList; // Para ArrayList
import java.util.Collections; // Para ordenar ArrayList
import java.util.Scanner;

public class UD0Ejer08 {
   public static void main( String[] args ) {
   
      Scanner entrada = new Scanner(System.in);
      ListaDeDatos l = new ListaDeDatos();
      String fin="fin";
      //incluir texto
      System.out.println("---------------------------------------------");
      String n="";
      
      do
      {
         System.out.print("Introduce Palabras ('fin' para terminar): ");
         n = entrada.nextLine();
         if(!n.equals(fin))
            l.incluir(n);
      } while(!n.equals(fin));
      
      System.out.println("---------------------------------------------");
      
      //Contiene texto
      do {
            System.out.print("buscar Palabras ('fin' para terminar): ");
            n = entrada.nextLine();
            if(!n.equals(fin))
            {
               if(l.contiene(n))
                  System.out.println(n + " aparece");
               else
                  System.out.println(n + " no aparece");
            }
         } while (!n.equals("fin"));
      
      System.out.println("---------------------------------------------");
      l.MostrarDatosOrdenados();
          
   }
}

class ListaDeDatos
{
   ArrayList<String> datos = new ArrayList<>();

   public void incluir(String texto)
   {
      datos.add(texto);
   }
   public boolean contiene(String texto)
   {
      boolean b = false;
         if(datos.contains(texto))
            b=true;
      return b;
   }
   public void MostrarDatosOrdenados()
   {
      Collections.sort(datos);
      for(int i=0;i<datos.size();i++)
      {
         System.out.println(datos.get(i));
      }  
   }
}