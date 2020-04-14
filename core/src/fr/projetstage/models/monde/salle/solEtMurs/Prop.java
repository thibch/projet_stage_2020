package fr.projetstage.models.monde.salle.solEtMurs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.projetstage.models.Entite;
import fr.projetstage.models.Orientation;

public class Prop implements Entite{

    private Orientation orientation;
    private TypeProp numProp;

    private Vector2 pos;

    public Prop(Vector2 position, Orientation orientationMur, TypeProp wallNumberTexture){
        this.orientation = orientationMur;
        numProp = wallNumberTexture;
        pos = position;
    }

    public void draw(SpriteBatch batch){
        batch.draw(numProp.getTexture(), pos.x, pos.y, 1f/2f, 1f/2f, 1, 1, 1, 1, orientation.getRotation(),0,0, numProp.getTexture().getWidth(), numProp.getTexture().getHeight(), false, false);
    }
}
