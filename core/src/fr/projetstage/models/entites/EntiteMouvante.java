package fr.projetstage.models.entites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.projetstage.models.monde.salle.Orientation;

public abstract class EntiteMouvante implements Entite {

    private Attaque attaque;

    @Override
    public abstract void draw(SpriteBatch batch);

    public abstract void setDirection(Orientation direction);
}
