package fr.projetstage.models.entites.objets.objetsPiedestal;

import com.badlogic.gdx.graphics.Texture;
import fr.projetstage.models.entites.objets.ObjetsTousTypes;
import fr.projetstage.models.monde.GameWorld;

public abstract class ObjetDansCoffre extends ObjetsTousTypes {

    protected String nom;
    private int rarete;
    protected GameWorld world;

    public ObjetDansCoffre(GameWorld world){
        this.world = world;
    }

    /**
     * Permet de récupérer la texture de l'objet
     * @return la texture de l'objet
     */
    public abstract Texture getTexture();

    @Override
    public abstract void applyEffect();

    @Override
    public abstract void reverseEffect();
}
