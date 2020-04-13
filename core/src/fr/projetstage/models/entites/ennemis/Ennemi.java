package fr.projetstage.models.entites.ennemis;

import com.badlogic.gdx.physics.box2d.Body;
import fr.projetstage.models.Animation;
import fr.projetstage.models.entites.EntiteMouvante;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.monde.GameWorld;

public abstract class Ennemi extends EntiteMouvante {

    protected int pointDeVie;
    private boolean touche;
    protected Type type;

    protected Animation idleAnimation;
    protected Animation runningAnimation;

    protected Body body;
    protected GameWorld world;

    public Ennemi(GameWorld world, Type type){
        this.world = world;
        this.type = type;
    }

    public Type getType(){
        return type;
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
