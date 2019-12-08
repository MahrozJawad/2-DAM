package ejercicio1;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class Example1Sender {
  public static void main(String[] args) {
    try {
      InetAddress receiverHost = InetAddress.getByName("localhost");
      int receiverPort1 = Integer.parseInt("12345");
      int receiverPort2 = Integer.parseInt("12346");
        ArrayList<Integer> receiverPorts = new ArrayList<>();
        receiverPorts.add(receiverPort1);
        receiverPorts.add(receiverPort2);
      String message = "Hola 1y2";

      // instantiates a datagram socket for sending the data
        for (int i = 0; i < 2; i++) {
            DatagramSocket  mySocket = new DatagramSocket();
            byte[ ] buffer = message.getBytes( );
            DatagramPacket datagram =
              new DatagramPacket(buffer, buffer.length,
                                 receiverHost, receiverPorts.get(i));
            mySocket.send(datagram);
            mySocket.close( );
        }
        
    } // end try
    catch (Exception ex) {
      ex.printStackTrace( );
    }
  } // end main
} // end class

