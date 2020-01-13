/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo1;

public class Ejemplo1 {
   public static void main(String [] args) {
      // propiedades del sistema en un array
       String t[] = {"java.class.path", "java.home", "java.vendor", "java.version", "os.name",
                           "os.version", "user.dir", "user.home", "user.name" };
        for(int i=0; i<t.length; i++) {
             System.out.println("Propiedad: " + t[i]);
              try {
                  String s=System.getProperty(t[i]); // valor de la propiedad
                  System.out.println("\t==> " + s);
              } catch(Exception e) {
                     System.err.println("\n\tExcepción " + e.toString());
               }
        }// for
   } // main
} // Ejemplo1
