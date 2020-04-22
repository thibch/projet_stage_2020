package fr.projetstage.models.monde.salle.solEtMurs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.Orientation;
import fr.projetstage.models.monde.salle.solEtMurs.meubles.NonDestructible;

public class Mur extends NonDestructible {

    private Orientation orientation;
    private TypeMur numMur;

    public Mur(GameWorld world, Vector2 position, Orientation orientationMur, TypeMur wallNumberTexture){
        super(world, position, 1f, 1f);
        this.orientation = orientationMur;
        numMur = wallNumberTexture;
    }

    public void draw(SpriteBatch batch, float x, float y){
        batch.draw(numMur.getTexture(), x + getPosition().x, y + getPosition().y, 1f/2f, 1f/2f, 1, 1, 1, 1, orientation.getRotation(),0,0, numMur.getTexture().getWidth(), numMur.getTexture().getHeight(), false, false);
    }

    public TypeMur getNumMur() {
        return numMur;
    }

    public Orientation getOrientation() {
        return orientation;
    }
}
