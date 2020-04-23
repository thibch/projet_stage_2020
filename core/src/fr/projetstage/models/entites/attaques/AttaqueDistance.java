package fr.projetstage.models.entites.attaques;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Animation;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.Orientation;

public class AttaqueDistance extends Attaque {

    private final GameWorld world;

    private Vector2 position;
    private Orientation direction;
    private final Animation animation;

    private final float largeurFleche;
    private final float hauteurFleche;
    private final float speed;

    private boolean isCharging;

    private Fleche fleche;

    private int munition;

    /**
     * @param world le gameWorld
     * @param largeurFleche la largeur de la flèche
     * @param hauteurFleche la hauteur de la flèche
     * @param tempsCharge le temps de charge de l'attaque à distance
     */
    public AttaqueDistance(GameWorld world, float largeurFleche, float hauteurFleche, float tempsCharge){
        this.world = world;
        this.largeurFleche = largeurFleche;
        this.hauteurFleche = hauteurFleche;
        this.speed = 10f;
        animation = new Animation(TextureFactory.getInstance().getArcCharging(), 3, tempsCharge);
        isCharging = false;
        munition = 5;
    }

    @Override
    public Fleche attaqueDistance(Vector2 positionLanceur, Orientation direction, int id) {
        // Spawn de body
        switch (direction){
            case BAS:
                fleche.lancee(new Vector2(0,-speed), id);
                break;
            case DROITE:
                fleche.lancee(new Vector2(speed,0), id);
                break;
            case HAUT:
                fleche.lancee(new Vector2(0,speed), id);
                break;
            default:
                fleche.lancee(new Vector2(-speed,0), id);
                break;
        }

        munition -= 1;

        animation.reset();
        isCharging = false;
        return fleche;
    }

    /**
     * Retourne vrai si l'attaque à distance charge et qu'il a des munitions
     * @return vrai si l'attaque charge et des munitions
     */
    public boolean isChargingAndHaveMunitions() {
        return isCharging && munition > 0;
    }

    /**
     * Getter sur le nombre de munitions restantes
     * @return le nombre de munitions restantes
     */
    public int getMunition() {
        return munition;
    }

    /**
     * Permet d'ajouter un certain nombre add aux munitions
     * @param add le nombre de munition que l'on veut ajouter
     */
    public void addMunition(int add) {
        munition += add;
    }

    /**
     * Permet de prévenir la classe de continuer de charger de l'attaque
     * @param position la position de l'arc
     * @param direction la direction visée
     */
    public void charge(Vector2 position, Orientation direction) {
        this.position = position;
        this.direction = direction;
        isCharging = true;
        if(fleche == null || fleche.estLancee()){
            fleche = new Fleche(world, position, largeurFleche, hauteurFleche, direction);
        }else{
            fleche.update(position, direction);
        }
        animation.updateLast();
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public void draw(SpriteBatch batch, float x, float y){
        TextureRegion text = animation.getFrame(false, direction == Orientation.HAUT || direction == Orientation.DROITE);
        batch.draw(text, x + position.x, y + position.y, 0.5f,0.5f,text.getRegionWidth()/16f, text.getRegionHeight()/16f, 1,1,direction.getRotation() + 90);
        fleche.draw(batch, x, y);
    }

}
