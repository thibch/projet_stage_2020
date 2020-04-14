package fr.projetstage.models.entites.attaques;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.entites.TypeEntite;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.Orientation;

public class Fleche extends Projectile{

    private Body body;
    private float largeur;
    private float hauteur;
    private float largeurBody;
    private float hauteurBody;

    private Orientation direction;
    private boolean estLancee;

    /**
     * Constructeur d'une flèche
     * @param world le gameWorld
     * @param position la position de la nouvelle flèche
     * @param largeur la largeur de la flèche
     * @param hauteur la hauteur de la flèche
     * @param id l'id de la flèche (Utile pour déterminer quelle flèche correspond dans une liste)
     * @param direction la direction de la flèche
     */
    public Fleche(GameWorld world, Vector2 position, float largeur, float hauteur, Orientation direction){
        this.direction = direction;
        this.largeur = largeur;
        this.hauteur = hauteur;

        largeurBody = largeur;
        hauteurBody = hauteur;

        estLancee = false;

        if(direction == Orientation.BAS || direction == Orientation.HAUT){
            largeurBody = hauteur;
            hauteurBody = largeur;
        }

        // BodyDef
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position);
        //

        // Récupération du body dans le world
        body = world.getWorld().createBody(bodyDef);


        // Création de la shape pour la flèche
        Vector2 posShape = new Vector2((direction == Orientation.BAS || direction == Orientation.HAUT? 6f/16f : 2f/16f), (direction == Orientation.GAUCHE || direction == Orientation.DROITE? 5f/16f : 2f/16f));
        Vector2[] vertices = new Vector2[4];
        vertices[0] = posShape;
        vertices[1] = new Vector2(posShape.x + largeurBody, posShape.y);
        vertices[2] = new Vector2(posShape.x + largeurBody, posShape.y + hauteurBody);
        vertices[3] = new Vector2(posShape.x, posShape.y + hauteurBody);

        PolygonShape rectangle = new PolygonShape();
        rectangle.set(vertices);

        // FixtureDef
        FixtureDef fixtureDef1 = new FixtureDef();
        fixtureDef1.shape = rectangle;
        fixtureDef1.isSensor = true;
        fixtureDef1.density = 0.00001f;
        fixtureDef1.restitution = 0f;
        fixtureDef1.friction = 0f;
        //

        // Met en place la fixture sur le body
        body.setFixedRotation(false);
        body.createFixture(fixtureDef1); // Association à l’objet

        rectangle.dispose();
    }

    /**
     * On lance la flèche
     * @param direction direction voulu
     */
    public void lancee(Vector2 direction, int id){
        body.setLinearVelocity(direction);
        body.setUserData(new Type(TypeEntite.DISTANCE, id));
        estLancee = true;
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        //TODO: a changer pour faire en fonction de l'angle
       /*switch (direction){
            case BAS:
                spriteBatch.draw(TextureFactory.getInstance().getFleche(), body.getPosition().x, body.getPosition().y + hauteurBody, 0.5f, 0.5f, 1, 1,
                        1, 1, 90+direction.getRotation(),0,0, TextureFactory.getInstance().getFleche().getWidth(), TextureFactory.getInstance().getFleche().getHeight(), false, false);
                break;
            case HAUT:
                spriteBatch.draw(TextureFactory.getInstance().getFleche(), body.getPosition().x + largeurBody, body.getPosition().y, 0.5f, 0.5f, 1, 1,
                        1, 1, 90+direction.getRotation(),0,0, TextureFactory.getInstance().getFleche().getWidth(), TextureFactory.getInstance().getFleche().getHeight(), false, false);
                break;
            case GAUCHE:
                spriteBatch.draw(TextureFactory.getInstance().getFleche(), body.getPosition().x + largeurBody, body.getPosition().y + hauteurBody, 0.5f, 0.5f, 1, 1,
                        1, 1, 90+direction.getRotation(),0,0, TextureFactory.getInstance().getFleche().getWidth(), TextureFactory.getInstance().getFleche().getHeight(), false, false);
                break;
            case DROITE:
                spriteBatch.draw(TextureFactory.getInstance().getFleche(), body.getPosition().x, body.getPosition().y, 0.5f, 0.5f, 1, 1,
                        1, 1, 90+direction.getRotation(),0,0, TextureFactory.getInstance().getFleche().getWidth(), TextureFactory.getInstance().getFleche().getHeight(), false, false);
                break;
        }*/
        spriteBatch.draw(TextureFactory.getInstance().getFleche(), body.getPosition().x, body.getPosition().y, 1/2f, 1/2f, 1, 1,
                1, 1, 90 + direction.getRotation(),0,0, TextureFactory.getInstance().getFleche().getWidth(), TextureFactory.getInstance().getFleche().getHeight(), false, direction == Orientation.BAS || direction == Orientation.GAUCHE);
   }

    public boolean estLancee() {
        return estLancee;
    }
}
