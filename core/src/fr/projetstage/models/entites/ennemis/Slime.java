package fr.projetstage.models.entites.ennemis;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Animation;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.Orientation;

public class Slime extends Ennemi {


    /**
     * Constructeur d'un Slime
     * @param world Le monde dans lequel se trouve le slime
     * @param position Sa position dans la salle
     * @param type Le type de monstre
     */
    public Slime(GameWorld world, Vector2 position, Type type) {
        super(world, type);
        // Stats
        setPointDeVie(3);
        setPointdeVieMax(3);
        setDegats(1);

        float hauteur = (12f / 16f);
        float largeur = (16f / 16f);

        // BodyDef
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position);
        //

        // Récupération du body dans le world
        body = world.getWorld().createBody(bodyDef);

        // Création de la shape pour le slime
        Vector2 posShape = new Vector2(0f / 16f, 1f / 16f); // La position du shape est en fonction de la position du body
        Vector2[] vertices = new Vector2[4];
        vertices[0] = posShape;
        vertices[1] = new Vector2(posShape.x + largeur, posShape.y);
        vertices[2] = new Vector2(posShape.x + largeur, posShape.y + hauteur);
        vertices[3] = new Vector2(posShape.x, posShape.y + hauteur);

        PolygonShape rectangle = new PolygonShape();
        rectangle.set(vertices);

        // FixtureDef
        FixtureDef fixtureDef1 = new FixtureDef();
        fixtureDef1.shape = rectangle;
        fixtureDef1.density = 1f;
        fixtureDef1.restitution = 0f;
        fixtureDef1.friction = 0f;
        //

        // Met en place la fixture sur le body
        body.setFixedRotation(true);
        body.createFixture(fixtureDef1); // Association à l’objet

        body.setUserData(type);

        rectangle.dispose();

        idleAnimation = new Animation(TextureFactory.getInstance().getSlimeIdleSpriteSheet(),6,0.8f);
        runningAnimation = new Animation(TextureFactory.getInstance().getSlimeRunSpriteSheet(),6,0.8f);

        this.comportement = new Comportement(body, 1f);
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

    @Override
    public void draw(SpriteBatch batch) {
        body.setLinearVelocity(new Vector2(0.8f * body.getLinearVelocity().x,0.8f * body.getLinearVelocity().y)); //TODO: a changer plus tard, juste pour pas qu'il glode à l'infini
        if(body.getLinearVelocity().isZero(0.1f)){
            idleAnimation.update();
            batch.draw(idleAnimation.getFrame(world.getJoueur().getX() < getX(), false), getX(), getY(), 1, 1);
        }// Sinon on met à jour l'animation du run (en faisant attention si on est sur la gauche ou droite)
        else{
            runningAnimation.update();
            batch.draw(runningAnimation.getFrame(world.getJoueur().getX() < getX(), false), getX(), getY(), 1, 1);
        }
    }

    @Override
    public void update(Orientation direction) {
        comportement.update();
        //TODO : mettre a jour l'orientation du slime selon la position du joueur par rapport à lui.
    }
}
