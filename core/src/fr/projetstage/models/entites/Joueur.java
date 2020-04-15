package fr.projetstage.models.entites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Animation;
import fr.projetstage.models.entites.attaques.AttaqueDistance;
import fr.projetstage.models.entites.attaques.CorpsACorps;
import fr.projetstage.models.entites.attaques.Fleche;
import fr.projetstage.models.entites.objets.Objet;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.Orientation;

import java.util.ArrayList;

public class Joueur extends EntiteMouvante {

    private ArrayList<Objet> inventaire;

    private Animation idleAnimation;
    private Animation runningAnimation;
    private Orientation lastDirection;
    private Orientation direction;

    private CorpsACorps attaqueCaC;
    private AttaqueDistance attaqueDistance;

    private ArrayList<Fleche> projectiles;

    private boolean utiliseEpee;
    private boolean attaqueMaintenant;
    private boolean onCoolDown;
    private float coolDownTime;

    private float currentTime;

    /**
     * Constructeur du joueur,
     * Met en place son body (Qui est un rectangle de 8 par 6 pixel)
     * @param position position au départ
     * @param world le monde où il se trouve
     */
    public Joueur(GameWorld world, Vector2 position){
        // stats:
        setPointDeVie(6);
        setPointdeVieMax(6);
        setDegats(1);

        float hauteur = (6f/16f);
        float largeur = (8f/16f);

        // BodyDef
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position);
        //

        // Récupération du body dans le world
        body = world.getWorld().createBody(bodyDef);

        // Création de la shape pour le héros
        Vector2 posShape = new Vector2(5f/16f, 1f/16f); // La position du shape est en fonction de la position du body
        Vector2[] vertices = new Vector2[4];
        vertices[0] = posShape;
        vertices[1] = new Vector2(posShape.x + largeur, posShape.y);
        vertices[2] = new Vector2(posShape.x + largeur, posShape.y + hauteur);
        vertices[3] = new Vector2(posShape.x, posShape.y + hauteur);

        PolygonShape rectangle = new PolygonShape();
        rectangle.set(vertices);

        // FixtureDef
        FixtureDef fixtureDef1 = new FixtureDef();
        fixtureDef1.shape = rectangle;
        fixtureDef1.density = 1f;
        fixtureDef1.restitution = 0f;
        fixtureDef1.friction = 0f;
        //

        // Met en place la fixture sur le body
        body.setFixedRotation(true);
        body.createFixture(fixtureDef1); // Association à l’objet
        body.setUserData(new Type(TypeEntite.JOUEUR, 0));

        rectangle.dispose();

        // On met en place l'attaque à distance
        attaqueDistance = new AttaqueDistance(world, 12f/16f, 5f/16f, 1f);
        projectiles = new ArrayList<>();

        // On met en place l'attaque au corps à corps
        attaqueCaC = new CorpsACorps(world, body, 1, 0.1f,1,0.25f);
        setWeapon(false);

        onCoolDown = false;
        attaqueMaintenant = false;
        currentTime = 0f;

        // creer les animations
        direction = Orientation.NO_ORIENTATION;
        lastDirection = Orientation.NO_ORIENTATION;
        idleAnimation = new Animation(TextureFactory.getInstance().getJoueurIdleSpriteSheet(),6,0.8f);
        runningAnimation = new Animation(TextureFactory.getInstance().getJoueurRunningSpriteSheet(),6,0.8f);
    }

    /**
     * On met a jour la direction du joueur, donc de l'attaque
     * @param direction direction de l'attaque
     */
    @Override
    public void update(Orientation direction) {
        currentTime += Gdx.graphics.getDeltaTime();

        // Si il est en train d'attaquer et qu'on a dépassé l'animation alors on remet les animations à zéros et on arrête d'attaquer
        if(attaqueMaintenant && currentTime > attaqueCaC.getDuration()){
            attaqueMaintenant = false;
        }

        // Si on est en coolDown mais que le temps est dépassé alors nous ne sommes plus en cooldown
        if(onCoolDown && currentTime > coolDownTime) {
            currentTime = 0;
            onCoolDown = false;
        }

        // Si on veut aller dans une direction
        if(direction != Orientation.NO_ORIENTATION){
            // Si on a finit d'attaquer on change la direction
            if(!attaqueMaintenant){
                lastDirection = direction;
            }
            // Si on est plus en cooldown ça veut dire que le joueur veut attaquer
            if(!onCoolDown){
                lastDirection = direction;
                currentTime = 0;

                // On lance une attaque
                if(utiliseEpee){
                    attaqueCaC.attaque(body, direction);
                }
                else{
                    attaqueDistance.charge(body.getPosition(), direction);
                }

                // On met en place le cooldown
                if(!attaqueDistance.isCharging()){
                    attaqueMaintenant = true;
                    onCoolDown = true;
                }
            }
        }else{
            //Qaund on lache la direction on lache la flèche de l'arc
            if(attaqueDistance.isCharging()){
                projectiles.add(attaqueDistance.attaqueDistance(new Vector2(getX(), getY()), lastDirection, projectiles.size()));
                attaqueMaintenant = true;
                onCoolDown = true;
            }
        }

        if(attaqueCaC.isRunning()){
            attaqueCaC.slash();
        }
    }

    /**
     * Getter sur la position x
     * @return la position x du Joueur
     */
    public float getX(){
        return body.getPosition().x;
    }

    /**
     * Getter sur la position y
     * @return la position y du Joueur
     */
    public float getY(){
        return body.getPosition().y;
    }

    /**
     * Déplace le Joueur en direction de la force
     * Le déplacement n'est pas linéaire
     * @param deplacement vecteur de déplacement du joueur
     */
    public void move(Vector2 deplacement){
        body.setLinearVelocity(new Vector2(deplacement.x + 0.65f * body.getLinearVelocity().x,deplacement.y + 0.65f * body.getLinearVelocity().y));
    }


    @Override
    public void draw(SpriteBatch listeAffImg) {


        // Si on est proche de l'arret (0 déplacement du joueur)
        // Alors on met à jour l'animation et on l'affiche
        if(body.getLinearVelocity().isZero(0.1f)){
            idleAnimation.update();
            listeAffImg.draw(idleAnimation.getFrameFlipX(lastDirection == Orientation.GAUCHE), getX(), getY(), 1, 1);
        }// Sinon on met à jour l'animation du run (en faisant attention si on est sur la gauche ou droite)
        else{
            runningAnimation.update();
            listeAffImg.draw(runningAnimation.getFrameFlipX(lastDirection == Orientation.GAUCHE), getX(), getY(), 1, 1);
        }

        if(attaqueDistance.isCharging()){
            attaqueDistance.draw(listeAffImg);
        }

        // On affiche toute les flèches
        for(Fleche fleche: projectiles){
            fleche.draw(listeAffImg);
        }

        // Demande l'animation de l'épée
        if(attaqueCaC.isRunning()){
            attaqueCaC.draw(listeAffImg);
        }

    }

    public boolean isAttacking() {
        return attaqueMaintenant;
    }

    public Orientation getOrientation() {
        return lastDirection;
    }

    public void setWeapon(boolean switchWeapon) {
        utiliseEpee = !switchWeapon;
        if(utiliseEpee){
            coolDownTime = 1f;
        }else{
            coolDownTime = 1.2f;
        }
    }
}
