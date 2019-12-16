/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 *
 * @author julio
 */
public class Teclado implements InputProcessor{
    private GameScreen juego;
    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;
    
    public Teclado(GameScreen juego) 
    {
        this.juego = juego;
    }

    @Override
    public boolean keyDown(int i) {
        boolean lPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean rPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);

        if(lPressed) 
        {
            System.out.println("LEFT"); 
            juego.updateDirection(LEFT);
        }
        if(rPressed) 
        {
            System.out.println("RIGHT"); 
            juego.updateDirection(RIGHT);
        }

        return true;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
}
