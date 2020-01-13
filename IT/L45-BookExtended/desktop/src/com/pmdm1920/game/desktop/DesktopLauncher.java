package com.pmdm1920.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.pmdm1920.game.FlappeeBeeGame;

public class DesktopLauncher {
    public static void main(String[] arg) throws Exception {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.height = 320;
        config.width = 240;

        TexturePacker.process("../assets", "../assets", "flappee_bee_assets");

        new LwjglApplication(new FlappeeBeeGame(), config);
    }
}
