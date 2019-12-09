package multicastsocket;

import com.sun.org.apache.xml.internal.serializer.utils.MsgKey;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class ServidorMulticastSocket {

    public static void main(String[] args) throws IOException {
        String msg = "";
        Scanner e = new Scanner(System.in);
        do {            
            //Se crea el socket multicast. No hace falta especificar puerto
        MulticastSocket ms = new MulticastSocket();
//Se define el puerto multicast
        int puerto = 12345;
//Se crea el grupo multicast
        InetAddress grupo = InetAddress.getByName("225.0.0.1");
        System.out.println("Introduce el mensaje para enviar, * para salir");
        msg = e.nextLine();
//Se crea el datagrama
        DatagramPacket paquete = new DatagramPacket(msg.getBytes(), msg.length(), grupo,
                puerto);
//Se envía el paquete al grupo
        ms.send(paquete);
// Cierra el socket
        ms.close();
        } while (!msg.equals("*"));
    }
}
