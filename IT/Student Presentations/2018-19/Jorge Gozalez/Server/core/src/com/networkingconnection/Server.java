package com.networkingconnection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.badlogic.gdx.utils.Disposable;
import java.io.IOException;

public class Server implements Disposable {

    private ServerSocket serverSocket;
    private ServerThread thread;

    public Server() {
        
        this.thread = new ServerThread();
        this.thread.start();
    }        
    
    @Override
    public void dispose() {
        
        try {
            thread.join();
            serverSocket.dispose();
        } catch (Exception ex) {
            Gdx.app.log("Server", ex.getMessage());
        }
    }
    
    private class ServerThread extends Thread {

        @Override
        public void run() {

            SocketHints  socketHints = new SocketHints();
            Socket socket = null;
            try {
                int acceptTimeout_sec = 15;
                ServerSocketHints hints = new ServerSocketHints();
                hints.acceptTimeout = acceptTimeout_sec * 1000;

                serverSocket = Gdx.net.newServerSocket(Net.Protocol.TCP, "localhost", 9999, hints);
                Gdx.app.log("Server", "Waiting " +acceptTimeout_sec+" sec. for connections on port 9999");
                socket = serverSocket.accept(socketHints);

                if (socket != null) {
                    Gdx.app.log("Server", "Connection from " + socket.getRemoteAddress());
                    Gdx.app.log("Server", "Socket ID = " + socket.hashCode());

                    byte[] read = new byte[1024];
                    int received = socket.getInputStream().read(read);
                    String message = new String(read).substring(0, received);
                    Gdx.app.log("Server", "Message received from Client: " + message);
                    socket.getOutputStream().write(("You ID is " + socket.hashCode()).getBytes());
                }
            } catch (Exception ex) {
                Gdx.app.log("Server", ex.getMessage());
            }
            finally {
                if (socket != null) {
                    socket.dispose();
					socket = null;
				}
            }
            Gdx.app.log("Server", "Good bye!!");
            Gdx.app.exit();
        }        
    }
}
