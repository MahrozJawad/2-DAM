package com.pmdm1920.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.pmdm1920.game.actors.Apple;
import com.pmdm1920.game.actors.Initializable;
import com.pmdm1920.game.actors.Snake;

public class MainStage extends Stage implements HudData {

    public enum State { PLAYING, GAME_OVER }

    private State state;
    private int score;

    private class KeysHandling extends InputListener {
        @Override
        public boolean keyDown(InputEvent event, int keycode) {
            switch (keycode) {
                case Input.Keys.SPACE:
                    if (state == State.GAME_OVER)
                        doRestart();
                    break;
            }
            return true;
        }
    }

    public MainStage(float width, float height) {
        super();

        state = State.PLAYING;
        score = 0;

        OrthographicCamera camera = new OrthographicCamera(width, height);
        camera.position.set(width / 2, height / 2, 0);
        camera.update();
        this.setViewport(new FitViewport(width, height, camera));

        Gdx.input.setInputProcessor(this);
        addListener(new KeysHandling());

        Snake snake = new Snake();
        this.addActor(snake);

        Apple apple = new Apple();
        this.addActor(apple);

        this.setKeyboardFocus(snake);
        // this.setDebugAll(true);
    }

    public void setFinJuego(){
        state = State.GAME_OVER;
    }

    public void addToScore() {
        final int POINTS_PER_APPLE = 20;
        score += POINTS_PER_APPLE;
    }

    public int getScore() {
        return score;
    }

    public MainStage.State getState() {
        return state;
    }

    @Override
    public void act(float delta) {
        if (state == State.PLAYING)
            super.act(delta);
    }

    private void doRestart() {
        for (Actor a : this.getActors()){
            if (a instanceof Initializable)
                ((Initializable)a).init();
        }

        state = State.PLAYING;
        score = 0;
    }
}
