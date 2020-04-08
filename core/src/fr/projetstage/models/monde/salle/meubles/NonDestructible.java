package fr.projetstage.models.monde.salle.meubles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.monde.salle.Obstacle;

public abstract class NonDestructible extends Obstacle {


    protected float taille;

    public NonDestructible(GameWorld world, Vector2 position, float taille){

        this.taille = taille;

        //BodyDef
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(position);
        //

        //Récupération du body dans le world
        body = world.getWorld().createBody(bodyDef);

        //Création de la shape pour le héros
        Vector2 posShape = new Vector2(0, 0); //La position du shape est en fonction de la position du body
        Vector2[] vertices = new Vector2[5];
        vertices[0] = posShape;
        vertices[1] = new Vector2(posShape.x + taille, posShape.y);
        vertices[2] = new Vector2(posShape.x + taille, posShape.y + taille);
        vertices[3] = new Vector2(posShape.x, posShape.y + taille);
        vertices[4] = posShape;

        ChainShape rectangle = new ChainShape();
        rectangle.createChain(vertices);

        //FixtureDef
        FixtureDef fixtureDef1 = new FixtureDef();
        fixtureDef1.shape = rectangle;
        fixtureDef1.density = 1f; // Densité de l’objet
        fixtureDef1.restitution = 0f; // Restitution de  l’objet
        fixtureDef1.friction = 0f; // Friction de  l’objet
        //

        //Met en place la fixture sur le body
        body.createFixture(fixtureDef1); // Association à l’objet

        rectangle.dispose();
    }


    @Override
    public boolean estNonDestructible() {
        return true;
    }
}
