package fr.projetstage.models.monde.salle.solEtMurs.meubles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.monde.GameWorld;

public class Biblio extends NonDestructible {
    /**
     * @param world le gameworld
     * @param position la position de la bibliothèque dans la salle
     */
    public Biblio(GameWorld world, Vector2 position) {
        super(world, position, 1f, 1f);
    }

    @Override
    public void draw(SpriteBatch batch, float x, float y) {
        batch.draw(TextureFactory.getInstance().getBibliotheque(), x + getPosition().x, y + getPosition().y, tailleX, tailleY);
    }
}
