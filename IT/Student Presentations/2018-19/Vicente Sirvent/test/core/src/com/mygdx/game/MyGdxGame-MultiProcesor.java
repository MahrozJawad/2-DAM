package com.mygdx.game;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame implements ApplicationListener, InputProcessor {
	private SpriteBatch batch;
	private BitmapFont font;
	private String message = "Touch something already!";
	private int w, h;
	private GlyphLayout glyphLayout = new GlyphLayout();



    class TouchInfo {
        public float touchX = 0;
        public float touchY = 0;
        public boolean touched = false;
    }
    
    private Map<Integer,TouchInfo> touches = new HashMap<Integer,TouchInfo>();
    
    @Override
    public void create() {        
        batch = new SpriteBatch();    
        font = new BitmapFont(Gdx.files.internal("data/arial-15.fnt"),false);
        font.setColor(Color.RED);
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
        Gdx.input.setInputProcessor(this);
        for(int i = 0; i < 5; i++){
            touches.put(i, new TouchInfo());
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public void render() {        
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        
        message = "";
        for(int i = 0; i < 5; i++){
            if(touches.get(i).touched)
                message += "Finger:" + Integer.toString(i) + "touch at:" +
                        Float.toString(touches.get(i).touchX) +
                        "," +
                        Float.toString(touches.get(i).touchY) +
                        "\n";
                                
        }
        TextBounds tb = font.getBounds(message);
        float x = w/2 - tb.width/2;
        float y = h/2 + tb.height/2;
        font.drawMultiLine(batch, message, x, y);
        
        batch.end();
    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(pointer < 5){
            touches.get(pointer).touchX = screenX;
            touches.get(pointer).touchY = screenY;
            touches.get(pointer).touched = true;
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(pointer < 5){
            touches.get(pointer).touchX = 0;
            touches.get(pointer).touchY = 0;
            touches.get(pointer).touched = false;
        }
        return true;
    }
}
}
