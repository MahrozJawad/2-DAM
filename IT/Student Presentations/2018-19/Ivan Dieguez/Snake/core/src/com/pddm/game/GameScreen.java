package com.pddm.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.ai.steer.behaviors.Hide;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

import box2dLight.ChainLight;
import box2dLight.ConeLight;
import box2dLight.DirectionalLight;
import box2dLight.Light;
import box2dLight.PointLight;
import box2dLight.RayHandler;


public class GameScreen extends ScreenAdapter {
    private SpriteBatch batch;

    private Texture snakeHead;
    private Texture apple;
    private Texture snakeBody;
    private Texture apple_bad;

    private Texture fondo1, fondo2, fondo3;


    private static final float MOVE_TIME = 0.5F;
    private float timer = MOVE_TIME;

    private static final int SNAKE_MOVEMENT = 32;
    private int snakeX = 0, snakeY = 0;

    private boolean appleAvailable = false;
    private int appleX, appleY;


    private boolean appleBadAvailable = false;
    private int appleBadX, appleBadY;


    private Array<BodyPart> bodyParts = new Array<BodyPart>();

    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;
    private int snakeDirection = RIGHT;

    private static final int GRID_CELL = 32;
    private ShapeRenderer shapeRenderer;
    private boolean directionSet;


    private int contadorFondo=0;
    private Texture fondoAColocar = new Texture(Gdx.files.internal("bg1.png"));
    private Array<Texture> fondos = new Array<>();


    private int snakeXBeforeUpdate = 0, snakeYBeforeUpdate = 0;

    private enum STATE {
        PLAYING, GAME_OVER
    }

    private STATE state = STATE.PLAYING;


    private BitmapFont bitmapFont;
    private GlyphLayout layout = new GlyphLayout();


    private static final String GAME_OVER_TEXT = "¡GAME OVER!" + "\r\n" + "SPACE TO RESTART";


    private static final int POINTS_PER_APPLE = 20;

    private static final float WORLD_WIDTH = 640;
    private static final float WORLD_HEIGHT = 480;

    private Viewport viewPort;
    private OrthographicCamera orthographicCamera;
    private PerspectiveCamera perspectiveCamera;
    private float lookAtX = WORLD_WIDTH / 2;
    private float lookAtY = WORLD_HEIGHT / 2;
    private float lookAtZ = 0;

    private int score = 0;
    private Viewport viewPortScore;

    private float posCameraX, posCameraY, minCameraX, maxCameraX, minCameraY, maxCameraY;

    //Box2D

    private World world;
    private Box2DDebugRenderer renderer;
    private Body wallBody, circleBody;
    private Fixture wall1Fixture, wall2Fixture;

    //Box2DLights
    private RayHandler rayHandler;
    private Light light, scoreLigh;
    private int lightDirection = 0;


    @Override
    public void show() {
        posCameraX = WORLD_WIDTH / 2;
        posCameraY = WORLD_HEIGHT / 2;


        orthographicCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        orthographicCamera.position.set(posCameraX, posCameraY, 0);


        perspectiveCamera = new PerspectiveCamera(60, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        perspectiveCamera.near = 1f;
        perspectiveCamera.far = WORLD_HEIGHT *2;
        perspectiveCamera.position.set(WORLD_WIDTH / 2, (WORLD_HEIGHT / 2) * 0.4f, WORLD_HEIGHT);
        perspectiveCamera.lookAt(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
        viewPort = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, orthographicCamera);


        viewPortScore = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT);
        viewPortScore.getCamera().position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
        viewPortScore.getCamera().lookAt(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);

        bitmapFont = new BitmapFont();
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        snakeHead = new Texture(Gdx.files.internal("snakehead.png"));
        apple = new Texture(Gdx.files.internal("apple.png"));
        snakeBody = new Texture(Gdx.files.internal("snakeBody.png"));
        apple_bad = new Texture(Gdx.files.internal("apple_bad.png"));
        fondo1 = new Texture(Gdx.files.internal("bg1.png"));
        fondo2 = new Texture(Gdx.files.internal("bg2.png"));
        fondo3 = new Texture(Gdx.files.internal("bg3.png"));

        fondos.add(fondo1);
        fondos.add(fondo2);
        fondos.add(fondo3);

        //Box2D
        world = new World(new Vector2(0,0), true);
        renderer = new Box2DDebugRenderer();

        BodyDef wallBodyDef = new BodyDef();
        wallBodyDef.position.set(96,96);
        wallBodyDef.type = BodyDef.BodyType.StaticBody;
        wallBody = world.createBody(wallBodyDef);

        PolygonShape wallshape = new PolygonShape();
        wallshape.setAsBox(32,32);
        FixtureDef wallFixtureDef = new FixtureDef();
        wallFixtureDef.density = 1;
        wallFixtureDef.shape = wallshape;
        wall1Fixture = wallBody.createFixture(wallFixtureDef);

        BodyDef circleBodyDef = new BodyDef();
        circleBodyDef.position.set(384,192);
        circleBodyDef.type = BodyDef.BodyType.StaticBody;
        circleBody = world.createBody(circleBodyDef);
        CircleShape circleshape = new CircleShape();
        circleshape.setRadius(24);
        FixtureDef circleFixtureDef = new FixtureDef();
        circleFixtureDef.density = 1;
        circleFixtureDef.shape = circleshape;
        wall2Fixture = circleBody.createFixture(circleFixtureDef);

        //Box2DLights
        rayHandler = new RayHandler(world);
        rayHandler.setShadows(true);
        rayHandler.setAmbientLight(1,1,1,0f);
        rayHandler.setBlurNum(1);
        light = new PointLight(rayHandler, 128, new Color(1,1,1,1), 200, snakeX, snakeY);
        scoreLigh = new PointLight(rayHandler, 128, new Color(1,1,1,1), 200, 20, 475);

    }

    @Override
    public void render(float delta) {

        changeCamera();

        if (viewPort.getCamera() == orthographicCamera) {
            zoom();
            followSnake();
        } else {
            moveCameraPerspective();
        }

        viewPort.getCamera().update();



        switch (state) {
            case PLAYING: {
                queryInput();
                updateSnake(delta);
                checkAppleCollision();
                checkAppleBADCollision();
                checkAndPlaceApple();
                checkAndPlaceAppleBAD();

            }
            break;
            case GAME_OVER: {
                checkForRestart();

            }
            break;
        }
        clearScreen();
        drawBackground();
        //drawGrid();
        draw();

        drawScoreAndGameOver();


        //Box2D
        world.step(delta, 6, 2);
        renderer.render(world, orthographicCamera.combined);

        //Box2DLight
        light.setPosition(snakeX + 16, snakeY + 16);
        rayHandler.setCombinedMatrix(orthographicCamera);
        rayHandler.updateAndRender();

        if (light.getClass() == ConeLight.class ) {
            light.setDirection(lightDirection);
        }
        if (light.getClass() == DirectionalLight.class ) {
            light.setDirection(lightDirection);
        }

    }


    public void followSnake() {
        minCameraX = ((OrthographicCamera) viewPort.getCamera()).zoom * (((OrthographicCamera) viewPort.getCamera()).viewportWidth / 2);
        maxCameraX = WORLD_WIDTH - minCameraX;

        minCameraY = ((OrthographicCamera) viewPort.getCamera()).zoom * (((OrthographicCamera) viewPort.getCamera()).viewportHeight / 2);
        maxCameraY = WORLD_HEIGHT - minCameraY;

        viewPort.getCamera().position.set((maxCameraX < snakeX) ? maxCameraX : snakeX < minCameraX ? minCameraX : snakeX,
                (maxCameraY < snakeY) ? maxCameraY : snakeY < minCameraY ? minCameraY : snakeY, 0);
    }


    public void changeCamera() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            viewPort.setCamera((viewPort.getCamera() == orthographicCamera) ? perspectiveCamera : orthographicCamera);

        }
    }

    public void moveCameraPerspective() {
        if (Gdx.input.isKeyPressed(Input.Keys.X)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
                viewPort.getCamera().position.add(0.7f, 0, 0);
               viewPort.getCamera().lookAt(WORLD_WIDTH/2,WORLD_HEIGHT/2,0);
            } else {
                viewPort.getCamera().position.add(-0.7f, 0, 0);
               viewPort.getCamera().lookAt(WORLD_WIDTH/2,WORLD_HEIGHT/2,0);

            }
        }


        if (Gdx.input.isKeyPressed(Input.Keys.Y)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
                viewPort.getCamera().lookAt(lookAtX, lookAtY += 0.7, lookAtZ);
            } else {
                viewPort.getCamera().lookAt(lookAtX, lookAtY -= 0.7, lookAtZ);
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
                viewPort.getCamera().lookAt(lookAtX, lookAtY, lookAtZ += 1);
            } else {
                viewPort.getCamera().lookAt(lookAtX, lookAtY, lookAtZ -= 1);
            }
        }

    }

    private void zoom() {
        if (Gdx.input.isKeyPressed(Input.Keys.O)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
                if (((OrthographicCamera) viewPort.getCamera()).zoom < 1) {
                    ((OrthographicCamera) viewPort.getCamera()).zoom += 0.025;
                }
            } else {
                if (((OrthographicCamera) viewPort.getCamera()).zoom > 0.6) {
                    ((OrthographicCamera) viewPort.getCamera()).zoom -= 0.025;
                }
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewPort.update(width, height);
        viewPortScore.update(width, height);
    }


    private void updateSnake(float delta) {

        queryInput();
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

    private void clearScreen() {
        Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g,
                Color.BLACK.b, Color.BLACK.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void addToScore() {
        score += POINTS_PER_APPLE;
    }


    private void drawScoreAndGameOver() {
        batch.setProjectionMatrix(viewPortScore.getCamera().projection);
        batch.setTransformMatrix(viewPortScore.getCamera().view);
        batch.begin();
        if (state == STATE.PLAYING) {
            String scoreAsString = Integer.toString(score);
            bitmapFont.draw(batch, "Points: " + scoreAsString, 5, 475);

        }
        batch.end();
        batch.begin();
        if (state == STATE.GAME_OVER) {
            layout.setText(bitmapFont, GAME_OVER_TEXT);
            bitmapFont.draw(batch, layout, (WORLD_WIDTH - layout.width) / 2, (viewPortScore.getWorldHeight() - layout.height) / 2);
        }


        batch.end();
    }

    private void draw() {
        batch.setProjectionMatrix(viewPort.getCamera().projection);
        batch.setTransformMatrix(viewPort.getCamera().view);
        batch.begin();


        batch.draw(snakeHead, snakeX, snakeY);
        for (BodyPart bodypart : bodyParts) {
            bodypart.draw(batch);
        }
        if (appleAvailable) {
            batch.draw(apple, appleX, appleY);
        }
        if (appleBadAvailable) {
            batch.draw(apple_bad, appleBadX, appleBadY);
        }


        batch.end();

    }

    private void drawGrid() {
        shapeRenderer.setProjectionMatrix(viewPort.getCamera().projection);
        shapeRenderer.setTransformMatrix(viewPort.getCamera().view);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for (int x = 0; x < viewPort.getWorldWidth(); x += GRID_CELL) {
            for (int y = 0; y < viewPort.getWorldHeight(); y += GRID_CELL) {
                shapeRenderer.rect(x, y, GRID_CELL, GRID_CELL);
            }
        }

        shapeRenderer.end();
    }


    private void drawBackground() {
        batch.setProjectionMatrix(viewPort.getCamera().projection);
        batch.setTransformMatrix(viewPort.getCamera().view);
        batch.begin();

        batch.draw(fondoAColocar, 0, 0);
        batch.end();

    }

    private void queryInput() {
        boolean lPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean rPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean uPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean dPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);

        if (lPressed) updateDirection(LEFT);
        if (rPressed) updateDirection(RIGHT);
        if (uPressed) updateDirection(UP);
        if (dPressed) updateDirection(DOWN);

        //ForBox2DLights
        boolean f1Presed = Gdx.input.isKeyJustPressed(Input.Keys.F1);
        boolean f2Presed = Gdx.input.isKeyJustPressed(Input.Keys.F2);
        boolean f3Presed = Gdx.input.isKeyJustPressed(Input.Keys.F3);
        boolean f4Presed = Gdx.input.isKeyJustPressed(Input.Keys.F4);
        boolean f5Presed = Gdx.input.isKeyJustPressed(Input.Keys.F5);
        boolean f6Presed = Gdx.input.isKeyJustPressed(Input.Keys.F6);
        boolean f7Presed = Gdx.input.isKeyJustPressed(Input.Keys.F7);

        if (f1Presed) changeColorLight();
        if (f2Presed) changeToConeLight();
        if (f3Presed) changeDistanceLight();
        if (f4Presed) changeToDirectional();
        if (f5Presed) changeToPointLight();
        if (f6Presed) changeAmbientLight();
        if (f7Presed) OriginalAmbientLight();


    }

    private void OriginalAmbientLight() {
        rayHandler.setAmbientLight(0,0,0,0);
    }

    private void changeAmbientLight() {
        rayHandler.setAmbientLight(MathUtils.random(), MathUtils.random(), MathUtils.random(), MathUtils.random());
        //rayHandler.setAmbientLight(new Random().nextFloat(), new Random().nextFloat(), new Random().nextFloat(), new Random().nextFloat());
    }

    private void changeToPointLight() {
        Color color = light.getColor();
        light.remove();
        light = new PointLight(rayHandler, 128, color, 200, snakeX, snakeY);
    }

    private void changeToDirectional() {
        Color color = light.getColor();
        light.remove();
        light = new DirectionalLight(rayHandler,50, new Color(0.5f,0.5f,0.5f,1),lightDirection);
        System.out.println(MathUtils.random());
    }

    private void changeDistanceLight() {
        light.setDistance(new Random().nextInt(600));
    }

    private void changeToConeLight() {
        Color color = light.getColor();
        light.remove();
        light = new ConeLight(rayHandler, 128, color, 200, snakeX, snakeY, lightDirection, 32);
    }

    private void changeColorLight(){

        light.setColor(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1);
    }

    private void moveSnake() {
        snakeXBeforeUpdate = snakeX;
        snakeYBeforeUpdate = snakeY;

        switch (snakeDirection) {
            case RIGHT: {
                snakeX += SNAKE_MOVEMENT;
                lightDirection = 0;
                return;
            }
            case LEFT: {
                snakeX -= SNAKE_MOVEMENT;
                lightDirection = 180;
                return;
            }
            case UP: {
                snakeY += SNAKE_MOVEMENT;
                lightDirection = 90;
                return;
            }
            case DOWN: {
                snakeY -= SNAKE_MOVEMENT;
                lightDirection = 270;
                return;
            }
        }
    }

    private void checkForRestart() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            doRestart();

            if (fondos.size-1 > contadorFondo){
                contadorFondo++;
                fondoAColocar = fondos.get(contadorFondo);
            }else{
                contadorFondo=0;
                fondoAColocar = fondos.get(contadorFondo);
            }
        }
    }

    private void doRestart() {
        state = STATE.PLAYING;
        bodyParts.clear();
        snakeDirection = RIGHT;
        directionSet = false;
        timer = MOVE_TIME;
        snakeX = 0;
        snakeY = 0;
        snakeXBeforeUpdate = 0;
        snakeYBeforeUpdate = 0;
        appleAvailable = false;
        appleBadAvailable = false;
        score = 0;
    }

    private void checkForOutOfBounds() {
        if (snakeX >= viewPort.getWorldWidth()) {
            snakeX = 0;

        }
        if (snakeX < 0) {
            snakeX = (int) viewPort.getWorldWidth() - SNAKE_MOVEMENT;

        }
        if (snakeY >= viewPort.getWorldHeight()) {
            snakeY = 0;

        }
        if (snakeY < 0) {
            snakeY = (int) viewPort.getWorldHeight() - SNAKE_MOVEMENT;

        }
    }

    private void checkAndPlaceApple() {
        if (!appleAvailable) {
            do {
                appleX = MathUtils.random((int) viewPort.getWorldWidth()
                        / SNAKE_MOVEMENT - 1) * SNAKE_MOVEMENT;
                appleY = MathUtils.random((int) viewPort.getWorldHeight()
                        / SNAKE_MOVEMENT - 1) * SNAKE_MOVEMENT;

                appleAvailable = true;
            } while (appleX == snakeX && appleY == snakeY);

        }
    }

    private void checkAndPlaceAppleBAD() {
        if (!appleBadAvailable) {
            do {
                appleBadX = MathUtils.random((int) viewPort.getWorldWidth()
                        / SNAKE_MOVEMENT - 1) * SNAKE_MOVEMENT;
                appleBadY = MathUtils.random((int) viewPort.getWorldHeight()
                        / SNAKE_MOVEMENT - 1) * SNAKE_MOVEMENT;

                appleBadAvailable = true;
            } while (appleBadX == snakeX && appleBadY == snakeY);

        }
    }


    private void checkAppleCollision() {
        if (appleAvailable && appleX == snakeX && appleY == snakeY) {
            for (int i = 0; i < 2; i++) {
                BodyPart bodyPart = new BodyPart(snakeBody);
                bodyPart.updateBodyPosition(snakeX, snakeY);
                bodyParts.insert(0, bodyPart);
            }
            addToScore();
            appleAvailable = false;
        }
    }

    private void checkAppleBADCollision() {
        if (appleBadAvailable && appleBadX == snakeX && appleBadY == snakeY) {

            if(bodyParts.size>0){
                bodyParts.removeValue(bodyParts.first(), true);
            }else{
                state = STATE.GAME_OVER;
            }

            score -= 10;
            appleBadAvailable = false;
        }
    }

    private void checkSnakeBodyCollision() {
        for (BodyPart bodyPart : bodyParts) {
            if (bodyPart.x == snakeX && bodyPart.y == snakeY)
                state = STATE.GAME_OVER;
        }
    }

    private void updateBodyPartsPosition() {
        if (bodyParts.size > 0) {
            BodyPart bodyPart = bodyParts.removeIndex(0);
            bodyPart.updateBodyPosition(snakeXBeforeUpdate, snakeYBeforeUpdate);
            bodyParts.add(bodyPart);
        }
    }

    private void updateIfNotOppositeDirection(int newSnakeDirection, int oppositeDirection) {

        if (snakeDirection != oppositeDirection || bodyParts.size == 0)
            snakeDirection = newSnakeDirection;
    }

    private void updateDirection(int newSnakeDirection) {
        if (!directionSet && snakeDirection != newSnakeDirection) {
            directionSet = true;
            switch (newSnakeDirection) {
                case LEFT: {
                    updateIfNotOppositeDirection(newSnakeDirection, RIGHT);
                }
                break;
                case RIGHT: {
                    updateIfNotOppositeDirection(newSnakeDirection, LEFT);
                }
                break;
                case UP: {
                    updateIfNotOppositeDirection(newSnakeDirection, DOWN);
                }
                break;
                case DOWN: {
                    updateIfNotOppositeDirection(newSnakeDirection, UP);
                }
                break;
            }
        }
    }

    private class BodyPart {
        private int x, y;
        private Texture texture;

        public BodyPart(Texture texture) {
            this.texture = texture;
        }

        public void updateBodyPosition(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void draw(Batch batch) {
            if (!(x == snakeX && y == snakeY)) batch.draw(texture, x, y);
        }
    }
}

