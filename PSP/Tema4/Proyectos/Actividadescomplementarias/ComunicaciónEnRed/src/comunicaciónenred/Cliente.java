package comunicaciónenred;

import java.io.*;
import java.net.*;

public class Cliente {
  public static void main(String[] args) throws IOException {

    Socket kkSocket = null;
    PrintWriter out = null;
    BufferedReader in = null;

    try {
      kkSocket = new Socket("localhost", 4444);
      out = new PrintWriter(kkSocket.getOutputStream(), true);
      in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
    } catch (UnknownHostException e) {
      System.err.println("Don't know about host: manis.csi.ull.es.");
      System.exit(1);
    } catch (IOException e) {
      System.err.println("Couldn't get I/O for the connection to: manis.csi.ull.es.");
      System.exit(1);
    }

    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
    String fromServer;
    String fromUser;
    int contador = 0;
    while (contador < 2) {
        while ((fromServer = in.readLine()) != null) {
          System.out.println("Server: " + fromServer);
          if (fromServer.equals("Bye."))
            break;

          fromUser = stdIn.readLine();
          if (fromUser != null) {
            System.out.println("Client: " + fromUser);
            out.println(fromUser);
          }
        }
        if(contador == 1) {
            out.println("Bye.");
        }
        contador++;
    }

    out.close();
    in.close();
    stdIn.close();
    kkSocket.close();
 }
}