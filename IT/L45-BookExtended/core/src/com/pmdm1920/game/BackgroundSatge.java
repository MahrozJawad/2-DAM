package com.pmdm1920.game;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class BackgroundSatge extends Stage {

    public BackgroundSatge(
        float width, float height, 
        TextureAtlas textureAtlas) {
        super();
        OrthographicCamera camera = new OrthographicCamera();
        camera.position.set(width / 2, height / 2, 0);
        camera.update();
        this.setViewport(new FitViewport(width, height, camera));
        this.addActor(new BackgroundActor(textureAtlas.findRegion("mountains"), 20f));
        this.addActor(new BackgroundActor(textureAtlas.findRegion("valleys"), 15f));
        this.addActor(new BackgroundActor(textureAtlas.findRegion("trees"), 11f));
    }
}
