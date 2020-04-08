package fr.projetstage.models.monde.salle;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Piege extends Obstacle {

    @Override
    public boolean estNonDestructible() {
        return false;
    }

    @Override
    public void draw(SpriteBatch batch) {

    }
}
