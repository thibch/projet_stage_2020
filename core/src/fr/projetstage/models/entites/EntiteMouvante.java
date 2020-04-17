package fr.projetstage.models.entites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Animation;
import fr.projetstage.models.Entite;
import fr.projetstage.models.entites.attaques.Attaque;
import fr.projetstage.models.Orientation;
import fr.projetstage.models.monde.GameWorld;

public abstract class EntiteMouvante implements Entite {

    protected Body body;
    protected GameWorld world;

    private int pointDeVie;
    private int pointdeVieMax;

    private int degats; //TODO : a definir par rapport a l'arme plus tard
    private float knockback = 3f;
    protected float coolDownTime; //temps entre 2 attaques
    protected boolean onCoolDown = false;
    protected float currentTime = 0f;


    protected boolean mort = false;
    private boolean mortAnimFinie = false;
    private final Animation animationMort = new Animation(TextureFactory.getInstance().getDeathSpriteSheet(),4,1f);

    public void setPointDeVie(int pointDeVie) {
        this.pointDeVie = Math.min(pointDeVie, pointdeVieMax);
    }

    public void setPointdeVieMax(int pointdeVieMax) {
        this.pointdeVieMax = pointdeVieMax;
    }

    public void setDegats(int degats) {
        this.degats = degats;
    }

    public int getDegats() { return degats; }

    public int getPointDeVie() {
        return pointDeVie;
    }

    public int getPointdeVieMax() {
        return pointdeVieMax;
    }

    public float getKnockback() {
        return knockback;
    }

    public void setKnockback(float knockback) {
        this.knockback = knockback;
    }

    public void setTouche(Entite source) {
        EntiteMouvante sourceDmg = (EntiteMouvante)source;
        setPointDeVie(getPointDeVie()- sourceDmg.getDegats());

        //knockback
        if(!mort && !((EntiteMouvante) source).mort) {
            Vector2 knockbackVector = new Vector2((body.getPosition().x - sourceDmg.body.getPosition().x), (body.getPosition().y - sourceDmg.body.getPosition().y));
            knockbackVector.setLength(sourceDmg.getKnockback());
            body.applyLinearImpulse(knockbackVector, body.getPosition(), true);
        }

        mort = getPointDeVie() <= 0;
    }

    public boolean estMort(){
        return mort && mortAnimFinie;
    }

    public void draw(SpriteBatch batch){
        //Animation de la mort
        if(mort && !mortAnimFinie){
            batch.draw(animationMort.getFrame(false, false), body.getPosition().x, body.getPosition().y, 1, 1);
        }
    }

    public void update(){
        if(mort){
            degats = 0;
            body.getFixtureList().first().setSensor(true);
            animationMort.update();
            mortAnimFinie = animationMort.isLastFrame();
        }
    }

    public boolean estMaxPointDeVie() {
        return pointDeVie == pointdeVieMax;
    }
}
