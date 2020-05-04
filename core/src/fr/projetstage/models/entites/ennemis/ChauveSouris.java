package fr.projetstage.models.entites.ennemis;

import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Filter;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Animation;
import fr.projetstage.models.CollisionFilter;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.entites.joueur.LocationJoueur;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.Orientation;

public class ChauveSouris extends Ennemi{


    /**
     * @param world le gameWorld
     * @param position la position de la chauve-souris
     * @param type le type de la chauve-souris
     */
    public ChauveSouris(GameWorld world, Vector2 position, Type type) {
        super(world, position,type);

        // Stats
        setPointdeVieMax(1);
        setPointDeVie(1);
        setDegats(2);
        coolDownTime = 1f;

        hauteur = (9f / 16f);
        largeur = (13f / 16f);

        this.position = position;

        idleAnimation = new Animation(TextureFactory.getInstance().getEyeBatSpriteSheet(),4,0.8f);
        runningAnimation = new Animation(TextureFactory.getInstance().getEyeBatSpriteSheet(),4,0.8f);

    }

    @Override
    public void generateBody() {
        super.generateBody();

        this.comportement = new Comportement(body, 0f);
        comportement.setBehavior(new Arrive<>(comportement, new LocationJoueur(world)));
        Filter tmp = new Filter();
        tmp.categoryBits = CollisionFilter.MONSTRESOL.getCategory();
        tmp.maskBits = CollisionFilter.JOUEUR.getCategory();
        body.getFixtureList().first().setFilterData(tmp);
    }

    @Override
    public void draw(SpriteBatch batch, float x, float y) {
        if(!mort){
            idleAnimation.update();
            batch.draw(idleAnimation.getFrame(world.getJoueur().getPosition().x < getPosition().x, false), x + getPosition().x, y + getPosition().y, 1, 1);
        }
        super.draw(batch, x, y);
    }

    @Override
    public void update() {
        super.update();

    }
}
