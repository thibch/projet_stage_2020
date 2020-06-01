package fr.projetstage.models.entites.objets.objetsCoffre;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.monde.GameWorld;

public class PotionForce extends Equipement {

    /**
     * Constructeur de l'objet potion d'attaque
     * @param world le monde dans lequel existe l'objet
     */
    public PotionForce(GameWorld world) {
        super(world, "Strange Brewing");
    }

    @Override
    public Texture getTexture() {
        return TextureFactory.getInstance().getPotionVerte();
    }

    @Override
    public void applyEffect() {
        world.getJoueur().setDegats(world.getJoueur().getDegats()+0.5f);
    }

    @Override
    public void reverseEffect() {
        world.getJoueur().setDegats(world.getJoueur().getDegats()-0.5f);
    }

    @Override
    public String getDescription() {
        return "Strange Brewing\nYou don't know what it is but you feel stronger.\n+0.5 attack";
    }

    @Override
    public void draw(SpriteBatch batch, float x, float y) {
        //Utile pour l'affichage dans l'interface (Et non dans le monde, voir le call de getTexture() pour ça)
    }
}
