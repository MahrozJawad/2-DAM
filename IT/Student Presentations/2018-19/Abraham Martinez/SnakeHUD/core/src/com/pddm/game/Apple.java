package com.pddm.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;

public class Apple extends Image implements Initiable {
    private boolean appleAvailable;
    private GameScreen mainScreen;

    private void setAnimation(){
        this.setScale(0f);
        this.addAction(scaleTo(1f, 1f, 0.75f, Interpolation.bounce));
    }


    public Apple(GameScreen mainScreen) {
        super(new Texture(Gdx.files.internal("apple.png")));
        this.mainScreen = mainScreen;
        this.init();
        setAnimation();
    }

    private boolean collisionWithSnake(){
        return getX() == mainScreen.getSnake().getX() && getY() == mainScreen.getSnake().getY();
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
            mainScreen.getSnake().addBodyPart();
            mainScreen.addToScore();
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
