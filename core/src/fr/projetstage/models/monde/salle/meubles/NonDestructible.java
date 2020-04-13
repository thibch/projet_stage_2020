package fr.projetstage.models.monde.salle.meubles;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import fr.projetstage.models.monde.GameWorld;

public abstract class NonDestructible extends Obstacle {


    protected float tailleX;
    protected float tailleY;

    public NonDestructible(GameWorld world, Vector2 position, float tailleX, float tailleY){

        this.tailleX = tailleX;
        this.tailleY = tailleY;

        // BodyDef
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(position);
        //

        // Récupération du body dans le world
        body = world.getWorld().createBody(bodyDef);

        // Création de la shape pour le héros
        Vector2 posShape = new Vector2(0, 0); // La position du shape est en fonction de la position du body
        Vector2[] vertices = new Vector2[5];
        vertices[0] = posShape;
        vertices[1] = new Vector2(posShape.x + tailleX, posShape.y);
        vertices[2] = new Vector2(posShape.x + tailleX, posShape.y + tailleY);
        vertices[3] = new Vector2(posShape.x, posShape.y + tailleY);
        vertices[4] = posShape;

        ChainShape rectangle = new ChainShape();
        rectangle.createChain(vertices);

        // FixtureDef
        FixtureDef fixtureDef1 = new FixtureDef();
        fixtureDef1.shape = rectangle;
        fixtureDef1.density = 1f;
        fixtureDef1.restitution = 0f;
        fixtureDef1.friction = 0f;
        //

        // Met en place la fixture sur le body
        body.createFixture(fixtureDef1); // Association à l’objet

        rectangle.dispose();
    }


    @Override
    public boolean estNonDestructible() {
        return true;
    }
}
