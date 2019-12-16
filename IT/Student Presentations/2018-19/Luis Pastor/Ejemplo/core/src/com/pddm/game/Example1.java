/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pddm.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 *
 * @author Luis
 */
public class Example1 extends ScreenAdapter {

    private Viewport viewport;
    private Camera camera;
    private static final float WORLD_WIDTH = 640;
    private static final float WORLD_HEIGHT = 480;
    SpriteBatch batch;

    World world;
    private float accumulator = 0;
    Box2DDebugRenderer debugRenderer;
    Body body;

    @Override
    public void show() {
        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        camera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
        camera.update();

        Box2D.init();
        world = new World(new Vector2(0, -10), true);
        debugRenderer = new Box2DDebugRenderer();

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DynamicBody;
        bodyDef.position.set(0, 20);
        bodyDef.angularVelocity = -1;

        body = world.createBody(bodyDef);

        body.applyLinearImpulse(35.0f, 75.f, bodyDef.position.x, bodyDef.position.y, true);

        CircleShape circle = new CircleShape();
        circle.setRadius(10f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f; 
        
        Fixture fixture = body.createFixture(fixtureDef);

        circle.dispose();

        BodyDef groundBodyDef = new BodyDef();

        groundBodyDef.type = BodyType.KinematicBody;
        groundBodyDef.position.set(new Vector2(0, 10));

        Body groundBody = world.createBody(groundBodyDef);

        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(camera.viewportWidth, 10.0f);

        groundBody.createFixture(groundBox, 0.0f);
        groundBody.setLinearVelocity(0.0f, 1.0f);

        groundBox.dispose();
    }

    @Override
    public void render(float delta) {
        doPhysicsStep(delta);
        debugRenderer.render(world, camera.combined);

    }

    private void doPhysicsStep(float deltaTime) {
        
        float frameTime = Math.min(deltaTime, 0.25f);
        accumulator += frameTime;
        while (accumulator >= 1 / 60f) {
            world.step(1 / 60f, 3, 2);
            accumulator -= 1 / 60f;
            Vector2 vel = body.getLinearVelocity();
            Vector2 pos = body.getPosition();

            if (Gdx.input.isKeyPressed(Keys.A) && vel.x > -100) {
                body.applyLinearImpulse(-8f, 0, pos.x, pos.y, true);
            }

            if (Gdx.input.isKeyPressed(Keys.D) && vel.x < 100) {
                body.applyLinearImpulse(8f, 0, pos.x, pos.y, true);
            }
            clearScreen();
        }
    }


    private void clearScreen() {
        Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b, Color.BLACK.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    
}
