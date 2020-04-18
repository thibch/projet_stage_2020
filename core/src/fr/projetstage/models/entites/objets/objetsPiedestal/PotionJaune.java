package fr.projetstage.models.entites.objets.objetsPiedestal;

import com.badlogic.gdx.graphics.Texture;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.monde.GameWorld;

public class PotionJaune extends ObjetSurPiedestal {

    public PotionJaune(GameWorld world) {
        super(world);
    }

    @Override
    public Texture getTexture() {
        return TextureFactory.getInstance().getPotionJaune();
    }

    @Override
    public void applyEffect() {
        world.getJoueur().setSpeed(world.getJoueur().getSpeed()*2);
    }
}
