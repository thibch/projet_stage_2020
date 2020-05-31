package fr.projetstage.models.entites.ennemis;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Filter;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Animation;
import fr.projetstage.models.CollisionFilter;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.monde.salle.Salle;


public class OrcShaman extends Ennemi {

    private Salle salle;
    private int healCoolDown = 3;
    private long cooldown;

    /**
     * @param world    le gameworld
     * @param position la position de l'ennemi
     * @param type     le type de l'ennemi
     */
    public OrcShaman(GameWorld world, Vector2 position, Type type, Salle salle) {
        super(world, position, type);
        // Stats
        setPointdeVieMax(3);
        setPointDeVie(3);
        setDegats(1);
        coolDownTime = 1f;
        setSpeed(1.4f);

        hauteur = (12f / 16f);
        largeur = (10f / 16f);

        this.salle = salle;
        this.position = position;
        cooldown = System.currentTimeMillis();

        idleAnimation = new Animation(TextureFactory.getInstance().getOrcShamanIdleSpriteSheet(),4,0.5f);
        runningAnimation = new Animation(TextureFactory.getInstance().getOrcShamanRunSpriteSheet(),4,0.5f);

    }

    @Override
    public void generateBody() {
        super.generateBody();

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

        //baton
        if(System.currentTimeMillis() < cooldown - 2000){
            batch.draw(TextureFactory.getInstance().getBatonMagique(), x-0.2f + getPosition().x, y + getPosition().y, 0.4f, 1.3f);
        }

        super.draw(batch, x, y);
    }

    @Override
    public void update() {
        super.update();

        if(System.currentTimeMillis() > cooldown){
            int nbEnnemis = salle.getNbEnnemis();
            for(int i = 0; i < nbEnnemis; i++){
                try{
                    salle.getEnnemi(i).setPointDeVie(salle.getEnnemi(i).getPointDeVie()+1);
                }catch (Exception e){/*ennemi mort en cours d'update*/}
            }
            cooldown = System.currentTimeMillis() + healCoolDown*1000;
        }

    }
}
