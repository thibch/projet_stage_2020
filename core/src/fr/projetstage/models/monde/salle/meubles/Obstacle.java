package fr.projetstage.models.monde.salle.meubles;

import com.badlogic.gdx.physics.box2d.Body;
import fr.projetstage.models.Entite;

public abstract class Obstacle implements Entite {

    protected Body body;

    public float getX(){
        return body.getPosition().x;
    }

    public float getY(){
        return body.getPosition().y;
    }

    public abstract boolean estNonDestructible();
}
