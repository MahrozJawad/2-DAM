/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo5;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Profesor
 */
public class Ejemplo5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // TODO code application logic here
        
        try {
            FileOutputStream fileout=new FileOutputStream("DATOS.DAT");
            ObjectOutputStream dataOS=new ObjectOutputStream(fileout);
            MessageDigest md=MessageDigest.getInstance("SHA");
            String datos="En un lugar de la Mancha, " +
                    "de cuyo nombre no quiero acordarme, no ha mucho tiempo "  +
                    "que vivia un hidalgo de los de lanza en astillero, "
                    + "adarga antigua, rocín flaco y galgo corredor.";
            byte dataBytes[]=datos.getBytes();
            md.update(dataBytes); // Texto a resumir
            byte resumen[]=md.digest(); // se calcula el resumen
            dataOS.writeObject(datos); // se escriben los datos
            dataOS.writeObject(resumen); // se escribe el resumen
            dataOS.close();
            fileout.close();                        
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    
}
