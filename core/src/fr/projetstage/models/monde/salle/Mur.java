package fr.projetstage.models.monde.salle;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.entites.Entite;
import fr.projetstage.models.monde.GameWorld;

public class Mur implements Entite{

    private Orientation orientation;
    private int numMur;

    private Vector2 pos;

    public Mur(Vector2 position, Orientation orientationMur, int wallNumberTexture){
        this.orientation = orientationMur;
        numMur = wallNumberTexture;
        pos = position;
    }

    public void draw(SpriteBatch batch){
        switch (numMur){
            case 1:
                batch.draw(TextureFactory.getInstance().getMur1(), pos.x, pos.y, 1f/2f, 1f/2f, 1, 1, 1, 1, orientation.getRotation(),0,0,TextureFactory.getInstance().getMur1().getWidth(), TextureFactory.getInstance().getMur1().getHeight(), false, false);
                break;
            case 2:
                batch.draw(TextureFactory.getInstance().getMur2(), pos.x, pos.y, 1f/2f, 1f/2f, 1, 1, 1, 1, orientation.getRotation(),0,0,TextureFactory.getInstance().getMur1().getWidth(), TextureFactory.getInstance().getMur1().getHeight(), false, false);
                break;
            case 3:
                batch.draw(TextureFactory.getInstance().getMur3(), pos.x, pos.y, 1f/2f, 1f/2f, 1, 1, 1, 1, orientation.getRotation(),0,0,TextureFactory.getInstance().getMur1().getWidth(), TextureFactory.getInstance().getMur1().getHeight(), false, false);
                break;
            default:
                batch.draw(TextureFactory.getInstance().getMur4(), pos.x, pos.y, 1f/2f, 1f/2f, 1, 1, 1, 1, orientation.getRotation(),0,0,TextureFactory.getInstance().getMur1().getWidth(), TextureFactory.getInstance().getMur1().getHeight(), false, false);
                break;
        }
    }
}
