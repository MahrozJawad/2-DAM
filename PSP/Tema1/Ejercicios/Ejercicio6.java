/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.util.*;

public class Ejercicio6 {
  public static void main(String args[])
   {
      try
      {
          int numero = Integer.MIN_VALUE;
          Scanner entrada = new Scanner(System.in);
          Process process[] = new Process[args.length]; 
         for(int i=0; i < args.length; i++)
         {
            process[i] = new ProcessBuilder(args[i]).start();
         }
         do
         {
            System.out.print("Para cerrar la Aplicación, introduce un numero entre 1 a " + (args.length) + " (0 para cerrar Directamente): ");
            numero = entrada.nextInt();

            if(numero == 0)
           {
               for(int i=0; i < args.length; i++)
               {
                   process[i].destroy();
               }
           }
            else
            {
                process[numero].destroy();
                System.out.println("Se ha cerrado el programa: " + args[numero]);
            }
         } while(numero != 0);
         
         for(int i=0; i < args.length; i++)
         {
             process[i].waitFor();
         }
         
         System.out.println ("Aplicaciones finalizada.");
      } 
      catch (IOException | InterruptedException ex) 
      {
      	System.out.println("Error al ejecutar el programa del sistema windows: " + ex);
      }
   }
}