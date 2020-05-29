package fr.projetstage.models.monde.salle.solEtMurs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Animation;
import fr.projetstage.models.Orientation;

public class Torche extends Prop{

    private Animation animationTorche;

    public Torche(Vector2 position, Orientation orientationMur) {
        super(position, orientationMur, null);
        animationTorche = new Animation(TextureFactory.getInstance().getTorcheSpriteSheet(), 6, 1f);
    }

    public void update(){
        animationTorche.update();
    }

    @Override
    public void draw(SpriteBatch batch, float x, float y) {
        batch.draw(animationTorche.getFrame(false, false), x + getPosition().x, y + getPosition().y, 1f / 2f, 1f / 2f, 1, 1, 1, 1, getOrientation().getRotation());
    }
}
