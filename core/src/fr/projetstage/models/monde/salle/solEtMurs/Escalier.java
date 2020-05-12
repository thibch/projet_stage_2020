package fr.projetstage.models.monde.salle.solEtMurs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Orientation;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.entites.TypeEntite;
import fr.projetstage.models.monde.GameWorld;

public class Escalier extends NonDestructible{

    /**
     * Methode permettant d'instancier un escalier qui permet de changer d'étage dans le monde.
     * @param world le monde dans lequel se trouve l'escalier
     * @param position la position de l'escalier dans le monde
     */
    public Escalier(GameWorld world, Vector2 position) {
        super(world, position, 1f, 1f);
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
        Vector2 posShape = new Vector2(); // La position du shape est en fonction de la position du body
        Vector2[] vertices = new Vector2[4];
        vertices[0] = posShape;
        vertices[1] = new Vector2(posShape.x + tailleX, posShape.y);
        vertices[2] = new Vector2(posShape.x + tailleX, posShape.y + tailleY);
        vertices[3] = new Vector2(posShape.x, posShape.y + tailleY);

        PolygonShape rectangle = new PolygonShape();
        rectangle.set(vertices);

        // FixtureDef
        FixtureDef fixtureDef1 = new FixtureDef();
        fixtureDef1.shape = rectangle;
        fixtureDef1.density = 1f;
        fixtureDef1.restitution = 0f;
        fixtureDef1.friction = 0f;
        fixtureDef1.isSensor = true;

        // Met en place la fixture sur le body
        body.setFixedRotation(true);
        body.createFixture(fixtureDef1); // Association à l’objet

        body.setUserData(new Type(TypeEntite.PORTE, Orientation.NO_ORIENTATION.getIndice()));

        rectangle.dispose();
    }


    public void draw(SpriteBatch batch, float x, float y) {
        batch.draw(TextureFactory.getInstance().getEscalier(), x + getPosition().x, y + getPosition().y, tailleX, tailleY);
    }
}
