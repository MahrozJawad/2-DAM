package ejercicio1;

import java.net.*;
import java.io.*;

public class Example1Receiver {
    
  public static void main(String[] args) throws UnknownHostException, InterruptedException {
    //Reciviendo desde emisor.
    int port = Integer.parseInt("12345");
    final int MAX_LEN = 20;
    RecibirDatos(port, MAX_LEN);
    //Duermo para que el sender se enciende y que se pone al puerto 12346 a escuchar.
      Thread.sleep(3000);
    //Enviando desde receptor al emisor
    port = Integer.parseInt("12346");
    InetAddress receiverHost = InetAddress.getByName("localhost");
    Example1Sender.EnviarDatos(receiverHost, port, "Soy Receptor");

  } // end main
  
  public static void RecibirDatos(int port, int MAX_LEN) {
      
    try {
            DatagramSocket  mySocket = new DatagramSocket(port);
            // instantiates a datagram socket for receiving the data
            byte[ ] buffer = new byte[MAX_LEN];
            DatagramPacket datagram = new DatagramPacket(buffer, MAX_LEN);
            mySocket.receive(datagram);
            String message = new String(buffer);
            
            System.out.println(message);
            mySocket.close( );
        
      
    } // end try
    catch (Exception ex) {
      ex.printStackTrace( );
    }
  }
  
  
} // end class
