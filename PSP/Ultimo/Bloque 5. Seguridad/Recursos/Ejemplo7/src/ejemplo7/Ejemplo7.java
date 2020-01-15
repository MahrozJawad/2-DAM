/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo7;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Profesor
 */
public class Ejemplo7 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException {
        try {

            //OBTENEMOS EL OBJETO GENERADOR            
            KeyPairGenerator KeyGen = KeyPairGenerator.getInstance("DSA");

            // SE INICIALIZA EL GENERADOR DE CLAVES
            SecureRandom numero = SecureRandom.getInstance("SHA1PRNG");
            //EL TAMAÑO DEBE ESTAR ENYTRE 512 Y 1024 Y DEBE SER MÚLTIPLO DE 64
            //SI SE PASA UN NUMERO INVALIDO DARÁ EXCEPCIÓN INVALIDPARAMETEREXCEPTION
            KeyGen.initialize(1024, numero);

            // SE CREA EL PAR DE CLAVES PRIVADA Y PÚBLICA
            KeyPair par = KeyGen.generateKeyPair();
            PrivateKey clavepriv = par.getPrivate();
            PublicKey clavepub = par.getPublic();

            //FIRMA CON CLAVE PRIVADA EL MENSAJE
            // AL OBJETO Signature SE LE SUMINISTRAN LOS DATOS A FIRMAR
            Signature dsa = Signature.getInstance("SHA1withDSA");
            dsa.initSign(clavepriv);
            String mensaje = "Este mensaje va a ser firmado";
            dsa.update(mensaje.getBytes());

            byte[] firma = dsa.sign(); //MENSAJE FIRMADO

            // EL RECEPTOR DEL MENSAJE
            // VERIFICA CON LA CLAVE PÚBLICA EL MENSAJE FIRMADO
            // AL OBJETO Signature SE LES SUMINIST. LOS DATOS A VERIFICAR
            Signature verificadsa = Signature.getInstance("SHA1withDSA");
            verificadsa.initVerify(clavepub);
            verificadsa.update(mensaje.getBytes());
            boolean check = verificadsa.verify(firma);
            if (check) {
                System.out.println("FIRMA VERIFICADA CON CLAVE pÜBLICA");
            } else {
                System.out.println("FIRMA NO VERIFICADA");
            }

            PKCS8EncodedKeySpec clavePrivadaSpec = new PKCS8EncodedKeySpec(clavepriv.getEncoded());
//Escribir a fichero binario la clave privada
            FileOutputStream outpriv = new FileOutputStream("Clave.privada");
            outpriv.write(clavePrivadaSpec.getEncoded());
            outpriv.close();

            X509EncodedKeySpec clavePublicaSpec = new X509EncodedKeySpec(clavepub.getEncoded());
//Escribir a fichero binario la clave pública
            FileOutputStream outpub = new FileOutputStream("Clave.publica");
            outpub.write(clavePublicaSpec.getEncoded());
            outpub.close();

        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(Ejemplo7.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//main    
} // Ejemplo 7

