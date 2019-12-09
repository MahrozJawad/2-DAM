package ejercicio1;

import java.awt.event.KeyEvent;
import java.net.*;
import java.io.*;

public class Example1Sender {

    public static void main(String[] args) {
        try {
            InetAddress receiverHost = InetAddress.getByName("localhost");
            int receiverPort = Integer.parseInt("12345");

            // instantiates a datagram socket for sending the data
            for (int i = 0; true; i++) {
                String message = "Mensaje " + (i + 1);
                DatagramSocket mySocket = new DatagramSocket();
                byte[] buffer = message.getBytes();
                DatagramPacket datagram
                        = new DatagramPacket(buffer, buffer.length,
                                receiverHost, receiverPort);
                mySocket.send(datagram);
                mySocket.close();
                Thread.sleep(3000);
            }
        } // end try
        catch (Exception ex) {
            ex.printStackTrace();
        }
    } // end main
} // end class

