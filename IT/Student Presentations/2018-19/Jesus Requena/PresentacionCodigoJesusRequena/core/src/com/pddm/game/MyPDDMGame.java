package com.pddm.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MyPDDMGame extends ApplicationAdapter {

    private static final float WORLD_WIDTH = 480;
    private static final float WORLD_HEIGHT = 520;
    Skin myskin;
    SpriteBatch batch;
    TextButton botonEmpezar,botonEstadisticas,botonSalir;
    private Stage stage;
    I18NBundle bundle;

    @Override
    public void create() {
        stage = new Stage(new FitViewport(WORLD_WIDTH, WORLD_HEIGHT));
        Gdx.input.setInputProcessor(stage); 
        myskin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        batch = new SpriteBatch(); 
        // bundle creation
        bundle = I18NBundle.createBundle(Gdx.files.internal("locale/locale"));
        
        // buttons creation
        botonEmpezar = new TextButton(bundle.get("startmenu.play"),myskin,"small");
        botonEstadisticas = new TextButton(bundle.get("startmenu.statistics"),myskin,"small");
        botonSalir = new TextButton(bundle.get("startmenu.exit"),myskin,"small");
        
        //buttons properties
        botonEmpezar.setSize(150, 50);
        botonEmpezar.setPosition((WORLD_WIDTH / 2)/2,(WORLD_HEIGHT / 2)+ 100 );   
        botonEstadisticas.setSize(150, 50);
        botonEstadisticas.setPosition((WORLD_WIDTH / 2)/2,WORLD_HEIGHT / 2 );
        botonSalir.setSize(150, 50);
        botonSalir.setPosition((WORLD_WIDTH / 2)/2,(WORLD_HEIGHT / 2)- 100  );
        
        //stage actors
        stage.addActor(botonEmpezar);
        stage.addActor(botonEstadisticas);
        stage.addActor(botonSalir);
        
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 60, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        //font.draw(batch, "Texto de prueba", WORLD_WIDTH / 2, WORLD_HEIGHT / 2);      
        batch.end();
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        batch.dispose();

    }
    
    
}
