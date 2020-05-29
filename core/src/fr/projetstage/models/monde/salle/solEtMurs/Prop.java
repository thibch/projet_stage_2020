package fr.projetstage.models.monde.salle.solEtMurs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.projetstage.models.Entite;
import fr.projetstage.models.Orientation;

public class Prop implements Entite{

    private Orientation orientation;
    private TypeProp numProp;

    private Vector2 position;

    public Prop(Vector2 position, Orientation orientationMur, TypeProp wallNumberTexture){
        this.orientation = orientationMur;
        this.numProp = wallNumberTexture;
        this.position = position;
    }

    public void draw(SpriteBatch batch, float x, float y){
        batch.draw(numProp.getTexture(), x + position.x, y + position.y, 1f/2f, 1f/2f, 1, 1, 1, 1, orientation.getRotation(),0,0, numProp.getTexture().getWidth(), numProp.getTexture().getHeight(), false, false);
    }

    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public void generateBody(){
    }

    @Override
    public void destroyBody(){
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    public void update() {
    }
}
