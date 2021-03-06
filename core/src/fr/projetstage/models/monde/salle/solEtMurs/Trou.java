package fr.projetstage.models.monde.salle.solEtMurs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.monde.salle.solEtMurs.meubles.Obstacle;

public class Trou extends Obstacle {

    public Trou(GameWorld world, Vector2 position) {
        super(world, position);
    }

    @Override
    public boolean estNonDestructible() {
        return true;
    }

    @Override
    public void draw(SpriteBatch batch, float x, float y) {

    }
}
