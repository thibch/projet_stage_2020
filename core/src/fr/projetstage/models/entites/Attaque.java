package fr.projetstage.models.entites;

import com.badlogic.gdx.math.Vector2;
import fr.projetstage.models.monde.salle.Orientation;

public abstract class Attaque {

    public abstract void attaque(Vector2 positionJoueur, Orientation direction);
    public abstract Fleches attaqueDistance(Vector2 positionJoueur, Orientation direction, int id);
}
