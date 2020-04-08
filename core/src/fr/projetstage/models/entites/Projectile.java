package fr.projetstage.models.entites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.projetstage.models.monde.salle.Orientation;

public class Projectile extends EntiteMouvante{

    private double dureeVie; //A détermnier en fonction de la portée et vitesse d'attaque (Classe Attaque)

    @Override
    public void draw(SpriteBatch batch) {

    }

    @Override
    public void setDirection(Orientation direction) {

    }
}
