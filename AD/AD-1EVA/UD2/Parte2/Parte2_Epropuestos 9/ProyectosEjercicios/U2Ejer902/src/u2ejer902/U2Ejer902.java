/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package u2ejer902;

/**
 *
 * @author Mahroz
 */
import java.io.File;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import u2ejer901.Persona;

public class U2Ejer902 {

    public static void main(String[] args) {
        
        FileInputStream ficheroSalida = null;
        Persona p = null;
        try {
            File fichero = new File("./datos/personas.dat");
            ficheroSalida = new FileInputStream(fichero);
            ObjectInputStream ficheroObjetos = new ObjectInputStream(ficheroSalida);
            
            while(ficheroSalida.available() > 0) {
                p = (Persona)ficheroObjetos.readObject();
                System.out.println(p.toString());
            }
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(U2Ejer902.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(U2Ejer902.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(U2Ejer902.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ficheroSalida.close();
            } catch (IOException ex) {
                Logger.getLogger(U2Ejer902.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
