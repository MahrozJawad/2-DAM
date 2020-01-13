/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo8;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.*;


/**
 *
 * @author Profesor
 */
public class Ejemplo8 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    
        try {
        // LECTURA DEL FICHERO DE CLAVE PRIVADA
        FileInputStream inpriv=new FileInputStream("Clave.privada");
        byte[] bufferPriv = new byte[inpriv.available()];
        inpriv.read(bufferPriv); // lectura de bytes
        inpriv.close();
        
        // RECUPERA CLAVE PRIVADA DESDE DATOS CODIFICADOS EN FORMATO PKCS8
         PKCS8EncodedKeySpec clavePrivadaSpec=new PKCS8EncodedKeySpec(bufferPriv);
        KeyFactory KeyDSA=KeyFactory.getInstance("DSA");
        PrivateKey clavePrivada=KeyDSA.generatePrivate(clavePrivadaSpec);
        
        // INICIALIZA FIRMA CON CLAVE PRIVADA
        Signature dsa=Signature.getInstance("SHA1withDSA");
        dsa.initSign(clavePrivada);
        
        
        // LECTURA DEL FICHERO A FIRMAR
        // Se suministra al objeto Signature los datos a firmar
        FileInputStream fichero = new FileInputStream("FICHERO.DAT");
        BufferedInputStream bis = new BufferedInputStream(fichero);
        byte[] buffer = new byte[bis.available()];
        int len;
        while ((len=bis.read(buffer))>=0) 
           dsa.update(buffer, 0, len);
        bis.close();
        
        
        // GENERA LA FIRMA DE LOS DATOS DEL FICHERO
        byte[] firma=dsa.sign();
        
        
        // GUARDA LA FIRMA EN OTRO FICHERO
        
        FileOutputStream fos=new FileOutputStream("FICHERO.FIRMA");
        fos.write(firma);
        fos.close();
      }     
      catch(Exception e1) {
          e1.printStackTrace();
      }
    }
    
}
