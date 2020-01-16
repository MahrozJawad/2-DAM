package com.pmdm1920.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class HUDStage extends Stage {

    private Label l;
    private ScoreProvider sp;

    public HUDStage(float width, float height, BitmapFont font, ScoreProvider sp) {
        super();
        this.sp = sp;
        OrthographicCamera camera = new OrthographicCamera();
        camera.position.set(width / 2, height / 2, 0);
        camera.update();
        this.setViewport(new FitViewport(width, height, camera));
        this.getViewport().update((int) width, (int) height);

        l = new Label("", new Label.LabelStyle(font, Color.RED));
        l.setX(this.getViewport().getWorldWidth() / 2);
        l.setX(4 * this.getViewport().getWorldHeight() / 2);
        l.setAlignment(Align.center);

        this.addActor(l);
    }

    @Override
    public void act() {
        super.act();
        String scoreAsString = Integer.toString(sp.getScore());
        l.setText(scoreAsString);
    }

    @Override
    public void draw() {
        super.draw();
    }
}
