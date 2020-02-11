
package examenfinalpsp;
import java.net.*;
import java.io.*;
import java.util.Scanner;


public class Servidor {

    public static void main(String[] args) throws IOException, InterruptedException {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(4444);
	    System.out.println("estoy después de crear el socket");
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }

        Socket clientSocket = null;
        try {
            System.out.println("Introduzca la cantidad que necesita para su proyecto");
            Scanner e = new Scanner(System.in);
            int cantidad = e.nextInt();
            while(true) {
                clientSocket = serverSocket.accept();
                System.out.println("estoy después de aceptar un cliente");
                Hilo h = new Hilo(clientSocket, serverSocket, cantidad);
                h.start();
                //System.out.println("Conectado el Cliente " + h.ClienteText);
            }
	    
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        serverSocket.close();
    }
    
}
