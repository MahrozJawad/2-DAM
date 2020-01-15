package pasarobjetosatravezdesockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class ClienteTCP {

    public static void main(String[] args) throws IOException {
        SSLSocket miSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            SocketFactory factory = SSLSocketFactory.getDefault();
            miSocket = (SSLSocket) factory.createSocket("localhost",4444);
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
        
        ArrayList<Persona> personas = new ArrayList<>();
        personas.add(new Persona("Mahroz", 21));
        personas.add(new Persona("María", 22));
        personas.add(new Persona("Paula", 23));
        
        
        
        ObjectOutputStream outObject = new ObjectOutputStream(miSocket.getOutputStream());
        outObject.writeObject(personas);
        
        
        out.close();
        in.close();
        stdIn.close();
        miSocket.close();
    }
}
