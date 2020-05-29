package fr.projetstage.models.entites.ennemis.boss;

import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Filter;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Animation;
import fr.projetstage.models.CollisionFilter;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.entites.ennemis.Comportement;
import fr.projetstage.models.entites.ennemis.Ennemi;
import fr.projetstage.models.entites.joueur.LocationJoueur;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.monde.salle.Salle;

import java.util.Random;

public class Satan extends Ennemi {

    private boolean hidden;
    private int healCoolDown = 5;
    private long timeLastHeal;

    private Salle salle;

    /**
     * Constructeur d'un Satan ( boss )
     * @param world Le monde dans lequel se trouve le slime
     * @param position Sa position dans la salle
     * @param type Le type de monstre
     */
    public Satan(GameWorld world, Vector2 position, Type type, Salle salle) {
        super(world, position, type);
        // Stats
        setPointdeVieMax(20);
        setPointDeVie(10);
        setDegats(1);
        coolDownTime = 1f;
        setSpeed(3f);

        this.salle = salle;

        hauteur = (12f / 8f);
        largeur = (10f / 8f);

        this.position = position;

        idleAnimation = new Animation(TextureFactory.getInstance().getBigOgreIdleSpriteSheet(),4,0.5f);
        runningAnimation = new Animation(TextureFactory.getInstance().getBigOgreRunSpriteSheet(),4,0.5f);

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
        if(!mort && !hidden){
            if(body == null || !body.getLinearVelocity().isZero(0.1f)){
                runningAnimation.update();
                batch.draw(runningAnimation.getFrame(world.getJoueur().getPosition().x < getPosition().x, false), x-0.5f + getPosition().x, y + getPosition().y, 2, 2);

          }// Sinon on met à jour l'animation idle (en faisant attention si on est sur la gauche ou droite)
            else{
                idleAnimation.update();
                batch.draw(idleAnimation.getFrame(world.getJoueur().getPosition().x < getPosition().x, false), x-0.5f + getPosition().x, y + getPosition().y, 2, 2);

            }

        }

        super.draw(batch, x, y);
    }

    @Override
    public void update() {
        super.update();
        //heal
        if(System.currentTimeMillis() > timeLastHeal){
            setPointDeVie(getPointDeVie()+getDegats());
            timeLastHeal = System.currentTimeMillis()+ (healCoolDown*1000);
        }

    }
}
