package fr.projetstage.models.entites.ennemis;

import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Animation;
import fr.projetstage.models.entites.EntiteMouvante;
import fr.projetstage.models.entites.joueur.LocationJoueur;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.monde.GameWorld;

import java.util.Map;

public class Slime extends Ennemi {

    /**
     * Constructeur d'un Slime
     * @param world Le monde dans lequel se trouve le slime
     * @param position Sa position dans la salle
     * @param type Le type de monstre
     */
    public Slime(GameWorld world, Vector2 position, Type type) {
        super(world, position, type);
        // Stats
        setPointdeVieMax(3);
        setPointDeVie(3);
        setDegats(1);
        coolDownTime = 1f;

        hauteur = (12f / 16f);
        largeur = (16f / 16f);

        this.position = position;

        idleAnimation = new Animation(TextureFactory.getInstance().getSlimeIdleSpriteSheet(),6,0.8f);
        runningAnimation = new Animation(TextureFactory.getInstance().getSlimeRunSpriteSheet(),6,0.8f);

    }

    @Override
    public void generateBody() {
        super.generateBody();

        this.comportement = new Comportement(body, 0f);
        comportement.setBehavior(new Arrive<>(comportement, new LocationJoueur(world)));
    }

    @Override
    public void draw(SpriteBatch batch, float x, float y) {
        if(!mort){
            if(body.getLinearVelocity().isZero(0.1f)){
                idleAnimation.update();
                batch.draw(idleAnimation.getFrame(world.getJoueur().getPosition().x < getPosition().x, false), x + getPosition().x, y + getPosition().y, 1, 1);
            }// Sinon on met à jour l'animation du run (en faisant attention si on est sur la gauche ou droite)
            else{
                runningAnimation.update();
                batch.draw(runningAnimation.getFrame(world.getJoueur().getPosition().x < getPosition().x, false), x + getPosition().x, y + getPosition().y, 1, 1);
            }
        }
        super.draw(batch, x, y);
    }

    @Override
    public void update() {
        super.update();
        comportement.update();
        body.setLinearVelocity(new Vector2(0.8f * body.getLinearVelocity().x,0.8f * body.getLinearVelocity().y)); //TODO: a changer plus tard, juste pour pas qu'il glide à l'infini
        position = body.getPosition();
        if(mort){
            comportement.getBehavior().setEnabled(false);
        }

        // Si on est en coolDown mais que le temps est dépassé alors nous ne sommes plus en cooldown
        if(onCoolDown && currentTime > coolDownTime) {
            currentTime = 0;
            onCoolDown = false;
        }
        //attaque CAC
        if(!onCoolDown){
            for(Map.Entry<EntiteMouvante, Boolean> target : targets.entrySet()){
                target.getKey().setTouche(this);
            }
            onCoolDown = true;
        }
    }
}
