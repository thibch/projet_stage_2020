package fr.projetstage.models.entites.objets.objetsAuSol;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Entite;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.entites.TypeEntite;
import fr.projetstage.models.entites.objets.ObjetsTousTypes;
import fr.projetstage.models.monde.GameWorld;

public abstract class ObjetAuSol extends ObjetsTousTypes {

    protected GameWorld world;


    public ObjetAuSol(GameWorld world, Vector2 position, Vector2 posShape, float largeur, float hauteur, int id){
        this.world = world;

        // BodyDef
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position);
        //

        // Récupération du body dans le world
        body = world.getWorld().createBody(bodyDef);

        // Création de la shape pour le slime
        /*Vector2[] vertices = new Vector2[4];
        vertices[0] = posShape;
        vertices[1] = new Vector2(posShape.x + largeur, posShape.y);
        vertices[2] = new Vector2(posShape.x + largeur, posShape.y + hauteur);
        vertices[3] = new Vector2(posShape.x, posShape.y + hauteur);*/

        CircleShape circleShape = new CircleShape();
        Vector2 posShapeCircle = new Vector2(posShape.x + largeur/2f, posShape.y + hauteur/2f);
        circleShape.setPosition(posShapeCircle);
        circleShape.setRadius(largeur/2f);


        /*PolygonShape rectangle = new PolygonShape();
        rectangle.set(vertices);*/

        // FixtureDef
        FixtureDef fixtureDef1 = new FixtureDef();
        fixtureDef1.shape = circleShape;
        fixtureDef1.isSensor = false;
        fixtureDef1.density = 10f;
        fixtureDef1.restitution = 0f;
        fixtureDef1.friction = 1f;
        //

        // Met en place la fixture sur le body
        body.setFixedRotation(true);
        body.createFixture(fixtureDef1); // Association à l’objet

        body.setUserData(new Type(TypeEntite.PICKUP, id));
    }


    @Override
    public void draw(SpriteBatch batch) {

    }

    @Override
    public void update() {
        body.setLinearVelocity(new Vector2(0.80f * body.getLinearVelocity().x,0.80f * body.getLinearVelocity().y));
        if(getTouche()){
            applyEffect();
        }
    }
}
