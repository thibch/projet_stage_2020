package fr.projetstage.models.entites.objets;

import com.badlogic.gdx.physics.box2d.Body;
import fr.projetstage.models.Entite;

public abstract class ObjetsTousTypes implements Entite {

    protected Body body;
    protected boolean detruit;
    private boolean touche;

    public void update(){
        if(getTouche()){
            applyEffect();
            setTouche(false);
        }
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
}
