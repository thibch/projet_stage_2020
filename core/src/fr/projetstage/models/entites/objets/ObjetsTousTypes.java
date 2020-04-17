package fr.projetstage.models.entites.objets;

import com.badlogic.gdx.physics.box2d.Body;
import fr.projetstage.models.Entite;

public abstract class ObjetsTousTypes implements Entite {

    protected Body body;
    protected boolean detruit;
    private boolean touche;

    public abstract void update();
    public abstract void applyEffect();

    public Body getBody(){
        return body;
    }

    public boolean estDetruit() {
        return detruit;
    }

    public void setTouche(boolean b){
        this.touche = b;
    }
    public boolean getTouche(){
        return this.touche;
    }
}
