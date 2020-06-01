package fr.projetstage.models.entites.attaques.projectiles.factory;

import com.badlogic.gdx.math.Vector2;
import fr.projetstage.models.Orientation;
import fr.projetstage.models.entites.attaques.projectiles.Projectile;
import fr.projetstage.models.monde.GameWorld;

public abstract class ProjectileFactory {

    protected GameWorld gameWorld;

    protected float largeurProj;
    protected float hauteurProj;

    public ProjectileFactory(GameWorld world, float largeurProj, float hauteurProj) {
        this.gameWorld = world;
        this.largeurProj = largeurProj;
        this.hauteurProj = hauteurProj;
    }

    public abstract Projectile getNewProjectile(Vector2 position, Orientation direction);
}
