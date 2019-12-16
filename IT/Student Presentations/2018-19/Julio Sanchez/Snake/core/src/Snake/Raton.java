/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import static com.badlogic.gdx.Input.Keys.LEFT;
import static com.badlogic.gdx.Input.Keys.RIGHT;
import static com.badlogic.gdx.Input.Keys.UP;
import com.badlogic.gdx.InputProcessor;

/**
 *
 * @author julio
 */
public class Raton implements InputProcessor{
 private GameScreen juego;
    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;
    
    public Raton(GameScreen juego) 
    {
        this.juego = juego;
    }
    @Override
    public boolean keyDown(int i) {
        return false;
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
        //boolean dPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN); 
        return false;
    }

    @Override
    public boolean scrolled(int i) 
    {
        if(i == 1)
        {
           System.out.println("DOWN"); 
           juego.updateDirection(DOWN);
        }
        else
        {
            System.out.println("UP"); 
            juego.updateDirection(UP);
        }
        return true;
    }
    
    
}
