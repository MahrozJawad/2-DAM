package com.pddm.game.Stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.pddm.game.Actors.Apple;
import com.pddm.game.Actors.Initiable;
import com.pddm.game.Actors.PoisonApple;
import com.pddm.game.Actors.Snake;

import java.util.ArrayList;
import java.util.List;

public class MainStage extends Stage {

    public enum State { PLAYING, GAME_OVER }

    private State state;
    private int score;
    final int POINTS_PER_APPLE = 20;
    private Sprite backgroundSprite;

    private int timer = 250;

    private List<PoisonApple> poisonApples = new ArrayList<>();

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

        backgroundSprite = new Sprite(new Texture("background.png"));

        Gdx.input.setInputProcessor(this);
        addListener(new KeysHandling());

        Snake snake = new Snake();
        this.addActor(snake);

        Apple apple = new Apple();
        this.addActor(apple);

        this.setKeyboardFocus(snake);
    }

    public void setEndGame(){
        state = State.GAME_OVER;
    }

    public void addToScore() {
        score += POINTS_PER_APPLE;
    }

    public void substractFromScore() {
        score -= POINTS_PER_APPLE + 10;
    }

    public int getScore() {
        return score;
    }

    public MainStage.State getState() {
        return state;
    }

    public List<PoisonApple> getPoisonApples(){
        return poisonApples;
    }

    public void clearPoisonApples(){
        for (Actor a : this.getActors()){
            if (a.getUserObject().toString().contains("poisonApple"))
                a.addAction(Actions.removeActor());
        }
        poisonApples.clear();
    }

    @Override
    public void act(float delta) {
        if (state == State.PLAYING) {
            super.act(delta);
            if (timer < delta) {
                PoisonApple poisonApple = new PoisonApple("poisonApple" + poisonApples.size());
                this.addActor(poisonApple);
                poisonApples.add(poisonApple);
                timer = 250;
            }
            timer -= delta;
        }
    }

    @Override
    public void draw() {
        getBatch().begin();
        backgroundSprite.draw(getBatch());
        getBatch().end();
        super.draw();
    }

    private void doRestart() {
        for (Actor a : this.getActors()){
            if (a instanceof com.pddm.game.Actors.Initiable)
                ((Initiable)a).init();
        }
        clearPoisonApples();
        state = State.PLAYING;
        score = 0;
    }
}
