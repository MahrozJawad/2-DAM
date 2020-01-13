/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo4;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;

/**
 *
 * @author usuario
 */
public class Ejemplo4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MessageDigest md;
        try{
            md=MessageDigest.getInstance("SHA");
            String texto="Esto es un texto plano.";
                    byte dataBytes[]= texto.getBytes(); // texto a abtes
                    md.update(dataBytes); // se introduce texto en bytes a resumir
                    byte resumen[]=md.digest(); // se calcula el resumen
                    
                    System.out.println("Mensaje original: " + texto);
                    System.out.println("Número de bytes " + md.getDigestLength());
                    System.out.println("Algoritmo: " + md.getAlgorithm());
                    System.out.println("Mensaje resumen: " + new String(resumen));
                    System.out.println("Mensaje en hexadecimal: " + Hexadecimal(resumen));
                    Provider proveedor = md.getProvider();
                    System.out.println("Proveedor : " + proveedor.toString());        
        }
        catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
                
    }// main
    
    // convierte un array de butes a hexadecimal
    static String Hexadecimal(byte[] resumen) {
        String hex="";
        for (int i = 0; i < resumen.length; i++) {
            String h=Integer.toHexString(resumen[i] & 0xFF);
            if (h.length()==1) 
                hex+="0";
            hex+=h;
        }
        return hex.toUpperCase();
    }// hexadecimal
}



