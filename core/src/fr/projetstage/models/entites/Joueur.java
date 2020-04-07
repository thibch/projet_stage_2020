package fr.projetstage.models.entites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.entites.objets.Objet;
import fr.projetstage.models.monde.GameWorld;

import java.util.ArrayList;

public class Joueur extends EntiteMouvante {

    private ArrayList<Objet> inventaire;

    private static float hauteur;
    private static float largeur;

    private Body body;

    public Joueur(Vector2 position, GameWorld world){
        hauteur = 1;
        largeur = 1;

        //BodyDef
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position);
        //

        //Récupération du body dans le world
        body = world.getWorld().createBody(bodyDef);

        //TODO: faire en fonction de l'image

        //Création de la shape pour le héros
        Vector2 posShape = new Vector2(5f/16f, 2f/16f); //La position du shape est en fonction de la position du body
        Vector2[] vertices = new Vector2[4];
        vertices[0] = posShape;
        vertices[1] = new Vector2(posShape.x + (8f/16f), posShape.y);
        vertices[2] = new Vector2(posShape.x + (8f/16f), posShape.y + (6f/16f));
        vertices[3] = new Vector2(posShape.x, posShape.y + (6f/16f));

        PolygonShape rectangle = new PolygonShape();
        rectangle.set(vertices);

        //FixtureDef
        FixtureDef fixtureDef1 = new FixtureDef();
        fixtureDef1.shape = rectangle;
        fixtureDef1.density = 10f; // Densité de l’objet
        fixtureDef1.restitution = 1f; // Restitution de  l’objet
        fixtureDef1.friction = 1; // Friction de  l’objet
        //

        //Met en place la fixture sur le body
        body.createFixture(fixtureDef1); // Association à l’objet

        rectangle.dispose();
    }

    public float getX(){
        return body.getPosition().x;
    }
    public float getY(){
        return body.getPosition().y;
    }

    public void applyForce(Vector2 force){
        body.applyForceToCenter(force, true);
    }

    public void draw(SpriteBatch listeAffImg) {
        listeAffImg.draw(TextureFactory.getInstance().getJoueur(), getX(), getY(), hauteur, largeur);
    }
}
