package fr.projetstage.models.entites.objets.objetsAuSol;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.projetstage.models.CollisionFilter;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.entites.TypeEntite;
import fr.projetstage.models.entites.objets.ObjetsTousTypes;
import fr.projetstage.models.monde.GameWorld;

public abstract class ObjetAuSol extends ObjetsTousTypes {

    private Vector2 posShape;
    private float hauteur;
    private float largeur;
    private int id;


    public ObjetAuSol(GameWorld world, Vector2 position, Vector2 posShape, float largeur, float hauteur, int id){
        super(world, position, id);
        this.posShape = posShape;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.id = id;
    }

    @Override
    public void generateBody() {

        // BodyDef
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position);
        //

        // Récupération du body dans le world
        body = world.getWorld().createBody(bodyDef);

        CircleShape circleShape = new CircleShape();
        Vector2 posShapeCircle = new Vector2(posShape.x + largeur/2f, posShape.y + hauteur/2f);
        circleShape.setPosition(posShapeCircle);
        circleShape.setRadius(largeur/2f);

        // FixtureDef
        FixtureDef fixtureDef1 = new FixtureDef();
        fixtureDef1.shape = circleShape;
        fixtureDef1.isSensor = false;
        fixtureDef1.density = 0;
        fixtureDef1.restitution = 0f;
        fixtureDef1.friction = 0f;
        //collision
        fixtureDef1.filter.categoryBits = CollisionFilter.OBJET.getCategory();
        fixtureDef1.filter.maskBits =(short) (CollisionFilter.JOUEUR.getCategory() | CollisionFilter.DECOR.getCategory());

        // Met en place la fixture sur le body
        body.setFixedRotation(true);
        body.createFixture(fixtureDef1); // Association à l’objet

        body.setUserData(new Type(TypeEntite.PICKUP, id));

        circleShape.dispose();
    }

    @Override
    public abstract void draw(SpriteBatch batch, float x, float y);

    @Override
    public void reverseEffect(){

    }

    @Override
    public void update() {
        if(Math.abs(body.getWorldCenter().x - world.getJoueur().getPosition().x) >= 0.8f || Math.abs(body.getWorldCenter().y - world.getJoueur().getPosition().y) >= 0.8f){
            body.setLinearVelocity(new Vector2(0.80f * body.getLinearVelocity().x,0.80f * body.getLinearVelocity().y));
        }
        super.update();
    }
}
