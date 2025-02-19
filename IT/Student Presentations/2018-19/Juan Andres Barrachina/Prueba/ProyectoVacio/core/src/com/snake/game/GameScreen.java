/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.snake.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

//IMPORT INPUTPROCESSOR
import com.badlogic.gdx.InputProcessor;

//IMPLEMENT INPUTPROCESSOR
public class GameScreen extends ScreenAdapter implements InputProcessor{
        private SpriteBatch batch;
        //private Texture img;
        
        private Texture snakeHead;
        
        private static final float MOVE_TIME = 0.25F;
        private float timer = MOVE_TIME;
        
        private static final int SNAKE_MOVEMENT = 32;
        private int snakeX = 0,snakeY = 0;
        
        private static final int RIGHT = 0;
        private static final int LEFT = 1;
        private static final int UP = 2;
        private static final int DOWN = 3;
        private int snakeDirection = RIGHT;
        
        private Texture apple;
        
        private boolean appleAvailable = false;
        private int appleX, appleY;
        
        private Texture snakeBody;
        
        private Array<BodyPart> bodyParts = new Array<BodyPart>(); 
        
        private int snakeXBeforeUpdate = 0, snakeYBeforeUpdate = 0;
        
        private ShapeRenderer shapeRenderer;
        
        private static final int GRID_CELL = 32;
        
        private boolean directionSet = false;
        
        private boolean hasHit = false;
        
        private boolean gridDraw = true;
               
        @Override
        public void show(){
            //ASSIGNATION INPUTPROCESSOR
            Gdx.input.setInputProcessor(this);
            
            batch = new SpriteBatch();
            snakeHead = new Texture(Gdx.files.internal("snakehead.png"));
            apple = new Texture(Gdx.files.internal("apple.png"));
            snakeBody = new Texture(Gdx.files.internal("snakeBody.png"));
            shapeRenderer = new ShapeRenderer();
        }
        
        
        //COMENTET QUERYINPUT
        @Override
        public void render(float delta){
            if(!hasHit){
                //queryInput();

                timer -= delta;
                if(timer <= 0){
                    timer = MOVE_TIME; 
                    moveSnake();
                    checkForOutOfBounds();
                    updateBodyPartsPosition();
                    checkSnakeBodyCollision();
                    directionSet = false;
                }
            
                checkAppleCollision();
                checkAndPlaceApple();
                clearScreen();
                if(gridDraw){
                    drawGrid();
                }
                draw();
            }
        }
        
        private void checkForOutOfBounds(){
            if(snakeX >= Gdx.graphics.getWidth()){
                snakeX = 0;
            }
            if(snakeX < 0){
                snakeX = Gdx.graphics.getWidth() - SNAKE_MOVEMENT;
            }
            if(snakeY >= Gdx.graphics.getHeight()){
                snakeY = 0;
            }
            if(snakeY < 0){
                snakeY = Gdx.graphics.getHeight() - SNAKE_MOVEMENT;
            }
        }
        
        private void moveSnake(){
            snakeXBeforeUpdate = snakeX;
            snakeYBeforeUpdate = snakeY;
            
            switch(snakeDirection){
                case RIGHT:{
                    snakeX += SNAKE_MOVEMENT;
                    return;
                }
                case LEFT:{
                    snakeX -= SNAKE_MOVEMENT;
                    return;
                }
                case UP:{
                    snakeY += SNAKE_MOVEMENT;
                    return;
                }
                case DOWN:{
                    snakeY -= SNAKE_MOVEMENT;
                    return;
                }
            }
        }
        
        private void checkAndPlaceApple() {
            if (!appleAvailable) {
                do {
                    appleX = MathUtils.random(Gdx.graphics.getWidth()
                        / SNAKE_MOVEMENT - 1) * SNAKE_MOVEMENT;
                    appleY = MathUtils.random(Gdx.graphics.getHeight()
                        / SNAKE_MOVEMENT - 1) * SNAKE_MOVEMENT;
                    appleAvailable = true;
                } while (appleX == snakeX && appleY == snakeY);
            }
        }
        
        private void clearScreen() {
            Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g,Color.BLACK.b, Color.BLACK.a);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        }
        
        private void draw() {
            batch.begin();
            batch.draw(snakeHead, snakeX, snakeY);
            for (BodyPart bodyPart : bodyParts) {
                bodyPart.draw(batch);
            }
            if (appleAvailable) {
                batch.draw(apple, appleX, appleY);
            }
            batch.end();
        }
        
        private void checkAppleCollision() {
            if (appleAvailable && appleX == snakeX && appleY == snakeY) {
                BodyPart bodyPart = new BodyPart(snakeBody);
                bodyPart.updateBodyPosition(snakeX, snakeY);
                bodyParts.insert(0,bodyPart);
                appleAvailable = false;
            }
        }
        
        private void updateBodyPartsPosition() {
            if (bodyParts.size > 0) {
                BodyPart bodyPart = bodyParts.removeIndex(0);
                bodyPart.updateBodyPosition(snakeXBeforeUpdate,snakeYBeforeUpdate);
                bodyParts.add(bodyPart);
            }
        }
        
       private void drawGrid() {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            for (int x = 0; x < Gdx.graphics.getWidth(); x += GRID_CELL) {
                for (int y = 0; y < Gdx.graphics.getHeight(); y += GRID_CELL) {
                    shapeRenderer.rect(x,y, GRID_CELL, GRID_CELL);
                }
            }
            shapeRenderer.end();
        }
       
       private void updateIfNotOppositeDirection(int newSnakeDirection, int oppositeDirection) {
            if (snakeDirection != oppositeDirection || bodyParts.size == 0){
                snakeDirection = newSnakeDirection;
            }                
        }
       
       private void updateDirection(int newSnakeDirection) {
            if (!directionSet && snakeDirection != newSnakeDirection) {
            
                directionSet = true;
                switch (newSnakeDirection) {
                    case LEFT: {
                            updateIfNotOppositeDirection(newSnakeDirection, RIGHT);
                        }
                        break;
                    case RIGHT: {
                            updateIfNotOppositeDirection(newSnakeDirection, LEFT);
                         }
                        break;
                    case UP: {
                             updateIfNotOppositeDirection(newSnakeDirection, DOWN);
                        }
                        break;
                    case DOWN: {
                            updateIfNotOppositeDirection(newSnakeDirection, UP);
                        }
                        break;
                }
            }
        }
       
       private void checkSnakeBodyCollision() {
            for (BodyPart bodyPart : bodyParts) {
                if (bodyPart.x == snakeX && bodyPart.y == snakeY) 
                    hasHit = true;
                }
        }
        
     //DELETE QUERYINPUT
       /*
       private void queryInput() {
            boolean lPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
            boolean rPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
            boolean uPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
            boolean dPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);
        
            if (lPressed) updateDirection(LEFT);
            if (rPressed) updateDirection(RIGHT);
            if (uPressed) updateDirection(UP);
            if (dPressed) updateDirection(DOWN);
       }
        */
     //END DELETE QUERYINPUT  
     
    //##############
    //INPUTPROCESSOR
    //##############
    @Override
    public boolean keyDown(int keycode) {
          
            if(keycode == Input.Keys.LEFT) updateDirection(LEFT);
            if(keycode == Input.Keys.RIGHT) updateDirection(RIGHT);
            if(keycode == Input.Keys.UP) updateDirection(UP);
            if(keycode == Input.Keys.DOWN) updateDirection(DOWN); 
            
        return false;    
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean keyUp(int keycode) { 
            if(keycode == Input.Keys.P) gridDraw = false;
            if(keycode == Input.Keys.O) gridDraw = true;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
            if(character == 'a') updateDirection(LEFT);
            if(character == 'd') updateDirection(RIGHT);
            if(character == 'w') updateDirection(UP);
            if(character == 's') updateDirection(DOWN); 
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        if(amount == 1){
            timer = timer * 5;
        }
        
        if(amount == -1){
            timer = timer / 2;
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return false;
    }

    //END INPUTPROCESSOR
       
        //CLASS BODYPART
        public class BodyPart {
    
            private int x,y;
            private Texture texture;
    
            public BodyPart(Texture texture) {
                this.texture = texture;
            }
    
            public void updateBodyPosition(int x, int y) {
                this.x = x;
                this.y = y;
            }
    
            public void draw(Batch batch) {
                 if (!(x == snakeX && y == snakeY)){ 
                     batch.draw(texture,x, y);
                 }
            }
        }
        //END BODYPART
        

        
}
