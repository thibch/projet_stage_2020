package fr.projetstage.models.monde.salle;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Trou extends Obstacle {


    @Override
    public boolean estNonDestructible() {
        return true;
    }

    @Override
    public void draw(SpriteBatch batch) {

    }
}
