package fr.projetstage.models.entites.joueur;

import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;
import fr.projetstage.models.monde.GameWorld;

public class LocationJoueur implements Location<Vector2> {

    private final GameWorld world;

    /**
     * La localisation du joueur dans la salle
     * @param world le gameworld
     */
    public LocationJoueur(GameWorld world) {
        this.world = world;
    }

    @Override
    public Vector2 getPosition() {
        return world.getJoueur().getPosition();
    }

    @Override
    public float getOrientation() {
        return 0;
    }

    @Override
    public void setOrientation(float orientation) {

    }

    @Override
    public float vectorToAngle(Vector2 vector) {
        return 0;
    }

    @Override
    public Vector2 angleToVector(Vector2 outVector, float angle) {
        return new Vector2();
    }

    @Override
    public Location<Vector2> newLocation() {
        return this;
    }
}
