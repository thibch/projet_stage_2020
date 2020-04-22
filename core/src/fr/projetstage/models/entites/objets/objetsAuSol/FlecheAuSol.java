package fr.projetstage.models.entites.objets.objetsAuSol;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.monde.GameWorld;

public class FlecheAuSol extends ObjetAuSol{
    public FlecheAuSol(GameWorld world, Vector2 position, Vector2 posShape, float largeur, float hauteur, int id) {
        super(world, position, posShape, largeur, hauteur, id);
    }

    @Override
    public void applyEffect() {
        world.getJoueur().addMunition(1);
    }

    @Override
    public void draw(SpriteBatch batch, float x, float y){
        batch.draw(TextureFactory.getInstance().getPotionRouge(), x + getPosition().x, y + getPosition().y, 1, 1);
    }
}
