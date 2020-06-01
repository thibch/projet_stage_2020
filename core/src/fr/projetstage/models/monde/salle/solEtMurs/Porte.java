package fr.projetstage.models.monde.salle.solEtMurs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Orientation;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.entites.TypeEntite;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.monde.salle.solEtMurs.meubles.Obstacle;

public class Porte extends Obstacle {
    private float tailleX;
    private float tailleY;
    private Orientation direction;

    private BodyDef bodyDefBloquant;
    private Body bodyBloquant;

    private BodyDef bodyDefLeft;
    private Body bodyLeft;
    private BodyDef bodyDefRight;
    private Body bodyRight;


    private float tailleXCotes;
    private float tailleYCotes;


    private boolean estOuverte;



    public Porte(GameWorld world, Vector2 position, float tailleX, float tailleY, Orientation direction) {
        super(world, position);

        this.direction = direction;

        this.tailleX = tailleX;
        this.tailleY = tailleY;

        bodyDefBloquant = new BodyDef();
        bodyDefBloquant.type = BodyDef.BodyType.StaticBody;
        bodyDefBloquant.position.set(position);

        Vector2 positionRight = new Vector2();
        Vector2 positionLeft = new Vector2();

        if(direction == Orientation.HAUT || direction == Orientation.BAS){
            tailleXCotes = 6f/16f;
            tailleYCotes = 1f;
            if(direction == Orientation.BAS){
                positionRight.y = 1f;
                positionLeft.y = 1f;
            }
        }else {
            tailleXCotes = 1f;
            tailleYCotes = 6f / 16f;
            if (direction == Orientation.DROITE) {
                positionRight.y = 2f - 6f/16f;
                positionRight.x = -1f;
            }else{
                positionLeft.x = 1f;
                positionLeft.y = 2f - 6f/16f;
            }
        }


        bodyDefLeft = new BodyDef();
        bodyDefLeft.type = BodyDef.BodyType.StaticBody;
        bodyDefLeft.position.set(new Vector2(position.x + positionLeft.x, position.y + positionLeft.y));

        bodyDefRight = new BodyDef();
        bodyDefRight.type = BodyDef.BodyType.StaticBody;
        bodyDefRight.position.set(new Vector2(position.x + tailleX - tailleXCotes + positionRight.x, position.y + positionRight.y));

        estOuverte = false;
    }

    public void ouverturePorte(){
        if(!estOuverte){
            world.getWorld().destroyBody(bodyBloquant);
            estOuverte = true;
        }
    }


    @Override
    public void generateBody(){

        // Body qui bloque le joueur si il n'a pas finis le niveau
        Vector2[] vertices = new Vector2[5];
        ChainShape rectangle;
        if(!estOuverte){
            bodyBloquant = world.getWorld().createBody(bodyDefBloquant);

            Vector2 posShapeBloquant = new Vector2(0, 0); // La position du shape est en fonction de la position du body
            vertices[0] = posShapeBloquant;
            vertices[1] = new Vector2(posShapeBloquant.x + tailleX, posShapeBloquant.y);
            vertices[2] = new Vector2(posShapeBloquant.x + tailleX, posShapeBloquant.y + tailleY);
            vertices[3] = new Vector2(posShapeBloquant.x, posShapeBloquant.y + tailleY);
            vertices[4] = posShapeBloquant;

            rectangle = new ChainShape();
            rectangle.createChain(vertices);

            FixtureDef fixtureDefBloquant = new FixtureDef();
            fixtureDefBloquant.shape = rectangle;
            fixtureDefBloquant.density = 1f;
            fixtureDefBloquant.restitution = 0f;
            fixtureDefBloquant.friction = 0f;

            // Met en place la fixture sur le body
            bodyBloquant.createFixture(fixtureDefBloquant); // Association à l’objet

            rectangle.dispose();
        }

        bodyLeft = world.getWorld().createBody(bodyDefLeft);

        Vector2 posShapeBloquant = new Vector2(0, 0); // La position du shape est en fonction de la position du body
        vertices[0] = posShapeBloquant;
        vertices[1] = new Vector2(posShapeBloquant.x + tailleXCotes, posShapeBloquant.y);
        vertices[2] = new Vector2(posShapeBloquant.x + tailleXCotes, posShapeBloquant.y + tailleYCotes);
        vertices[3] = new Vector2(posShapeBloquant.x, posShapeBloquant.y + tailleYCotes);
        vertices[4] = posShapeBloquant;

        rectangle = new ChainShape();
        rectangle.createChain(vertices);

        FixtureDef fixtureDefBloquant = new FixtureDef();
        fixtureDefBloquant.shape = rectangle;
        fixtureDefBloquant.density = 1f;
        fixtureDefBloquant.restitution = 0f;
        fixtureDefBloquant.friction = 0f;

        bodyLeft.createFixture(fixtureDefBloquant);

        rectangle.dispose();



        bodyRight = world.getWorld().createBody(bodyDefRight);

        posShapeBloquant = new Vector2(0, 0); // La position du shape est en fonction de la position du body
        vertices[0] = posShapeBloquant;
        vertices[1] = new Vector2(posShapeBloquant.x + tailleXCotes, posShapeBloquant.y);
        vertices[2] = new Vector2(posShapeBloquant.x + tailleXCotes, posShapeBloquant.y + tailleYCotes);
        vertices[3] = new Vector2(posShapeBloquant.x, posShapeBloquant.y + tailleYCotes);
        vertices[4] = posShapeBloquant;

        rectangle = new ChainShape();
        rectangle.createChain(vertices);

        fixtureDefBloquant = new FixtureDef();
        fixtureDefBloquant.shape = rectangle;
        fixtureDefBloquant.density = 1f;
        fixtureDefBloquant.restitution = 0f;
        fixtureDefBloquant.friction = 0f;
        bodyRight.createFixture(fixtureDefBloquant);

        rectangle.dispose();



        // Body pour la transition
        body = world.getWorld().createBody(bodyDef);

        body.setUserData(new Type(TypeEntite.PORTE, direction.getIndice()));

        float tailleBodyX = tailleX;
        float tailleBodyY = tailleY/2;

        if(direction == Orientation.GAUCHE || direction == Orientation.DROITE){
            tailleBodyX = tailleX/2;
            tailleBodyY = tailleY;
        }

        Vector2 posShape = new Vector2(direction == Orientation.DROITE?tailleBodyX:0, direction == Orientation.HAUT?tailleBodyY:0); // La position du shape est en fonction de la position du body
        //Vector2[] vertices = new Vector2[5];
        vertices[0] = posShape;
        vertices[1] = new Vector2(posShape.x + tailleBodyX, posShape.y);
        vertices[2] = new Vector2(posShape.x + tailleBodyX, posShape.y + tailleBodyY);
        vertices[3] = new Vector2(posShape.x, posShape.y + tailleBodyY);
        vertices[4] = posShape;

        rectangle = new ChainShape();
        rectangle.createChain(vertices);


        fixtureDef = new FixtureDef();
        fixtureDef.shape = rectangle;
        fixtureDef.density = 1f;
        fixtureDef.restitution = 0f;
        fixtureDef.friction = 0f;

        // Met en place la fixture sur le body
        body.createFixture(fixtureDef); // Association à l’objet

        rectangle.dispose();

    }

    @Override
    public void destroyBody(){
        world.getWorld().destroyBody(body);
        world.getWorld().destroyBody(bodyLeft);
        world.getWorld().destroyBody(bodyRight);
        if(!estOuverte){
            world.getWorld().destroyBody(bodyBloquant);
        }
    }

    @Override
    public boolean estNonDestructible() {
        return false;
    }

    @Override
    public void draw(SpriteBatch batch, float x, float y) {
        if(estOuverte){
            batch.draw(TextureFactory.getInstance().getPorteOuverte(), x + getPosition().x, y + getPosition().y, tailleX/2, tailleY/2, tailleX, tailleY, 1, 1, direction.getRotation(),0,0, TextureFactory.getInstance().getPorteOuverte().getWidth(), TextureFactory.getInstance().getPorteOuverte().getHeight(), false, false);
        }else{
            batch.draw(TextureFactory.getInstance().getPorteFerme(), x + getPosition().x, y + getPosition().y, tailleX/2, tailleY/2, tailleX, tailleY, 1, 1, direction.getRotation(),0,0, TextureFactory.getInstance().getPorteOuverte().getWidth(), TextureFactory.getInstance().getPorteOuverte().getHeight(), false, false);
        }
    }

    public Orientation getOrientation() {
        return direction;
    }
}
