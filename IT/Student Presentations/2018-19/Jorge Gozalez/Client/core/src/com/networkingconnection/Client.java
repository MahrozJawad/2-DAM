package com.networkingconnection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.badlogic.gdx.utils.Disposable;

public class Client implements Disposable {
    
    private Socket socket;
    private ClientThread thread;

    public Client() {
        socket = null;
        this.thread = new ClientThread();
        this.thread.start();
    }        

    @Override
    public void dispose() {
        
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Gdx.app.log("Client", ex.getMessage());
        }
        if (socket != null) {
            socket.dispose();
            socket = null;
        }
    }
    
    private class ClientThread extends Thread {

        @Override
        public void run() {
            int connectTimeout_sec = 15;
            SocketHints hints = new SocketHints();
            hints.connectTimeout = connectTimeout_sec * 1000;
            hints.socketTimeout = connectTimeout_sec * 1000;

            try {
                Gdx.app.log("Client", "Connecting localhost:9999 timeout " + connectTimeout_sec + " sec.");
                socket = Gdx.net.newClientSocket(Net.Protocol.TCP, "localhost", 9999, hints);

                if (socket != null) {
                    Gdx.app.log("Client", "Connected with " + socket.getRemoteAddress());

                    byte[] read = new byte[1024];

                    socket.getOutputStream().write("Hi Server! I'm Jorge".getBytes());
                    int readed = socket.getInputStream().read(read);


                    String  message = new String(read).substring(0, readed);
                    Gdx.app.log("Client", "Message Received from Server : \"" + message + "\"");
                }
            } catch (Exception ex) {
                Gdx.app.log("Client", ex.getMessage());
            }
            finally {
                if (socket != null) {
                    socket.dispose();
                    socket = null;
                }
            }
            Gdx.app.log("Client", "Good bye!");
            Gdx.app.exit();
        }
    }
}
