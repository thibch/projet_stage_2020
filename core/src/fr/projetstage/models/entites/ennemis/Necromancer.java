package fr.projetstage.models.entites.ennemis;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Filter;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Animation;
import fr.projetstage.models.CollisionFilter;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.entites.TypeEntite;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.monde.salle.Salle;

import java.util.ArrayList;
import java.util.Random;

public class Necromancer extends Ennemi {

    private Salle salle;
    private ArrayList<Skelet> invocations;
    private int nbInvocs;
    private int invocationCoolDown = 999;
    private long timelastInvocation;

    /**
     * Constructeur d'un skelet
     * @param world Le monde dans lequel se trouve le slime
     * @param position Sa position dans la salle
     * @param type Le type de monstre
     * @param salle salle dans laquelle le necromancer ce trouve
     */
    public Necromancer(GameWorld world, Vector2 position, Type type, Salle salle) {
        super(world, position, type);
        this.salle = salle;
        invocations = new ArrayList<>();
        nbInvocs = 0;
        // Stats
        setPointdeVieMax(10);
        setPointDeVie(10);
        setDegats(2);
        coolDownTime = 1f;
        timelastInvocation = System.currentTimeMillis();

        hauteur = (12f / 16f);
        largeur = (10f / 16f);

        this.position = position;

        idleAnimation = new Animation(TextureFactory.getInstance().getNecromancerIdleSpriteSheet(),4,0.5f);
        runningAnimation = new Animation(TextureFactory.getInstance().getNecromancerRunSpriteSheet(),4,0.5f);
    }

    @Override
    public void generateBody() {
        super.generateBody();


        //this.comportement = new Comportement(body, 0f);
        //comportement.setBehavior(new Arrive<>(comportement, new LocationJoueur(world)));

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
        if(System.currentTimeMillis() > timelastInvocation){
            Random rand = new Random();
            salle.ajouterMonstre(new Skelet(world,new Vector2(rand.nextInt(salle.getLargeur()),rand.nextInt(salle.getHauteur())),new Type(TypeEntite.ENNEMI, salle.getNbEnnemis())));
            salle.ajouterMonstre(new Skelet(world,new Vector2(rand.nextInt(salle.getLargeur()),rand.nextInt(salle.getHauteur())),new Type(TypeEntite.ENNEMI, salle.getNbEnnemis())));
            salle.ajouterMonstre(new Skelet(world,new Vector2(rand.nextInt(salle.getLargeur()),rand.nextInt(salle.getHauteur())),new Type(TypeEntite.ENNEMI, salle.getNbEnnemis())));

            timelastInvocation = System.currentTimeMillis()+ (invocationCoolDown*1000);
        }
        //TODO : corriger ses mouvement pétés il shake
        //TODO : il fuit pas le joueur vers le haut ( bad maths )
        //deplacements
        if((Math.sqrt(Math.pow((world.getJoueur().getPosition().x - body.getPosition().x), 2) + Math.pow((world.getJoueur().getPosition().y - body.getPosition().y), 2)) < 2) && ((salle.getLargeur()/2f) - body.getPosition().x < 3) && ((salle.getHauteur()/2f) - body.getPosition().y < 3)){
            //joueur a moins de 3 de distance et qu'il a moins de 3 blocs du centre
            body.setLinearVelocity((body.getPosition().x - world.getJoueur().getPosition().x) * getSpeed(), (body.getPosition().y - world.getJoueur().getPosition().y)* getSpeed());
        }
        else if((Math.sqrt(Math.pow((world.getJoueur().getPosition().x - body.getPosition().x), 2) + Math.pow((world.getJoueur().getPosition().y - body.getPosition().y), 2)) > 1.5f)){ //retourne au centre
            body.setLinearVelocity(((salle.getLargeur()/2f) - body.getPosition().x) * getSpeed(), ((salle.getHauteur()/2f)- body.getPosition().y)* getSpeed());
        }
    }
}
