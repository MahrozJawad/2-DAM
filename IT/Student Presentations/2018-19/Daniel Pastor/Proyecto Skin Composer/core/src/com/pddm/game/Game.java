package com.pddm.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

public class Game extends ApplicationAdapter {

    private Stage stage;
    private Skin skin;
    private Table table;
    SpriteBatch batch;
    Texture img;

    @Override
    public void create() {
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("nuevocha/neutralizer-ui.json"));
        CrearEscena();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 7, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        stage.act();

    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }

    private void CrearEscena() {
        table = new Table();
        table.setFillParent(true);

        Label pokemon = new Label("who's this pokemon?", skin);

        Button chaNegro = new Button(skin, "Cha");
        Button chaNormal = new Button(skin, "ChaNormal");

        Button starmieNegro = new Button(skin, "starmieNegro");
        Button starmieNormal = new Button(skin, "starmieNormal");

        ImageTextButton reiniciar = new ImageTextButton("Reiniciar", skin, "default");

        ImageTextButton botonEnviar = new ImageTextButton("Enviar", skin, "EnviarBotonCha");

        SelectBox seleccionPokemon = new SelectBox(skin, "SelectPokemon");
        Array<String> items = new Array();
        items.add("1");
        items.add("2");
        //items.add("Scyther");
        seleccionPokemon.setItems(items);

        seleccionPokemon.addListener(new ClickListener());
        seleccionPokemon.addCaptureListener(new ChangeListener() { //EVENTO QUE CAMBIA EL POKEMON POR SELECCION
            @Override
            public void changed(ChangeListener.ChangeEvent ce, Actor actor) {
                String seleccionado = String.valueOf(seleccionPokemon.getSelected());

                if (seleccionado.equals("2")) {
                    table = new Table();
                    table.setFillParent(true);
                    stage.clear();
                    table.add(pokemon); //Label
                    table.row().padTop(10);
                    table.add(starmieNegro);      //Imagen Pokemon
                    table.row().padTop(10);
                    botonEnviar.setVisible(true);
                    table.add(botonEnviar); //Boton enviar
                    table.row().padTop(100);
                    table.add(seleccionPokemon);
                    stage.addActor(table);
                } else if (seleccionado.equals("1")) {
                    table = new Table();
                    table.setFillParent(true);
                    stage.clear();
                    table.add(pokemon); //Label
                    table.row().padTop(10);
                    table.add(chaNegro);      //Imagen Pokemon
                    table.row().padTop(10);
                    botonEnviar.setVisible(true);
                    table.add(botonEnviar); //Boton enviar
                    table.row().padTop(100);
                    table.add(seleccionPokemon);
                    stage.addActor(table);
                }
            }
        });

        botonEnviar.addListener(new ChangeListener() { //EVENTO QUE CAMBIA LA IMAGEN
            @Override
            public void changed(ChangeListener.ChangeEvent ce, Actor actor) {
                table = new Table();
                table.setFillParent(true);
                stage.clear();
                table.add(pokemon);          //Label
                table.row().padTop(10);
                if (seleccionPokemon.getSelected().equals("1")) {
                    table.add(chaNormal);    //Imagen Pokemon
                } else {
                    table.add(starmieNormal);    //Imagen Pokemon
                }

                table.row().padTop(10);
                botonEnviar.setVisible(false);
                table.add(botonEnviar);     //Boton enviar
                table.row().padTop(100);
                table.add(reiniciar);     //Boton reiniciar

                stage.addActor(table);
            }
        });

        reiniciar.addListener(new ChangeListener() { //EVENTO QUE REINICIA AL ESTADO ANTERIOR
            @Override
            public void changed(ChangeListener.ChangeEvent ce, Actor actor) {
                table = new Table();
                table.setFillParent(true);
                stage.clear();
                table.add(pokemon); //Label
                table.row().padTop(10);
                if (seleccionPokemon.getSelected().equals("1")) {
                    table.add(chaNegro);    //Imagen Pokemon
                } else {
                    table.add(starmieNegro);    //Imagen Pokemon
                }
                table.row().padTop(10);
                botonEnviar.setVisible(true);
                table.add(botonEnviar); //Boton enviar
                table.row().padTop(100);
                table.add(seleccionPokemon);
                stage.addActor(table);
            }
        });
        table.add(pokemon); //Label
        table.row().padTop(10);
        table.add(chaNegro);      //Imagen Pokemon
        table.row().padTop(10);
        table.add(botonEnviar); //Boton enviar
        table.row().padTop(100);
        table.add(seleccionPokemon);

        stage.addActor(table);
    }
}
