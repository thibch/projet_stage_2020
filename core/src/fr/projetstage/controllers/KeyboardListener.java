package fr.projetstage.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import fr.projetstage.dataFactories.SoundFactory;
import fr.projetstage.models.monde.salle.Orientation;

public class KeyboardListener implements InputProcessor {

    private static int coefKeyboard = 20;
    private boolean quit = false;
    private boolean afficheDebug = false;
    private Vector2 acceleration = new Vector2(0f, 0f);
    private Orientation direction;

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

    public Orientation getDirection(){
        return direction;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.SPACE:
                SoundFactory.getInstance().playsoundDeath(100);
                break;
            case Input.Keys.ESCAPE:
                quit = true;
                break;
            case Input.Keys.LEFT:
                direction = Orientation.GAUCHE;
                break;
            case Input.Keys.RIGHT:
                direction = Orientation.DROITE;
                break;
            case Input.Keys.UP:
                direction = Orientation.HAUT;
                break;
            case Input.Keys.DOWN:
                direction = Orientation.BAS;
                break;
            case Input.Keys.Q:
                acceleration.set(-1, acceleration.y);
                break;
            case Input.Keys.D:
                acceleration.set(1, acceleration.y);
                break;
            case Input.Keys.Z:
                acceleration.set(acceleration.x, 1);
                break;
            case Input.Keys.S:
                acceleration.set(acceleration.x, -1);
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.J) {
            afficheDebug = !afficheDebug;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            acceleration.set(1, acceleration.y);
        }else if(Gdx.input.isKeyPressed(Input.Keys.Q)){
            acceleration.set(-1, acceleration.y);
        }else{
            acceleration.set(0, acceleration.y);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.Z)){
            acceleration.set(acceleration.x, 1);
        }else if(Gdx.input.isKeyPressed(Input.Keys.S)){
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
