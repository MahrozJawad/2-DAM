package com.pmdm1920.game;

import com.badlogic.gdx.Game;
import com.pmdm1920.game.screens.GameScreen;

public class SnakeGame extends Game {

    @Override
    public void create() {
        setScreen(new GameScreen());
    }
}
