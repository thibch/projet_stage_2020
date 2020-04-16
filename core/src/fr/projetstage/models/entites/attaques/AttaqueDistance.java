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
    private final Animation animation;
    private boolean isCharging;
    private Fleche fleche;

    private Vector2 position;
    private Orientation direction;


    public AttaqueDistance(GameWorld world, float largeur, float hauteur, float tempsCharge){
        this.world = world;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.speed = 10f;
        animation = new Animation(TextureFactory.getInstance().getArcCharging(), 3, tempsCharge);
        isCharging = false;
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


        animation.reset();
        isCharging = false;
        return fleche;
    }

    public boolean isCharging() {
        return isCharging;
    }

    public void charge(Vector2 position, Orientation direction) {
        isCharging = true;
        if(fleche == null || fleche.estLancee()){
            fleche = new Fleche(world, position, largeur, hauteur, direction);
        }else{
            fleche.update(position, direction);
        }
        this.position = position;
        this.direction = direction;
        animation.updateLast();
    }

    public void draw(SpriteBatch batch){
        TextureRegion text = animation.getFrame(false, direction == Orientation.HAUT || direction == Orientation.DROITE);
        batch.draw(text, position.x, position.y, 0.5f,0.5f,text.getRegionWidth()/16f, text.getRegionHeight()/16f, 1,1,direction.getRotation() + 90);
        fleche.draw(batch);
    }

}
