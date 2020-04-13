package fr.projetstage.models.monde.salle;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Entite;

public class Prop implements Entite{

    private Orientation orientation;
    private int numProp;

    private Vector2 pos;

    public Prop(Vector2 position, Orientation orientationMur, int wallNumberTexture){
        this.orientation = orientationMur;
        numProp = wallNumberTexture;
        pos = position;
    }

    public void draw(SpriteBatch batch){
        switch (numProp){
            case 1:
                batch.draw(TextureFactory.getInstance().getDrapeauVert(), pos.x, pos.y, 1f/2f, 1f/2f, 1, 1, 1, 1, orientation.getRotation(),0,0,TextureFactory.getInstance().getDrapeauVert().getWidth(), TextureFactory.getInstance().getDrapeauVert().getHeight(), false, false);
                break;
            case 2:
                batch.draw(TextureFactory.getInstance().getDrapeauRouge(), pos.x, pos.y, 1f/2f, 1f/2f, 1, 1, 1, 1, orientation.getRotation(),0,0,TextureFactory.getInstance().getDrapeauRouge().getWidth(), TextureFactory.getInstance().getDrapeauRouge().getHeight(), false, false);
                break;
            default:
                batch.draw(TextureFactory.getInstance().getPrisoner(), pos.x, pos.y, 1f/2f, 1f/2f, 1, 1, 1, 1, orientation.getRotation(),0,0,TextureFactory.getInstance().getPrisoner().getWidth(), TextureFactory.getInstance().getPrisoner().getHeight(), false, false);
                break;
        }
    }
}
