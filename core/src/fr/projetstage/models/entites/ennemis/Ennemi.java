package fr.projetstage.models.entites.ennemis;

import com.badlogic.gdx.physics.box2d.Body;
import fr.projetstage.models.Animation;
import fr.projetstage.models.entites.EntiteMouvante;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.monde.GameWorld;

public abstract class Ennemi extends EntiteMouvante {

    protected Type type;

    protected Animation idleAnimation;
    protected Animation runningAnimation;

    public Ennemi(GameWorld world, Type type){
        this.world = world;
        this.type = type;
    }

    public Type getType(){
        return type;
    }

    public Body getBody() {
        return body;
    }
}
