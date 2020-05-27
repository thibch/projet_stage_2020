package fr.projetstage.models.entites.ennemis;

import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Filter;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Animation;
import fr.projetstage.models.CollisionFilter;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.entites.TypeEntite;
import fr.projetstage.models.entites.joueur.LocationJoueur;
import fr.projetstage.models.monde.GameWorld;

import java.util.Random;

public class OrcMasque extends Ennemi {

    private boolean hidden;
    private Animation animationHide;
    private int fleeCoolDown = 3;
    private long timeLastFlee;

    /**
     * Constructeur d'un orc masqué
     * @param world Le monde dans lequel se trouve le slime
     * @param position Sa position dans la salle
     * @param type Le type de monstre
     */
    public OrcMasque(GameWorld world, Vector2 position, Type type) {
        super(world, position, type);
        // Stats
        setPointdeVieMax(3);
        setPointDeVie(3);
        setDegats(1);
        coolDownTime = 1f;
        setSpeed(1.2f);

        hidden = true;
        timeLastFlee = System.currentTimeMillis();

        hauteur = (12f / 16f);
        largeur = (10f / 16f);

        this.position = position;

        animationHide = new Animation(TextureFactory.getInstance().getDeathSpriteSheet(),4,0.5f);
        idleAnimation = new Animation(TextureFactory.getInstance().getOrcMaskedIdleSpriteSheet(),4,0.5f);
        runningAnimation = new Animation(TextureFactory.getInstance().getOrcMaskedRunSpriteSheet(),4,0.5f);

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
                batch.draw(runningAnimation.getFrame(world.getJoueur().getPosition().x < getPosition().x, false), x + getPosition().x, y + getPosition().y, 1, 1);

          }// Sinon on met à jour l'animation idle (en faisant attention si on est sur la gauche ou droite)
            else{
                idleAnimation.update();
                batch.draw(idleAnimation.getFrame(world.getJoueur().getPosition().x < getPosition().x, false), x + getPosition().x, y + getPosition().y, 1, 1);

            }

        }
        //animation camouflage
        if(!animationHide.isLastFrame()){
            batch.draw(animationHide.getFrame(world.getJoueur().getPosition().x < getPosition().x, false), x + getPosition().x, y + getPosition().y, 1, 1);
            animationHide.update();
        }

        super.draw(batch, x, y);
    }

    @Override
    public void update() {
        super.update();
        //si a plus de 3 de distance du joueur il se camoufle
        boolean tmp = hidden;
        hidden = (Math.sqrt(Math.pow((world.getJoueur().getPosition().x - body.getPosition().x), 2) + Math.pow((world.getJoueur().getPosition().y - body.getPosition().y), 2)) > 3f);
        if(hidden != tmp){
            animationHide.reset();
        }

        if(onCoolDown){ // a frappé le joueur
            timeLastFlee = System.currentTimeMillis()+ (fleeCoolDown*1000); //fuis
        }

        //si il a frappé le joueur il fuit dans une direction aléatoire pendant x secondes et devient invisible
        if(System.currentTimeMillis() < timeLastFlee){
            //fuis dans une direction aléatoire
            body.setLinearVelocity((body.getPosition().x - world.getJoueur().getPosition().x) * getSpeed(), (body.getPosition().y - world.getJoueur().getPosition().y)* getSpeed());
        }
        comportement.getBehavior().setEnabled(System.currentTimeMillis() > timeLastFlee);
    }
}
