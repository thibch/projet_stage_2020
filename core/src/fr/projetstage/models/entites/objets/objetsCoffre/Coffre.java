package fr.projetstage.models.entites.objets.objetsCoffre;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Animation;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.entites.TypeEntite;
import fr.projetstage.models.entites.objets.ObjetsTousTypes;
import fr.projetstage.models.monde.GameWorld;

public class Coffre extends ObjetsTousTypes {

    private ObjetDansCoffre objet;
    private final Body body;
    private final GameWorld world;

    private boolean open;
    private boolean openAndTook;

    private final float timeToTake = 0.5f;
    private float openTime;

    private final Animation animation;

    /**
     * Créé un Coffre avec une hitbox
     * @param world le world
     * @param position la position du piedestal dans le monde
     * @param objet l'objet qui sera dans le piedestale
     * @param id l'id du piedestal dans la salle
     */
    public Coffre(GameWorld world, Vector2 position, ObjetDansCoffre objet, int id){
        this.world = world;
        this.objet = objet;
        open = false;
        openAndTook = false;
        openTime = 0f;

        float largeur = 5f/16f;
        float hauteur = 5f/16f;

        // BodyDef
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(position);
        //

        // Récupération du body dans le world
        body = world.getWorld().createBody(bodyDef);

        // Création de la shape pour le slime
        Vector2 posShape = new Vector2(5f/16f, 5f/16f);
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
        fixtureDef1.isSensor = false;
        fixtureDef1.density = 10f;
        fixtureDef1.restitution = 0f;
        fixtureDef1.friction = 1f;
        //

        // Met en place la fixture sur le body
        body.setFixedRotation(true);
        body.createFixture(fixtureDef1); // Association à l’objet

        body.setUserData(new Type(TypeEntite.PICKUP, id));
        animation = new Animation(TextureFactory.getInstance().getCoffreSpriteSheet(), 8, 2f);
    }

    @Override
    public void update(){
        //move objet
        if(open){
            openTime += Gdx.graphics.getDeltaTime();
        }
        super.update();
    }

    @Override
    public void applyEffect() {
        open = true;
        if(openTime >= timeToTake){
            world.getJoueur().getInventaire().add(objet);
            openAndTook = true;
        }
    }

    @Override
    public void reverseEffect() {

    }

    @Override
    public void draw(SpriteBatch batch) {
        animation.update();
        if(!open){
            batch.draw(animation.getFrame(false, false), body.getPosition().x, body.getPosition().y, 1,1);
        }else{
            batch.draw(TextureFactory.getInstance().getCoffreOpen(), body.getPosition().x, body.getPosition().y, 1,1);
            if(!openAndTook){
                batch.draw(objet.getTexture(), body.getPosition().x, body.getPosition().y, 1, 1);
            }
        }
    }
}
