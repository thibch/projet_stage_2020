package fr.projetstage.models.entites.objets.objetsPiedestal;

import com.badlogic.gdx.graphics.Texture;
import fr.projetstage.models.monde.GameWorld;

public abstract class ObjetSurPiedestal{

    private String nom;
    private int rarete;
    protected GameWorld world;

    public ObjetSurPiedestal(GameWorld world){
        this.world = world;
    }

    public abstract Texture getTexture();

    public abstract void applyEffect();
}
