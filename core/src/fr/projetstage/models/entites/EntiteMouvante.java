package fr.projetstage.models.entites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Animation;
import fr.projetstage.models.Entite;
import fr.projetstage.models.monde.GameWorld;

public abstract class EntiteMouvante implements Entite {

    protected Body body;
    protected GameWorld world;

    private int pointDeVie;
    private int pointdeVieMax;

    private float speed;

    private int degats; //TODO : a definir par rapport a l'arme plus tard
    private float knockback = 3f;
    protected float coolDownTime; //temps entre 2 attaques
    protected float coolDownTimeInvincible = 0.5f; //temps entre 2 attaques reçues
    protected boolean onCoolDown = false;
    protected float currentTime = 0f;
    protected float currentTimeInvincible = 0f;

    protected Vector2 position;

    protected boolean mort = false;
    private boolean mortAnimFinie = false;
    private final Animation animationMort = new Animation(TextureFactory.getInstance().getDeathSpriteSheet(),4,1f);

    /**
     * @param world le gameworld
     * @param position la position de l'entité
     */
    public EntiteMouvante(GameWorld world, Vector2 position){
        this.world = world;
        this.position = position;
    }

    public int getPointDeVie() {
        return pointDeVie;
    }

    public void setPointDeVie(int pointDeVie) {
        this.pointDeVie = Math.min(pointDeVie, pointdeVieMax);
    }

    public int getPointdeVieMax() {
        return pointdeVieMax;
    }

    public void setPointdeVieMax(int pointdeVieMax) {
        this.pointdeVieMax = pointdeVieMax;
    }

    public int getDegats() { return degats; }

    public void setDegats(int degats) {
        this.degats = degats;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getKnockback() {
        return knockback;
    }

    public void setKnockback(float knockback) {
        this.knockback = knockback;
    }

    /**
     * Si l'entité est morte et qu'elle a finit de faire son animation
     * @return vrai si l'entité est morte et qu'elle a finit de faire son animation
     */
    public boolean estMort(){
        return mort && mortAnimFinie;
    }

    public boolean estMaxPointDeVie() {
        return pointDeVie == pointdeVieMax;
    }

    public void setTouche(Entite source) {
        if(currentTimeInvincible > coolDownTimeInvincible){
            currentTimeInvincible = 0f;
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

    }

    public void draw(SpriteBatch batch, float x, float y){
        //Animation de la mort
        if(mort && !mortAnimFinie){
            batch.draw(animationMort.getFrame(false, false), x + getPosition().x, y + getPosition().y, 1, 1);
        }
    }

    public void update(){
        currentTime += Gdx.graphics.getDeltaTime();
        currentTimeInvincible += Gdx.graphics.getDeltaTime();
        if(mort){
            degats = 0;
            body.getFixtureList().first().setSensor(true);
            animationMort.update();
            mortAnimFinie = animationMort.isLastFrame();
        }
        position = body.getPosition();
    }

    @Override
    public Vector2 getPosition(){
        return position;
    }
}
