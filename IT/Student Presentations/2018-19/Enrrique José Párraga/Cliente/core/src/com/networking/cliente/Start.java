package com.networking.cliente;

import com.badlogic.gdx.ApplicationAdapter;

public class Start extends ApplicationAdapter {
    private Client client;

    @Override
    public void create() {
        client = new Client();
    }

    @Override
    public void dispose() {
        client.dispose();
    }
}
