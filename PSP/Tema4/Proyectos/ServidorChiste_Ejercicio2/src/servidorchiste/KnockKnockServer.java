package servidorchiste;

import java.net.*;
import java.io.*;

public class KnockKnockServer {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(4444);
	    System.out.println("estoy despu�s de crear el socket");
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }

        Socket clientSocket = null;
        System.out.println("estoy despu�s de aceptar un cliente");
        try {
            while(true) {
                clientSocket = serverSocket.accept();
                Hilo h = new Hilo(clientSocket);
                h.start();
            }
	    
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        serverSocket.close();
        
    }
}