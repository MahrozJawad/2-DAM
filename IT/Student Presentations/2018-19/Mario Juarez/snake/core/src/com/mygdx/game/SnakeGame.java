package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SnakeGame extends Game {

    /*SpriteBatch batch;
    Texture img;*/

    @Override
    public void create() {
        setScreen(new GameScreen());
    }
}
