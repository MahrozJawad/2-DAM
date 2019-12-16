/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.g2d.BitmapFont.BitmapFontData;

/**
 *
 * @author alumno
 */

public class GameScreen extends ScreenAdapter{
    private Texture fondo = new Texture(Gdx.files.internal("bg2.png"));
    private Texture fondo2 = new Texture(Gdx.files.internal("bg3.png"));
    private int imageSelect = 0;
    private SpriteBatch batch;
    private SpriteBatch batchScore;
    private Texture snakeHead;
    private static float MOVE_TIME = 0.3F;//1F
    private float timer = MOVE_TIME;
    private static final int SNAKE_MOVEMENT = 32;
    private int snakeX = 0, snakeY = 0;
    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;
    private int snakeDirection = RIGHT;
    private Texture apple;
    private boolean appleAvaliable=false;
    private int appleX,appleY;
    private Texture snakeBody;
    private int snakeXBeforeUpdate = 0, snakeYBeforeUpdate = 0;
    private ShapeRenderer shapeRenderer;
    private static final int GRID_CELL = 32;
    boolean directionSet=false;
    private Array<BodyPart> bodyParts = new Array<BodyPart>();
    private boolean hasHit = false;
    private GlyphLayout layout = new GlyphLayout();
    String text = "This Snake Game is AWESOME!";
    private Viewport viewport;
    private Camera camera;
    private Camera camaraOrto;
    private Camera perspective;
    private float FOAV_NOSE = 60;
    private static final float WORLD_WIDTH = 640; 
    private static final float WORLD_HEIGHT = 480;
    private static final String GAME_OVER_TEXT = "Game Over... Tap space to restart!";
    private int score = 0;
    private static final int POINTS_PER_APPLE = 20;
    private BitmapFont bitmapFont;
    private int MaxScore = 0;
    private float minCameraX;
    private float minCameraY;
    private float maxCameraX;
    private float maxCameraY;
    private float posCameraY;
    private float posCameraX;
    
    /////////////////////////////////////////////////
    /////////////////////////////////////////////////
    ///////////////////// HERE //////////////////////
    /////////////////////////////////////////////////
    /////////////////////////////////////////////////
    private InputMultiplexer multiplexer;
    /////////////////////////////////////////////////
    /////////////////////////////////////////////////
    ///////////////////// HERE //////////////////////
    /////////////////////////////////////////////////
    /////////////////////////////////////////////////
    
    private enum STATE
    {
        PLAYING, GAME_OVER
        
    }
    private STATE state = STATE.PLAYING;
    
    private class BodyPart{
        private int x,y;
        private Texture texture;
        
        public BodyPart(Texture texture)
        {
            this.texture = texture;
        }
        public void updateBodyPosition(int x, int y)
        {
            this.x= x;
            this.y=y;
        }
        public void draw(Batch batch)
        {
            if(!(x==snakeX && y==snakeY)) batch.draw(texture,x,y);
        }
    }
    private void addToScore()
    {
       score += POINTS_PER_APPLE;
    }
    private void checkForRestart()
    {
        if(MaxScore < score)
        {
            MaxScore = score;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE))doRestart();
    }
    private void doRestart()
    {
        state = STATE.PLAYING;   
        bodyParts.clear();   
        snakeDirection = RIGHT;   
        directionSet = false; 
        timer = MOVE_TIME;   
        snakeX = 0;   
        snakeY = 0;  
        snakeXBeforeUpdate = 0;  
        snakeYBeforeUpdate = 0; 
        appleAvaliable = false;
        score = 0;
        
    }
    private void checkSnakeBodyCollision()
    {
        for(BodyPart bodyPart : bodyParts)
        {
            if(bodyPart.x == snakeX && bodyPart.y == snakeY) state = STATE.GAME_OVER;
        }
    }
    @Override
    public void show() {
                camaraOrto = new OrthographicCamera(WORLD_WIDTH,WORLD_HEIGHT);
                perspective = new PerspectiveCamera(FOAV_NOSE,WORLD_WIDTH,WORLD_HEIGHT);
                camera = camaraOrto;
                camera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);      
                camera.update();   
                viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
                bitmapFont = new BitmapFont();
                shapeRenderer = new ShapeRenderer();
                batch = new SpriteBatch();
                batchScore = new SpriteBatch();
                snakeHead = new Texture(Gdx.files.internal("snakehead.png"));
                apple = new Texture(Gdx.files.internal("apple.png"));
                snakeBody = new Texture(Gdx.files.internal("snakeBody.png"));
                
                
    /////////////////////////////////////////////////
    /////////////////////////////////////////////////
    ///////////////////// HERE //////////////////////
    /////////////////////////////////////////////////
    /////////////////////////////////////////////////
                multiplexer = new InputMultiplexer();
                multiplexer.addProcessor(new Raton(this));
                multiplexer.addProcessor(new Teclado(this));
                Gdx.input.setInputProcessor(multiplexer);
    /////////////////////////////////////////////////
    /////////////////////////////////////////////////
    ///////////////////// HERE //////////////////////
    /////////////////////////////////////////////////
    /////////////////////////////////////////////////
                
                
                
    }
    @Override
    public void render(float delta) { 
        

            switch(state)
            {
                case PLAYING:{
                    queryInput();
                    updateSnake(delta);
                    checkAppleCollision();
                    checkAndPlaceApple();
                }
                break;
                case GAME_OVER:{  
                    checkForRestart();
                }
                break;
            }
            clearScreen();
           
            drawGrid();
            draw(); 

           
        }

    private void clearScreen()
    {
       Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b, Color.BLACK.a);
       Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
       
    }
    private void drawScore() 
    {   
       if (state == STATE.PLAYING)
        {      
            String scoreAsString = "Points: " + Integer.toString(score);
            layout.setText(bitmapFont, scoreAsString);
        
            bitmapFont.draw(batchScore, scoreAsString, (200 - layout.width) / 2, viewport.getWorldHeight() - layout.height / 2);
        }
    } 
    
    private void draw()
    {
        batch.setProjectionMatrix(camera.projection);  
        batch.setTransformMatrix(camera.view);
        batch.begin();
        switch(imageSelect)
            {
                case 0:
                    
                    break;
                case 1:
                    batch.draw(fondo,0,0);
                    break;
                case 2:
                    batch.draw(fondo2,0,0);
                    break;
            }
        batch.draw(snakeHead,snakeX,snakeY);
        for(BodyPart bodyPart : bodyParts)
        {
            bodyPart.draw(batch);
        }
        if(appleAvaliable)
        {
            batch.draw(apple, appleX, appleY);
        }
        
        if(state == STATE.GAME_OVER)
        {
            layout.setText(bitmapFont, GAME_OVER_TEXT);
            bitmapFont.draw(batch,GAME_OVER_TEXT,(viewport.getWorldWidth()-layout.width)/2, (viewport.getWorldWidth()-layout.height)/2);
        }
       
        batch.end();
        batchScore.begin();
         drawScore();
        batchScore.end();
    }
    private void checkAppleCollision()
    {
        if(appleAvaliable && appleX == snakeX && appleY == snakeY)
        {
            BodyPart bodyPart = new BodyPart(snakeBody);
            bodyPart.updateBodyPosition(snakeX, snakeY);
            bodyParts.insert(0, bodyPart);
             addToScore();
            appleAvaliable = false;
        }
    }
    private void checkAndPlaceApple()
    {
        if(!appleAvaliable)
        {
            do{
                appleX = MathUtils.random((int) (viewport.getWorldWidth() / SNAKE_MOVEMENT) - 1) * SNAKE_MOVEMENT;
                appleY = MathUtils.random((int) (viewport.getWorldHeight() / SNAKE_MOVEMENT) - 1) * SNAKE_MOVEMENT;
                appleAvaliable = true;
            }while(appleX == snakeX && appleY == snakeY);
        }
    }
    private void queryInput()
    {
        boolean lPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean rPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean uPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean dPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN); 
        
        boolean spacePressed = Gdx.input.isKeyJustPressed(Input.Keys.SPACE); 
        
        boolean oPressed = Gdx.input.isKeyPressed(Input.Keys.O); 
        boolean oJustPressed = Gdx.input.isKeyJustPressed(Input.Keys.O); 
        
        boolean lShiftPressed = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT);
        boolean rShiftPressed = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT);
        
        boolean pPressed = Gdx.input.isKeyJustPressed(Input.Keys.P);
        
        /*if(lPressed) updateDirection(LEFT);
        if(rPressed) updateDirection(RIGHT);
        if(uPressed) updateDirection(UP);
        if(dPressed) updateDirection(DOWN);*/
        if(spacePressed)
        {
            if(imageSelect == 2)
            {
                imageSelect = 0;
            }
            else
            {
                imageSelect +=1;
            }
        }
        if (camera.getClass()==OrthographicCamera.class)
        {
            if(oJustPressed)
            {
                if(((OrthographicCamera)camera).zoom > 0.6)
                {
                    ((OrthographicCamera)camera).zoom -= 0.025;
                }
            }
            if((rShiftPressed || lShiftPressed)&& oPressed)
            {
                if(((OrthographicCamera)camera).zoom < 1)
                {
                    ((OrthographicCamera)camera).zoom += 0.025;
                }            
            }
             camera.update();
        }
        if(pPressed)
        {
            if(camera.getClass()==OrthographicCamera.class)
            {
                camera = perspective;
                camera.near = 1f;
                camera.far = WORLD_HEIGHT * 2f;
                camera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2 * 0.4f, WORLD_HEIGHT);
                camera.lookAt(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
                camera.update(); 
            }
            else
            {
                camera = camaraOrto;
            }
        }
        if (camera.getClass()==PerspectiveCamera.class){
            if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {
                camera.translate(10f, 0, 0);
            }
            if ((Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) && Gdx.input.isKeyPressed(Input.Keys.X)) {
                camera.translate(-10f, 0, 0);
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.Y)) {
                camera.translate(0, 10f, 0);
            }
            if ((Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) && Gdx.input.isKeyPressed(Input.Keys.Y)) {
                camera.translate(0, -10f, 0);
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
                camera.translate(0, 0, 10f);
            }
            if ((Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) && Gdx.input.isKeyPressed(Input.Keys.Z)) {
                camera.translate(0, 0, -10f);
            }
            camera.lookAt(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
            camera.update(); 
        }
        


    }
    private void checkForOutOfBounds()
    {
        if(snakeX >= Gdx.graphics.getWidth())
        {
            snakeX = 0;
        }
        if(snakeX < 0)
        {
            snakeX = Gdx.graphics.getWidth()-SNAKE_MOVEMENT;
        }
        if(snakeY >= Gdx.graphics.getHeight())
        {
            snakeY = 0;
        }
        if(snakeY < 0)
        {
            snakeY = Gdx.graphics.getHeight()-SNAKE_MOVEMENT;
        }
    } 
    private void moveSnake()
    {
        snakeXBeforeUpdate = snakeX;
        snakeYBeforeUpdate = snakeY;
        switch(snakeDirection)
        {
            case RIGHT : {
                snakeX += SNAKE_MOVEMENT;
                return;
            }
            case LEFT : {
                snakeX -= SNAKE_MOVEMENT;
                return;
            }
            case UP : {
                snakeY += SNAKE_MOVEMENT;
                return;
            }
            case DOWN : {
                snakeY -= SNAKE_MOVEMENT;
                return;
            }
        }   
    }
    private void updateBodyPartsPosition()
    {
        if(bodyParts.size > 0)
        {
            BodyPart bodyPart = bodyParts.removeIndex(0);
            bodyPart.updateBodyPosition(snakeXBeforeUpdate, snakeYBeforeUpdate);
            bodyParts.add(bodyPart);
        }
    }
    private void CambiarCamara()
    {
        
    }
    private void drawGrid()
    {
        /*shapeRenderer.setProjectionMatrix(camera.projection);  
        shapeRenderer.setTransformMatrix(camera.view); 
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for(int x = 0;x<Gdx.graphics.getWidth();x+=GRID_CELL)
        {
            for(int z = 0;z<Gdx.graphics.getHeight();z+=GRID_CELL)
            {
                shapeRenderer.rect(x,z,GRID_CELL,GRID_CELL);
            }
        }
        shapeRenderer.end();*/
    }
    private void updateIfNotOppositeDirection(int newSnakeDirection, int oppositeDirection)
    {
        if((snakeDirection != oppositeDirection) || bodyParts.size == 0) 
            snakeDirection = newSnakeDirection;
    }
    
    private void updateSnake(float delta)
    {
        
        if(!hasHit)
        {
            timer -=delta;
            if(timer <= 0)
            {
             timer = MOVE_TIME;
             moveSnake();
             checkForOutOfBounds();
             updateBodyPartsPosition();
             checkSnakeBodyCollision();
             directionSet = false;
             modificaCamara();
            } 
        }  
    }
    
    private void modificaCamara()
    {
        if(camera.getClass() == OrthographicCamera.class)
        {
            minCameraX = ((OrthographicCamera)camera).zoom * (camera.viewportWidth /2);
            maxCameraX = WORLD_WIDTH - minCameraX;
            minCameraY = ((OrthographicCamera)camera).zoom * (camera.viewportHeight /2);
            maxCameraY = WORLD_HEIGHT - minCameraY;
            posCameraX = (snakeX > minCameraX ? snakeX : minCameraX);
            posCameraX = (posCameraX < maxCameraX ? posCameraX : maxCameraX);
            posCameraY = (snakeY > minCameraY ? snakeY : minCameraY);
            posCameraY = (posCameraY < maxCameraY ? posCameraY : maxCameraY);
            camera.position.set(posCameraX,posCameraY,0);
            camera.update();
                    
        }
    }
    
    public void updateDirection(int newSnakeDirection)
    {
        if(!directionSet && snakeDirection!=newSnakeDirection)
        {
            switch(newSnakeDirection)
            {
                case LEFT:
                {
                    updateIfNotOppositeDirection(newSnakeDirection,RIGHT);
                }
                break;
                case RIGHT:
                {
                    updateIfNotOppositeDirection(newSnakeDirection,LEFT);
                }
                break;
                case UP:
                {
                    updateIfNotOppositeDirection(newSnakeDirection,DOWN);
                }
                break;
                case DOWN:
                {
                    updateIfNotOppositeDirection(newSnakeDirection,UP);
                }
                break;
            }
            directionSet = true;
        }
    }
}
