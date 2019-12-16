package com.pddm.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.*;
import com.pddm.game.Stages.HudStage;
import com.pddm.game.Stages.MainStage;

/**
 * Created by James on 29/01/2015.
 * Refactored by Juanjo on 26/11/2018 with Scene2D
 * Edited, and added features by Sergio on 26/01/2019 with libgdx-AI
 */
public class GameScreen extends ScreenAdapter {

    public static final int GRID_CELL = 32;
    private MainStage mainStage;
    private HudStage hudStage;

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        mainStage.getViewport().update(width, height);
        hudStage.getViewport().update(width, height);
    }

    @Override
    public void show() {
        super.show();
        final float WORLD_WIDTH = 640;
        final float WORLD_HEIGHT = 480;
        mainStage = new MainStage(WORLD_WIDTH, WORLD_HEIGHT);
        hudStage = new HudStage(WORLD_WIDTH, WORLD_HEIGHT, mainStage);
    }

    @Override
    public void render(float delta) {
        clearScreen();
        mainStage.act();
        hudStage.act();
        mainStage.draw();
        hudStage.draw();
        super.render(delta);
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b, Color.BLACK.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
