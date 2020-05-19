package fr.projetstage.models.entites.objets.objetsCoffre;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.monde.GameWorld;

public class Coeur extends Equipement {

    /**
     * Constructeur de l'objet coeur
     * @param world le monde dans lequel existe l'objet
     */
    public Coeur(GameWorld world) {
        super(world);
        nom = "Coeur";
    }

    @Override
    public Texture getTexture() {
        return TextureFactory.getInstance().getCoeurVide();
    }

    @Override
    public void applyEffect() {
        world.getJoueur().setPointdeVieMax(world.getJoueur().getPointdeVieMax()+2);
    }

    @Override
    public void reverseEffect() {
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
