package fr.projetstage.models.entites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Animation;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.monde.salle.Orientation;

public class CorpsACorps extends Attaque{

    private GameWorld gameWorld;
    private Animation animation;
    private Body bodyParent;
    private Body body;

    private float duration;
    private float totalAngle = 1.570796f; //90 degres clockwise
    private float currentAngle;
    private boolean isRunning = false;

    public CorpsACorps(GameWorld world, Body bodyEntite){
        this.gameWorld = world;
        bodyParent = bodyEntite;
    }

    public void initAttack(float longueur, float largeur, int degats,float vitesse ,Orientation orientation){

        //BodyDef
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(bodyParent.getPosition().x+(9f / 16f), bodyParent.getPosition().y+ (4f / 16f)));

        //Récupération du body dans le world
        body = gameWorld.getWorld().createBody(bodyDef);

        //Création de la shape pour l'epée
        Vector2 posShape = new Vector2(0, 0); //La position du shape est en fonction de la position du body
        Vector2[] vertices = new Vector2[4];
        vertices[0] = posShape;
        vertices[1] = new Vector2(posShape.x + longueur, posShape.y);
        vertices[2] = new Vector2(posShape.x + longueur, posShape.y+largeur);
        vertices[3] = new Vector2(posShape.x, posShape.y + largeur);

        PolygonShape rectangle = new PolygonShape();
        rectangle.set(vertices);

        //FixtureDef
        FixtureDef fixtureDef1 = new FixtureDef();
        fixtureDef1.shape = rectangle;
        fixtureDef1.isSensor = true;
        fixtureDef1.density = 0.00001f; // Densité de l’objet
        fixtureDef1.restitution = 0f; // Restitution de  l’objet
        fixtureDef1.friction = 0f; // Friction de  l’objet
        //

        //Met en place la fixture sur le body
        body.setFixedRotation(true);
        body.createFixture(fixtureDef1); // Association à l’objet
        body.setUserData(new Type(TypeEntite.CORPS_A_CORPS));

        rectangle.dispose();

        //On met en place les jointures entre les attaques au CaC et le joueur
        RevoluteJointDef rjd = new RevoluteJointDef();
        rjd.initialize(bodyParent, body, new Vector2(bodyParent.getPosition().x +(9f / 16f), bodyParent.getPosition().y + (4f / 16f)));

        gameWorld.getWorld().createJoint(rjd);

        duration = vitesse;

        animation = new Animation(TextureFactory.getInstance().getAttaqueSpriteSheet(),3, duration+0.2f);
        isRunning = true;
        currentAngle = 0.785398f;
    }

    public void slash(){
        currentAngle -= (totalAngle/duration)/80 ; //TODO: trouver un calcul plus correct
        body.setTransform(body.getPosition(),currentAngle);
        if(currentAngle <= -0.785398f){
            isRunning = false;
            gameWorld.getWorld().destroyBody(body);
        }
        //body.setTransform(body.getPosition(),-0.785398f); //pos finale
    }

    public Body getBody() {
        return body;
    }

    public boolean isRunning(){
        return isRunning;
    }

    private float getX(){
        return body.getPosition().x;
    }

    private float getY(){
        return body.getPosition().y;
    }

    public float getDuration() {
        return duration;
    }

    public void reset() {
        animation.reset();
    }

    public void drawAnimation(SpriteBatch batch) {
        animation.update();
        batch.draw(TextureFactory.getInstance().getEpee(), body.getPosition().x, body.getPosition().y, 0, 0.15f, 0.9f, 0.9f, 1, 1, (body.getAngle()*(180/3.1415926f))-30,0,0,TextureFactory.getInstance().getEpee().getWidth(), TextureFactory.getInstance().getEpee().getHeight(), false, false);
        //batch.draw(animation.getFrame(false,false),body.getPosition().x,body.getPosition().y,1,1);
        /*
        if(flipX){
            if(rotateY){ // Bas
                batch.draw(animation.getFrameFlipX(true), getX()+11f/16f + 0.5f/16f, getY()-9f/16f, 0, 0, 1, 1, 1, 1, 90);
            }else{ // Gauche
                batch.draw(animation.getFrameFlipX(true), getX()-12f/16f, getY()-2f/16f, 1, 1);
            }
        }else{
            if(rotateY){ // Haut
                batch.draw(animation.getFrameFlipX(false), getX()+11f/16f + 0.5f/16f, getY(), 0, 0, 1, 1, 1, 1, 90);
            }else{ // Droite
                batch.draw(animation.getFrameFlipX(false), getX(), getY()-2f/16f, 1, 1);
            }
        }
        */
    }


    @Override
    public void attaque(Vector2 positionJoueur, Orientation direction) {
        //Make the body spawn at orientation dans rotate
    }

    @Override
    public Fleches attaqueDistance(Vector2 positionJoueur, Orientation direction, int id) {
        return null;
    }
}
