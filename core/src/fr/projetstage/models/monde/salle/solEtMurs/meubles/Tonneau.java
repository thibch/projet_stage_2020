package fr.projetstage.models.monde.salle.solEtMurs.meubles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.projetstage.models.monde.GameWorld;

public class Tonneau extends Obstacle {

    public Tonneau(GameWorld world, Vector2 position) {
        super(world, position);
    }

    @Override
    public boolean estNonDestructible() {
        return false;
    }

    @Override
    public void draw(SpriteBatch batch, float x, float y) {

    }

    @Override
    public void generateBody() {

    }

    @Override
    public void destroyBody() {

    }
}
