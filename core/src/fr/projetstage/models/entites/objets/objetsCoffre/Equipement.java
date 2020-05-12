package fr.projetstage.models.entites.objets.objetsCoffre;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import fr.projetstage.models.entites.objets.ObjetsTousTypes;
import fr.projetstage.models.monde.GameWorld;

public abstract class Equipement extends ObjetsTousTypes {

    protected String nom;
    private int rarete;

    /**
     * Constructeur d'objets qu'on peut mettre dans l'inventaire du joueur
     * @param world le monde physique ou se trouve l'objet
     */
    public Equipement(GameWorld world){
        super(world, new Vector2(0,0), 0);
    }


    @Override
    public void generateBody() {
        //Ne fais rien
    }

    @Override
    public void destroyBody() {
        //Ne fais rien
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

    @Override
    public abstract String getDescription();
}
