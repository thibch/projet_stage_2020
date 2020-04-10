package fr.projetstage.models.entites.ennemis;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.monde.salle.Orientation;

public class ChauveSouris extends Ennemi{
    public ChauveSouris(GameWorld world, Vector2 position, Type type) {
        super(world, type);
    }

    @Override
    public void draw(SpriteBatch batch) {

    }

    @Override
    public void update(Orientation direction) {

    }
}
