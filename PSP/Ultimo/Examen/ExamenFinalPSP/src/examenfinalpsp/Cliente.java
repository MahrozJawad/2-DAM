
package examenfinalpsp;
import java.io.*;
import java.net.*;

public class Cliente {

    public String desdeUsuario = "";
    
    public static void main(String[] args) throws IOException {

    Socket scoket = null;
    PrintWriter out = null;
    BufferedReader in = null;
    

    try {
      scoket = new Socket("localhost", 4444);
      out = new PrintWriter(scoket.getOutputStream(), true);
      in = new BufferedReader(new InputStreamReader(scoket.getInputStream()));
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

    while ((fromServer = in.readLine()) != null) {
      System.out.println(fromServer);
      if (fromServer.equals("0"))
      {
        break;
      }
      fromUser = stdIn.readLine();
      if (fromUser != null) {
        System.out.println("Client: " + fromUser);
        out.println(fromUser);
      }
    }

    out.close();
    in.close();
    stdIn.close();
    scoket.close();
 }
    
}
