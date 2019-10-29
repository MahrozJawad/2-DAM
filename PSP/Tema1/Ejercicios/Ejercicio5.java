/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.util.*;

public class Ejercicio5 {
  public static void main(String args[])
   {
      try
      {
          Process process[] = new Process[args.length]; 
         for(int i=0; i < args.length; i++)
         {
            process[i] = new ProcessBuilder(args[i]).start();
         }
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