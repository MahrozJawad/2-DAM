package Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Client
{

    public static void main(String[] args) throws IOException, InterruptedException
    {
        //Se crea el socket multicast en el puerto 12345
        MulticastSocket ms = new MulticastSocket(1234);
        //Se configura la IP del grupo al que nos conectaremos
        InetAddress grupo = InetAddress.getByName("225.0.0.62");
        //Se une el grupo multicast
        ms.joinGroup(grupo);
        //Recibe el datagrama
       
        String serverInput = "";
        while(!serverInput.trim().equals("*"))
        {
            byte[] buf = new byte[1000];
            DatagramPacket recibido = new DatagramPacket(buf, buf.length);
            ms.receive(recibido);
            serverInput = new String(buf);
            System.out.println(serverInput);
        }
        //Salimos del grupo multicast
        ms.leaveGroup(grupo);
        // Cierra el socket
        ms.close();
    }

}
