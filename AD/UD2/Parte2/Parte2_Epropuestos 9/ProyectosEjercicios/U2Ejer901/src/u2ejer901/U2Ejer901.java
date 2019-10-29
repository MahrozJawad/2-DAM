
package u2ejer901;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class U2Ejer901 {
    
    public static void main(String[] args) {
        FileOutputStream ficheroSalida = null;
        try {
            File fichero = new File("./datos/personas.dat");
            ficheroSalida = new FileOutputStream(fichero);
            ObjectOutputStream ficheroObjetos = new ObjectOutputStream(ficheroSalida);
            Persona p1 = new Persona("nombre1", "Email1", 1997);
            ficheroObjetos.writeObject(p1);
            p1 = new Persona("nombre2", "Email2", 1998);
            ficheroObjetos.writeObject(p1);
            p1 = new Persona("nombre3", "Email3", 1999);
            ficheroObjetos.writeObject(p1);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(U2Ejer901.class.getName()).log(Level.SEVERE, null, ex);
         
        } catch (IOException ex) {
            Logger.getLogger(U2Ejer901.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ficheroSalida.close();
            } catch (IOException ex) {
                Logger.getLogger(U2Ejer901.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
