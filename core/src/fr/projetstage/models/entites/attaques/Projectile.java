package fr.projetstage.models.entites.attaques;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import fr.projetstage.models.entites.EntiteMouvante;
import fr.projetstage.models.Orientation;
import fr.projetstage.models.monde.GameWorld;

public abstract class Projectile extends EntiteMouvante {

    private double dureeVie; // A détermnier en fonction de la portée et vitesse d'attaque (Classe Attaque)
    protected Body body;

    public Projectile(GameWorld world, Vector2 position) {
        super(world, position);
    }


    @Override
    public void draw(SpriteBatch batch, float x, float y) {

    }

    public void update(Orientation direction) {

    }

    @Override
    public Vector2 getPosition(){
        if(body != null) return body.getPosition();
        System.out.println("after BodyPosition");
        return position;
    }

    @Override
    public void generateBody(){
    }

    @Override
    public void destroyBody(){
    }

}
