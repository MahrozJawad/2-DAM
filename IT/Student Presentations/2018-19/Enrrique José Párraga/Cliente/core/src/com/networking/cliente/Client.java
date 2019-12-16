package com.networking.cliente;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.badlogic.gdx.utils.Disposable;

import java.io.IOException;

public class Client implements Disposable {
	private Socket client;
    private ClientThread thread;

    public Client() {

        thread = new ClientThread();
        thread.start();

    }

    @Override
	public void dispose() {

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        client.dispose();
	}

	private class ClientThread extends Thread{

        @Override
        public void run() {

            SocketHints hints= new SocketHints();
            hints.connectTimeout = 3000;

            client = Gdx.net.newClientSocket(Net.Protocol.TCP, "localhost", 6066, hints);

            if (client != null){
                byte[] read = new byte[1024];

                try{
                    client.getOutputStream().write("Hi server".getBytes());

                    client.getInputStream().read(read);

                    String message = new String(read);

                    System.out.println("Message Received for server: " + message);

                    client.dispose();

                } catch (IOException ex){
                    System.out.println(ex.getMessage());
                }

            }

            Gdx.app.exit();
        }
    }
}
