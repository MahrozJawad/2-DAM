import java.io.*;
import java.util.*;
import java.text.*;
import java.lang.*;

public class UD2Ejer507
{
   public static void main(String[] args)
   {
      String[] dias = {"Lun","Mar","Mie", "Jue", "Vie", "Sab","Dom"};
      ArrayList diasLista = new ArrayList<>(Arrays.asList(dias));
      
      String[] meses = {"Enero","Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre","Octubre", "Noviembre", "Diciembre"};
      ArrayList mesesLista = new ArrayList<>(Arrays.asList(meses));
      
      String espacios = "    ";
      String nombreMes = "";
      int primerDia = 0;
   
      Scanner entrada = new Scanner(System.in);
      
      //Compruebo si el mes existe o no.
      do
      {
         System.out.print("Introduce el nombre del mes: ");
         nombreMes = entrada.nextLine();
         
         if(!mesesLista.contains(nombreMes))
            System.out.println("ERROR: El mes no existe...");
      
      } while(!mesesLista.contains(nombreMes));
      //Compruebo que si el número esta entre 1 y 7
      do
      {
         System.out.print("Introduce primer dia que tiene este mes: \n1: "+dias[0]+"\n2: "+dias[1]+"\n3: "+dias[2]+"\n4: "+dias[3]+"\n5: "+dias[4]+"\n6: "+dias[5]+"\n7: "+dias[6]+"\nIntroduce: ");
         primerDia = entrada.nextInt();
         
         if((primerDia > 7) || (primerDia < 1))
            System.out.println("ERROR: Introduce un número entre [1 y 7]...");
         else
            break;
      
      } while(true);
      int diasTotales = 0;
      do
      {
         System.out.print("Introduce número de días que tiene este mes: ");
         diasTotales = entrada.nextInt();
         
         if((diasTotales > 31) || (diasTotales < 28))
            System.out.println("ERROR: Introduce un número entre [28 y 31]...");
         else
            break;
         
      } while(true);
      String fichero = "calendario" + nombreMes + ".txt";
      BufferedWriter w = null;

      try
      {
         w = new BufferedWriter(new FileWriter(fichero,false));
         
         w.write("----------------\n" + nombreMes + "\n" + "----------------" + "\n");
         
         for(int i=0; i<diasLista.size(); i++)
            w.write(dias[i] + " ");
         w.write("\n");
         for(int i=0; i<primerDia-1; i++)
            w.write(espacios);
         
         int dias0A6 = primerDia - 1;
         for(int i=1; i <= diasTotales; i++)
         {
            if(i <= 9)
               w.write(i + "   ");
            else
               w.write(i + "  ");
            dias0A6++;
            if(dias0A6 == 7)
            {
               dias0A6 = 0;
               w.write("\n");
            }
         }
         w.close();
         System.out.print("Se ha guardado el fichero '"+fichero+"'");
      }
      catch (IOException e)
      {
         System.out.println(e.getMessage());
      }
   }
}