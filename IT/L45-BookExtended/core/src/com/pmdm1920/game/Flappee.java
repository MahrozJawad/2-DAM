package com.pmdm1920.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.Disposable;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by James on 17/03/2015.
 */
public class Flappee implements Disposable {

    private static final float DIVE_ACCEL = 0.1F;
    private static final float FLY_ACCEL = 2F;
    private static final float COLLISION_RADIUS = 24f;
    private final Circle collisionCircle;

    private float x = 0;
    private float y = 0;

    private float ySpeed = 0;
    private AnimatedStates animations;

    @Override
    public void dispose() {
        animations.dispose();
    }

    private enum State { UP, DOWN }
    private State state;

    public Flappee(TextureAtlas atlas) {
        state = State.DOWN;
        animations = new AnimatedStates(
                            atlas,
                            "maya",
                            Stream.of(State.values())
                                .map(s->s.toString())
                                .collect(Collectors.toList()),
                            state.toString());
        collisionCircle = new Circle(x, y, COLLISION_RADIUS);
    }

    public Circle getCollisionCircle() {
        return collisionCircle;
    }

    public void update(float delta) {
        ySpeed -= DIVE_ACCEL;
        setPosition(x, y + ySpeed);
        state = ySpeed > 0 ? State.UP : State.DOWN;
    }

    public void flyUp() {
        ySpeed = FLY_ACCEL;
        setPosition(x, y + ySpeed);
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        updateCollisionCircle();
    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }

    public void draw(SpriteBatch batch) {
        TextureRegion flappeeTexture = animations.getCurrentFrame(state.toString());
        float textureX = collisionCircle.x - flappeeTexture.getRegionWidth() / 2;
        float textureY = collisionCircle.y - flappeeTexture.getRegionHeight() / 2;
        batch.draw(flappeeTexture, textureX, textureY);
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.circle(collisionCircle.x, collisionCircle.y, collisionCircle.radius);
    }

    private void updateCollisionCircle() {
        collisionCircle.setX(x);
        collisionCircle.setY(y);
    }
}
