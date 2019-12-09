package multicastsocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ClienteMulticastSocket {

    public static void main(String[] args) throws IOException {
        
        String msg = "";
        do {            
        //Se crea el socket multicast en el puerto 12345
            MulticastSocket ms = new MulticastSocket(12345);
        //Se configura la IP del grupo al que nos conectaremos
            InetAddress grupo = InetAddress.getByName("225.0.0.1");
        //Se une el grupo multicast
            ms.joinGroup(grupo);
        //Recibe el datagrama
            byte[] buf = new byte[1000];
            DatagramPacket recibido = new DatagramPacket(buf, buf.length);
            ms.receive(recibido);
            msg = new String(recibido.getData());
            System.out.println(msg);
        //Salimos del grupo multicast
            ms.leaveGroup(grupo);
            ms.close();    
        } while (!msg.trim().equals("*"));
        
// Cierra el socket
        

    }
}
