/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo6;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Profesor
 */
public class Ejemplo6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // TODO code application logic here
        try {
            FileInputStream fileout = new FileInputStream("DATOS.DAT");
            ObjectInputStream dataOS=new ObjectInputStream(fileout);
            Object o = dataOS.readObject();
            // primera lectura, se obtiene el String
            String datos = (String) o;
            System.out.println("Datos: " + datos);
            // Segunda lectura, se obtiene el resumen
            o=dataOS.readObject();
            byte resumenOriginal[]=(byte[]) o;
            
            MessageDigest md=MessageDigest.getInstance("SHA");
            // Se calcula el resumen del String leido del fichero
            md.update(datos.getBytes()); // texto a resumir
            byte resumenActual[]=md.digest(); // se calcula el resumen
            // se comprueban los dos resúmenes
            if (MessageDigest.isEqual(resumenActual, resumenOriginal))
                System.out.println("DATOS VÁLIDOS");
            else
                System.out.println("DATOS NO VÁLIDOS");
            dataOS.close();
            fileout.close();
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }
    
}
