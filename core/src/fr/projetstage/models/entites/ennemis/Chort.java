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

public class Chort extends Ennemi {

    private int chargeCoolDown = 3;
    private long timeLastCharge;

    /**
     * Constructeur d'un orc masqué
     * @param world Le monde dans lequel se trouve le slime
     * @param position Sa position dans la salle
     * @param type Le type de monstre
     */
    public Chort(GameWorld world, Vector2 position, Type type) {
        super(world, position, type);
        // Stats
        setPointdeVieMax(6);
        setPointDeVie(6);
        setDegats(1);
        coolDownTime = 1f;
        setSpeed(0.6f);

        timeLastCharge = System.currentTimeMillis();

        hauteur = (6f / 16f);
        largeur = (8f / 16f);

        this.position = position;

        idleAnimation = new Animation(TextureFactory.getInstance().getChortIdleSpriteSheet(),4,0.5f);
        runningAnimation = new Animation(TextureFactory.getInstance().getChortRunSpriteSheet(),4,0.5f);

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

        super.draw(batch, x, y);
    }

    @Override
    public void update() {
        super.update();

        //charge
        if(System.currentTimeMillis() > timeLastCharge){
            body.setLinearVelocity(body.getLinearVelocity().x*5f,body.getLinearVelocity().y*5f);
            timeLastCharge = System.currentTimeMillis()+ (chargeCoolDown*1000);
        }
    }
}
