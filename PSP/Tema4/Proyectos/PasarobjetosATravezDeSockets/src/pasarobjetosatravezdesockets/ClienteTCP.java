package pasarobjetosatravezdesockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteTCP {

    public static void main(String[] args) throws IOException {
        Socket miSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            miSocket = new Socket("localhost", 4444);
            out = new PrintWriter(miSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(miSocket.getInputStream()));
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
        
        ObjectOutputStream outObject = new ObjectOutputStream(miSocket.getOutputStream());
        ObjectInputStream inObject = new ObjectInputStream(miSocket.getInputStream());
        
        while ((fromServer = in.readLine()) != null) {
            System.out.println("Server: " + fromServer);
            if (fromServer.equals("Bye.")) {
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
        miSocket.close();
    }
}
