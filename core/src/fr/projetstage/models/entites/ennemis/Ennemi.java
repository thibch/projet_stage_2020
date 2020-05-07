package fr.projetstage.models.entites.ennemis;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.projetstage.models.Animation;
import fr.projetstage.models.entites.EntiteMouvante;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.monde.GameWorld;

import java.util.HashMap;
import java.util.Map;

public abstract class Ennemi extends EntiteMouvante {

    protected Type type;

    protected Animation idleAnimation;
    protected Animation runningAnimation;

    protected Comportement comportement;

    protected HashMap<EntiteMouvante,Boolean> targets;

    protected float largeur;
    protected float hauteur;

    /**
     * @param world le gameworld
     * @param position la position de l'ennemi
     * @param type le type de l'ennemi
     */
    public Ennemi(GameWorld world, Vector2 position, Type type){
        super(world, position);
        this.world = world;
        this.type = type;
        targets = new HashMap<>();
    }

    /**
     * Le type de l'ennemi
     * @return le type de l'ennemi
     */
    public Type getType(){
        return type;
    }

    /**
     * Le body de l'ennmi
     * @return le body
     */
    public Body getBody() {
        return body;
    }

    /**
     * Ajoute une cible à l'ennemi
     * @param target la cible de l'ennemi
     */
    public void addTarget(EntiteMouvante target){
        if(!targets.containsKey(target)){
            targets.put(target, false);
        }
    }

    /**
     * Supprime une cible de l'ennemi
     * @param target la cible à supprimer
     */
    public void removeTarget(EntiteMouvante target){
        targets.remove(target);
    }

    @Override
    public void generateBody(){// BodyDef
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position);
        //

        // Récupération du body dans le world
        body = world.getWorld().createBody(bodyDef);

        // Création de la shape pour le slime
        Vector2 posShape = new Vector2(0f, 1f / 16f); // La position du shape est en fonction de la position du body
        Vector2[] vertices = new Vector2[4];
        vertices[0] = posShape;
        vertices[1] = new Vector2(posShape.x + largeur, posShape.y);
        vertices[2] = new Vector2(posShape.x + largeur, posShape.y + hauteur);
        vertices[3] = new Vector2(posShape.x, posShape.y + hauteur);

        PolygonShape rectangle = new PolygonShape();
        rectangle.set(vertices);

        // FixtureDef
        FixtureDef fixtureDef1 = new FixtureDef();
        fixtureDef1.shape = rectangle;
        fixtureDef1.density = 1f;
        fixtureDef1.restitution = 0f;
        fixtureDef1.friction = 0f;

        // Met en place la fixture sur le body
        body.setFixedRotation(true);
        body.createFixture(fixtureDef1); // Association à l’objet

        body.setUserData(type);

        rectangle.dispose();
    }

    @Override
    public void destroyBody(){
        world.getWorld().destroyBody(body);
    }

    public void update(){
        super.update();

        comportement.update();
        body.setLinearVelocity(new Vector2(0.8f * body.getLinearVelocity().x,0.8f * body.getLinearVelocity().y)); //TODO: a changer plus tard, juste pour pas qu'il glide à l'infini
        position = body.getPosition();
        if(mort){
            comportement.getBehavior().setEnabled(false);
        }

        // Si on est en coolDown mais que le temps est dépassé alors nous ne sommes plus en cooldown
        if(onCoolDown && currentTime > coolDownTime) {
            currentTime = 0;
            onCoolDown = false;
        }
        //attaque CAC
        if(!onCoolDown){
            for(Map.Entry<EntiteMouvante, Boolean> target : targets.entrySet()){
                target.getKey().setTouche(this);
            }
            onCoolDown = true;
        }
    }
}
