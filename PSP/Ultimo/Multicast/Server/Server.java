package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class Server
{

    public static void main(String[] args) throws IOException
    {
        //Se crea el socket multicast. No hace falta especificar puerto
        MulticastSocket ms = new MulticastSocket();
        //Se define el puerto multicast
        int puerto = 1234;
        //Se crea el grupo multicast
        InetAddress grupo = InetAddress.getByName("225.0.0.62");
        String msg = "Bienvenidos";
        //Se crea el datagrama
        
        //Se envía el paquete al grupo
        Scanner input = new Scanner(System.in);
        while (!msg.equals("*"))
        {
            msg = input.next();
            DatagramPacket paquete = new DatagramPacket(msg.getBytes(), msg.length(), grupo, puerto);
            ms.send(paquete);
        }
        // Cierra el socket
        ms.close();  
    }
}
