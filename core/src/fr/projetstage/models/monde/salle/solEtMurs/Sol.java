package fr.projetstage.models.monde.salle.solEtMurs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Entite;

public class Sol implements Entite{

    private final TypeSol numSol;
    private final Vector2 pos;

    public Sol(Vector2 position, TypeSol solNumberTexture){
        numSol = solNumberTexture;
        pos = position;
    }

    public void draw(SpriteBatch batch){
        batch.draw(numSol.getTexture(), pos.x, pos.y, 1f/2f, 1f/2f, 1, 1, 1, 1, 0,0,0, numSol.getTexture().getWidth(), numSol.getTexture().getHeight(), false, false);
    }
}
