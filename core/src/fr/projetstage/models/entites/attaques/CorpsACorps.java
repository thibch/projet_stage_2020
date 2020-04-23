package fr.projetstage.models.entites.attaques;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
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
    private final float longueurEpee;
    private final float largeurEpee;
    private final int degats;

    private final float totalAngle = 1.570796f; // 90 degres clockwise
    private float startAngle;
    private float currentAngle;
    private float[] tabAngles = {-0.785398f - totalAngle, 0.785398f, 0.785398f + totalAngle, -0.785398f};

    private boolean isRunning = false;

    private Epee epee;

    /**
     * @param world le gameworld
     * @param bodyEntite l'entité qui a lancé l'attaque
     * @param longueurEpee la longueur de l'épée
     * @param largeurEpee la largeur de l'épée
     * @param degats les dégâts de l'épée
     * @param duree la durée de l'attaque
     */
    public CorpsACorps(GameWorld world, Body bodyEntite, float longueurEpee, float largeurEpee, int degats, float duree){
        this.gameWorld = world;
        this.bodyParent = bodyEntite;
        this.longueurEpee = longueurEpee;
        this.largeurEpee = largeurEpee;
        this.degats = degats;
        this.duration = duree;
    }

    /**
     * Equivalent à charge de AttaqueDistance, prévient la classe que le joueur est en train d'attaque
     */
    public void slash(){
        float currentTime = Gdx.graphics.getDeltaTime();
        currentAngle -= ((totalAngle)*((currentTime/duration)));
        epee.setAngle(currentAngle);
        if(currentAngle <= startAngle-totalAngle){
            isRunning = false;
            epee.destroyBody(); //On détruit le body de l'épée car elle a finie son attaque
            epee = null;
        }
    }

    /**
     * Si l'attaque est toujours en cours
     * @return vrai si l'attaque est toujours en cours
     */
    public boolean isRunning(){
        return isRunning;
    }

    @Override
    public void attaque(Body bodyParent, Orientation direction) {

        epee = new Epee(gameWorld, bodyParent, longueurEpee, largeurEpee, direction);

        animation = new Animation(TextureFactory.getInstance().getAttaqueSpriteSheet(),3, duration+0.2f);
        isRunning = true;
        startAngle = tabAngles[direction.getIndice()];
        currentAngle = startAngle;
    }

    @Override
    public void draw(SpriteBatch batch, float x, float y) {
        animation.update();
        epee.draw(batch, x, y);
        // batch.draw(animation.getFrame(false,false),getPosition().x,getPosition().y,1,1);
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
    public Vector2 getPosition() {
        return bodyParent.getPosition();
    }
}
