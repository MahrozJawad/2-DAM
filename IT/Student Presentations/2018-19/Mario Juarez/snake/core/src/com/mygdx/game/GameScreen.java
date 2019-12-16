/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

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
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author mario
 */
public class GameScreen extends ScreenAdapter {

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width, height);//To change body of generated methods, choose Tools | Templates.
    }

    private static final float WORLD_WIDTH = 640;
    private static final float WORLD_HEIGHT = 480;
    private float MOVE_TIME = 0.1F;
    private int snakeX = 0, snakeY = 0;
    private float timer = MOVE_TIME;
    private static final int SNAKE_MOVEMENT = 32;

    private Texture snakeHead;
    private Texture snakeBody;

    private static final int APPLE = 0;
    private static final int POISONED_APPLE = 1;
    private static final int MUSHROOM = 2;

    private Texture[] eaters;
    private int nEaters = 0;

    private Texture[] fondos;
    private int nFondo = 0;

    private Array<BodyPart> bodyParts = new Array<BodyPart>();

    private SpriteBatch batch;

    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;
    private int snakeDirection = LEFT;

    private boolean appleAvailable = false;
    private int appleX, appleY;

    private int snakeXBeforeUpdate = 0, snakeYBeforeUpdate = 0;

    private ShapeRenderer shapeRenderer;

    private static final int GRID_CELL = 32;

    private boolean directionSet = false;

    private boolean hasHit = false;

    private enum STATE {
        PLAYING, GAME_OVER, PAUSED
    }
    private STATE state = STATE.PLAYING;

    private BitmapFont bitmapFont;

    private GlyphLayout layout = new GlyphLayout();

    private static final String GAME_OVER_TEXT = "Game Over... Tap space to restart!";
    private static final String PAUSE_TEXT = "Game paused";
    private int score = 0;
    private static final int POINTS_PER_APPLE = 20;

    private Viewport viewport;
    private Viewport scoreViewport;
    private OrthographicCamera cameraO;
    private PerspectiveCamera cameraP;
    private OrthographicCamera cameraScore;
    
    private ParticleEffect slashParticle = new ParticleEffect();
    
    private void addToScore() {
        score += POINTS_PER_APPLE;
    }

    @Override
    public void show() {
        //'Camara ortografica'
        cameraO = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        cameraO.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
        cameraO.update();
        
        //camara perspectiva
        cameraP = new PerspectiveCamera(60f, WORLD_WIDTH, WORLD_HEIGHT);
        cameraP.position.set(WORLD_WIDTH / 2, (WORLD_HEIGHT / 2) * 0.4f, WORLD_HEIGHT);
        cameraP.lookAt(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0f);
        cameraP.near = 1;
        cameraP.far = 2 * WORLD_HEIGHT;
        cameraP.update();

        cameraScore = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        cameraScore.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
        cameraScore.update();
        //'Viewport'
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, cameraO);
        scoreViewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, cameraScore);
        bitmapFont = new BitmapFont();
        shapeRenderer = new ShapeRenderer();
        //Particulas
        slashParticle.load(Gdx.files.internal("explosion.party"),Gdx.files.internal(""));
        //
        snakeBody = new Texture(Gdx.files.internal("snakeBody.png"));
        batch = new SpriteBatch();

        eaters = new Texture[]{new Texture(Gdx.files.internal("apple.png")), new Texture(Gdx.files.internal("applePoison.png")), new Texture(Gdx.files.internal("mushroom.png"))};
        snakeHead = new Texture(Gdx.files.internal("snakehead.png"));

        fondos = new Texture[]{new Texture(Gdx.files.internal("fondo1.png")), new Texture(Gdx.files.internal("fondo2.jpg")), new Texture(Gdx.files.internal("fondo3.png")), new Texture(Gdx.files.internal("fondo4.png"))};
    }

    private void draw() {
        //camera = cameraO;
        //viewport.setCamera(camera);

        batch.setProjectionMatrix(viewport.getCamera().projection);
        batch.setTransformMatrix(viewport.getCamera().view);
        batch.begin();
        if (nFondo == 4) {
            nFondo = 0;
        }
        batch.draw(fondos[nFondo], 0, 0);
        batch.draw(snakeHead, snakeX, snakeY);
        for (BodyPart bodyPart : bodyParts) {
            bodyPart.draw(batch);

        }
        if (appleAvailable) {
            batch.draw(eaters[nEaters], appleX, appleY);
        }
        if (state == STATE.GAME_OVER) {
            layout.setText(bitmapFont, GAME_OVER_TEXT);
            bitmapFont.draw(batch, GAME_OVER_TEXT, (viewport.getWorldWidth() - layout.width) / 2, (viewport.getWorldHeight() - layout.height) / 2);
        }
        if (state == STATE.PAUSED) {
            layout.setText(bitmapFont, PAUSE_TEXT);
            bitmapFont.draw(batch, PAUSE_TEXT, (viewport.getWorldWidth() - layout.width) / 2, (viewport.getWorldHeight() - layout.height) / 2);
        }
        batch.end();
        batch.setProjectionMatrix(scoreViewport.getCamera().projection);
        batch.setTransformMatrix(scoreViewport.getCamera().view);
        batch.begin();
        drawScore();
        batch.end();
    }

    private void checkForRestart() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            doRestart();
        }
    }

    private void doRestart() {
        state = STATE.PLAYING;
        bodyParts.clear();
        snakeDirection = RIGHT;
        directionSet = false;
        timer = MOVE_TIME;
        cameraO.zoom = 1.0f;
        cameraO.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
        cameraO.update();
        snakeX = 0;
        snakeY = 0;
        snakeXBeforeUpdate = 0;
        snakeYBeforeUpdate = 0;
        appleAvailable = false;
        score = 0;
    }

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
            bodyPart.updateBodyPosition(snakeXBeforeUpdate,
                    snakeYBeforeUpdate);
            bodyParts.add(bodyPart);
        }
    }

    private void queryInput() {
        boolean lPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean rPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean uPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean dPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);
        boolean pPressed = Gdx.input.isKeyJustPressed(Input.Keys.F);
        boolean cPressed = Gdx.input.isKeyJustPressed(Input.Keys.C);
        boolean escPressed = Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE);
        boolean restartPressed = Gdx.input.isKeyJustPressed(Input.Keys.R);
        boolean plusPressed = Gdx.input.isKeyJustPressed(Input.Keys.PLUS);
        boolean minusPressed = Gdx.input.isKeyJustPressed(Input.Keys.MINUS);
        boolean oPressed = Gdx.input.isKeyJustPressed(Input.Keys.O);
        boolean shiftPressed = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT);
        boolean xPressed = Gdx.input.isKeyJustPressed(Input.Keys.X);
        boolean yPressed = Gdx.input.isKeyJustPressed(Input.Keys.Y);
        boolean zPressed = Gdx.input.isKeyJustPressed(Input.Keys.Z);

        if (lPressed) {
            updateDirection(LEFT);
            System.out.println(cameraO.zoom);
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
        if (pPressed) {
            nFondo++;
        }
        if (cPressed) {
            viewport.setCamera(viewport.getCamera() == cameraP ? cameraO : cameraP);
            // viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
            viewport.getCamera().update();
        }
        if (escPressed) {
            state = STATE.PAUSED;
        }
        if (oPressed) {
            if (shiftPressed) {
                cameraO.zoom += 0.025f;
            } else {
                cameraO.zoom -= 0.025f;
            }
            viewport.getCamera().update();
        }
        if (viewport.getCamera() == cameraP) {
            if (xPressed) {
                if (shiftPressed) {
                    cameraP.position.x += 0.9f;
                } else {
                   cameraP.position.x -= 0.9f;
                }                
            }
            if (yPressed) {
                 if (shiftPressed) {
                    cameraP.position.y += 0.9f;
                } else {
                   cameraP.position.y -= 0.9f;
                }                    
            }
            if (oPressed) {
                 if (shiftPressed) {
                    cameraP.position.z += 0.9f;
                } else {
                   cameraP.position.z -= 0.9f;
                }    
            }
            viewport.getCamera().update();
        }
        if (restartPressed) {
            doRestart();
        }
        if (plusPressed) {
            MOVE_TIME -= 0.1f;
        }
        if (minusPressed) {
            MOVE_TIME += 0.1f;
        }
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b, Color.BLACK.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void updateCamera() {
        if (cameraO.zoom != 1) {
            float minCameraX = cameraO.zoom * (viewport.getWorldWidth() / 2);
            float maxCameraX = WORLD_WIDTH - minCameraX;
            float minCameraY = cameraO.zoom * (viewport.getWorldHeight() / 2);
            float maxCameraY = WORLD_HEIGHT - minCameraY;

            cameraO.position.x = snakeX;
            cameraO.position.y = snakeY;
            //    cameraO.lookAt(timer, timer, timer);
            viewport.getCamera().update();

        }
    }

    private void checkForResume() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            state = STATE.PLAYING;
        }

    }

    @Override
    public void render(float delta) {
        switch (state) {
            case PLAYING: {
                queryInput();
                updateSnake(delta);
                updateCamera();
                checkAppleCollision();
                checkAndPlaceApple();
            }
            break;
            case GAME_OVER: {
                checkForRestart();
            }
            case PAUSED: {
                checkForResume();
            }
            break;
        }
        clearScreen();
        draw();
        drawGrid();

    }

    private void updateSnake(float delta) {
        if (!hasHit) {
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

    }

    private void checkAndPlaceApple() {
        if (!appleAvailable) {
            do {
                appleX = MathUtils.random((int) (viewport.getWorldWidth() / SNAKE_MOVEMENT) - 1) * SNAKE_MOVEMENT;
                appleY = MathUtils.random((int) (viewport.getWorldHeight() / SNAKE_MOVEMENT) - 1) * SNAKE_MOVEMENT;
                appleAvailable = true;
            } while (appleX == snakeX && appleY == snakeY);

        }
    }

    private void drawScore() {
        if (state == STATE.PLAYING) {
            String scoreAsString = Integer.toString(score);
            layout.setText(bitmapFont, scoreAsString);
            bitmapFont.draw(batch, scoreAsString, 0, scoreViewport.getWorldHeight() - 32);
        }
    }

    private void checkAppleCollision() {
        if (appleAvailable && appleX == snakeX && appleY == snakeY) {
            BodyPart bodyPart = new BodyPart(snakeBody);
            //BodyPart bodyPart2 = new BodyPart(snakeBody);
            bodyPart.updateBodyPosition(snakeX, snakeY);
            // bodyPart2.updateBodyPosition(snakeX, snakeY);
            bodyParts.insert(0, bodyPart);
            //bodyParts.insert(1, bodyPart2);
            appleAvailable = false;
            addToScore();
        }
    }

    private void checkForOutOfBounds() {
        if (snakeX >= viewport.getWorldWidth()) {
            snakeX = 0;
        }
        if (snakeX < 0) {
            snakeX = (int) viewport.getWorldWidth() - SNAKE_MOVEMENT;
        }
        if (snakeY >= viewport.getWorldHeight()) {
            snakeY = 0;
        }
        if (snakeY < 0) {
            snakeY = (int) viewport.getWorldHeight() - SNAKE_MOVEMENT;
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
                batch.draw(texture,
                        x, y);
            }
        }
    }

    private void drawGrid() {
        shapeRenderer.setProjectionMatrix(viewport.getCamera().projection);
        shapeRenderer.setTransformMatrix(viewport.getCamera().view);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for (int x = 0; x < viewport.getWorldWidth(); x += GRID_CELL) {
            for (int y = 0; y < viewport.getWorldHeight(); y += GRID_CELL) {
                shapeRenderer.rect(x, y, GRID_CELL, GRID_CELL);
            }
        }
        shapeRenderer.end();
    }

    private void updateIfNotOppositeDirection(int newSnakeDirection, int oppositeDirection) {
        if (snakeDirection != oppositeDirection || bodyParts.size == 0) {
            snakeDirection = newSnakeDirection;
        }
    }

    private void updateDirection(int newSnakeDirection) {
        if (!directionSet && snakeDirection != newSnakeDirection) {
            directionSet = false;
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
}
