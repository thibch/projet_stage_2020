package fr.projetstage.models.monde.salle;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.entites.Entite;
import fr.projetstage.models.monde.GameWorld;

public class Mur implements Entite{

    private Orientation orientation;
    private int numMur;

    private Body body;

    public Mur(GameWorld world, Vector2 position, Orientation orientationMur, int wallNumberTexture){
        this.orientation = orientationMur;
        numMur = wallNumberTexture;

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
        vertices[1] = new Vector2(posShape.x + 1f, posShape.y);
        vertices[2] = new Vector2(posShape.x + 1f, posShape.y + 1f);
        vertices[3] = new Vector2(posShape.x, posShape.y + 1f);
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

    //TODO: A optimiser
    public void draw(SpriteBatch batch){
        switch (numMur){
            case 1:
                batch.draw(TextureFactory.getInstance().getMur1(), body.getPosition().x, body.getPosition().y, 1f/2f, 1f/2f, 1, 1, 1, 1, orientation.getRotation(),0,0,TextureFactory.getInstance().getMur1().getWidth(), TextureFactory.getInstance().getMur1().getHeight(), false, false);
                break;
            case 2:
                batch.draw(TextureFactory.getInstance().getMur2(), body.getPosition().x, body.getPosition().y, 1f/2f, 1f/2f, 1, 1, 1, 1, orientation.getRotation(),0,0,TextureFactory.getInstance().getMur1().getWidth(), TextureFactory.getInstance().getMur1().getHeight(), false, false);
                break;
            case 3:
                batch.draw(TextureFactory.getInstance().getMur3(), body.getPosition().x, body.getPosition().y, 1f/2f, 1f/2f, 1, 1, 1, 1, orientation.getRotation(),0,0,TextureFactory.getInstance().getMur1().getWidth(), TextureFactory.getInstance().getMur1().getHeight(), false, false);
                break;
            default:
                batch.draw(TextureFactory.getInstance().getMur4(), body.getPosition().x, body.getPosition().y, 1f/2f, 1f/2f, 1, 1, 1, 1, orientation.getRotation(),0,0,TextureFactory.getInstance().getMur1().getWidth(), TextureFactory.getInstance().getMur1().getHeight(), false, false);
                break;
        }
    }

    public int getNumMur() {
        return numMur;
    }

    public Vector2 getPos() {
        return body.getPosition();
    }

    public Orientation getOrientation() {
        return orientation;
    }
}
