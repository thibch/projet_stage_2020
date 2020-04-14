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
    private final float largeur;
    private final float hauteur;
    private final float speed;
    private boolean isCharging;
    private Animation animation;
    private Fleche fleche;


    public AttaqueDistance(GameWorld world, float largeur, float hauteur, float tempsCharge){
        this.world = world;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.speed = .1f;
        animation = new Animation(TextureFactory.getInstance().getArcCharging(), 3, tempsCharge);
        isCharging = false;
    }

    @Override
    public Fleche attaqueDistance(Vector2 positionLanceur, Orientation direction, int id) {
        // Spawn de body
        fleche = new Fleche(world, positionLanceur, largeur, hauteur, direction);
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


        animation.reset();
        isCharging = false;
        return fleche;
    }

    public boolean isCharging() {
        return isCharging;
    }

    public void charge(Vector2 position, Orientation direction) {
        isCharging = true;
        /*if(fleche == null || fleche.estLancee()){
            fleche = new Fleche(world, position, largeur, hauteur, direction);
        }else{
            fleche.update(direction);
        }*/
        animation.updateLast();
    }

    public void draw(SpriteBatch batch){
        TextureRegion text = animation.getFrame(false, false);
        batch.draw(text,0,0, 9f/16f, 13f/16f);
    }


}
