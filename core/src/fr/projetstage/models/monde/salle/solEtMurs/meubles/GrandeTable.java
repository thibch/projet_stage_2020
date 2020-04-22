package fr.projetstage.models.monde.salle.solEtMurs.meubles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.monde.GameWorld;

public class GrandeTable extends NonDestructible {
    public GrandeTable(GameWorld world, Vector2 position) {
        super(world, position, 2f, 1f);
    }

    @Override
    public void draw(SpriteBatch batch, float x, float y) {
        batch.draw(TextureFactory.getInstance().getGrandeTable(), x + getPosition().x, y + getPosition().y, tailleX, tailleY);
    }
}
