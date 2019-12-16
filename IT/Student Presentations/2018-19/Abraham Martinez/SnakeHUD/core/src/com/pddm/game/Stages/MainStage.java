package com.pddm.game.Stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.pddm.game.Actors.Apple;
import com.pddm.game.Actors.Initiable;
import com.pddm.game.Actors.Snake;
import com.sun.org.apache.regexp.internal.RE;

public class MainStage extends Stage {

    public enum State { PLAYING, GAME_OVER }

    private State state;
    private int score;
    public  Snake snake;
    private int maxScore;
    private static float MOVE_TIME_NORMAL = 0.25F;
    private static float MOVE_TIME_MEDIUM = 0.15F;
    private static float MOVE_TIME_HARD = 0.05F;



    private int vidas=3;




    public int comprobarMaxScore(){

        if(score>maxScore)
            maxScore=score;

        return maxScore;
    }

    public String cambiarDificultad(){

       if(snake.getMoveTime()==MOVE_TIME_NORMAL)
           return "NORMAL";
       else if (snake.getMoveTime()==MOVE_TIME_MEDIUM)
           return "MEDIUM";
       else
           return "HARD";
    }

    public int getVidas() {
        return vidas;
    }

    private class KeysHandling extends InputListener {
        @Override
        public boolean keyDown(InputEvent event, int keycode) {
            switch (keycode) {
                case Input.Keys.SPACE:
                    if (state == State.GAME_OVER)
                        doRestart();
                    break;

                case Input.Keys.R:
                    snake.setMoveTime(MOVE_TIME_NORMAL);
                    break;

                case Input.Keys.T:
                    snake.setMoveTime(MOVE_TIME_MEDIUM);
                    break;

                case Input.Keys.F:
                    snake.setMoveTime(MOVE_TIME_HARD);
                    break;

            }
            return true;
        }
    }

    public MainStage(float width, float height) {
        super();

        state = State.PLAYING;
        score = 0;
        maxScore=0;

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

        if(vidas>1){
            vidas--;
        }else {
            state = State.GAME_OVER;
        }
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
            if (a instanceof com.pddm.game.Actors.Initiable)
                ((Initiable)a).init();
        }
        state = State.PLAYING;
        score = 0;
        vidas=3;
    }
}
