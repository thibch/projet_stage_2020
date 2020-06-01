package fr.projetstage.models.entites.objets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import fr.projetstage.models.Entite;
import fr.projetstage.models.monde.GameWorld;

import java.util.Objects;

public abstract class ObjetsTousTypes implements Entite {

    protected GameWorld world;
    protected Body body;
    protected Vector2 position;
    protected final int id;

    private boolean touche;
    protected boolean detruit;
    private String nom;

    /**
     * Constructeur d'un objet
     * @param world le monde dans lequel se trouve l'objet
     * @param position la position de l'objet dans le monde
     * @param id l'id de l'objet permettant de l'identifier
     * @param nom nom de l'objet
     */
    public ObjetsTousTypes(GameWorld world, Vector2 position, int id, String nom) {
        this.world = world;
        this.position = position;
        this.id = id;
        this.nom = nom;
    }

    /**
     * Methode permettant d'appliquer l'effet d'un objet si il est ramassé
     */
    public void update(){
        if(getTouche()){
            applyEffect();
            setTouche(false);
        }
        position = body.getPosition();
    }

    /**
     * Applique l'effet de l'objet
     */
    public abstract void applyEffect();

    /**
     * Applique l'effet inver de l'objet
     */
    public abstract void reverseEffect();

    /**
     * Getter du body
     * @return le body
     */
    public Body getBody(){
        return body;
    }

    /**
     * Vrai si l'objet est a détruire
     * @return l'objet à détruire
     */
    public boolean estDetruit() {
        return detruit;
    }

    /**
     * Setter si on a touché
     * @param touche touché ou non
     */
    public void setTouche(boolean touche){
        this.touche = touche;
    }

    /**
     * Getter touché
     * @return touché ou non
     */
    public boolean getTouche(){
        return this.touche;
    }

    @Override
    public void destroyBody(){
        world.getWorld().destroyBody(body);
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    /**
     * Permet de récupérer la texture de l'objet
     * @return la texture de l'objet
     */
    public abstract Texture getTexture();

    /**
     * Methode permettant d'obtenir la description d'un objet si il est ramassable
     * @return un String de la description de l'objet
     */
    public abstract String getDescription();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjetsTousTypes that = (ObjetsTousTypes) o;
        return nom.equals(that.nom);
    }

    public String getNom() {
        return nom;
    }
}
