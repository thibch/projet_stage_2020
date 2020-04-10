package fr.projetstage.models.entites.ennemis;

import com.badlogic.gdx.physics.box2d.Body;
import fr.projetstage.models.Animation;
import fr.projetstage.models.entites.EntiteMouvante;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.entites.TypeEntite;
import fr.projetstage.models.monde.GameWorld;

public abstract class Ennemi extends EntiteMouvante {

    protected int pointDeVie;
    protected Body body;

    protected Animation idleAnimation;
    protected Animation runningAnimation;

    private boolean touche;

    protected GameWorld world;
    protected Type type;

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
