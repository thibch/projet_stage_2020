package fr.projetstage.models.monde.salle.meubles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.monde.GameWorld;

public class Table extends NonDestructible {
    public Table(GameWorld world, Vector2 position) {
        super(world, position, 1f);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(TextureFactory.getInstance().getPetiteTable(), body.getPosition().x, body.getPosition().y, taille, taille);
    }
}
