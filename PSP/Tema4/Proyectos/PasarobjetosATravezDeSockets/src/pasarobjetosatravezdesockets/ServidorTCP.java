
package pasarobjetosatravezdesockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.net.SocketFactory;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class ServidorTCP {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SSLServerSocket serverSocket = null;
        try {
            SSLServerSocketFactory sslFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            serverSocket = (SSLServerSocket) sslFactory.createServerSocket(4444);
	    System.out.println("estoy después de crear el socket");
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }

        SSLSocket clientSocket = null;
        try {
            clientSocket = (SSLSocket) serverSocket.accept();
	    System.out.println("estoy después de aceptar un cliente");
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
				new InputStreamReader(
				clientSocket.getInputStream()));
        
        ObjectInputStream inObject = new ObjectInputStream(clientSocket.getInputStream());
        ArrayList<Persona> personas = (ArrayList<Persona>) inObject.readObject();
        
         System.out.println(personas.toString());
        
        
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
