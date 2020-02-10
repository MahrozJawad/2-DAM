package client;

import java.net.*;
import java.io.*;
import server.server;

public class client
{

    public static void main(String[] args) throws IOException
    {
        Socket clientSocket = null;
        BufferedReader input = null;
        PrintWriter output = null;

        try
        {
            clientSocket = new Socket("localhost", server.PORT);
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);
        } catch (IOException e)
        {
            System.err.println("No puede establer canales de E/S para la conexión");
            System.exit(-1);
        }
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        try
        {
            while (true)
            {
                System.out.println(input.readLine());
                output.println(stdIn.readLine());
            }
        } catch (IOException e)
        {
            System.out.println("IOException: " + e.getMessage());
        }

        output.close();
        input.close();
        stdIn.close();
        clientSocket.close();
    }
}
