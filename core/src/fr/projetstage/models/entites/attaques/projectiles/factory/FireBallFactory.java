package fr.projetstage.models.entites.attaques.projectiles.factory;

import com.badlogic.gdx.math.Vector2;
import fr.projetstage.models.Orientation;
import fr.projetstage.models.entites.attaques.projectiles.FireBall;
import fr.projetstage.models.entites.attaques.projectiles.Projectile;
import fr.projetstage.models.monde.GameWorld;

public class FireBallFactory extends ProjectileFactory{

    public FireBallFactory(GameWorld world, float largeurFleche, float hauteurFleche) {
        super(world, largeurFleche, hauteurFleche);
    }

    @Override
    public Projectile getNewProjectile(Vector2 position, Orientation direction) {
        return new FireBall(gameWorld, position, largeurProj, hauteurProj, direction);
    }
}
