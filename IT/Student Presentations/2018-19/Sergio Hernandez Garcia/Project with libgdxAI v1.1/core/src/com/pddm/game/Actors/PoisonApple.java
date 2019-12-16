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

public class PoisonApple extends Image implements Initiable {
    private boolean poisionAppleAvailable;

    public PoisonApple(String tag) {
        super(new Texture(Gdx.files.internal("applePoison.png")));
        this.setUserObject(tag);
        this.init();
    }

    private Snake getSnake() {
        com.pddm.game.Actors.Snake s = null;
        for (Actor a : getStage().getActors()) {
            if (a.getUserObject() == "snake") {
                s = (Snake) a;
                break;
            }
        }
        return s;
    }

    private Apple getApple() {
        com.pddm.game.Actors.Apple s = null;
        for (Actor a : getStage().getActors()) {
            if (a.getUserObject() == "apple") {
                s = (Apple) a;
                break;
            }
        }
        return s;
    }

    private boolean collisionWithPoisonApples(){
        List<PoisonApple> pas = ((MainStage)getStage()).getPoisonApples();
        for (PoisonApple p : pas){
            if(getX() == p.getX() && getY() == p.getY() && p.getUserObject() != getUserObject()){
                return true;
            }
        }
        return false;
    }

    private boolean collisionWithSnake() {
        return getX() == getSnake().getX() && getY() == getSnake().getY();
    }

    private boolean collisionWithApple() {
        return getX() == getApple().getX() && getY() == getApple().getY();
    }


    private void checkAndPlacePoisonApple() {
        if (!poisionAppleAvailable) {
            do {
                setX(MathUtils.random((int) (getStage().getViewport().getWorldWidth() / GameScreen.GRID_CELL) - 1) * GameScreen.GRID_CELL);
                setY(MathUtils.random((int) (getStage().getViewport().getWorldHeight() / GameScreen.GRID_CELL) - 1) * GameScreen.GRID_CELL);
            } while ( collisionWithSnake() || collisionWithApple()  || collisionWithPoisonApples() );
            poisionAppleAvailable = true;
        }
    }

    private void checkPoisonAppleCollision() {
        if (poisionAppleAvailable && collisionWithSnake()) {
            if (getSnake().getBodyParts() == 0){
                ((MainStage) getStage()).setEndGame();
            } else {
                getSnake().removeBodyPart();
                ((MainStage) getStage()).substractFromScore();
                ((MainStage) getStage()).clearPoisonApples();
                poisionAppleAvailable = false;
            }
        }
    }

    public void init() {
        poisionAppleAvailable = false;
    }

    @Override
    public void act(float delta) {
        checkAndPlacePoisonApple();
        checkPoisonAppleCollision();
        super.act(delta);
    }

    public void draw(Batch batch, float parentAlpha) {
        if (poisionAppleAvailable) {
            super.draw(batch, parentAlpha);
        }
    }
}
