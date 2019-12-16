package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.XmlReader;
import com.sun.org.apache.regexp.internal.RE;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class MyGdxGame extends ApplicationAdapter {
	private Stage stage;
	private Skin skin;
	private Table table;
	private ScrollPane scroll;
	private SelectBox<String> selectBox;
	@Override
	public void create () {
		stage = new Stage();
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		//Ejemplo1();
		Ejemplo2();
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
		stage.act();

	}
	
	@Override
	public void dispose () {
	}
	private void Ejemplo1(){
		table = new Table();
		table.setFillParent(true);

		Label labelNombre = new Label("Nombre", skin);
		Label labelPuntuacion = new Label("Nick", skin);
		TextField textFieldNombre = new TextField("",skin);
		TextField textFieldPuntuacion = new TextField("", skin);
		Label labelDescripcion = new Label("Descripcion", skin);
		Label labelPais = new Label("Pais", skin);
		TextField textFieldDescripcion = new TextField("",skin);
		RellenarPaises();
		final TextButton textButton = new TextButton("Enviar", skin);
		textButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				labelNombre.addAction(Actions.sequence(Actions.delay(0f), Actions.parallel(Actions.moveBy(0,600,0.5f)), Actions.fadeOut(0.5f)));
				textFieldNombre.addAction(Actions.sequence(Actions.delay(0f), Actions.parallel(Actions.moveBy(0,600,0.5f)), Actions.fadeOut(0.5f)));
				labelPuntuacion.addAction(Actions.sequence(Actions.delay(0.1f), Actions.parallel(Actions.moveBy(0,600,0.5f)), Actions.fadeOut(0.5f)));
				textFieldPuntuacion.addAction(Actions.sequence(Actions.delay(0.1f), Actions.parallel(Actions.moveBy(0,600,0.5f)), Actions.fadeOut(0.5f)));
				labelDescripcion.addAction(Actions.sequence(Actions.delay(0.2f), Actions.parallel(Actions.moveBy(0,600,0.5f)), Actions.fadeOut(0.5f)));
				textFieldDescripcion.addAction(Actions.sequence(Actions.delay(0.2f), Actions.parallel(Actions.moveBy(0,600,0.5f)), Actions.fadeOut(0.5f)));
				labelPais.addAction(Actions.sequence(Actions.delay(0.3f), Actions.parallel(Actions.moveBy(0,600,0.5f)), Actions.fadeOut(0.5f)));
				selectBox.addAction(Actions.sequence(Actions.delay(0.4f), Actions.parallel(Actions.moveBy(0,600,0.5f)), Actions.fadeOut(0.5f)));
				textButton.addAction(Actions.sequence(Actions.delay(0.5f), Actions.parallel(Actions.moveBy(0,600,0.5f)), Actions.fadeOut(0.5f)));

			}
		});
		//table.top();

		table.add(labelNombre);
		table.add(textFieldNombre).width(200);
		table.row().padTop(20);
		table.add(labelPuntuacion);
		table.add(textFieldPuntuacion).width(200);
		table.row().padTop(20);
		table.add(labelDescripcion);
		table.add(textFieldDescripcion).width(200).height(70);
		table.row().padTop(20);
		table.add(labelPais).colspan(2);
		table.row();
		table.add(selectBox).colspan(2);
		table.row().padTop(20);
		table.add(textButton).colspan(2).center();
		stage.addActor(table);
		//table.debug();
	}
	private void Ejemplo2(){
		RellenarPaises();
		TextButton Ejemplo = new TextButton("Ejemplo", skin);
		SplitPane splitPane = new SplitPane(scroll, Ejemplo, false, skin);
		splitPane.setFillParent(true);
		stage.addActor(splitPane);
	}
	private void RellenarPaises(){
		try {
			ArrayList<String> items = new ArrayList<>();
			XmlReader xml = new XmlReader();
			XmlReader.Element xml_element = xml.parse(Gdx.files.internal("XMLCountryList.txt"));
			Iterator iterator_level = xml_element.getChildrenByName("country").iterator();
			while(iterator_level.hasNext()){
				XmlReader.Element level_element = (XmlReader.Element)iterator_level.next();
				String pais = level_element.getText();
				items.add(pais);
			}
			List<String> lista = new List<String>(skin);
			String[] aux = new String[items.size()];
			lista.setItems(items.toArray(aux));
			//Inicializamos los dos aunque solo mostremos uno por comodidad

			scroll = new ScrollPane(lista, skin);
			selectBox = new SelectBox<String>(skin);
			selectBox.setItems(aux);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
