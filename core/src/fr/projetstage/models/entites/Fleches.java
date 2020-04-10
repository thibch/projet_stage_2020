package fr.projetstage.models.entites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.monde.salle.Orientation;

public class Fleches {

    private Body body;
    private float largeur;
    private float hauteur;

    private Orientation direction;

    public Fleches(GameWorld world, Vector2 position, float largeur, float hauteur, int id, Orientation direction){
        this.direction = direction;
        this.largeur = largeur;
        this.hauteur = hauteur;

        float largeurBody = largeur;
        float hauteurBody = hauteur;

        if(direction == Orientation.BAS || direction == Orientation.HAUT){
            largeurBody = hauteur;
            hauteurBody = largeur;
        }


        //BodyDef
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position);
        //

        //Récupération du body dans le world
        body = world.getWorld().createBody(bodyDef);


        //Création de la shape pour le héros
        Vector2 posShape = new Vector2(0, 0); //La position du shape est en fonction de la position du body
        Vector2[] vertices = new Vector2[4];
        vertices[0] = posShape;
        vertices[1] = new Vector2(posShape.x + largeurBody, posShape.y);
        vertices[2] = new Vector2(posShape.x + largeurBody, posShape.y + hauteurBody);
        vertices[3] = new Vector2(posShape.x, posShape.y + hauteurBody);

        PolygonShape rectangle = new PolygonShape();
        rectangle.set(vertices);

        //FixtureDef
        FixtureDef fixtureDef1 = new FixtureDef();
        fixtureDef1.shape = rectangle;
        fixtureDef1.isSensor = true;
        fixtureDef1.density = 0.00001f; // Densité de l’objet
        fixtureDef1.restitution = 0f; // Restitution de  l’objet
        fixtureDef1.friction = 0f; // Friction de  l’objet
        //

        //Met en place la fixture sur le body
        body.setFixedRotation(false);
        body.createFixture(fixtureDef1); // Association à l’objet
        body.setUserData(new Type(TypeEntite.DISTANCE, id));

        rectangle.dispose();
    }

    public void lancee(Vector2 direction){
        body.setLinearVelocity(direction);
    }


    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(TextureFactory.getInstance().getFleche(), body.getPosition().x, body.getPosition().y, largeur/2f, hauteur/2f, largeur, hauteur, 1, 1, 90+direction.getRotation(),0,0, TextureFactory.getInstance().getFleche().getWidth(), TextureFactory.getInstance().getFleche().getHeight(), false, false);
    }
}
