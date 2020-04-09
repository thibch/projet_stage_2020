package fr.projetstage.models.entites.ennemis;

import com.badlogic.gdx.physics.box2d.Body;
import fr.projetstage.models.Animation;
import fr.projetstage.models.entites.EntiteMouvante;
import fr.projetstage.models.entites.TypeAttaque;

public abstract class Ennemi extends EntiteMouvante {

    protected int pointDeVie;
    protected Body body;

    protected Animation idleAnimation;
    protected Animation runningAnimation;

    private boolean touche;


    public TypeAttaque getTypeAttaque(){
        return (TypeAttaque)body.getUserData();
    }


    public void setTouche(boolean b) {
        touche = b;
    }

    public boolean getTouche(){
        return touche;
    }

    public Body getBody() {
        return body;
    }
}
