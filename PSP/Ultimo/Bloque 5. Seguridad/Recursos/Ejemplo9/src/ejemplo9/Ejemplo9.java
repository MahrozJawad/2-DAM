/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo9;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;

/**
 *
 * @author Profesor
 */
public class Ejemplo9 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        try {
            // LECTURA DE LA CLAVE PÚBLICA DEL FICHERO
            FileInputStream inpub = new FileInputStream("Clave.publica");
            byte[] bufferPub = new byte [inpub.available()];
            inpub.read(bufferPub); // lectura de bytes
            inpub.close();
            
            // RECUPERA CLAVE PÚBLICA DESDE DATOS CODIFICADOS EN FORMATO X509
            
            KeyFactory keyDSA=KeyFactory.getInstance("DSA");
            X509EncodedKeySpec clavePublicaSpec=new X509EncodedKeySpec(bufferPub);
            PublicKey clavePublica=keyDSA.generatePublic(clavePublicaSpec);
            
            // LECTURA DEL FICHERO QUE CONTIENE LA FIRMA
            FileInputStream firmafic=new FileInputStream("FICHERO.FIRMA");
            byte [] firma = new byte[firmafic.available()];
            firmafic.read(firma);
            firmafic.close();
            
            
            // INICIALIZA EL OBJETO Signature CON CLAVE PÚBLICA PARA VERIFICAR
            Signature dsa= Signature.getInstance("SHA1withDSA");
            dsa.initVerify(clavePublica);
            
            
            
            // LECTURA DEL FICHERO QUE CONTIENE LOS DATOS A VERIFICAR
            // Se suministra al objeto Signature los datos a verificar
            FileInputStream fichero = new FileInputStream("FICHERO.DAT");
            BufferedInputStream bis= new BufferedInputStream(fichero);
            byte[] buffer = new byte [bis.available()];
            int len;
            while((len=bis.read(buffer))>=0)
               dsa.update(buffer, 0, len);
            bis.close();
            
            // VERIFICAR LA FIRMA DE LOS DATOS LEIDOS
            boolean verifica= dsa.verify(firma);
            // COMPROBAR LA VERIFICACION
            if (verifica)
                System.out.println("LOS DATOS SE CORRESPONDEN CON SU FIRMA.");
            else
                System.out.println("LOS DATOS NO SE CORRESPONDEN CON SU FIRMA"); 
            
        }
        catch(Exception e1) {
            e1.printStackTrace();
        }
    }    
}
