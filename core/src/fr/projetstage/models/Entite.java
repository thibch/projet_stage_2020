package fr.projetstage.models;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;

/**
 * Entité du monde avec un draw
 */
public interface Entite {
    /**
     * Permet d'afficher l'entité
     * @param batch le spriteBatch pour draw
     * @param x Position de la salle en x
     * @param y Position de la salle en y
     */
    void draw(SpriteBatch batch, float x, float y);

    Vector2 getPosition();

    void generateBody();

    void destroyBody();
}
