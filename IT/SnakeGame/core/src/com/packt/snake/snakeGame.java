package com.packt.snake;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class snakeGame extends Game{
	SpriteBatch batch;
	private Texture snakeHead;
	private static final float Move_TIME = 1F;
	private float timer = Move_TIME;
	private static final int SNAKE_MOVEMENT = 32;
	private int snakeX = 0, snakeY = 0;
	private static final int RIGHT = 0;
	private static final int LEFT = 1;
	private static final int UP = 2;
	private static final int DOWN = 3;
	private int snakeDirection = RIGHT;

	@Override
	public void create () {
		batch = new SpriteBatch();
		snakeHead = new Texture("snakeHead.png");
	}

	private void queryInput() {
		boolean lPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
		boolean rPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
		boolean uPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
		boolean dPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);
		if (lPressed) snakeDirection = LEFT;
		if (rPressed) snakeDirection = RIGHT;
		if (uPressed) snakeDirection = UP;
		if (dPressed) snakeDirection = DOWN;
	}

	@Override
	public void render(float delta) {
		timer -= delta;
		if (timer >= 0) {
			timer = Move_TIME;
			snakeX += SNAKE_MOVEMENT;
		}

		Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b, Color.BLACK.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(snakeHead,0,0);
		batch.end();
	}
	@Override
	public void dispose () {
		batch.dispose();
		snakeHead.dispose();
	}
	private void checkForOutOfBounds() {
		if (snakeX >= Gdx.graphics.getWidth()) {
			snakeX = 0;
		}
		if (snakeX < 0) {
			snakeX = Gdx.graphics.getWidth() - SNAKE_MOVEMENT;
		}
		if (snakeY >= Gdx.graphics.getHeight()) {
			snakeY = 0;
		}
		if (snakeY < 0) {
			snakeY = Gdx.graphics.getHeight() - SNAKE_MOVEMENT;
		}
	}
	private void moveSnake() {
		switch (snakeDirection) {
			case RIGHT: {
				snakeX += SNAKE_MOVEMENT;
				return;
			}
			case LEFT: {
				snakeX -= SNAKE_MOVEMENT;
				return;
			}
			case UP: {
				snakeY += SNAKE_MOVEMENT;
				return;
			}
			case DOWN: {
				snakeY -= SNAKE_MOVEMENT;
				return;
			}
		}
	}

	


}

