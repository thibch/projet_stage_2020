package fr.projetstage.models.entites.objets.objetsAuSol;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
        fixtureDef1.filter.groupIndex = (short)-2;

        // Met en place la fixture sur le body
        body.setFixedRotation(true);
        body.createFixture(fixtureDef1); // Association à l’objet

        body.setUserData(new Type(TypeEntite.PICKUP, id));

        circleShape.dispose();
    }


    @Override
    public void draw(SpriteBatch batch) {

    }

    @Override
    public void update() {
        if(Math.abs(body.getWorldCenter().x - world.getJoueur().getX()) >= 0.8f || Math.abs(body.getWorldCenter().y - world.getJoueur().getY()) >= 0.8f){
            body.setLinearVelocity(new Vector2(0.80f * body.getLinearVelocity().x,0.80f * body.getLinearVelocity().y));
        }
        if(getTouche()){
            applyEffect();
        }
    }
}
