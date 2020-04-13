package fr.projetstage.models.monde.salle;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Entite;

public class Sol implements Entite{

    private int numSol;

    private Vector2 pos;

    public Sol(Vector2 position, int solNumberTexture){
        numSol = solNumberTexture;
        pos = position;
    }

    public void draw(SpriteBatch batch){
        switch (numSol){
            case 1:
                batch.draw(TextureFactory.getInstance().getSol1(), pos.x, pos.y, 1f/2f, 1f/2f, 1, 1, 1, 1, 0,0,0,TextureFactory.getInstance().getSol1().getWidth(), TextureFactory.getInstance().getSol1().getHeight(), false, false);
                break;
            case 2:
                batch.draw(TextureFactory.getInstance().getSol2(), pos.x, pos.y, 1f/2f, 1f/2f, 1, 1, 1, 1, 0,0,0,TextureFactory.getInstance().getSol2().getWidth(), TextureFactory.getInstance().getSol2().getHeight(), false, false);
                break;
            case 3:
                batch.draw(TextureFactory.getInstance().getSol3(), pos.x, pos.y, 1f/2f, 1f/2f, 1, 1, 1, 1, 0,0,0,TextureFactory.getInstance().getSol3().getWidth(), TextureFactory.getInstance().getSol3().getHeight(), false, false);
                break;
            case 4:
                batch.draw(TextureFactory.getInstance().getSol4(), pos.x, pos.y, 1f/2f, 1f/2f, 1, 1, 1, 1, 0,0,0,TextureFactory.getInstance().getSol4().getWidth(), TextureFactory.getInstance().getSol4().getHeight(), false, false);
                break;
            case 5:
                batch.draw(TextureFactory.getInstance().getSol5(), pos.x, pos.y, 1f/2f, 1f/2f, 1, 1, 1, 1, 0,0,0,TextureFactory.getInstance().getSol5().getWidth(), TextureFactory.getInstance().getSol5().getHeight(), false, false);
                break;
            case 6:
                batch.draw(TextureFactory.getInstance().getSol6(), pos.x, pos.y, 1f/2f, 1f/2f, 1, 1, 1, 1, 0,0,0,TextureFactory.getInstance().getSol6().getWidth(), TextureFactory.getInstance().getSol6().getHeight(), false, false);
                break;
            case 7:
                batch.draw(TextureFactory.getInstance().getSol7(), pos.x, pos.y, 1f/2f, 1f/2f, 1, 1, 1, 1, 0,0,0,TextureFactory.getInstance().getSol7().getWidth(), TextureFactory.getInstance().getSol7().getHeight(), false, false);
                break;
            case 8:
                batch.draw(TextureFactory.getInstance().getSol8(), pos.x, pos.y, 1f/2f, 1f/2f, 1, 1, 1, 1, 0,0,0,TextureFactory.getInstance().getSol8().getWidth(), TextureFactory.getInstance().getSol8().getHeight(), false, false);
                break;
            case 9:
                batch.draw(TextureFactory.getInstance().getSol9(), pos.x, pos.y, 1f/2f, 1f/2f, 1, 1, 1, 1, 0,0,0,TextureFactory.getInstance().getSol9().getWidth(), TextureFactory.getInstance().getSol9().getHeight(), false, false);
                break;
            default:
                batch.draw(TextureFactory.getInstance().getSol10(), pos.x, pos.y, 1f/2f, 1f/2f, 1, 1, 1, 1, 0,0,0,TextureFactory.getInstance().getSol10().getWidth(), TextureFactory.getInstance().getSol10().getHeight(), false, false);
                break;
        }
    }
}
