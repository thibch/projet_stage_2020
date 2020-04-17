package fr.projetstage.models.entites.ennemis;

import com.badlogic.gdx.physics.box2d.Body;
import fr.projetstage.models.Animation;
import fr.projetstage.models.entites.EntiteMouvante;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.monde.GameWorld;

import java.util.HashMap;

public abstract class Ennemi extends EntiteMouvante {

    protected Type type;

    protected Animation idleAnimation;
    protected Animation runningAnimation;

    protected Comportement comportement;

    protected HashMap<EntiteMouvante,Boolean> targets;

    public Ennemi(GameWorld world, Type type){
        this.world = world;
        this.type = type;
        targets = new HashMap<>();
    }

    public Type getType(){
        return type;
    }

    public Body getBody() {
        return body;
    }

    public void addTarget(EntiteMouvante target){
        if(!targets.containsKey(target)){
            targets.put(target, false);
        }
    }

    public void removeTarget(EntiteMouvante target){
        targets.remove(target);
    }
}
