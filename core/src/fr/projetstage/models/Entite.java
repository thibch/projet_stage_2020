package fr.projetstage.models;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

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

    /**
     * Permet de récupérer la position de l'entite
     * @return la position de l'entité
     */
    Vector2 getPosition();

    /**
     * Génère le corps de l'entité
     */
    void generateBody();

    /**
     * Détruit le corps de l'entité
     */
    void destroyBody();
}
