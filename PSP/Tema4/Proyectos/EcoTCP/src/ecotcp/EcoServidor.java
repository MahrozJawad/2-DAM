package ecotcp;

import java.io.*;
import java.net.*;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

public class EcoServidor {  
  public static final int PORT = 4444;
  public static void main(String[] args) throws IOException {
    // Establece el puerto en el que escucha peticiones
    SSLServerSocket serverSocket = null;
    try {
      SSLServerSocketFactory sslFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            serverSocket = (SSLServerSocket) sslFactory.createServerSocket(4444);
    } catch (IOException e) {
      System.out.println("No puede escuchar en el puerto: " + PORT);
      System.exit(-1);
    }

    SSLSocket socketCliente = null;
    BufferedReader entrada = null;
    PrintWriter salida = null;

    System.out.println("Escuchando: " + serverSocket);
    try {
      // Se bloquea hasta que recibe alguna petici�n de un cliente
      // abriendo un socket para el cliente
      socketCliente =(SSLSocket) serverSocket.accept();
      System.out.println("Connexi�n acceptada: "+ socketCliente);
      // Establece canal de entrada
      entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
      // Establece canal de salida
      salida = new PrintWriter(new BufferedWriter(new
	  OutputStreamWriter(socketCliente.getOutputStream())),true);
      
      // Hace eco de lo que le proporciona el cliente, hasta que recibe "Adios"
      while (true) {  
        String str = entrada.readLine();
	System.out.println("Cliente: " + str);
	salida.println(str);
	if (str.equalsIgnoreCase("Adios")) break;
      }

    } catch (IOException e) {
      System.out.println("IOException: " + e.getMessage());
    }  
    salida.close();
    entrada.close();
    socketCliente.close();
    serverSocket.close();
  }
}
