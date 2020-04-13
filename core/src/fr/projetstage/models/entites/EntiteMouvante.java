package fr.projetstage.models.entites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.projetstage.models.Entite;
import fr.projetstage.models.entites.attaques.Attaque;
import fr.projetstage.models.monde.salle.Orientation;

public abstract class EntiteMouvante implements Entite {

    protected Attaque attaque;

    @Override
    public abstract void draw(SpriteBatch batch);

    public abstract void update(Orientation direction);
}
