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
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.JointDef;
import static com.badlogic.gdx.physics.box2d.JointDef.JointType.MouseJoint;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.physics.box2d.joints.MotorJointDef;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.physics.box2d.joints.PulleyJointDef;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 *
 * @author Luis
 */
public class Example2 extends ScreenAdapter {

    private Viewport viewport;
    private Camera camera;
    private static final float WORLD_WIDTH = 640;
    private static final float WORLD_HEIGHT = 480;
    SpriteBatch batch;

    World world;
    private float accumulator = 0;
    Box2DDebugRenderer debugRenderer;
    Body body;

    DistanceJoint dJ;

    @Override
    public void show() {
        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        camera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
        camera.update();

        Box2D.init();

        world = new World(new Vector2(0, -10), true);
        debugRenderer = new Box2DDebugRenderer();

        DistanceJoint();

    }

    @Override
    public void render(float delta) {
        doPhysicsStep(delta);
        debugRenderer.render(world, camera.combined);

    }

    private void doPhysicsStep(float deltaTime) {
        // max frame time to avoid spiral of death (on slow devices)
        float frameTime = Math.min(deltaTime, 0.25f);
        accumulator += frameTime;
        while (accumulator >= 1 / 60f) {
            world.step(1 / 60f, 3, 2);
            accumulator -= 1 / 60f;

            clearScreen();

        }
        if (Gdx.input.isKeyPressed(Keys.SPACE)) {
            dJ.setLength(10);
        }

        if (Gdx.input.isKeyPressed(Keys.A)) {
            dJ.setLength(70);
        }
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b, Color.BLACK.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void DistanceJoint() {

        Body circulo = Circulo(170);

        Body groundBody = PlataformaCuadrada(150,20, false, 50);
        Body groundBody2 = PlataformaCuadrada(150,70, true, 50);

        DistanceJointDef defJoint = new DistanceJointDef();
        defJoint.length = 70;
        defJoint.bodyA = groundBody;
        defJoint.bodyB = groundBody2;
        defJoint.dampingRatio = 2f;
        defJoint.frequencyHz = 1f;
        
        dJ = (DistanceJoint) world.createJoint(defJoint);

    }

    Body Circulo(float posY) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DynamicBody;

        bodyDef.position.set(150, posY);

        body = world.createBody(bodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(20f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f; // Make it bounce a little bit

        Fixture fixture = body.createFixture(fixtureDef);

        circle.dispose();
        return body;
    }

    Body PlataformaCuadrada(float posX,float posY, Boolean dinamico, float size) {
        PolygonShape groundBox;
        BodyDef groundBodyDef2 = new BodyDef();
        if (dinamico) {
            groundBodyDef2.type = BodyType.DynamicBody;
        }
        groundBodyDef2.position.set(new Vector2(posX, posY));
        Body groundBody2 = world.createBody(groundBodyDef2);
        groundBox = new PolygonShape();
        groundBox.setAsBox(size, 10.0f);
        groundBody2.createFixture(groundBox, 0.3f);
        groundBox.dispose();
        return groundBody2;
    }

}
