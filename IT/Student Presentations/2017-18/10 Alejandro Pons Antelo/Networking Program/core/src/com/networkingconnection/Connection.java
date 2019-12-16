package com.networkingconnection;

import com.badlogic.gdx.ApplicationAdapter;

public class Connection extends ApplicationAdapter {

    private boolean isServer = false;        // Controls if is executing the server or the client.
    private Server server;
    private Client client;
    
    // Client sends a hello to server. The server answers with a confirmation to the client
    
	@Override
	public void create () {

            if (isServer) {
                
                server = new Server();
            }
            else {
                
                client = new Client();
            }
	}
	
	@Override
	public void dispose () {

            if (isServer) {
                
                server.dispose();
            }
            else {
                
                client.dispose();
            }
	}
}
