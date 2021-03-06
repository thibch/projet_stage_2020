package fr.projetstage.models.monde.salle.solEtMurs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.projetstage.models.Entite;

public class Sol implements Entite{

    private final TypeSol numSol;
    private final Vector2 position;

    public Sol(Vector2 position, TypeSol solNumberTexture){
        numSol = solNumberTexture;
        this.position = position;
    }

    public void draw(SpriteBatch batch, float x, float y){
        batch.draw(numSol.getTexture(), x + position.x, y + position.y, 1f/2f, 1f/2f, 1, 1, 1, 1, 0,0,0, numSol.getTexture().getWidth(), numSol.getTexture().getHeight(), false, false);
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
}
