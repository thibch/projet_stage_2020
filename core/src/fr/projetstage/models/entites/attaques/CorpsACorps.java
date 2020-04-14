package fr.projetstage.models.entites.attaques;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Animation;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.Orientation;

public class CorpsACorps extends Attaque{

    private final GameWorld gameWorld;
    private Animation animation;
    private final Body bodyParent;

    private final float duration;
    private final float longueur;
    private final float largeur;
    private final int degats;

    private final float totalAngle = 1.570796f; // 90 degres clockwise
    private float startAngle;
    private float currentAngle;
    private float[] tabAngles = {-0.785398f - totalAngle, 0.785398f, 0.785398f + totalAngle, -0.785398f};

    private boolean isRunning = false;

    private Epee epee;

    public CorpsACorps(GameWorld world, Body bodyEntite, float longueur, float largeur, int degats, float vitesse){
        this.gameWorld = world;
        this.bodyParent = bodyEntite;
        this.longueur = longueur;
        this.largeur = largeur;
        this.degats = degats;
        this.duration = vitesse;
    }

    public void slash(){
        float currentTime = Gdx.graphics.getDeltaTime();
        currentAngle -= ((totalAngle)*((currentTime/duration)));
        epee.setAngle(currentAngle);
        if(currentAngle <= startAngle-totalAngle){
            isRunning = false;
            epee.stop();
            epee = null;
        }
        // body.setTransform(body.getPosition(),-0.785398f); // pos finale
    }

    public boolean isRunning(){
        return isRunning;
    }

    public float getDuration() {
        return duration;
    }

    public void draw(SpriteBatch batch) {
        animation.update();
        epee.draw(batch);
        // batch.draw(animation.getFrame(false,false),body.getPosition().x,body.getPosition().y,1,1);
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
    public void attaque(Body bodyParent, Orientation direction) {

        epee = new Epee(gameWorld, bodyParent, longueur, largeur, direction);

        animation = new Animation(TextureFactory.getInstance().getAttaqueSpriteSheet(),3, duration+0.2f);
        isRunning = true;
        startAngle = tabAngles[direction.getIndice()];
        currentAngle = startAngle;
    }
}
