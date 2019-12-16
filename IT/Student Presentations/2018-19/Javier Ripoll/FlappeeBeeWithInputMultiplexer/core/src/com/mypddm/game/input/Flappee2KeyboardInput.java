package com.mypddm.game.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mypddm.game.Flappee;

public class Flappee2KeyboardInput implements InputProcessor {
    private Flappee flappee;

    public Flappee2KeyboardInput(Flappee flappee) {
        this.flappee = flappee;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                flappee.flyUp();
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
