package fr.projetstage.models.entites.objets.objetsCoffre;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.monde.GameWorld;

public class Sunglasses extends Equipement {

    /**
     * Constructeur de l'objet lunette de soleil
     * @param world le monde dans lequel existe l'objet
     */
    public Sunglasses(GameWorld world) {
        super(world, "Sunglasses");
    }

    @Override
    public Texture getTexture() {
        return TextureFactory.getInstance().getSunglasses();
    }

    @Override
    public void applyEffect() {
        //aucuns
    }

    @Override
    public void reverseEffect() {
    }

    @Override
    public String getDescription() {
        return "Sunglasses\nThere is not sunlight down there.\nYou look cooler.";
    }

    @Override
    public void draw(SpriteBatch batch, float x, float y) {
        //Utile pour l'affichage dans l'interface (Et non dans le monde, voir le call de getTexture() pour ça)
    }
}
