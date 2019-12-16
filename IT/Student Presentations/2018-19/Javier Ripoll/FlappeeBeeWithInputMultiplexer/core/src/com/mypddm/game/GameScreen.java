package com.mypddm.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mypddm.game.input.Flappee2KeyboardInput;
import com.mypddm.game.input.FlappeeKeyboardInput;
import com.mypddm.game.input.FlappeeTouchInput;

public class GameScreen extends ScreenAdapter {
    private static final float WORLD_WIDTH = 480;
    private static final float WORLD_HEIGHT = 640;
    private static final float GAP_BETWEEN_FLOWERS = 200F;

    private Flappee[] flappee = new Flappee[2];
    
    private TextureRegion flowerBottom;
    private TextureRegion flowerTop;
    
    private Flower flower;
    private Array<Flower> flowers = new Array<Flower>();

    private ShapeRenderer shapeRenderer;
    private Viewport viewport;
    private Camera camera;
    private SpriteBatch batch;
    
    private int score = 0;
    private BitmapFont bitmapFont;
    private GlyphLayout glyphLayout;
    
    private TextureRegion background;
    private TextureRegion[] flappeeTexture = new TextureRegion[2];

    private FlappeeBeeGame flappeeBeeGame;

    public GameScreen(FlappeeBeeGame flappeeBeeGame) {
        this.flappeeBeeGame = flappeeBeeGame;
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.position.set(WORLD_WIDTH/2, WORLD_HEIGHT/2, 0);
        camera.update();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        glyphLayout = new GlyphLayout();
        TextureAtlas textureAtlas = flappeeBeeGame.getAssetManager().
        		get("flappee_bee_assets.atlas");
        bitmapFont = flappeeBeeGame.getAssetManager().get("score.fnt");
        background = textureAtlas.findRegion("bg");
        flowerBottom = textureAtlas.findRegion("flowerBottom");
        flowerTop = textureAtlas.findRegion("flowerTop");
        flappeeTexture[0] = textureAtlas.findRegion("bee");
        flappeeTexture[1] = textureAtlas.findRegion("horriblebee");
        flappee[0] = new Flappee(flappeeTexture[0]);
        flappee[0].setPosition(WORLD_WIDTH/4, WORLD_HEIGHT/2);
        //flappee[1] = new Flappee(flappeeTexture[1]);
        //flappee[1].setPosition(WORLD_WIDTH/4 - 40, WORLD_HEIGHT/2);

        /*InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(new FlappeeKeyboardInput(flappee[0]));
        multiplexer.addProcessor(new GestureDetector(new FlappeeTouchInput(flappee[0])));
        */

		////multiplexer.addProcessor(new Flappee2KeyboardInput(flappee[1]));

        //Gdx.input.setInputProcessor(multiplexer);

        Gdx.input.setInputProcessor(new FlappeeKeyboardInput(flappee[0]));
        Gdx.input.setInputProcessor(new GestureDetector(new FlappeeTouchInput(flappee[0])));
    }

    @Override
    public void render(float delta) {
        clearScreen();
        draw();
        //drawDebug();
        update(delta);
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g,
                Color.BLACK.b, Color.BLACK.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void update(float delta) {
        updateFlapee(delta);
        updateFlowers(delta);
        updateScore();
        if(checkForCollision()) {
        	restart();
        }
    }
    
    private void updateFlapee(float delta) {
        for(Flappee f : flappee)
            if(f!=null)
    	        f.update(delta);
        //if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
        //    flappee.flyUp();
        blockFlappeeLeavingTheWorld();
    }
    
    private void updateFlowers(float delta) {
    	for(Flower flower : flowers) {
    		flower.update(delta);
    	}
    	checkIfNewFlowerIsNeeded();
    	removeFlowersIfPassed();
    }
    
    private void updateScore() {
    	Flower flower = flowers.first();
    	if(flower.getX() < flappee[0].getX() && !flower.isPointClaimed()) {
    		flower.markPointClaimed();
    		score++;
    	}
    }

    private void blockFlappeeLeavingTheWorld() {
        for(Flappee f : flappee)
            if(f!=null)
                f.setPosition(f.getX(),
                        MathUtils.clamp(f.getY(), 0, WORLD_HEIGHT));
    }

    private void createNewFlower() {
    	Flower newFlower = new Flower(flowerBottom, flowerTop);
    	newFlower.setPosition(WORLD_WIDTH + Flower.WIDTH);
    	flowers.add(newFlower);
    }
    
    private void checkIfNewFlowerIsNeeded() {
		if(flowers.size == 0) {
			createNewFlower();
		} else {
			Flower flower = flowers.peek();
			if(flower.getX() < WORLD_WIDTH - GAP_BETWEEN_FLOWERS) {
				createNewFlower();
			}
		}
	}
    
    private boolean checkForCollision() {
    	for(Flower flower : flowers) {
    		if(flower.isFlappeeColliding(flappee[0])) {
    			return true;
    		}
    	}
    	return false;
    }
    
    private void removeFlowersIfPassed() {
    	if(flowers.size > 0) {
    		Flower firstFlower = flowers.first();
    		if(firstFlower.getX() < -Flower.WIDTH) {
    			flowers.removeValue(firstFlower, true);
    		}
    	}
    }
    
    private void drawDebug() {
    	shapeRenderer.setProjectionMatrix(camera.projection);
        shapeRenderer.setTransformMatrix(camera.view);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        flappee[0].drawDebug(shapeRenderer);
        for(Flower flower : flowers) {
        	flower.drawDebug(shapeRenderer);
        }
        shapeRenderer.end();
    }
    
    private void drawScore() {
    	String scoreAsString = Integer.toString(score);
		glyphLayout.setText(bitmapFont, scoreAsString);
		bitmapFont.draw(batch, scoreAsString, (viewport.getWorldWidth()
				- glyphLayout.width) / 2, (4 * viewport.getWorldHeight() / 5) -
				glyphLayout.height / 2);
    }
    
    private void draw() {
    	batch.totalRenderCalls = 0;
    	batch.setProjectionMatrix(camera.projection);
        batch.setTransformMatrix(camera.view);
        batch.begin();
        batch.draw(background, 0, 0);
        drawFlowers();
        for(Flappee f : flappee)
            if(f!=null)
                f.draw(batch);
        drawScore();
        batch.end();
        Gdx.app.log("Debug", String.valueOf(batch.totalRenderCalls));
    }
    
    private void drawFlowers() {
    	for(Flower flower : flowers) {
    		flower.draw(batch);
    	}
    }
    
    private void restart() {
        flappee[0].setPosition(WORLD_WIDTH / 4, WORLD_HEIGHT / 2);
        //flappee[1].setPosition(WORLD_WIDTH / 4 - 40, WORLD_HEIGHT / 2);
        flowers.clear();
        score = 0;
    }
    
}
