package fr.projetstage.models.monde.salle.solEtMurs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Entite;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.monde.salle.Orientation;

public class Mur implements Entite{

    private Orientation orientation;
    private TypeMur numMur;

    private Body body;

    public Mur(GameWorld world, Vector2 position, Orientation orientationMur, TypeMur wallNumberTexture){
        this.orientation = orientationMur;
        numMur = wallNumberTexture;

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
        vertices[1] = new Vector2(posShape.x + 1f, posShape.y);
        vertices[2] = new Vector2(posShape.x + 1f, posShape.y + 1f);
        vertices[3] = new Vector2(posShape.x, posShape.y + 1f);
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

    public void draw(SpriteBatch batch){
        batch.draw(numMur.getTexture(), body.getPosition().x, body.getPosition().y, 1f/2f, 1f/2f, 1, 1, 1, 1, orientation.getRotation(),0,0, numMur.getTexture().getWidth(), numMur.getTexture().getHeight(), false, false);
    }

    public TypeMur getNumMur() {
        return numMur;
    }

    public Vector2 getPos() {
        return body.getPosition();
    }

    public Orientation getOrientation() {
        return orientation;
    }
}
