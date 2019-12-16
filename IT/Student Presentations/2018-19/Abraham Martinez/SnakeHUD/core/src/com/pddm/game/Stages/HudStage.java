package com.pddm.game.Stages;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

import javax.xml.soap.Text;

public class HudStage extends Stage {
    private MainStage mainStage;
    static public Texture vida;
    static public Label corazonLabel;
    static public Label puntuacionMaxima;
    static public Label puntuacionMaximaTexto;
    static public Label dificultad;

    public HudStage(float width, float height, MainStage mainStage) {
        this.mainStage = mainStage;

        OrthographicCamera camera = new OrthographicCamera(width, height);
        camera.position.set(width / 2, height / 2, 0);
        camera.update();
        this.setViewport(new FitViewport(width, height, camera));

        // We are using Scene2D.ui
        // label actor is not allowed to act.
        addActor(new Label("", new Label.LabelStyle(new BitmapFont(), Color.WHITE)));
        vida = new Texture(Gdx.files.internal("vida.png"));
        Image corazon = new Image(vida);
        addActor(corazon);
        corazon.setPosition( getViewport().getWorldWidth()-corazon.getWidth(),   getViewport().getWorldHeight() - corazon.getHeight());
        corazonLabel = new Label(Integer.toString(mainStage.getVidas()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        corazonLabel.setPosition(getViewport().getWorldWidth()-2*corazon.getWidth(),   getViewport().getWorldHeight() - corazon.getHeight());
        addActor(corazonLabel);
        puntuacionMaximaTexto= new Label("MAX SCORE: ", new Label.LabelStyle(new BitmapFont(), Color.RED));
        puntuacionMaximaTexto.setPosition(10,getViewport().getWorldHeight()-20);
        addActor(puntuacionMaximaTexto);
        puntuacionMaxima=new Label("0", new Label.LabelStyle(new BitmapFont(), Color.RED));
        puntuacionMaxima.setPosition(puntuacionMaximaTexto.getWidth()+20,getViewport().getWorldHeight()-20);
        addActor(puntuacionMaxima);
        dificultad=new Label("0", new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        dificultad.setPosition(getViewport().getWorldWidth() / 2, getViewport().getWorldHeight()-dificultad.getHeight());

        addActor(dificultad);
    }

    @Override
    public void act() {
        super.act();
        String text;
        String maxScoreText;
        Label l = (Label)getActors().first();
        l.setAlignment(Align.center);
        corazonLabel.setText(Integer.toString(mainStage.getVidas()));
        switch (mainStage.getState()) {
            case PLAYING:
                l.setPosition(getViewport().getWorldWidth() / 2, 4 * getViewport().getWorldHeight() / 5);
                text = Integer.toString(mainStage.getScore());
                puntuacionMaxima.setText(Integer.toString(mainStage.comprobarMaxScore()));
                dificultad.setText(mainStage.cambiarDificultad());
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
