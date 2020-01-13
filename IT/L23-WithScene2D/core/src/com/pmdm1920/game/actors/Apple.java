package com.pmdm1920.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.pmdm1920.game.screens.GameScreen;
import com.pmdm1920.game.stages.MainStage;

public class Apple extends Image implements Initializable {
    private boolean appleAvailable;

    private void setAnimation(){
        this.setScale(0f);
        this.addAction(
                Actions.sequence(
                    Actions.scaleTo(1.3f, 1.3f, 0.4f),
                    Actions.scaleTo(1f, 1f, 0.3f, Interpolation.bounceOut)
                )
        );
    }

    public Apple() {
        super(new Texture(Gdx.files.internal("apple.png")));
        this.init();
        setAnimation();
    }

    private Snake getSnake() {
        Snake s = null;
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

    private void checkAndPlaceApple() {
        if (!appleAvailable) {
            do {
                setX(MathUtils.random((int) (getStage().getViewport().getWorldWidth() / GameScreen.GRID_CELL) - 1) * GameScreen.GRID_CELL);
                setY(MathUtils.random((int) (getStage().getViewport().getWorldHeight() / GameScreen.GRID_CELL) - 1) * GameScreen.GRID_CELL);
            } while (collisionWithSnake());
            appleAvailable = true;
            setAnimation();
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
