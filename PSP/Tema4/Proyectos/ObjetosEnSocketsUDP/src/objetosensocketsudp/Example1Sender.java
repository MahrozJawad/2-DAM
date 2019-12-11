package objetosensocketsudp;

import java.net.*;
import java.io.*;

public class Example1Sender {

    public static void main(String[] args) {
        try {
            //Enviando
            InetAddress receiverHost = InetAddress.getByName("localhost");
            int receiverPort = Integer.parseInt("12345");
            Persona p = new Persona("Mahroz", 21);
            System.out.println("El cliente envía: " + p.toString());
            EnviarDatos(receiverHost, receiverPort, p);
            
            //Reciviendo mensaje desde receptor
            int port = Integer.parseInt("12346");
            final int MAX_LEN = 1000;
            p = Example1Receiver.RecibirDatos(port, MAX_LEN);
            System.out.println("El cliente Recibe: " + p.toString());
            
        } // end try
        catch (Exception ex) {
            ex.printStackTrace();
        }
    } // end main
    
    public static void EnviarDatos(InetAddress receiverHost, int receiverPort, Persona persona) {
        try {
            
            // instantiates a datagram socket for sending the data
                DatagramSocket  mySocket = new DatagramSocket();
            
            //Convertimos objeto a bytes
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bs);
            out.writeObject(persona); // escribir objeto persona en el stream
            out.close();
            byte[] bytes = bs.toByteArray(); //objeto en bytes

            DatagramPacket datagram
                    = new DatagramPacket(bytes, bytes.length,
                            receiverHost, receiverPort);
            mySocket.send(datagram);
            mySocket.close();
            
        } // end try
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
} // end class

