package com.pddm.game.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pddm.game.Screens.GameScreen;
import com.pddm.game.Stages.MainStage;

public class Snake extends Actor implements Initiable {

    private enum Direction { RIGHT, LEFT, UP, DOWN }

    private static final float MOVE_TIME = 0.25F;
    private static final int SNAKE_MOVEMENT = GameScreen.GRID_CELL;
    private float snakeXBeforeUpdate, snakeYBeforeUpdate;
    private Texture snakeHead;
    private Texture snakeBody;
    private Array<BodyPart> bodyParts;

    private float timer;
    private Direction snakeDirection;
    private boolean directionSet;

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

    // Body Part is not going to be added neither a stage nor a group
    // but I take advantage of their features.
    private class BodyPart extends Actor{
        private Texture texture;
        private Actor head;

        public BodyPart(float x, float y, Texture texture, Actor head) {
            this.texture = texture;
            this.head = head;
            setPosition(x, y);
            setSize(texture.getWidth(), texture.getHeight());
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            super.draw(batch, parentAlpha);
            if (!(getX() == head.getX() && getY() == head. getY()))
                batch.draw(
                        texture,
                        getX(), getY(), getOriginX(), getOriginY(),
                        getWidth(), getHeight(), getScaleX(), getScaleY(),
                        getRotation(),0,0,
                        texture.getWidth(), texture.getHeight(),false,false);
        }
    }

	public Snake() {
		super();
        this.setUserObject("snake"); // Allows to a specific object being found through a tag.
		bodyParts = new Array<BodyPart>();
		snakeHead = new Texture(Gdx.files.internal("snakehead.png"));
		snakeBody = new Texture(Gdx.files.internal("snakebody.png"));
		this.addListener(new SnakeHandling());
		updateAnimations();
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
            bodyPart.setPosition(snakeXBeforeUpdate, snakeYBeforeUpdate);
            bodyParts.add(bodyPart);
        }
    }

    private void checkSnakeBodyCollision() {
        for (BodyPart bodyPart : bodyParts) {
            if (bodyPart.getX() == getX() && bodyPart.getY() == getY())
                ((MainStage)getStage()).setFinJuego();
        }
    }

    public void init() {
        setPosition(0, 0);
        setSize(snakeHead.getWidth(), snakeHead.getHeight());
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

		bodyParts.forEach(bp -> bp.act(delta)); // Otherwise the body parts animations won't be updated.
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(
				snakeHead,
				getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(),
				getRotation(),0,0,
				snakeHead.getWidth(),snakeHead.getHeight(),false,false);

        bodyParts.forEach(bp -> bp.draw(batch, parentAlpha));
	}

    public void addBodyPart() {
        BodyPart bodyPart = new BodyPart(getX(), getY(), snakeBody, this);
        bodyParts.insert(0, bodyPart);
        updateAnimations();
    }

    private void updateAnimations(){
        this.clearActions();
        this.addAction(
                Actions.forever(
                        Actions.sequence(
                            Actions.scaleTo(0.85f, 0.85f, 0.5f),
                            Actions.scaleTo(1.05f, 1.05f, 0.5f)
                ))
        );
        float delay = 0.15f;
        for (BodyPart bp: bodyParts) {
            bp.clearActions();
            bp.addAction(
                    Actions.forever(
                            Actions.sequence(
                                Actions.delay(delay),
                                Actions.scaleTo(0.85f, 0.85f, 0.5f),
                                Actions.scaleTo(1.05f, 1.05f, 0.5f)

                    ))
            );
            delay += 0.15f;
        }
    }
}
