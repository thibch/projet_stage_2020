package fr.projetstage.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class KeyboardListener implements InputProcessor {

    private static int coefKeyboard = 20;
    private boolean quit = false;
    private boolean afficheDebug = false;
    private Vector2 acceleration = new Vector2(0f, 0f);

    public void remiseAZeroAccel(){
        acceleration.set(0f, 0f);
    }

    public static int getCoefKeyboard() {
        return coefKeyboard;
    }

    public boolean isQuit() {
        return quit;
    }

    public boolean isAfficheDebug() {
        return afficheDebug;
    }

    public Vector2 getAcceleration() {
        return acceleration;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.SPACE:
            case Input.Keys.Q:
                quit = true;
                break;
            case Input.Keys.LEFT:
                acceleration.set(-1, acceleration.y);
                break;
            case Input.Keys.RIGHT:
                acceleration.set(1, acceleration.y);
                break;
            case Input.Keys.UP:
                acceleration.set(acceleration.x, 1);
                break;
            case Input.Keys.DOWN:
                acceleration.set(acceleration.x, -1);
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.D) {
            afficheDebug = !afficheDebug;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            acceleration.set(1, acceleration.y);
        }else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            acceleration.set(-1, acceleration.y);
        }else{
            acceleration.set(0, acceleration.y);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            acceleration.set(acceleration.x, 1);
        }else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            acceleration.set(acceleration.x, -1);
        }else{
            acceleration.set(acceleration.x, 0);
        }
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
