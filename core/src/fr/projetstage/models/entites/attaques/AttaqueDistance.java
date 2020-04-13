package fr.projetstage.models.entites.attaques;

import com.badlogic.gdx.math.Vector2;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.monde.salle.Orientation;

public class AttaqueDistance extends Attaque {


    private final GameWorld world;
    private final float largeur;
    private final float hauteur;
    private final float speed;

    public AttaqueDistance(GameWorld world, float largeur, float hauteur){
        this.world = world;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.speed = 10.f;
    }

    @Override
    public Fleche attaqueDistance(Vector2 positionLanceur, Orientation direction, int id) {
        // Spawn de body

        Fleche fleche = new Fleche(world, positionLanceur, largeur, hauteur, id, direction);

        switch (direction){
            case BAS:
                fleche.lancee(new Vector2(0,-speed));
                break;
            case DROITE:
                fleche.lancee(new Vector2(speed,0));
                break;
            case HAUT:
                fleche.lancee(new Vector2(0,speed));
                break;
            case GAUCHE:
                fleche.lancee(new Vector2(-speed,0));
                break;
        }

        return fleche;
    }
}
