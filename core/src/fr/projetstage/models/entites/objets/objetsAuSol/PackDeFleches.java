package fr.projetstage.models.entites.objets.objetsAuSol;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.monde.GameWorld;

public class PackDeFleches extends Consommable {
    /**
     * Flèche qui se trouve au sol dans la salle
     * @param world le gameworld
     * @param position la position de l'objet
     * @param id l'id de l'objet
     */
    public PackDeFleches(GameWorld world, Vector2 position, int id) {
        super(world, position, new Vector2(0,0), 5f/16f, 5f/16f, id, "PackDeFleches");
    }

    @Override
    public void applyEffect() {
        world.getJoueur().addMunition(1);
        detruit = true;
    }

    @Override
    public Texture getTexture() {
        return TextureFactory.getInstance().getArrowUI();
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void draw(SpriteBatch batch, float x, float y){
        batch.draw(TextureFactory.getInstance().getArrowUI(), x + getPosition().x, y + getPosition().y, 1, 1);
    }
}
