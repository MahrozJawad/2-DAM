package examenfinalpsp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hilo extends Thread {

    Socket clientSocket;
    ServerSocket serverSocket;
    int cantidadmax;
    String ServerText;
    String ClienteText;
    
    

    public Hilo(Socket clientSocket, ServerSocket serverSocket, int Cantidad) {
        this.clientSocket = clientSocket;
        this.serverSocket = serverSocket;
        cantidadmax = cantidadmax;
    }

    public void run() {

        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            
            String inputLine, outputLine;
            Protocolo protocolo = new Protocolo(cantidadmax);
            outputLine = protocolo.processInput(null);
            out.println(outputLine);
            while ((inputLine = in.readLine()) != null) {
                outputLine = protocolo.processInput(inputLine);
                out.println(outputLine);
                ClienteText = inputLine;
            }
            out.close();
            in.close();
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
