package fr.projetstage.models.entites.ennemis.boss;

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
import fr.projetstage.models.entites.attaques.projectiles.Projectile;
import fr.projetstage.models.entites.attaques.projectiles.factory.TridentFactory;
import fr.projetstage.models.entites.ennemis.Comportement;
import fr.projetstage.models.entites.ennemis.Ennemi;
import fr.projetstage.models.entites.joueur.LocationJoueur;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.monde.salle.Salle;

import java.util.ArrayList;

public class Satan extends Ennemi {

    private int healCoolDown = 5;
    private long timeLastHeal;

    private int chargeCoolDown = 3;
    private long timeLastCharge;
    private long timeLastStun;

    private Salle salle;

    private AttaqueDistance attaqueDistance;
    private ArrayList<Projectile> projectiles;

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
        setPointDeVie(12);
        setDegats(1);
        coolDownTime = 1f;
        setSpeed(3f);

        timeLastHeal = System.currentTimeMillis();
        timeLastCharge = System.currentTimeMillis();
        timeLastStun = System.currentTimeMillis();

        this.salle = salle;

        hauteur = (8f / 8f);
        largeur = (10f / 8f);

        this.position = position;

        idleAnimation = new Animation(TextureFactory.getInstance().getBigDemonIdleSpriteSheet(),4,0.5f);
        runningAnimation = new Animation(TextureFactory.getInstance().getBigDemonRunSpriteSheet(),4,0.5f);

        attaqueDistance = new AttaqueDistance(world, new TridentFactory(world,5f/16f, 5f/16f), 1f);
        attaqueDistance.setSpeed(15f);
        projectiles = new ArrayList<>();
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
                batch.draw(runningAnimation.getFrame(world.getJoueur().getPosition().x < getPosition().x, false), x-0.5f + getPosition().x, y + getPosition().y, 2, 2);

          }// Sinon on met à jour l'animation idle (en faisant attention si on est sur la gauche ou droite)
            else{
                idleAnimation.update();
                batch.draw(idleAnimation.getFrame(world.getJoueur().getPosition().x < getPosition().x, false), x-0.5f + getPosition().x, y + getPosition().y, 2, 2);

            }

        }

        for(Projectile proj : projectiles){
            proj.draw(batch, x, y);
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

        //charge
        if(System.currentTimeMillis() > timeLastCharge){
            body.setLinearVelocity(body.getLinearVelocity().x*10f,body.getLinearVelocity().y*10f);
            timeLastCharge = System.currentTimeMillis()+ (chargeCoolDown*1000);
            timeLastStun = System.currentTimeMillis()+1500;
        }

        if(currentTime < 3f){
            attaqueDistance.charge(getPosition(), Orientation.DROITE);
        }else{
            projectiles.add(attaqueDistance.attaqueDistanceJoueur(new Vector2(getPosition().x, getPosition().y), projectiles.size()));
            currentTime = 0;
        }

        comportement.getBehavior().setEnabled(System.currentTimeMillis() > timeLastStun);

    }
}
