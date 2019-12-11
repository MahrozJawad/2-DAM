package objetosensocketsudp;

import java.net.*;
import java.io.*;

public class Example1Receiver {

    public static void main(String[] args) throws InterruptedException, UnknownHostException {
        int port = Integer.parseInt("12345");
        final int MAX_LEN = 1000;
        Persona p = RecibirDatos(port, MAX_LEN);
        System.out.println("El servidor recibe " + p.toString());
        p.setNombre("Mahroz Jawad");
        System.out.println("El Servidor Modifica el nombre a " + p.getNombre());
        //Duermo para que el sender se enciende y que se pone al puerto 12346 a escuchar.
        Thread.sleep(3000);
        //Enviando desde receptor al emisor
        port = Integer.parseInt("12346");
        InetAddress receiverHost = InetAddress.getByName("localhost");
        System.out.println("El servidor envía " + p.toString());
        Example1Sender.EnviarDatos(receiverHost, port, p);
    } // end main

    public static Persona RecibirDatos(int port, int MAX_LEN) {
        Persona p = null;
        try {
            DatagramSocket mySocket = new DatagramSocket(port);
            // instantiates a datagram socket for receiving the data
            byte[] buffer = new byte[MAX_LEN];
            DatagramPacket datagram = new DatagramPacket(buffer, MAX_LEN);
            mySocket.receive(datagram);

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer);
            ObjectInputStream in = new ObjectInputStream(byteArrayInputStream);

            p = (Persona) in.readObject();

            mySocket.close();
            in.close();

        } // end try
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return p;
    }

} // end class
