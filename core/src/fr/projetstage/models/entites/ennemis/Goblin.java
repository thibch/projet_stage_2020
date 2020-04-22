package fr.projetstage.models.entites.ennemis;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.Orientation;

public class Goblin extends Ennemi{
    public Goblin(GameWorld world, Vector2 position, Type type) {
        super(world, position, type);
    }

    @Override
    public void draw(SpriteBatch batch, float x, float y) {

    }

    public void update(Orientation direction) {

    }
}
