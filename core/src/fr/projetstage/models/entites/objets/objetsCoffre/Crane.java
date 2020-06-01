package fr.projetstage.models.entites.objets.objetsCoffre;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.monde.GameWorld;

public class Crane extends Equipement {

    /**
     * Constructeur de l'objet crane
     * @param world le monde dans lequel existe l'objet
     */
    public Crane(GameWorld world) {
        super(world, "Crane");
    }

    @Override
    public Texture getTexture() {
        return TextureFactory.getInstance().getCrane();
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
        return "Skully\nYour only friend here.";
    }

    @Override
    public void draw(SpriteBatch batch, float x, float y) {
        //Utile pour l'affichage dans l'interface (Et non dans le monde, voir le call de getTexture() pour ça)
    }
}
