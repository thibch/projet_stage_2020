package fr.projetstage.models;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
}
