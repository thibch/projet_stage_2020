package fr.projetstage.models.monde.salle.solEtMurs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.projetstage.models.monde.salle.meubles.Obstacle;

public class Piege extends Obstacle {

    @Override
    public boolean estNonDestructible() {
        return false;
    }

    @Override
    public void draw(SpriteBatch batch) {

    }
}
