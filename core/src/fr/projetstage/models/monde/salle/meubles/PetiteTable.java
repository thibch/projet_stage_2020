package fr.projetstage.models.monde.salle.meubles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.monde.GameWorld;

public class PetiteTable extends NonDestructible {
    public PetiteTable(GameWorld world, Vector2 position) {
        super(world, position, 1f, 1f);
    }

    @Override
    public void draw(SpriteBatch batch, float x, float y) {
        batch.draw(TextureFactory.getInstance().getPetiteTable(), x + body.getPosition().x, y + body.getPosition().y, tailleX, tailleY);
    }
}
