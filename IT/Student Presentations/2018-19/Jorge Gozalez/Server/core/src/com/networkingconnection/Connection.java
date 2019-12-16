package com.networkingconnection;

import com.badlogic.gdx.ApplicationAdapter;

public class Connection extends ApplicationAdapter {

    private Server server;

	@Override
	public void create () {
        server = new Server();
	}
	
	@Override
	public void dispose () {
        server.dispose();
	}
}
