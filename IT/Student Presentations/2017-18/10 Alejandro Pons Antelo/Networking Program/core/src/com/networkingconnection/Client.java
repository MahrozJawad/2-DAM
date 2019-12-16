package com.networkingconnection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.badlogic.gdx.utils.Disposable;
import java.io.IOException;

public class Client implements Disposable {
    
    private Socket socket;
    private ClientThread thread;

    public Client() {
    
        this.thread = new ClientThread();
        this.thread.start();
    }        

    @Override
    public void dispose() {
        
        try {
            thread.join();
            
        } catch (InterruptedException ex) {
            
        }
        socket.dispose();
    }
    
    private class ClientThread extends Thread {

        @Override
        public void run() {
            
            SocketHints hints = new SocketHints();
            hints.connectTimeout = 40000;
            
            socket = Gdx.net.newClientSocket(Net.Protocol.TCP, "localhost", 6066, hints);
            
            if (socket != null) {
                
                byte[] read = new byte[1024];
                
                try {
                    socket.getOutputStream().write("Hi Server!".getBytes());                                                                                
                    
                    socket.getInputStream().read(read);                                        
                    
                    String message = new String(read);
                    System.out.println("Message Received from Server: " + message);
                    
                    socket.dispose();
                    
                } catch (IOException ex) {
                    
                }                
            }
            Gdx.app.exit();
        }                
    }
}
