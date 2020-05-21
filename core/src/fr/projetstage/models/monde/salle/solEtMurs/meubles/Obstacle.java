package fr.projetstage.models.monde.salle.solEtMurs.meubles;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import fr.projetstage.models.Entite;
import fr.projetstage.models.monde.GameWorld;

public abstract class Obstacle implements Entite {

    protected Vector2 position;
    protected final GameWorld world;

    protected Body body;
    protected BodyDef bodyDef;
    protected FixtureDef fixtureDef;

    /**
     * Constructeur d'un obstacle
     * @param world le monde dans lequel se trouve l'obstacle
     * @param position la position de l'obstacle dans le monde
     */
    public Obstacle(GameWorld world, Vector2 position){
        this.world = world;
        this.position = position;

        // BodyDef
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(position);
        //

    }

    @Override
    public void generateBody(){
        // Récupération du body dans le world
        body = world.getWorld().createBody(bodyDef);

        // Met en place la fixture sur le body
        body.createFixture(fixtureDef); // Association à l’objet

    }

    @Override
    public void destroyBody(){
        world.getWorld().destroyBody(body);
    }

    public Vector2 getPosition(){
        return position;
    }

    /**
     * Renvoie une booleen qui permet de savoir si un obstacle est destructible ou non
     * @return vrai si l'objet est indestructible
     */
    public abstract boolean estNonDestructible();
}
