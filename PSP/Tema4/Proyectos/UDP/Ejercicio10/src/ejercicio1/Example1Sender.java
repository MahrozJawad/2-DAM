package ejercicio1;

import java.awt.event.KeyEvent;
import java.net.*;
import java.io.*;

public class Example1Sender {

    public static void main(String[] args) throws UnknownHostException {
        //Enviando mensaje a recetor
        InetAddress receiverHost = InetAddress.getByName("localhost");
            int receiverPort = Integer.parseInt("12345");
            EnviarDatos(receiverHost, receiverPort, "Soy Sender");
            //Reciviendo mensaje desde receptor
            int port = Integer.parseInt("12346");
            final int MAX_LEN = 20;
            Example1Receiver.RecibirDatos(port, MAX_LEN);
    } // end main
    
    public static void EnviarDatos(InetAddress receiverHost, int receiverPort, String message) {
        try {
            
            // instantiates a datagram socket for sending the data
                DatagramSocket mySocket = new DatagramSocket();
                byte[] buffer = message.getBytes();
                DatagramPacket datagram
                        = new DatagramPacket(buffer, buffer.length,
                                receiverHost, receiverPort);
                mySocket.send(datagram);
                mySocket.close();
            
        } // end try
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
} // end class

