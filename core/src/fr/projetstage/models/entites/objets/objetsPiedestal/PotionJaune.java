package fr.projetstage.models.entites.objets.objetsPiedestal;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.monde.GameWorld;

public class PotionJaune extends ObjetDansCoffre {

    public PotionJaune(GameWorld world) {
        super(world);
        nom = "Potion Jaune";
    }

    @Override
    public Texture getTexture() {
        return TextureFactory.getInstance().getPotionJaune();
    }

    @Override
    public void applyEffect() {
        world.getJoueur().setSpeed(world.getJoueur().getSpeed()*2);
    }

    @Override
    public void reverseEffect() {
        world.getJoueur().setSpeed(world.getJoueur().getSpeed()/2);
    }

    @Override
    public void draw(SpriteBatch batch) {
        //Utile pour l'affichage dans l'interface (Et non dans le monde, voir le call de getTexture() pour ça)
    }
}
