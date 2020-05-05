package fr.projetstage.models.entites.objets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import fr.projetstage.models.Entite;
import fr.projetstage.models.monde.GameWorld;

public abstract class ObjetsTousTypes implements Entite {

    protected GameWorld world;
    protected Body body;
    protected Vector2 position;
    protected final int id;

    private boolean touche;
    protected boolean detruit;

    public ObjetsTousTypes(GameWorld world, Vector2 position, int id) {
        this.world = world;
        this.position = position;
        this.id = id;
    }

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
}
