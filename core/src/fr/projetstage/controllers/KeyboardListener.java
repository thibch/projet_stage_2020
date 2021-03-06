package fr.projetstage.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import fr.projetstage.dataFactories.SoundFactory;
import fr.projetstage.models.Orientation;

public class KeyboardListener implements InputProcessor {

    private boolean afficheDebug = false;
    private boolean switchWeapon = false;
    private Vector2 acceleration = new Vector2(0f, 0f);
    private Orientation direction;
    private boolean afficheMobile;
    private boolean pause;

    /**
     * Constructeur de l'écouteur de frappe
     */
    public KeyboardListener(){
        reset();
    }

    /**
     * permet de réinitialiser la position et vitesse du joueur
     */
    public void reset(){
        acceleration.set(0f, 0f);
        direction = Orientation.NO_ORIENTATION;
    }

    /**
     * Renvoie une booléen pour activer / desactiver l'affichage de pause
     * @return un booléen a vrai si la touche correspondante à été enfoncée
     */
    public boolean isPause() {
        return pause;
    }

    /**
     * Renvoie une booléen pour activer / desactiver l'affichage de pause
     * @param pause le status du jeu
     */
    public void setPause(boolean pause) {
        this.pause = pause;
    }

    /**
     * Renvoie une booléen pour activer / desactiver l'affichage debug
     * @return un booléen a vrai si la touche correspondante à été enfoncée
     */
    public boolean isAfficheDebug() {
        return afficheDebug;
    }

    /**
     * Renvoie une booléen pour activer / desactiver l'affichage mobile
     * @return un booléen a vrai si on est sur un mobile
     */
    public boolean isAfficheMobile() {
        return afficheMobile;
    }

    /**
     * Renvoie une booléen pour changer d'arme
     * @return un booléen a vrai si la touche correspondante à été enfoncée
     */
    public boolean isSwitchWeapon() {
        return switchWeapon;
    }

    /**
     * Methode permettant de récuperer l'acceleration du joueur
     * @return un Vector2 de l'acceleration du joueur
     */
    public Vector2 getAcceleration() {
        return acceleration;
    }

    /**
     * Methode permettant de renvoyer l'orientation du joueur dans le monde
     * @return un objet Orientation indiquant la direction dans laquelle regarde le joueur
     */
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
                pause = true;
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
        if(keycode == Input.Keys.CONTROL_LEFT){
            switchWeapon = !switchWeapon;
        }
        if(keycode == Input.Keys.L){
            afficheMobile = !afficheMobile;
        }


        // Si des touches sont encore appuyés on ne les ignore pas
        // (Exemple quand on appuit sur gauche puis droite et qu'on lache droite)
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

        // Idem mais pour les flèches
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            direction = Orientation.GAUCHE;
        }else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            direction = Orientation.DROITE;
        }else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            direction = Orientation.BAS;
        }else if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            direction = Orientation.HAUT;
        }else{
            direction = Orientation.NO_ORIENTATION;
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
