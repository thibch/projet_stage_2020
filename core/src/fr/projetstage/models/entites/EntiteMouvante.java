package fr.projetstage.models.entites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class EntiteMouvante implements Entite {

    private Attaque attaque;

    @Override
    public abstract void draw(SpriteBatch batch);
}
