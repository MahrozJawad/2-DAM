package ftpcliente;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import packUtils.Menu;

public class FTPCliente {

    private static FTPClient clienteFTP;
    private static String servidorURL = "localhost";
    private static String usuario = "admin";
    private static String password = "";
    private static boolean valida;
    private static Scanner entrada = new Scanner(System.in);

    public static void Conectar() throws IOException {
        clienteFTP = new FTPClient();
        //conexión del cliente al servidor FTP
        clienteFTP.connect(servidorURL);
        int reply = clienteFTP.getReplyCode();
        valida = FTPReply.isPositiveCompletion(reply);
    }

    public static void RenombrarDirectorioOFichero() {

        try {
            String[] nombreCarpeta = clienteFTP.listNames();
            for (int i = 0; i < nombreCarpeta.length; i++) {
                System.out.println(nombreCarpeta[i]);
            }

            System.out.println("Introduce me el nombre de la Carpeta o fichero al que quieres renombrar: ");
            String nombreAnterior = entrada.nextLine();
            nombreCarpeta = clienteFTP.listNames();
            for (int i = 0; i < nombreCarpeta.length; i++) {
                if (nombreAnterior.equals(nombreCarpeta[i])) {
                    System.out.println("Introduce un nuevo nombre: ");
                    String nombreNuevo = entrada.nextLine();
                    clienteFTP.rename(nombreAnterior, nombreNuevo);
                }
                System.out.println();
            }
        } catch (IOException ex) {
            Logger.getLogger(FTPCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void DescargarUnFichero() {

        try {
            FileOutputStream ficheroObtenido;

            String[] nombreCarpeta = clienteFTP.listNames();
            for (int i = 0; i < nombreCarpeta.length; i++) {
                System.out.println(nombreCarpeta[i]);
            }

            System.out.print("Introduce la ruta de la carpeta donde este el fichero en ftp: ");
            String rutaFicheroRemote = entrada.nextLine();

            System.out.print("Introduce el nombre del fichero que quieres descargar: ");
            String nombreFichero = entrada.nextLine();

            System.out.print("Introduce la ruta donde quieres descargar el fichero en tu maquina local: ");
            String rutaFicheroLocal = entrada.nextLine();
            ficheroObtenido = new FileOutputStream(rutaFicheroLocal + "/" + nombreFichero);

            clienteFTP.retrieveFile(rutaFicheroRemote + "/" + nombreFichero, ficheroObtenido);
            ficheroObtenido.close();

        } catch (IOException ex) {
            Logger.getLogger(FTPCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void SubirUnFichero() {

        try {
            String[] nombreCarpeta = clienteFTP.listNames();
            for (int i = 0; i < nombreCarpeta.length; i++) {
                System.out.println(nombreCarpeta[i]);
            }

            System.out.print("Introduce la ruta completa del fichero de la maquina local: ");
            String rutaFicherolocal = entrada.nextLine();

            System.out.print("Introduce el nombre del directorio en el que quieres subir el fichero: ");
            String rutaFicheroftp = entrada.nextLine();

            clienteFTP.setFileType(FTP.BINARY_FILE_TYPE);
            //stream de entrada con el fichero a subir
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(rutaFicherolocal));
            if (clienteFTP.storeFile(rutaFicheroftp + "/" + "hola.txt", in)) {
                System.out.println("Fichero subido...");
            } else {
                System.out.println("No se ha podido subir el fichero...");
            }

        } catch (IOException ex) {
            Logger.getLogger(FTPCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void ListarDirectorio() {

        try {

            String[] nombreCarpeta = clienteFTP.listNames();
            for (int i = 0; i < nombreCarpeta.length; i++) {
                System.out.println(nombreCarpeta[i]);
            }

            System.out.println("Introduce el nombre de directorio que quieres listar: ");
            String dir = entrada.nextLine();

            nombreCarpeta = clienteFTP.listNames("/" + dir);
            for (int i = 0; i < nombreCarpeta.length; i++) {
                System.out.println(nombreCarpeta[i]);
            }
        } catch (IOException ex) {
            Logger.getLogger(FTPCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void EliminarFichero() {
        try {
            String[] nombreCarpeta = clienteFTP.listNames();
            for (int i = 0; i < nombreCarpeta.length; i++) {
                System.out.println(nombreCarpeta[i]);
            }
            
            System.out.print("Introduce el fichero al que quieres borrar: ");
            String ficheroBorrar = entrada.nextLine();
            clienteFTP.deleteFile(ficheroBorrar);
        } catch (IOException ex) {
            Logger.getLogger(FTPCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void Desconectar() throws IOException {
        clienteFTP.logout();
        clienteFTP.disconnect();
    }

    public static void main(String[] args) throws IOException {

        Conectar();
        if (valida) {
            clienteFTP.login(usuario, password);
            Menu.Mostrar();
        }
        Desconectar();
    }
}
