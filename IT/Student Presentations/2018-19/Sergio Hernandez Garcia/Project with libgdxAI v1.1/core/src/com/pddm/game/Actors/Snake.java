package com.pddm.game.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pddm.game.Screens.GameScreen;
import com.pddm.game.Stages.MainStage;
import com.pddm.game.ai.AiMap;
import com.pddm.game.ai.AiPathFInding;
import com.pddm.game.ai.Node;

public class Snake extends Actor implements Initiable {

    public enum Direction {RIGHT, LEFT, UP, DOWN}

    private static final float MOVE_TIME = 0.25F;
    private static final int SNAKE_MOVEMENT = GameScreen.GRID_CELL;
    private float snakeXBeforeUpdate, snakeYBeforeUpdate;
    private Texture snakeHead;
    private Texture snakeBody;
    private Array<BodyPart> bodyParts;

    private float timer;
    private Direction snakeDirection;

    private final int grow = 2;
    private final int shrink = grow;

    private AiPathFInding pathfinder;

    private int alternativeWay = 0;

    private class BodyPart extends Actor {
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
            if (!(getX() == head.getX() && getY() == head.getY())) {
                batch.draw(
                        texture,
                        getX(), getY(), getOriginX(), getOriginY(),
                        getWidth(), getHeight(), getScaleX(), getScaleY(),
                        getRotation(), 0, 0,
                        texture.getWidth(), texture.getHeight(), false, false);
            }
        }
    }

    public Snake() {
        super();
        this.setUserObject("snake");
        bodyParts = new Array<>();
        snakeHead = new Texture(Gdx.files.internal("snakehead.png"));
        snakeBody = new Texture(Gdx.files.internal("snakebody.png"));
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

    public void setNextMovement(Node next) {
        if (next != null) {
            alternativeWay = 0;
            if (next.x > getX() / 32) {
                snakeDirection = Direction.RIGHT;
            } else if (next.x < getX() / 32) {
                snakeDirection = Direction.LEFT;
            } else if (next.y > getY() / 32) {
                snakeDirection = Direction.UP;
            } else if (next.y < getY() / 32) {
                snakeDirection = Direction.DOWN;
            }
        } else {
            AiMap map = new AiMap(640 / 32, 480 / 32);
            if (alternativeWay < 80)
                updateMapIgnoringPoisonApples(map);
            pathfinder = new AiPathFInding(map);
            alternativeWay++;
            if (alternativeWay > 40)
                setNextMovement(pathfinder.findAlternativeWay(new Vector2(getX() / 32, getY() / 32)));
            else
                setNextMovement(pathfinder.findNextNode(new Vector2(getX() / 32, getY() / 32), new Vector2(getApple().getX() / 32, getApple().getY() / 32)));

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
                ((MainStage) getStage()).setEndGame();
        }
    }

    public void init() {
        setPosition(0, 0);
        setSize(snakeHead.getWidth(), snakeHead.getHeight());
        bodyParts.clear();
        snakeDirection = Direction.RIGHT;
        timer = MOVE_TIME;
        snakeXBeforeUpdate = 0;
        snakeYBeforeUpdate = 0;
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

    public void updateMap(AiMap map) {
        map.clearMap();
        for (BodyPart bp : bodyParts) {
            map.getNodeAt(MathUtils.floor(bp.getX()) / 32, MathUtils.floor(bp.getY()) / 32).isBody = true;
        }
        for (PoisonApple pa : ((MainStage) getStage()).getPoisonApples()) {
            map.getNodeAt(MathUtils.floor(pa.getX()) / 32, MathUtils.floor(pa.getY()) / 32).isPoisonApple = true;
        }
    }

    public void updateMapIgnoringPoisonApples(AiMap map) {
        map.clearMap();
        for (BodyPart bp : bodyParts) {
            map.getNodeAt(MathUtils.floor(bp.getX()) / 32, MathUtils.floor(bp.getY()) / 32).isBody = true;
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        timer -= delta;
        if (timer <= 0) {
            timer = MOVE_TIME;
            AiMap map = new AiMap(640 / 32, 480 / 32);
            updateMap(map);
            pathfinder = new AiPathFInding(map);
            System.out.println(map);
            setNextMovement(pathfinder.findNextNode(new Vector2(getX() / 32, getY() / 32), new Vector2(getApple().getX() / 32, getApple().getY() / 32)));
            moveSnake();
            checkForOutOfBounds();
            updateBodyPartsPosition();
            checkSnakeBodyCollision();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(
                snakeHead,
                getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(),
                getRotation(), 0, 0,
                snakeHead.getWidth(), snakeHead.getHeight(), false, false);

        bodyParts.forEach(bp -> bp.draw(batch, parentAlpha));
    }

    public void addBodyPart() {
        for (int i = 0; i < grow; i++) {
            BodyPart bodyPart = new BodyPart(getX(), getY(), snakeBody, this);
            bodyParts.insert(0, bodyPart);
        }
    }

    public void removeBodyPart() {
        for (int i = 0; i < shrink; i++) {
            bodyParts.removeIndex(0);
        }
    }

    public int getBodyParts() {
        return bodyParts.size;
    }
}
