
package u2ejer903;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import u2ejer901.Persona;

public class U2Ejer903 {
    public static void main(String[] args) {
       try {
            final File FICHERO = new File("./datos/personas.dat");
            
            Scanner entrada = new Scanner(System.in);
            //Input para leer
            FileInputStream ficheroInput = null;
            ObjectInputStream ficheroObjectInput = null;
            
            //output para escribir
            FileOutputStream ficheroOutput = null;
            ObjectOutputStream ficheroObjectOutput = null;
            
            ArrayList<Persona> personas = new ArrayList<>();
            
            //Proceso de carga.
            ficheroInput = new FileInputStream(FICHERO);
            ficheroObjectInput = new ObjectInputStream(ficheroInput);
            while (ficheroInput.available() > 0) {                
                personas.add((Persona)ficheroObjectInput.readObject());
            }
            ficheroObjectInput.close();
            
            //proceso de Añadir más personas en el array
            int añadir;
            do {
                System.out.print("Si quiere añadir Persona pulse 1 y si no pulse cualquier otro número: ");
                añadir = entrada.nextInt();
                
                if(añadir == 1) {
                    System.out.print("Introduce Nombre de la persona: ");
                    String nombre = entrada.nextLine();
                    System.out.print("Introduce Email de la persona: ");
                    String email = entrada.nextLine();
                    System.out.print("Introduce año de Nacimiento de la persona: ");
                    int año = entrada.nextInt();
                    personas.add(new Persona(nombre, email, año));
                }
                else
                    System.out.println("Hasta Pronto");
                
            } while (añadir == 1);
            
            //Proceso para guardar el fichero.
            ficheroOutput = new FileOutputStream(FICHERO);
            ficheroObjectOutput = new ObjectOutputStream(ficheroOutput);
            for (int i = 0; i < personas.size(); i++) {
                ficheroObjectOutput.writeObject(personas.get(i));
            }
            ficheroObjectOutput.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(U2Ejer903.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(U2Ejer903.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(U2Ejer903.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}