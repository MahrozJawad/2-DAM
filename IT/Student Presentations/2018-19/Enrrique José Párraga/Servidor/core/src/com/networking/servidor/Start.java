package com.networking.servidor;

import com.badlogic.gdx.ApplicationAdapter;

public class Start extends ApplicationAdapter {
    private Server server;

    @Override
    public void create() {
        server = new Server();
    }

    @Override
    public void dispose() {
        server.dispose();
    }
}
