package fr.projetstage.models.entites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Animation;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.monde.salle.Orientation;

public class CorpsACorps extends Attaque{

    private Animation animation;
    private Body body;

    private float duration;

    public CorpsACorps(GameWorld world, Vector2 position, float largeur, float hauteur){

        //BodyDef
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position);
        //

        //Récupération du body dans le world
        body = world.getWorld().createBody(bodyDef);


        //Création de la shape pour le héros
        Vector2 posShape = new Vector2(0, 0); //La position du shape est en fonction de la position du body
        Vector2[] vertices = new Vector2[4];
        vertices[0] = posShape;
        vertices[1] = new Vector2(posShape.x + largeur, posShape.y);
        vertices[2] = new Vector2(posShape.x + largeur, posShape.y + hauteur);
        vertices[3] = new Vector2(posShape.x, posShape.y + hauteur);

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
        body.setFixedRotation(false);
        body.createFixture(fixtureDef1); // Association à l’objet
        body.setUserData(TypeAttaque.CORPS_A_CORPS);

        rectangle.dispose();

        duration = 0.5f;

        animation = new Animation(TextureFactory.getInstance().getAttaqueSpriteSheet(),3, duration+0.1f);
    }

    public Body getBody() {
        return body;
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

    public void drawAnimation(SpriteBatch batch, boolean flipX, boolean rotateY) {
        //Si on est proche de l'arret (0 déplacement du joueur)
        //Alors on met à jour l'animation et on l'affiche
        animation.update();
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
    }


    @Override
    public void attaque(Vector2 positionJoueur, Orientation direction) {
        //Make the body spawn at orientation dans rotate
    }

    @Override
    public Fleches attaqueDistance(Vector2 positionJoueur, Orientation direction) {
        return null;
    }
}
