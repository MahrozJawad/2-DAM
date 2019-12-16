package com.pddm.game;

import com.badlogic.gdx.Game;
import com.pddm.game.Screens.GameScreen;

public class SnakeGame extends Game {

    @Override
    public void create() {
        setScreen(new GameScreen());
    }
}
