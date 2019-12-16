package com.pddm.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.physics.box2d.joints.PulleyJoint;
import com.badlogic.gdx.physics.box2d.joints.PulleyJointDef;
import com.badlogic.gdx.physics.box2d.joints.RopeJointDef;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.pddm.game.Utils.Constants.PPM;

public class MyPDDMGame extends ApplicationAdapter {
    public static float PPM = 32; //PIXELS PER METER

    private OrthographicCamera camera;
    private World world;
    private Body player, platform, target;
    private Box2DDebugRenderer b2dr;

    @Override
    public void create() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w / 2, h / 2);
        camera.zoom += 1.3f;

        world = new World(new Vector2(0, -9.5f), false);
        b2dr = new Box2DDebugRenderer();

        target = createBox(-3 * 32, 4 * 32, 30, 30, false, true, 1);
        target.setLinearDamping(1f);

        player = createBox(2 * 32, 500, 30, 32, false, true, 6);
        player = createBox(2 * 32, 500, 30, 32, false, true, 6);
        platform = createBox(0, -180, 800, 32, true, true, 1);
        buildRopeJoints();
        createBridge();
        buildPulleyJoints();
    }

    public void createBridge() {
        ArrayList<Body> bodies = new ArrayList<>();

        bodies.add(createBox(400, -180, 32, 32, true, true, 1));

        for (int i = 1; i <= 20; i++) {

            if (i == 20) {
                bodies.add(createBox(850, -180, 32, 32, true, true, 1));
            } else {
                bodies.add(createBox(19 * 32, 0, 14, 10, false, false, 0f));
            }

            RopeJointDef rDef = new RopeJointDef();
            rDef.bodyA = bodies.get(i - 1);
            rDef.bodyB = bodies.get(i);
            rDef.collideConnected = true;
            rDef.maxLength = 0.8f;

            rDef.localAnchorA.set(0, -0.25f);
            rDef.localAnchorB.set(0, 0.25f);

            world.createJoint(rDef);
        }

    }


    public void buildPulleyJoints() {
        Body bodyA = createBox(-100, -30, 30, 32, false, true, 1);
        Body bodyB = createBox(100, 30, 30, 32, false, true, 1);

        bodyA.setLinearDamping(1.75f);
        bodyB.setLinearDamping(1.75f);

        PulleyJointDef pDef = new PulleyJointDef();
        pDef.bodyA = bodyA;
        pDef.bodyB = bodyB;
        pDef.collideConnected = false;
        pDef.localAnchorA.set(0, 0);
        pDef.localAnchorB.set(0, 0);
        pDef.lengthA = 4;
        pDef.lengthB = 2;
        pDef.groundAnchorA.set(-3, 4);
        pDef.ratio = 1f;

        pDef.groundAnchorB.set(2, 4);

        world.createJoint(pDef);
    }

    public void buildRopeJoints() {
        ArrayList<Body> bodies = new ArrayList<>();
        int inicio = 56;
        bodies.add(createBox(0, inicio, 30, 32, true, true, 1));

        for (int i = 1; i < 5; i++) {
            bodies.add(createBox(0, (-i * 32) + inicio, 4, 10, false, false, 1));

            RopeJointDef rDef = new RopeJointDef();
            rDef.bodyA = bodies.get(i - 1);
            rDef.bodyB = bodies.get(i);
            rDef.collideConnected = true;
            rDef.maxLength = 0.5f;

            rDef.localAnchorA.set(0, -0.25f);
            rDef.localAnchorB.set(0, 0.25f);

            world.createJoint(rDef);
        }
    }


    public Body createBox(int x, int y, int width, int height, boolean isStatic, boolean fixedRotation, float density) {
        Body pBody;
        BodyDef def = new BodyDef();

        if (isStatic) {
            def.type = BodyDef.BodyType.StaticBody;
        } else {
            def.type = BodyDef.BodyType.DynamicBody;
        }


        def.position.set(x / PPM, y / PPM);
        def.fixedRotation = fixedRotation;
        pBody = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / PPM, height / 2 / PPM);

        pBody.createFixture(shape, density);
        shape.dispose();
        return pBody;
    }


    @Override
    public void render() {
        update(Gdx.graphics.getDeltaTime());
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        b2dr.render(world, camera.combined.scl(PPM));
    }


    public void update(float delta) {
        world.step(1 / 60f, 4, 2);

        inputUpdate(delta);
        cameraUpdate();
    }

    public void inputUpdate(float delta) {
        float x = 0, y = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            x += 0.75;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            x -= 0.75;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            y += 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            y -= 1;
        }

        if (x != 0) {
            target.setLinearVelocity(x * 350 * delta, target.getLinearVelocity().y);
        }
        if (y != 0) {
            target.setLinearVelocity(target.getLinearVelocity().x, y * 350 * delta);
        }
    }

    public void cameraUpdate() {
        Vector3 position = camera.position;
        position.x = target.getPosition().x * PPM;
        position.y = target.getPosition().y * PPM;
        camera.position.set(position);
        camera.update();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width / 2, height / 2);
    }

    @Override
    public void dispose() {
        world.dispose();
        b2dr.dispose();
    }
}
