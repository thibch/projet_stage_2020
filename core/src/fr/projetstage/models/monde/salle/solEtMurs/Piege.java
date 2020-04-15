package fr.projetstage.models.monde.salle.solEtMurs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.projetstage.dataFactories.SoundFactory;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Animation;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.monde.salle.meubles.NonDestructible;

public class Piege extends NonDestructible {

    private Animation animation = new Animation(TextureFactory.getInstance().getPiegeSpriteSheet(),10,2);

    public Piege(GameWorld world, Vector2 position) {
        super(world, position, 1f, 1f);
        body.getFixtureList().first().setSensor(true);
    }

    @Override
    public boolean estNonDestructible() {
        return false;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animation.getFrame(false, false), body.getPosition().x, body.getPosition().y, 1, 1);
        animation.update();
    }
}
