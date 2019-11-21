import java.util.ArrayList; // Para ArrayList
import java.util.Collections; // Para ordenar ArrayList
import java.util.Scanner;

public class UD0Ejer07 {
   public static void main( String[] args ) {
      Scanner entrada = new Scanner(System.in);
      String n=""; String fin="fin";
      ArrayList<String> datos = new ArrayList<>();
      System.out.println("---------------------------------------------");
      do
      {

         System.out.print("Introduce Palabras ('fin' para terminar): ");
         n = entrada.nextLine();
         if(!n.equals(fin))
            datos.add(n);
      } while(!n.equals(fin));
      System.out.println("---------------------------------------------");
      n = "";
      while(!n.equals(fin))
      {
         System.out.print("buscar Palabras ('fin' para terminar): ");
         n = entrada.nextLine();
         if(datos.contains(n))
            System.out.println(n + " aparece");
         else
            System.out.println(n + " no aparece");
      }
      System.out.println("---------------------------------------------");
      Collections.sort(datos);
      for(int i=0;i<datos.size();i++)
      {
         System.out.println(datos.get(i));
      }      
   }
}