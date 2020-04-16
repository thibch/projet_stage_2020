package fr.projetstage.models.entites.objets;

import com.badlogic.gdx.math.Vector2;
import fr.projetstage.models.monde.GameWorld;

public class PotionRouge extends ObjetAuSol{
    public PotionRouge(GameWorld world, Vector2 position) {
        super(world, position, new Vector2(4f/16f,4f/16f), 7f/16f, 10f/16f);
    }

    @Override
    public void applyEffect() {
        world.getJoueur().setPointDeVie(world.getJoueur().getPointDeVie()+2);
    }
}
