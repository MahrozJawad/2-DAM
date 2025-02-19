/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pddm.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;

public class Example3 extends ScreenAdapter {

    private World world;
    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;
    private Body ball, ground;

    private MouseJointDef jointDef;
    private MouseJoint joint;

    @Override
    public void show() {
        world = new World(new Vector2(0, -9.81f), true);
        renderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera();

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                camera.unproject(tmp.set(screenX, screenY, 0));
                world.QueryAABB(queryCallback, tmp.x, tmp.y, tmp.x, tmp.y);
                return true;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                if (joint == null) {
                    return false;
                }

                camera.unproject(tmp.set(screenX, screenY, 0));
                joint.setTarget(tmp2.set(tmp.x, tmp.y));
                return true;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                if (joint == null) {
                    return false;
                }

                world.destroyJoint(joint);
                joint = null;
                return true;
            }

        });

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DynamicBody;

        // ball
        CircleShape ballShape = new CircleShape();
        ballShape.setRadius(1);

        ball = world.createBody(bodyDef);
        ball.createFixture(ballShape, 1);

        ballShape.dispose();

        // ground
        EdgeShape groundShape = new EdgeShape();
        groundShape.set(-500, -20, 500, -20);

        bodyDef.type = BodyType.StaticBody;
        ground = world.createBody(bodyDef);
        ground.createFixture(groundShape, 0);

        groundShape.dispose();

        // mouse joint
        jointDef = new MouseJointDef();
        jointDef.bodyA = ground;
        jointDef.collideConnected = true;
        jointDef.maxForce = 500;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(1 / 60f, 8, 3);
        renderer.render(world, camera.combined);
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width / 10;
        camera.viewportHeight = height / 10;
        camera.update();
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        world.dispose();
        renderer.dispose();
    }

    private Vector3 tmp = new Vector3();
    private Vector2 tmp2 = new Vector2();

    private QueryCallback queryCallback = new QueryCallback() {

        @Override
        public boolean reportFixture(Fixture fixture) {
            if (!fixture.testPoint(tmp.x, tmp.y)) {
                return true;
            }

            jointDef.bodyB = fixture.getBody();
            jointDef.target.set(tmp.x, tmp.y);
            joint = (MouseJoint) world.createJoint(jointDef);
            return false;
        }

    };

}
