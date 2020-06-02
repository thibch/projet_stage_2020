package fr.projetstage.models.entites.ennemis.boss;

import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Filter;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Animation;
import fr.projetstage.models.CollisionFilter;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.entites.TypeEntite;
import fr.projetstage.models.entites.ennemis.Comportement;
import fr.projetstage.models.entites.ennemis.Ennemi;
import fr.projetstage.models.entites.joueur.LocationJoueur;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.monde.salle.Salle;

import java.util.Random;

public class Ogre extends Ennemi {

    private boolean hidden;
    private Animation animationHide;
    private int fleeCoolDown = 1;
    private long timeLastFlee;
    private int currentHP;

    private int chargeCoolDown = 3;
    private long timeLastCharge;

    private Salle salle;

    /**
     * Constructeur d'un Ogre ( boss )
     * @param world Le monde dans lequel se trouve le slime
     * @param position Sa position dans la salle
     * @param type Le type de monstre
     */
    public Ogre(GameWorld world, Vector2 position, Type type, Salle salle) {
        super(world, position, type);
        // Stats
        setPointdeVieMax(12);
        setPointDeVie(12);
        currentHP = 10;
        setDegats(1);
        coolDownTime = 1f;
        setSpeed(1f);

        hidden = true;
        timeLastFlee = System.currentTimeMillis();
        timeLastCharge = System.currentTimeMillis();
        this.salle = salle;

        hauteur = (8f / 8f);
        largeur = (10f / 8f);

        this.position = position;

        animationHide = new Animation(TextureFactory.getInstance().getDeathSpriteSheet(),4,0.5f);
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
        //animation camouflage
        if(!animationHide.isLastFrame()){
            batch.draw(animationHide.getFrame(world.getJoueur().getPosition().x < getPosition().x, false), x-0.5f + getPosition().x, y + getPosition().y, 2, 2);
            animationHide.update();
        }

        super.draw(batch, x, y);
    }

    @Override
    public void update() {
        super.update();
        //si a plus de 2 de distance du joueur il se camoufle
        boolean tmp = hidden;
        hidden = (Math.sqrt(Math.pow((world.getJoueur().getPosition().x - body.getPosition().x), 2) + Math.pow((world.getJoueur().getPosition().y - body.getPosition().y), 2)) > 2.5f);
        if(hidden != tmp){
            animationHide.reset();
        }

        if(onCoolDown || getPointDeVie() != currentHP){ // a frappé le joueur ou a pris un coup
            timeLastFlee = System.currentTimeMillis()+ (fleeCoolDown*1000); //fuis
            //se téléporte aléatoirement
            Random rand = new Random();
            body.setTransform(rand.nextInt(salle.getLargeur()-1),rand.nextInt(salle.getHauteur()-1),0);
            currentHP = (int)getPointDeVie();
        }

        //charge
        if(System.currentTimeMillis() > timeLastCharge){
            body.setLinearVelocity(body.getLinearVelocity().x*10f,body.getLinearVelocity().y*10f);
            timeLastCharge = System.currentTimeMillis()+ (chargeCoolDown*1000);
        }

        comportement.getBehavior().setEnabled(System.currentTimeMillis() > timeLastFlee);
    }
}
