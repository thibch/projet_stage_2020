package fr.projetstage.models.entites.ennemis;

import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Filter;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Animation;
import fr.projetstage.models.CollisionFilter;
import fr.projetstage.models.Orientation;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.entites.attaques.AttaqueDistance;
import fr.projetstage.models.entites.attaques.FlecheFactory;
import fr.projetstage.models.entites.attaques.Projectile;
import fr.projetstage.models.entites.joueur.LocationJoueur;
import fr.projetstage.models.monde.GameWorld;

import java.util.ArrayList;

public class EnnemiADistance extends Ennemi {

    //////// CE QUE J'AI RAJOUTER POUR L'ATTAQUE A DISTANCE ////////
    private AttaqueDistance attaqueDistance;
    private ArrayList<Projectile> projectiles;
    ////////

    /**
     * @param world    le gameworld
     * @param position la position de l'ennemi
     * @param type     le type de l'ennemi
     */
    public EnnemiADistance(GameWorld world, Vector2 position, Type type) {
        super(world, position, type);
        // Stats
        setPointdeVieMax(3);
        setPointDeVie(3);
        setDegats(1);
        coolDownTime = 1f;
        setSpeed(1.4f);

        hauteur = (12f / 16f);
        largeur = (10f / 16f);

        this.position = position;

        idleAnimation = new Animation(TextureFactory.getInstance().getSkeletIdleSpriteSheet(),4,0.5f);
        runningAnimation = new Animation(TextureFactory.getInstance().getSkeletRunSpriteSheet(),4,0.5f);


        //////// CE QUE J'AI RAJOUTER POUR L'ATTAQUE A DISTANCE ////////
        attaqueDistance = new AttaqueDistance(world, new FlecheFactory(world,12f/16f, 5f/16f), 1f);
        projectiles = new ArrayList<>();
        ////////
    }

    @Override
    public void generateBody() {
        super.generateBody();

        this.comportement = new Comportement(body, 0f);
        comportement.setBehavior(new Arrive<>(comportement, new LocationJoueur(world)));
        Filter tmp = new Filter();
        tmp.categoryBits = CollisionFilter.MONSTRESOL.getCategory();
        tmp.maskBits =(short) (CollisionFilter.JOUEUR.getCategory() | CollisionFilter.DECOR.getCategory());
        body.getFixtureList().first().setFilterData(tmp);
    }

    @Override
    public void draw(SpriteBatch batch, float x, float y) {
        if(!mort){
            if(body == null || !body.getLinearVelocity().isZero(0.1f)){
                runningAnimation.update();
                batch.draw(runningAnimation.getFrame(world.getJoueur().getPosition().x < getPosition().x, false), x + getPosition().x, y + getPosition().y, 1, 1);

            }// Sinon on met à jour l'animation idle (en faisant attention si on est sur la gauche ou droite)
            else{
                idleAnimation.update();
                batch.draw(idleAnimation.getFrame(world.getJoueur().getPosition().x < getPosition().x, false), x + getPosition().x, y + getPosition().y, 1, 1);

            }
        }

        //////// CE QUE J'AI RAJOUTER POUR L'ATTAQUE A DISTANCE ////////
        for(Projectile proj : projectiles){
            proj.draw(batch, x, y);
        }
        ////////

        super.draw(batch, x, y);
    }

    @Override
    public void update() {
        super.update();

        //////// CE QUE J'AI RAJOUTER POUR L'ATTAQUE A DISTANCE ////////
        if(currentTime < 3f){
            attaqueDistance.charge(getPosition(), Orientation.DROITE);
        }else{
            projectiles.add(attaqueDistance.attaqueDistance(new Vector2(getPosition().x, getPosition().y), Orientation.DROITE, projectiles.size()));
            currentTime = 0;
        }
        ////////
    }
}
