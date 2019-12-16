/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pddm.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.util.Set;

/**
 *
 * @author alumno
 */
public class GameScreen extends ScreenAdapter {

    private static final float WORLD_WIDTH = 640;
    private static final float WORLD_HEIGHT = 480;

    private ParticleEffect particle= new ParticleEffect();
    
    
    private enum STATE {
        PLAYING, GAME_OVER, Pause
    }

    private STATE state = STATE.PLAYING;

    private SpriteBatch batch;
    private SpriteBatch batchScore;
    private Texture snakeHead;
    private Texture apple;
    private Texture snakeBody;
    private Texture fondo1;
    private Texture fondo2;
    private Array<BodyPart> bodyParts = new Array<BodyPart>();
    private int snakeXBeforeUpdate = 0, snakeYBeforeUpdate = 0;
    private boolean appleAvailable = false;
    private int appleX, appleY;
    private static final float MOVE_TIME = 0.25F;
    private float timer = MOVE_TIME;
    private static final int SNAKE_MOVEMENT = 32;
    private int snakeX = 0, snakeY = 0;
    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;
    private int snakeDirection = RIGHT;

    private ShapeRenderer shapeRenderer;
    private static final int GRID_CELL = 32;
    boolean directionSet = false;

    private BitmapFont bitmapFont;
    private GlyphLayout layout = new GlyphLayout();
    private static final String GAME_OVER_TEXT = "Game Over...Tap space to restart";
    private int score = 0;
    private static final int POINTS_PER_APPLE = 20;

    private Viewport viewport;
    private Camera camera;
    private PerspectiveCamera camaraPerspectiveCamera;
    private OrthographicCamera camaraOrthographicCamera;

    private Texture fondoactual;
    private Array<Texture> fondos = new Array<Texture>();

    private void moveSnake() {

        snakeXBeforeUpdate = snakeX;
        snakeYBeforeUpdate = snakeY;
        switch (snakeDirection) {
            case RIGHT: {
                snakeX += SNAKE_MOVEMENT;
                return;

            }
            case LEFT: {
                snakeX -= SNAKE_MOVEMENT;
                return;

            }
            case UP: {
                snakeY += SNAKE_MOVEMENT;
                return;

            }
            case DOWN: {
                snakeY -= SNAKE_MOVEMENT;
                return;

            }

        }

    }

    private void updateBodyPartsPosition() {
        if (bodyParts.size > 0) {
            BodyPart bodyPart = bodyParts.removeIndex(0);
            bodyPart.updateBodyPosition(snakeXBeforeUpdate, snakeYBeforeUpdate);
            bodyParts.add(bodyPart);
        }
    }

    @Override
    public void show() {

        final float FOVY_DEGREES = 60;
        camaraPerspectiveCamera = new PerspectiveCamera(FOVY_DEGREES, 640, 480);
        camaraPerspectiveCamera.position.set(640 / 2, 480 / 2 * -1.75f, 480);
        camaraPerspectiveCamera.near = 1f; // Near Clipping plane or VP.
        camaraPerspectiveCamera.far = WORLD_WIDTH * 2f; // Far Clipping plane.
        camaraPerspectiveCamera.lookAt(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);

        camaraOrthographicCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());;
        camera = camaraOrthographicCamera;
        camera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
        ((OrthographicCamera) camera).zoom = 1;

        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        ((OrthographicCamera) camera).zoom = 1;

        bitmapFont = new BitmapFont();
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        batchScore = new SpriteBatch();
        snakeHead = new Texture(Gdx.files.internal("snakehead.png"));
        apple = new Texture(Gdx.files.internal("apple.png"));
        snakeBody = new Texture(Gdx.files.internal("snakebody.png"));
        fondo1 = new Texture(Gdx.files.internal("bg2.png"));
        fondo2 = new Texture(Gdx.files.internal("bg3.png"));

        fondoactual = fondo1;

        particle.load(Gdx.files.internal("rain.p"),Gdx.files.internal(""));
        particle.getEmitters().first().setPosition(0,Gdx.graphics.getHeight());
        particle.start();


    }

    @Override
    public void render(float delta) {

        switch (state) {
            case PLAYING:
                queryInput();
                Pausar();
                updateSnake(delta);
                updateLluvia(delta);

                checkAppleCollision();
                checkAndPlaceApple();
                
             /*   batch2.begin();
                effect.draw(batch2,delta);
                batch2.end();*/
                        

                break;
            case GAME_OVER:
                checkForRestart();

                break;
                
            case Pause:
                Pausar();
                break;
        }

        clearScreen(fondoactual);

      //  drawGrid();
        draw();
        drawParticle();

    }

    private void drawParticle() {
        batch.begin();
        particle.draw(batch);
        batch.end();
    }

    private void updateLluvia(float delta)
    {
        particle.update(delta);

    }

    private void updateSnake(float delta) {

        if (state != STATE.Pause) {
            timer -= delta;
            if (timer <= 0) {
                timer = MOVE_TIME;
                moveSnake();
                checkForOutOfBounds();
                updateBodyPartsPosition();
                checkSnakeBodyCollision();
                directionSet = false;
                if (camera.getClass() == OrthographicCamera.class) {
                    moidificaCamara();
                }

                viewport.getCamera().update();
            }

        }

    }

    private void moidificaCamara() {

        Float minCameraX = ((OrthographicCamera) camera).zoom * (camera.viewportWidth / 2);
        Float maxCameraX = WORLD_WIDTH - minCameraX;
        Float minCameraY = ((OrthographicCamera) camera).zoom * (camera.viewportHeight / 2);
        Float maxCameraY = WORLD_HEIGHT - minCameraY;
        Float posCameraX = (snakeX > minCameraX ? snakeX : minCameraX);
        posCameraX = (posCameraX < maxCameraX ? posCameraX : maxCameraX);
        Float posCameraY = (snakeY > minCameraY ? snakeY : minCameraY);
        posCameraY = (posCameraY < maxCameraY ? posCameraY : maxCameraY);

        /*if (((OrthographicCamera) viewport.getCamera()).zoom < 0.60) {
            viewport.getCamera().position.set(posCameraX, posCameraY, 0);
            camaraOrthographicCamera = (OrthographicCamera) camera;
        } else {
            viewport.getCamera().position.set(maxCameraX, maxCameraY, 0);
            camaraOrthographicCamera = (OrthographicCamera) camera;
        }*/
        camera.position.set(posCameraX,posCameraY,0);

    }

    private void clearScreen(Texture fondo) {

        Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b, Color.BLACK.a);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(fondo, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
        batch.end();
    }

    private void draw() {

        batch.setProjectionMatrix(camera.projection);
        batch.setTransformMatrix(camera.view);
        batch.begin();
        particle.draw(batch);
        batch.draw(snakeHead, snakeX, snakeY);
        for (BodyPart bodyPart : bodyParts) {
            bodyPart.draw(batch);
        }

        if (appleAvailable) {
            batch.draw(apple, appleX, appleY);
        }

        if (state == STATE.GAME_OVER) {
            layout.setText(bitmapFont, GAME_OVER_TEXT);
            bitmapFont.draw(batch, GAME_OVER_TEXT, (viewport.getWorldWidth() - layout.width) / 2, (viewport.getWorldHeight() - layout.height) / 2);
        }
         if (state == STATE.Pause) {
            layout.setText(bitmapFont, GAME_OVER_TEXT);
            bitmapFont.draw(batch, "Pausa", (viewport.getWorldWidth() - layout.width) / 2, (viewport.getWorldHeight() - layout.height) / 2);
        }

        batch.end();

        batchScore.begin();
        drawSore();
        batchScore.end();

    }

    private void checkForOutOfBounds() {
        if (snakeX >= viewport.getWorldWidth()) {
            snakeX = 0;
        }
        if (snakeX < 0) {
            snakeX = (int) viewport.getWorldWidth() - SNAKE_MOVEMENT;
        }
        if (snakeY >= (int) viewport.getWorldHeight()) {
            snakeY = 0;
        }
        if (snakeY < 0) {
            snakeY = (int) viewport.getWorldHeight() - SNAKE_MOVEMENT;
        }

    }

    private void queryInput() {
        boolean lPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean rPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean uPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean dPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);
        boolean fPressed = Gdx.input.isKeyJustPressed(Input.Keys.F);
        boolean oPressed = Gdx.input.isKeyJustPressed(Input.Keys.O);
        boolean shiftoPressed = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && Gdx.input.isKeyPressed(Input.Keys.O);
        boolean pPressed = Gdx.input.isKeyJustPressed(Input.Keys.P);
        boolean shiftX = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && Gdx.input.isKeyPressed(Input.Keys.X);
        boolean shiftY = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && Gdx.input.isKeyPressed(Input.Keys.Y);
        boolean shiftZ = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && Gdx.input.isKeyPressed(Input.Keys.Z);


        if (lPressed) {
            updateDirection(LEFT);
        }
        if (rPressed) {
            updateDirection(RIGHT);
        }
        if (uPressed) {
            updateDirection(UP);
        }
        if (dPressed) {
            updateDirection(DOWN);
        }
        if (fPressed) {
            updatefondo();

        }
        if (camera.getClass() == OrthographicCamera.class) {
            if (oPressed) {

                if (((OrthographicCamera) camera).zoom < 1) {
                    ((OrthographicCamera) camera).zoom += 0.025;
                }

            }
            if (shiftoPressed) {
                if (((OrthographicCamera) camera).zoom > 0.60) {
                    ((OrthographicCamera) camera).zoom -= 0.025;
                }

            }
        }

        if (pPressed) {

            if (camera.getClass() == OrthographicCamera.class) {
                camera = camaraPerspectiveCamera;

            } else {
                camera = camaraOrthographicCamera;
            }

            camera.update();
        }

        if (camera.getClass() == PerspectiveCamera.class) {

            if (shiftX) {

                camera.translate(10, 0, 0);

            }
            if (shiftY) {
                camera.translate(0, 10, 0);

            }
            if (shiftZ) {

                camera.translate(0, 0, 10);

            }
            camera.lookAt(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);

            camera.update();

        }
     

    }
    
    private void Pausar()
    {
        boolean espacio = Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
           if (espacio) {
            if(state==STATE.PLAYING)
            {
                  state = STATE.Pause;
            
            }
            else
            {
                state=STATE.PLAYING;
            
            }
          

        }
    
    
    }

    private void updatefondo() {
        if (fondoactual.equals(fondo1)) {
            fondoactual = fondo2;
        } else {
            fondoactual = fondo1;
        }
    }

    private void checkAndPlaceApple() {
        if (!appleAvailable) {
            do {
                appleX = (int) MathUtils.random(viewport.getWorldWidth() / SNAKE_MOVEMENT - 1) * SNAKE_MOVEMENT;
                appleY = (int) MathUtils.random(viewport.getWorldHeight() / SNAKE_MOVEMENT - 1) * SNAKE_MOVEMENT;

                appleAvailable = true;
            } while (appleX == snakeX && appleY == snakeY);
        }

    }

    private void checkAppleCollision() {
        if (appleAvailable && appleX == snakeX && appleY == snakeY) {
            appleAvailable = false;
            BodyPart bodyPart = new BodyPart(snakeBody);
            bodyPart.updateBodyPosition(snakeX, snakeY);
            bodyParts.insert(0, bodyPart);
            addScore();

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
            if (!(x == snakeX && y == snakeY)) {
                batch.draw(texture, x, y);
            }
        }
    }

    private void drawGrid() {

        shapeRenderer.setProjectionMatrix(camera.projection);
        shapeRenderer.setTransformMatrix(camera.view);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        for (int x = 0; x < viewport.getWorldWidth(); x += GRID_CELL) {
            for (int y = 0; y < viewport.getWorldHeight(); y += GRID_CELL) {
                shapeRenderer.rect(x, y, GRID_CELL, GRID_CELL);

            }

        }
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width, height);
    }

    private void updateIfNotOppositeDirection(int newSnakeDirection, int oppositeDirection) {

        if (snakeDirection != oppositeDirection || bodyParts.size == 0) {
            snakeDirection = newSnakeDirection;
        }

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

    private void checkSnakeBodyCollision() {
        for (BodyPart bodyPart : bodyParts) {
            if (bodyPart.x == snakeX && bodyPart.y == snakeY) {
                state = STATE.GAME_OVER;
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
        score = 0;

    }

    private void checkForRestart() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            doRestart();
        }

    }

    private void addScore() {
        score += POINTS_PER_APPLE;

    }

    private void drawSore() {
        if (state == STATE.PLAYING) {
            String scoreAsString = Integer.toString(score);
            GlyphLayout scoreBounds = new GlyphLayout();
            scoreBounds.setText(bitmapFont, scoreAsString);
            //bitmapFont.draw(batchScore, scoreAsString, (viewport.getWorldWidth() - scoreBounds.width) , (4 * viewport.getWorldHeight() / 5) - scoreBounds.height / 2);
            bitmapFont.draw(batchScore, scoreAsString, (scoreBounds.width), 20);

        }

    }
    
    
}
