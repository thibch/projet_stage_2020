package fr.projetstage.models.entites.attaques;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.projetstage.models.entites.EntiteMouvante;
import fr.projetstage.models.Orientation;

public abstract class Projectile extends EntiteMouvante {

    private double dureeVie; // A détermnier en fonction de la portée et vitesse d'attaque (Classe Attaque)

    @Override
    public void draw(SpriteBatch batch, float x, float y) {

    }

    public void update(Orientation direction) {

    }
}
