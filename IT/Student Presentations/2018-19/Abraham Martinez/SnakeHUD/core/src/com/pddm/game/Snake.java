package com.pddm.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Snake extends Actor implements Initiable {

    private enum Direction { RIGHT, LEFT, UP, DOWN }

    private static final float MOVE_TIME = 0.15F;
    private static final int SNAKE_MOVEMENT = GameScreen.GRID_CELL;
    private float snakeXBeforeUpdate, snakeYBeforeUpdate;
    private Texture snakeHead;
    private Texture snakeBody;
    private Array<BodyPart> bodyParts;

    private float timer;
    private Direction snakeDirection;
    private boolean directionSet;

    private GameScreen mainScreen;

    private class SnakeHandling extends InputListener {

        private void updateDirection(Direction newSnakeDirection) {
            if (!directionSet && snakeDirection != newSnakeDirection) {
                directionSet = true;
                switch (newSnakeDirection) {
                    case LEFT: {
                        updateIfNotOppositeDirection(newSnakeDirection, Direction.RIGHT);
                    }
                    break;
                    case RIGHT: {
                        updateIfNotOppositeDirection(newSnakeDirection, Direction.LEFT);
                    }
                    break;
                    case UP: {
                        updateIfNotOppositeDirection(newSnakeDirection, Direction.DOWN);
                    }
                    break;
                    case DOWN: {
                        updateIfNotOppositeDirection(newSnakeDirection, Direction.UP);
                    }
                    break;
                }
            }
        }

        private void updateIfNotOppositeDirection(
                                    Direction newSnakeDirection,
                                    Direction oppositeDirection) {
            if ((snakeDirection != oppositeDirection) || bodyParts.size == 0)
                snakeDirection = newSnakeDirection;
        }

        @Override
        public boolean keyDown(InputEvent event, int keycode) {
            switch (keycode) {
                case Input.Keys.LEFT:
                    updateDirection(Direction.LEFT);
                    break;
                case Input.Keys.RIGHT:
                    updateDirection(Direction.RIGHT);
                    break;
                case Input.Keys.UP:
                    updateDirection(Direction.UP);
                    break;
                case Input.Keys.DOWN:
                    updateDirection(Direction.DOWN);
                    break;
            }
            return true;
        }
    }

    private class BodyPart {

        private float x, y;
        private Texture texture;

        public BodyPart(Texture texture) {
            this.texture = texture;
        }

        public void updateBodyPosition(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public void draw(Batch batch) {
            if (!(x == getX() && y == getY())) batch.draw(texture, x, y);
        }
    }

    public Snake(GameScreen mainScreen) {
        super();
        this.mainScreen = mainScreen;
        bodyParts = new Array<BodyPart>();
        snakeHead = new Texture(Gdx.files.internal("snakehead.png"));
        snakeBody = new Texture(Gdx.files.internal("snakebody.png"));
        this.addListener(new SnakeHandling());

        this.init();
    }

    private void moveSnake() {
        snakeXBeforeUpdate = getX();
        snakeYBeforeUpdate = getY();

        switch (snakeDirection) {
            case RIGHT: {
                setX(getX() + SNAKE_MOVEMENT);
                return;
            }
            case LEFT: {
                setX(getX() - SNAKE_MOVEMENT);
                return;
            }
            case UP: {
                setY(getY() + SNAKE_MOVEMENT);
                return;
            }
            case DOWN: {
                setY(getY() - SNAKE_MOVEMENT);
                return;
            }
        }
    }

    private void checkForOutOfBounds() {
        Viewport v = this.getStage().getViewport();
        if (getX() >= v.getWorldWidth()) {
            setX(0);
        }
        if (getX() < 0) {
            setX(MathUtils.round(v.getWorldWidth()) - SNAKE_MOVEMENT);
        }
        if (getY() >= v.getWorldHeight()) {
            setY(0);
        }
        if (getY() < 0) {
            setY(MathUtils.round(v.getWorldHeight()) - SNAKE_MOVEMENT);
        }
    }

    private void updateBodyPartsPosition() {
        if (bodyParts.size > 0) {
            BodyPart bodyPart = bodyParts.removeIndex(0);
            bodyPart.updateBodyPosition(snakeXBeforeUpdate, snakeYBeforeUpdate);
            bodyParts.add(bodyPart);
        }
    }

    private void checkSnakeBodyCollision() {
        for (BodyPart bodyPart : bodyParts) {
            if (bodyPart.x == getX() && bodyPart.y == getY())
                mainScreen.setFinJuego();
        }
    }

    public void init() {
        setPosition(0, 0);
        bodyParts.clear();
        snakeDirection = Direction.RIGHT;
        directionSet = false;
        timer = MOVE_TIME;
        snakeXBeforeUpdate = 0;
        snakeYBeforeUpdate = 0;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        timer -= delta;
        if (timer <= 0) {
            timer = MOVE_TIME;
            moveSnake();
            checkForOutOfBounds();
            updateBodyPartsPosition();
            checkSnakeBodyCollision();
            directionSet = false;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(snakeHead, getX(), getY());
        for (BodyPart bodyPart : bodyParts) {
            bodyPart.draw(batch);
        }
    }

    public void addBodyPart() {
        BodyPart bodyPart = new BodyPart(snakeBody);
        bodyPart.updateBodyPosition(getX(), getY());
        bodyParts.insert(0, bodyPart);
    }
}
