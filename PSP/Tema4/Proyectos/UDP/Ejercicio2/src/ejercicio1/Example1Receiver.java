package ejercicio1;

import java.net.*;
import java.io.*;

public class Example1Receiver {
  public static void main(String[] args) {
    int port = Integer.parseInt("12345");
    final int MAX_LEN = 10;
    // This is the assumed maximum byte length of the datagram to be received.
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
  } // end main
} // end class
