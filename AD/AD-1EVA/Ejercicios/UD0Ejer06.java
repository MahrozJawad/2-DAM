import java.util.ArrayList; // Para ArrayList
import java.util.Collections; // Para ordenar ArrayList
import java.util.Scanner;

public class UD0Ejer06 {
   public static void main( String[] args ) {
      Scanner entrada = new Scanner(System.in);
      int n=0;
      ArrayList<Integer> datos = new ArrayList<>();
      System.out.println("---------------------------------------------");
      while(n>=0)
      {

         System.out.print("Introduce un número entero (negativo para terminar): ");
         n = entrada.nextInt();
         if(n>=0)
            datos.add(n);
      }
      System.out.println("---------------------------------------------");
      n = 0;
      while(n>=0)
      {
         System.out.print("Introduce un número Para buscar (negativo para terminar): ");
         n = entrada.nextInt();
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