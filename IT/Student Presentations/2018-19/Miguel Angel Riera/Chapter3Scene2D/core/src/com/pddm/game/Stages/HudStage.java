package com.pddm.game.Stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class HudStage extends Stage {
    private MainStage mainStage;

    public HudStage(float width, float height, MainStage mainStage) {
        this.mainStage = mainStage;

        OrthographicCamera camera = new OrthographicCamera(width, height);
        camera.position.set(width / 2, height / 2, 0);
        camera.update();
        this.setViewport(new FitViewport(width, height, camera));

        // We are using Scene2D.ui
        // label actor is not allowed to act.
        addActor(new Label("", new Label.LabelStyle(new BitmapFont(), Color.WHITE)));
        addActor(new Label("", new Label.LabelStyle(new BitmapFont(), Color.WHITE)));
    }

    @Override
    public void act() {
        super.act();
        String textL, textVidas ="";
        Actor[] actores = getActors().items;
        Label l = (Label)actores[0];
        Label vidas = (Label)actores[1];
        l.setAlignment(Align.center);
        vidas.setAlignment(Align.right);
        switch (mainStage.getState()) {
            case PLAYING:
                l.setPosition(50, getViewport().getWorldHeight()-10);
                vidas.setPosition(getViewport().getWorldWidth(), 10);
                textL = "Puntos: "+Integer.toString(mainStage.getScore());
                textVidas = "Vidas: "+Integer.toString(mainStage.getVidas());
                break;
            case GAME_OVER:
                if(mainStage.getVidas()>0){
                    l.setPosition(getViewport().getWorldWidth() / 2, getViewport().getWorldWidth() / 2);
                    textL = "Aun te quedan " + mainStage.getVidas() + " vidas.\n Pulsa Space para empezar una nueva partida.";
                }else{
                    l.setPosition(getViewport().getWorldWidth() / 2, getViewport().getWorldWidth() / 2);
                    textL = "Game Over!\nINSERT COINS";
                }
                break;
            default:
                textL = null;
                break;
        }
        l.setText(textL);
        vidas.setText(textVidas);
    }
}
