package fr.projetstage.models.monde.salle.solEtMurs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Animation;
import fr.projetstage.models.entites.EntiteMouvante;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.entites.ennemis.Ennemi;
import fr.projetstage.models.monde.GameWorld;

import java.util.Map;

public class Piege extends Ennemi {

    private final Animation animation = new Animation(TextureFactory.getInstance().getPiegeSpriteSheet(),10,2);

    public Piege(GameWorld world, Vector2 position, Type type) {
        super(world, position, type);

        setPointDeVie(0);
        setPointdeVieMax(0);
        setDegats(1);
        setKnockback(0);

        largeur = 1f;
        hauteur = 1f;
    }

    @Override
    public void generateBody(){// BodyDef
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(position);
        //

        // Récupération du body dans le world
        body = world.getWorld().createBody(bodyDef);

        // Création de la shape pour le slime
        Vector2 posShape = new Vector2(0f / 16f, 0f / 16f); // La position du shape est en fonction de la position du body
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
        fixtureDef1.isSensor = true;
        fixtureDef1.filter.groupIndex = (short)-2;

        // Met en place la fixture sur le body
        body.setFixedRotation(true);
        body.createFixture(fixtureDef1); // Association à l’objet

        body.setUserData(type);

        rectangle.dispose();
    }


    public void draw(SpriteBatch batch, float x, float y) {
        batch.draw(animation.getFrame(false, false), x + getPosition().x, y + getPosition().y, 1, 1);
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
