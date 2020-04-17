package fr.projetstage.models.entites.objets.objetsPiedestal;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Animation;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.entites.TypeEntite;
import fr.projetstage.models.entites.objets.ObjetsTousTypes;
import fr.projetstage.models.monde.GameWorld;

public class Piedestal extends ObjetsTousTypes {

    private ObjetSurPiedestal objet;
    private final Body body;
    private final GameWorld world;

    private boolean open;

    private final Animation animation;

    public Piedestal(GameWorld world, Vector2 position, ObjetSurPiedestal objet, int id){
        this.world = world;
        this.objet = objet;
        open = false;

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


    public void update(){
        animation.update();
        //move objet
        if(getTouche()){
            applyEffect();
        }
    }

    @Override
    public void applyEffect() {
        objet.applyEffect();
        open = true;
    }

    @Override
    public void draw(SpriteBatch batch) {
        if(!open){
            batch.draw(animation.getFrame(false, false), body.getPosition().x, body.getPosition().y, 1,1);
        }else{
            batch.draw(TextureFactory.getInstance().getCoffreOpen(), body.getPosition().x, body.getPosition().y, 1,1);
            batch.draw(objet.getTexture(), body.getPosition().x, body.getPosition().y, 1, 1);
        }
    }
}
