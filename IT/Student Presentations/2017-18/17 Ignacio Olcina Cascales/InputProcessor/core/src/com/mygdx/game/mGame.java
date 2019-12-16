package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.sun.org.apache.xpath.internal.operations.String;

public class mGame extends ApplicationAdapter implements InputProcessor{
    // we will use 32px/unit in world
    private final static float SCALE = 32f;
    private final static float INV_SCALE = 1.f/SCALE;


    // this is our "target" resolution, not that the window can be any size, it is not bound to this one
    private final static float VP_WIDTH = 1280 * INV_SCALE;
    private final static float VP_HEIGHT = 720 * INV_SCALE;

    private OrthographicCamera camera;
    private ExtendViewport viewport;
    private ShapeRenderer shapes;
    private SpriteBatch batch;
    private java.lang.String txt = "";
    private BitmapFont yourBitmapFontName;

    @Override public void create () {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        // pick a viewport that suits your thing, ExtendViewport is a good start
        viewport = new ExtendViewport(VP_WIDTH, VP_HEIGHT, camera);
        // ShapeRenderer so we can see our touch point
        shapes = new ShapeRenderer();
        yourBitmapFontName = new BitmapFont();
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render () {
        batch.begin();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shapes.setProjectionMatrix(camera.combined);
        shapes.begin(ShapeRenderer.ShapeType.Filled);
        shapes.circle(tp.x, tp.y, 0.25f, 16);
        yourBitmapFontName.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        yourBitmapFontName.draw(batch, txt, 25, 100);
        batch.end();
        shapes.end();
    }
    private Vector3 tp = new Vector3();
    private boolean dragging;
    @Override public boolean mouseMoved (int screenX, int screenY) {
        // we can also handle mouse movement without anything pressed
		camera.unproject(tp.set(screenX, screenY, 0));
        return false;
    }

    @Override public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        // ignore if its not left mouse button or first touch pointer
        if (button != Input.Buttons.LEFT || pointer > 0) return false;
        camera.unproject(tp.set(screenX, screenY, 0));
        dragging = true;
        return true;
    }

    @Override public boolean touchDragged (int screenX, int screenY, int pointer) {
        if (!dragging) return false;
        camera.unproject(tp.set(screenX, screenY, 0));
        return true;
    }

    @Override public boolean touchUp (int screenX, int screenY, int pointer, int button) {
        if (button != Input.Buttons.LEFT || pointer > 0) return false;
        camera.unproject(tp.set(screenX, screenY, 0));
        dragging = false;
        return true;
    }

    @Override public void resize (int width, int height) {
        txt = "window resized to:" + width + "," + height;
        viewport.update(width, height, true);
    }

    @Override public void dispose () {
        // disposable stuff must be disposed
        shapes.dispose();
    }

    @Override
    public boolean keyDown (int keycode) {
        moveVector(keycode);
        return false;
    }

    @Override
    public boolean keyUp (int keycode) {
        moveVector(keycode);
        return false;
    }
    private void moveVector(int keycode){
        switch (keycode)
        {
            case Input.Keys.LEFT:
                tp.set((tp.x - 1),tp.y,0);
                break;
            case Input.Keys.RIGHT:
                tp.set((tp.x + 1),tp.y,0);
                break;
        }
    }
    @Override
    public boolean keyTyped (char character) {
        txt = java.lang.String.valueOf(character);
        return false;
    }

    @Override
    public boolean scrolled (int amount) {
        txt = "you scrolled " + amount;
        return false;
    }
}
