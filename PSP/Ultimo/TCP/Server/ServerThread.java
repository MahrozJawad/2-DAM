package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerThread extends Thread
{

    Socket clientSocket;

    public ServerThread(Socket clientSocket)
    {
        this.clientSocket = clientSocket;
    }
    
    public  void run()
    {
        try
        {
            BufferedReader input = null;
            PrintWriter output = null;
            
            try
            {
                System.out.println("Accepted: " + clientSocket);
                input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);
                
                Server.Game.MenuSelection menuSelection;
                do
                {
                    menuSelection = Server.Game.sendMenu(output, input);
                    Server.Game.startGame(output, input, menuSelection);
                    input.readLine();   // Enter client confirmation
                }
                while(menuSelection != Server.Game.MenuSelection.Exit);
                output.println("Bye");
                
            } catch (IOException e)
            {
                System.out.println("IOException: " + e.getMessage());
            }
            output.close();
            input.close();
            clientSocket.close();
        } catch (IOException ex)
        {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
