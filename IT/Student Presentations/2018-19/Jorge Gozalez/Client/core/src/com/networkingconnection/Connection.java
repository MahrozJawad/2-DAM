package com.networkingconnection;

import com.badlogic.gdx.ApplicationAdapter;

public class Connection extends ApplicationAdapter {

    private Client client;
    
	@Override
	public void create () {
        client = new Client();
	}
	
	@Override
	public void dispose () {
        client.dispose();
	}
}
