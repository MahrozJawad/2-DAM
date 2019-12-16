package com.pddm.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;

public class GameScreen extends ScreenAdapter {

    public static final int GRID_CELL = 32;
    private static final float WORLD_WIDTH = 640;
    private static final float WORLD_HEIGHT = 480;
    private static final int POINTS_PER_APPLE = 20;
    private static final String GAME_OVER_TEXT = "Game Over... Tap space to restart!";

    private Stage stage;
    private Snake snake;
    private GlyphLayout layout = new GlyphLayout();
    private BitmapFont bitmapFont;
    private ShapeRenderer shapeRenderer;
    private int score;

    private enum STATE { PLAYING, GAME_OVER }

    private STATE state = STATE.PLAYING;

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().update(width, height);
    }

    @Override
    public void show() {
        super.show();

        OrthographicCamera camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        camera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
        camera.update();
        Viewport viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        score = 0;
        bitmapFont = new BitmapFont();
        shapeRenderer = new ShapeRenderer();

        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        snake = new Snake(this);
        stage.addActor(snake);

        Apple apple = new Apple(this);
        stage.addActor(apple);

        stage.setKeyboardFocus(snake);
    }

    @Override
    public void render(float delta) {
        clearScreen();
        switch (state) {
            case PLAYING: {
                stage.act(delta);
            }
            break;
            case GAME_OVER: {
                checkForRestart();
            }
            break;
        }
        stage.draw();
        drawGrid();
        drawTexts();
        super.render(delta);
    }


    private void checkForRestart() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) doRestart();
    }

    private void doRestart() {
        for (Actor a : stage.getActors()){
            if (a instanceof Initiable)
                ((Initiable)a).init();
        }

        state = STATE.PLAYING;
        score = 0;
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b, Color.BLACK.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void drawGrid() {
        shapeRenderer.setProjectionMatrix(stage.getViewport().getCamera().projection);
        shapeRenderer.setTransformMatrix(stage.getViewport().getCamera().view);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line); //
        for (int x = 0; x < stage.getViewport().getWorldWidth(); x += GRID_CELL) {
            for (int y = 0; y < stage.getViewport().getWorldHeight(); y += GRID_CELL) {
                shapeRenderer.rect(x, y, GRID_CELL, GRID_CELL);
            }
        }
        shapeRenderer.end();
    }

    private void drawScore() {
        String scoreAsString = Integer.toString(score);
        layout.setText(bitmapFont, scoreAsString);
        bitmapFont.draw(
                stage.getBatch(), scoreAsString,
                stage.getViewport().getWorldWidth() / 2 - layout.width / 2,
                (4 * stage.getViewport().getWorldHeight() / 5) - layout.height / 2);
    }

    private void drawGameOver() {
        layout.setText(bitmapFont, GAME_OVER_TEXT);
        bitmapFont.draw(
                stage.getBatch(), GAME_OVER_TEXT,
                stage.getViewport().getWorldWidth() / 2 - layout.width / 2,
                stage.getViewport().getWorldHeight() / 2 - layout.height / 2);
    }

    private void drawTexts() {
        stage.getBatch().setProjectionMatrix(stage.getViewport().getCamera().projection);
        stage.getBatch().setTransformMatrix(stage.getViewport().getCamera().view);
        stage.getBatch().begin();
        if (state == STATE.PLAYING)
            drawScore();
        if (state == STATE.GAME_OVER)
            drawGameOver();
        stage.getBatch().end();
    }

    public void addToScore() {
        score += POINTS_PER_APPLE;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setFinJuego(){
        state = STATE.GAME_OVER;
    }
}
