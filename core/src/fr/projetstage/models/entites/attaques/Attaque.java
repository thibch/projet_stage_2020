package fr.projetstage.models.entites.attaques;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import fr.projetstage.models.Orientation;

public abstract class Attaque {

    /**
     * Lorsque l'on attaque au Corps à Corps
     * @param bodyParent le body du lanceur d'attaque
     * @param direction la direction auquel se trouve le lanceur d'attaque
     */
    public void attaque(Body bodyParent, Orientation direction){

    }

    /**
     * Lorsque l'on attaque à distance
     * @param positionLanceur La position du lanceur d'attaque
     * @param direction La direction du lanceur d'attaque
     * @param id l'id du projectile qui sera lancé
     * @return le projectile lancé
     */
    public Fleche attaqueDistance(Vector2 positionLanceur, Orientation direction, int id) {
        return null;
    }
}
