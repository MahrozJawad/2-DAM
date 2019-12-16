package com.pddm.game.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.pddm.game.Screens.GameScreen;
import com.pddm.game.Stages.MainStage;
import java.util.List;

public class Apple extends Image implements Initiable {
    private boolean appleAvailable;

    public Apple() {
        super(new Texture(Gdx.files.internal("apple.png")));
        this.setUserObject("apple");
        this.init();
    }

    private Snake getSnake() {
        com.pddm.game.Actors.Snake s = null;
        for (Actor a : getStage().getActors()){
            if (a.getUserObject() == "snake"){
                s = (Snake)a;
                break;
            }
        }
        return s;
    }

    private boolean collisionWithSnake(){
        return getX() == getSnake().getX() && getY() == getSnake().getY();
    }

    private boolean collisionWithPoisonApples(){
        List<PoisonApple> pas = ((MainStage)getStage()).getPoisonApples();
        for (PoisonApple p : pas){
            if(getX() == p.getX() && getY() == p.getY()){
                return true;
            }
        }
        return false;
    }

    private void checkAndPlaceApple() {
        if (!appleAvailable) {
            do {
                setX(MathUtils.random((int) (getStage().getViewport().getWorldWidth() / GameScreen.GRID_CELL) - 1) * GameScreen.GRID_CELL);
                setY(MathUtils.random((int) (getStage().getViewport().getWorldHeight() / GameScreen.GRID_CELL) - 1) * GameScreen.GRID_CELL);
            } while (collisionWithSnake() || collisionWithPoisonApples());
            appleAvailable = true;
        }
    }

    private void checkAppleCollision() {
        if (appleAvailable && collisionWithSnake()) {
            getSnake().addBodyPart();
            ((MainStage)getStage()).addToScore();
            appleAvailable = false;
        }
    }

    public void init() {
        appleAvailable = false;
    }

    @Override
    public void act(float delta) {
        checkAndPlaceApple();
        checkAppleCollision();
        super.act(delta);
    }

    public void draw(Batch batch, float parentAlpha) {
        if (appleAvailable) {
            super.draw(batch, parentAlpha);
        }
    }
}
