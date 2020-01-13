package com.pmdm1920.game.stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class HudStage extends Stage {
    private final HudData hudData;

    public HudStage(float width, float height, HudData hudData) {
        this.hudData = hudData;

        OrthographicCamera camera = new OrthographicCamera(width, height);
        camera.position.set(width / 2, height / 2, 0);
        camera.update();
        this.setViewport(new FitViewport(width, height, camera));

        // We are using Scene2D.ui
        // label actor is not allowed to act.
        addActor(new Label("", new Label.LabelStyle(new BitmapFont(), Color.WHITE)));
    }

    @Override
    public void act() {
        super.act();
        String text;
        Label l = (Label)getActors().first();
        l.setAlignment(Align.center);
        switch (hudData.getState()) {
            case PLAYING:
                l.setPosition(getViewport().getWorldWidth() / 2, 4 * getViewport().getWorldHeight() / 5);
                text = Integer.toString(hudData.getScore());
                break;
            case GAME_OVER:
                l.setPosition(getViewport().getWorldWidth() / 2, getViewport().getWorldWidth() / 2);
                text = "Game Over... Tap space to restart!";
                break;
            default:
                text = null;
                break;
        }
        l.setText(text);
    }
}
