package fr.projetstage.models.monde.salle.solEtMurs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.projetstage.models.monde.salle.meubles.Obstacle;

public class Trou extends Obstacle {

    @Override
    public boolean estNonDestructible() {
        return true;
    }

    @Override
    public void draw(SpriteBatch batch, float x, float y) {

    }
}
