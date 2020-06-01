package fr.projetstage.models.entites.objets.objetsCoffre;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.monde.GameWorld;

public class PotionVitesse extends Equipement {

    public PotionVitesse(GameWorld world) {
        super(world, "Potion Jaune");
    }

    @Override
    public Texture getTexture() {
        return TextureFactory.getInstance().getPotionJaune();
    }

    @Override
    public void applyEffect() {
        world.getJoueur().setSpeed(world.getJoueur().getSpeed()*1.5f);
    }

    @Override
    public void reverseEffect() {
        world.getJoueur().setSpeed(world.getJoueur().getSpeed()/1.5f);
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void draw(SpriteBatch batch, float x, float y) {
        //Utile pour l'affichage dans l'interface (Et non dans le monde, voir le call de getTexture() pour ça)
    }
}
