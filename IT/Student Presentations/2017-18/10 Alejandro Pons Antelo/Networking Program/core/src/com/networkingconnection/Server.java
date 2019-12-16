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
            
        } catch (InterruptedException ex) {
            
        }
        serverSocket.dispose();
    }
    
    private class ServerThread extends Thread {

        @Override
        public void run() {
            
            ServerSocketHints hints = new ServerSocketHints();
            hints.acceptTimeout = 40000;
        
            serverSocket = Gdx.net.newServerSocket(Net.Protocol.TCP, "localhost", 6066, hints);
            
            SocketHints socketHints = new SocketHints();
            Socket socket = serverSocket.accept(socketHints);
            
            if (socket != null) {
                
                byte[] read = new byte[1024];                
                
                try {                                        
                    socket.getInputStream().read(read);
                                        
                    String message = new String(read);                    
                    System.out.println("Message received from Client: " + message);
                    
                    String confirmation = "Ok, you are connected";
                    
                    socket.getOutputStream().write(confirmation.getBytes());
                    socket.dispose();
                    
                } catch (IOException ex) {
                    
                }
            }
            Gdx.app.exit();
        }        
    }
}
