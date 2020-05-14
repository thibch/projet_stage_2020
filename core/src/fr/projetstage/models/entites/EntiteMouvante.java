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

    private float speed = 1f;

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

    /**
     * Methode permettant d'obtenir les points de vie actuels de l'entite
     * @return un entier des points des vie.
     */
    public int getPointDeVie() {
        return pointDeVie;
    }

    /**
     * Methode permettant de changer le nombre de points de vie actuels de l'entite
     * @param pointDeVie le nouveau nombre de points de vie.
     */
    public void setPointDeVie(int pointDeVie) {
        this.pointDeVie = Math.min(pointDeVie, pointdeVieMax);
    }

    /**
     * Methode permettant de retourner le nombre maximum de points de vie d'une entite
     * @return le nombre max de points de vie d'une entite
     */
    public int getPointdeVieMax() {
        return pointdeVieMax;
    }

    /**
     * Methode permettant de modifier le nombre maximal de point de vie d'une entite
     * @param pointdeVieMax le nouveau nombre de points de vie maximum de l'entite
     */
    public void setPointdeVieMax(int pointdeVieMax) {
        this.pointdeVieMax = pointdeVieMax;
    }

    /**
     * methode permettant de retourner le nombre de degats que fait l'entite
     * @return un entier des degats de l'entite
     */
    public int getDegats() { return degats; }

    /**
     * methode permettant de changer les degats d'une entite
     * @param degats un entier des degats de l'entite
     */
    public void setDegats(int degats) {
        this.degats = degats;
    }

    /**
     * methode permettant de retourner la vitesse d'une entite
     * @return un float de la vitesse de l'entite
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * methode permettant de definir la vitesse d'une entite
     * @param speed un float de la nouvelle vitesse de l'entite
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /**
     * methode permettant de retourner le recul d'une entite
     * @return un float du recul de l'entite
     */
    public float getKnockback() {
        return knockback;
    }

    /**
     * methode permettant de definir le recul d'une entite
     * @param knockback un float du recul de l'entite
     */
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

    /**
     * methode permettant de savoir si l'entite à toute sa vie
     * @return un booleen vrai si l'entite à tout ses points de vie
     */
    public boolean estMaxPointDeVie() {
        return pointDeVie == pointdeVieMax;
    }

    /**
     * methode permettant de faire des degats a une entite avec recul
     * @param source la source des degats
     */
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

    /**
     * Methode permettant de dessiner une entite
     * @param batch le spriteBatch pour draw
     * @param x Position de la salle en x
     * @param y Position de la salle en y
     */
    public void draw(SpriteBatch batch, float x, float y){
        //Animation de la mort
        if(mort && !mortAnimFinie){
            batch.draw(animationMort.getFrame(false, false), x + getPosition().x, y + getPosition().y, 1, 1);
        }
    }

    /**
     * Methode permettant de mettre à jour physiquement l'entite
     */
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
