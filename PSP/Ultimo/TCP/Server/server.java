package server;

import java.io.*;
import java.net.*;

public class server
{

    public static final int PORT = 4444;

    public static void main(String[] args) throws IOException
    {
        ServerSocket serverSocket = null;
        try
        {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e)
        {
            System.out.println("No puede escuchar en el puerto: " + PORT);
            System.exit(-1);
        }

        Socket clientSocket;
        Thread t;

        try
        {
            while (true)
            {
                clientSocket = serverSocket.accept();
                t = new Server.ServerThread(clientSocket);
                t.start();
            }

        } catch (IOException e)
        {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        serverSocket.close();
    }
}
