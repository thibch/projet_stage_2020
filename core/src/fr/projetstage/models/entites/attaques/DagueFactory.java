package fr.projetstage.models.entites.attaques;

import com.badlogic.gdx.math.Vector2;
import fr.projetstage.models.Orientation;
import fr.projetstage.models.monde.GameWorld;

public class DagueFactory extends ProjectileFactory{

    public DagueFactory(GameWorld world, float largeurFleche, float hauteurFleche) {
        super(world, largeurFleche, hauteurFleche);
    }

    @Override
    public Projectile getNewProjectile(Vector2 position, Orientation direction) {
        return new Dague(gameWorld, position, largeurProj, hauteurProj, direction);
    }
}
