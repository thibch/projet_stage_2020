package fr.projetstage.models.entites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Animation;
import fr.projetstage.models.entites.objets.Objet;
import fr.projetstage.models.monde.GameWorld;

import java.util.ArrayList;

public class Joueur extends EntiteMouvante {

    private ArrayList<Objet> inventaire;

    private static float hauteur;
    private static float largeur;

    private Body body;
    private Animation idleAnimation;
    private Animation runningAnimation;
    private boolean facingLeft;


    /**
     * Constructeur du joueur,
     * Met en place son body (Qui est un rectangle de 8 par 6 pixel)
     * @param position position au départ
     * @param world le monde où il se trouve
     */
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
        fixtureDef1.density = 1f; // Densité de l’objet
        fixtureDef1.restitution = 0f; // Restitution de  l’objet
        fixtureDef1.friction = 0f; // Friction de  l’objet
        //

        //Met en place la fixture sur le body
        body.setFixedRotation(true);
        body.createFixture(fixtureDef1); // Association à l’objet

        rectangle.dispose();

        //creer les animations
        facingLeft = false;
        idleAnimation = new Animation(TextureFactory.getInstance().getJoueurIdleSpriteSheet(),6,0.8f);
        runningAnimation = new Animation(TextureFactory.getInstance().getJoueurRunningSpriteSheet(),6,0.8f);
    }

    /**
     * Getter sur la position x
     * @return la position x du Joueur
     */
    public float getX(){
        return body.getPosition().x;
    }

    /**
     * Getter sur la position y
     * @return la position y du Joueur
     */
    public float getY(){
        return body.getPosition().y;
    }

    /**
     * Déplace le Joueur en direction de la force
     * Le déplacement n'est pas linéaire
     * @param deplacement vecteur de déplacement du joueur
     */
    public void move(Vector2 deplacement){
        body.setLinearVelocity(new Vector2(deplacement.x + 0.8f * body.getLinearVelocity().x,deplacement.y + 0.8f * body.getLinearVelocity().y));
    }


    @Override
    public void draw(SpriteBatch listeAffImg) {

        //Si on est proche de l'arret (0 déplacement du joueur)
        //Alors on met à jour l'animation et on l'affiche
        if(body.getLinearVelocity().isZero(0.1f)){
            idleAnimation.update();
            listeAffImg.draw(idleAnimation.getFrame(facingLeft), getX(), getY(), hauteur, largeur);
        }//Sinon on met à jour l'animation du run (en faisant attention si on est sur la gauche ou droite)
        else{
            if(body.getLinearVelocity().x != 0f){
                facingLeft = (body.getLinearVelocity().x < 0f);
            }
            runningAnimation.update();
            listeAffImg.draw(runningAnimation.getFrame(facingLeft), getX(), getY(), hauteur, largeur);
        }
    }
}
