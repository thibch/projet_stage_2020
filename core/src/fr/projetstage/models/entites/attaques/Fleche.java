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

    private final float largeur;
    private final float hauteur;

    private Orientation direction;
    private boolean estLancee;

    /**
     * Constructeur d'une flèche
     * @param world le gameWorld
     * @param position la position de la nouvelle flèche
     * @param largeur la largeur de la flèche
     * @param hauteur la hauteur de la flèche
     * @param direction la direction de la flèche
     */
    public Fleche(GameWorld world, Vector2 position, float largeur, float hauteur, Orientation direction){
        super(world, position);
        this.direction = direction;
        this.largeur = largeur;
        this.hauteur = hauteur;

        estLancee = false;

        // BodyDef
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position);
        //

        // Récupération du body dans le world
        body = world.getWorld().createBody(bodyDef);
        body.setFixedRotation(false);
    }

    /**
     * On lance la flèche
     * @param direction direction voulu
     * @param id l'id de la flèche
     */
    @Override
    public void launch(Orientation direction, float speed, int id){
        super.launch(direction, speed, id);
        generateBody();
        estLancee = true;
    }

    @Override
    public void draw(SpriteBatch spriteBatch, float x, float y) {
        spriteBatch.draw(TextureFactory.getInstance().getFleche(), x + body.getPosition().x, y + body.getPosition().y, 1/2f, 1/2f, 1, 1,
                1, 1, 90 + direction.getRotation(),0,0, TextureFactory.getInstance().getFleche().getWidth(), TextureFactory.getInstance().getFleche().getHeight(), false, direction == Orientation.BAS || direction == Orientation.GAUCHE);
   }

    /**
     * Getter si la flèche est lancée dans le monde ou non
     * @return vrai si la flèche est lancée
     */
    @Override
    public boolean isLaunched() {
        return estLancee;
    }

    /**
     * Met à jour la position de la flèche
     * @param position nouvelle position de la flèche
     * @param direction direction à laquelle elle se trouve
     */
    public void update(Vector2 position, Orientation direction) {
        body.setTransform(position, 0);
        this.direction = direction;
    }

    @Override
    public void generateBody() {
        float largeurBody;
        float hauteurBody;
        if(direction == Orientation.BAS || direction == Orientation.HAUT){
            largeurBody = hauteur;
            hauteurBody = largeur;
        }else{
            largeurBody = largeur;
            hauteurBody = hauteur;
        }

        // Création de la shape pour la flèche
        Vector2 posShape = new Vector2((this.direction == Orientation.BAS || this.direction == Orientation.HAUT? 6f/16f : 2f/16f),
                (this.direction == Orientation.GAUCHE || this.direction == Orientation.DROITE? 5f/16f : 2f/16f));

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
        body.createFixture(fixtureDef1); // Association à l’objet

        rectangle.dispose();
    }
}
