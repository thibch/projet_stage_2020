package fr.projetstage.models.monde.salle.solEtMurs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Animation;
import fr.projetstage.models.Entite;
import fr.projetstage.models.entites.EntiteMouvante;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.entites.ennemis.Ennemi;
import fr.projetstage.models.monde.GameWorld;

import java.util.Map;

public class Piege extends Ennemi {

    private final Animation animation = new Animation(TextureFactory.getInstance().getPiegeSpriteSheet(),10,2);

    public Piege(GameWorld world, Vector2 position, Type type) {
        super(world, type);

        setPointDeVie(0);
        setPointdeVieMax(0);
        setDegats(1);
        setKnockback(0);

        // BodyDef
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position);
        //

        // Récupération du body dans le world
        body = world.getWorld().createBody(bodyDef);

        // Création de la shape pour le piege
        Vector2 posShape = new Vector2(0,0); // La position du shape est en fonction de la position du body
        Vector2[] vertices = new Vector2[4];
        vertices[0] = posShape;
        vertices[1] = new Vector2(posShape.x + 1f, posShape.y);
        vertices[2] = new Vector2(posShape.x + 1f, posShape.y + 1f);
        vertices[3] = new Vector2(posShape.x, posShape.y + 1f);

        PolygonShape rectangle = new PolygonShape();
        rectangle.set(vertices);

        // FixtureDef
        FixtureDef fixtureDef1 = new FixtureDef();
        fixtureDef1.shape = rectangle;
        fixtureDef1.isSensor = true;
        fixtureDef1.density = 1f;
        fixtureDef1.restitution = 0f;
        fixtureDef1.friction = 0f;
        //

        // Met en place la fixture sur le body
        body.setFixedRotation(true);
        body.createFixture(fixtureDef1); // Association à l’objet

        body.setUserData(type);
        body.setBullet(true);

        rectangle.dispose();
    }

    public void draw(SpriteBatch batch) {
        batch.draw(animation.getFrame(false, false), body.getPosition().x, body.getPosition().y, 1, 1);
        animation.update();
    }

    public void update(){
        if(animation.getCurrentFrameCount() >= 7){
            for(Map.Entry<EntiteMouvante, Boolean> target : targets.entrySet()){
                if(!target.getValue()){
                    target.getKey().setTouche(this);
                    target.setValue(true);
                }
            }
        }
        else{
            for(Map.Entry<EntiteMouvante, Boolean> target : targets.entrySet()){
                if(target.getValue()){
                    target.setValue(false);
                }
            }
        }
    }
}
