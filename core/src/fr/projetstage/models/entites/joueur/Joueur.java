package fr.projetstage.models.entites.joueur;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Animation;
import fr.projetstage.models.CollisionFilter;
import fr.projetstage.models.entites.EntiteMouvante;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.entites.TypeEntite;
import fr.projetstage.models.entites.attaques.*;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.Orientation;

import java.util.ArrayList;

public class Joueur extends EntiteMouvante {

    private Inventaire inventaire;

    private Animation idleAnimation;
    private Animation runningAnimation;
    private Orientation lastDirection;

    private CorpsACorps attaqueCaC;
    private AttaqueDistance attaqueDistance;

    private ArrayList<Projectile> projectiles;

    private boolean utiliseEpee;
    private boolean attaqueMaintenant;

    private float hauteur;
    private float largeur;

    /**
     * Constructeur du joueur,
     * Met en place son body (Qui est un rectangle de 8 par 6 pixel)
     * @param position position au départ
     * @param world le monde où il se trouve
     */
    public Joueur(GameWorld world, Vector2 position){
        super(world, position);
        // stats:
        setPointdeVieMax(6);
        setPointDeVie(6);
        setDegats(1);
        setSpeed(1f);

        coolDownTimeInvincible = 1f;

        inventaire = new Inventaire();

        hauteur = (6f/16f);
        largeur = (8f/16f);

        generateBody();
        // On met en place l'attaque à distance
        attaqueDistance = new AttaqueDistance(world, new FlecheFactory(world,12f/16f, 5f/16f), 1f);
        projectiles = new ArrayList<>();

        // On met en place l'attaque au corps à corps
        attaqueCaC = new CorpsACorps(world, body, 1, 0.1f,1,0.25f);
        setWeapon(false);

        attaqueMaintenant = false;

        lastDirection = Orientation.NO_ORIENTATION;
        // creer les animations
        idleAnimation = new Animation(TextureFactory.getInstance().getJoueurIdleSpriteSheet(),6,0.8f);
        runningAnimation = new Animation(TextureFactory.getInstance().getJoueurRunningSpriteSheet(),6,0.8f);
    }

    /**
     * On met a jour la direction du joueur, donc de l'attaque
     * @param direction direction de l'attaque
     */
    public void update(Orientation direction) {
        this.update();

        // Si il est en train d'attaquer et qu'on a dépassé l'animation alors on remet les animations à zéros et on arrête d'attaquer
        attaqueMaintenant = attaqueCaC.isRunning();
        if(attaqueCaC.isRunning()){
            attaqueCaC.slash();
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
                }else{
                    attaqueDistance.charge(getPosition(), direction);
                }

                // On met en place le cooldown
                if(!attaqueDistance.isChargingAndHaveMunitions()){
                    attaqueMaintenant = true;
                    onCoolDown = true;
                }
            }
        }else{
            //Quand on lache la direction on lache la flèche de l'arc
            if(attaqueDistance.isChargingAndHaveMunitions()){
                projectiles.add(attaqueDistance.attaqueDistance(new Vector2(getPosition().x, getPosition().y), lastDirection, projectiles.size()));
                attaqueMaintenant = true;
                onCoolDown = true;
            }
        }
    }

    /**
     * Déplace le Joueur en direction de la force
     * Le déplacement n'est pas linéaire
     * @param deplacement vecteur de déplacement du joueur
     */
    public void move(Vector2 deplacement){
        body.setLinearVelocity(new Vector2((deplacement.x * getSpeed()) + 0.65f * body.getLinearVelocity().x,(deplacement.y * getSpeed()) + 0.65f * body.getLinearVelocity().y));
    }


    @Override
    public void draw(SpriteBatch listeAffImg, float x, float y) {

        // Si on est proche de l'arret (0 déplacement du joueur)
        // Alors on met à jour l'animation et on l'affiche
        if(body.getLinearVelocity().isZero(0.1f)){
            idleAnimation.update();
            listeAffImg.draw(idleAnimation.getFrameFlipX(lastDirection == Orientation.GAUCHE), x + getPosition().x, y + getPosition().y, 1, 1);
        }// Sinon on met à jour l'animation du run (en faisant attention si on est sur la gauche ou droite)
        else{
            runningAnimation.update();
            listeAffImg.draw(runningAnimation.getFrameFlipX(lastDirection == Orientation.GAUCHE), x + getPosition().x, y + getPosition().y, 1, 1);
        }

        if(attaqueDistance.isChargingAndHaveMunitions()){
            attaqueDistance.draw(listeAffImg, x, y);
        }

        // On affiche toute les flèches
        for(Projectile fleche: projectiles){
            fleche.draw(listeAffImg, x, y);
        }

        // Demande l'animation de l'épée
        if(attaqueCaC.isRunning()){
            attaqueCaC.draw(listeAffImg, x, y);
        }

    }

    @Override
    public void generateBody() {
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
        //filtre collision
        fixtureDef1.filter.categoryBits = CollisionFilter.JOUEUR.getCategory();


        // Met en place la fixture sur le body
        body.setFixedRotation(true);
        body.createFixture(fixtureDef1); // Association à l’objet
        body.setUserData(new Type(TypeEntite.JOUEUR, 0));

        rectangle.dispose();
    }

    @Override
    public void destroyBody() {
        world.getWorld().destroyBody(body);
    }

    /**
     * Change d'arme, si le boolean est à vrai alors on passe à l'arc sinon on passe à l'épée
     * @param switchWeapon le booleen qui permet de passer de l'un à l'autre
     */
    public void setWeapon(boolean switchWeapon) {
        utiliseEpee = !switchWeapon && !attaqueDistance.isChargingAndHaveMunitions();
        if(utiliseEpee){
            coolDownTime = 1f;
        }else{
            coolDownTime = 1.2f;
        }
    }

    /**
     * Retourne l'inventaire du joueur
     * @return l'inventaire du joueur
     */
    public Inventaire getInventaire() {
        return inventaire;
    }

    /**
     * Retourne le nombre de munitions du joueur
     * @return les munitions du joueur
     */
    public int getMunition(){
        return this.attaqueDistance.getMunition();
    }

    /**
     * Ajoute un certain nombre de munition au joueur
     * @param nbMun le nombre de munition à ajouter
     */
    public void addMunition(int nbMun) {
        this.attaqueDistance.addMunition(nbMun);
    }

    /**
     * Permet de connaitre si le joueur utilise l'arc ou l'épée
     * @return le booleen si le joueur utilise l'arc ou l'épée
     */
    public boolean isSwitchedWeapon() {
        return utiliseEpee;
    }

    /**
     * Permet de changer la position du joueur
     * @param newPos la nouvelle position du joueur
     */
    public void setPosition(Vector2 newPos) {
        position = newPos;
        body.setTransform(newPos, 0);
    }
}
