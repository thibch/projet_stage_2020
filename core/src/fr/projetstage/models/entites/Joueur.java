package fr.projetstage.models.entites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Animation;
import fr.projetstage.models.entites.objets.Objet;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.monde.salle.Orientation;

import java.util.ArrayList;

public class Joueur extends EntiteMouvante {

    private ArrayList<Objet> inventaire;
    private int pointDeVie;
    private int pointdeVieMax;

    private Body body;
    private Animation idleAnimation;
    private Animation runningAnimation;
    private Orientation lastDirection;
    private Orientation direction;

    private Attaque[] attaqueJoueur;

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
    public Joueur(Vector2 position, GameWorld world){
        //stats:
        pointDeVie = 6;
        pointdeVieMax = 6;

        float hauteur = (6f/16f);
        float largeur = (8f/16f);

        //BodyDef
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position);
        //

        //Récupération du body dans le world
        body = world.getWorld().createBody(bodyDef);

        //Création de la shape pour le héros
        Vector2 posShape = new Vector2(5f/16f, 1f/16f); //La position du shape est en fonction de la position du body
        Vector2[] vertices = new Vector2[4];
        vertices[0] = posShape;
        vertices[1] = new Vector2(posShape.x + largeur, posShape.y);
        vertices[2] = new Vector2(posShape.x + largeur, posShape.y + hauteur);
        vertices[3] = new Vector2(posShape.x, posShape.y + hauteur);

        PolygonShape rectangle = new PolygonShape();
        rectangle.set(vertices);

        //FixtureDef
        FixtureDef fixtureDef1 = new FixtureDef();
        fixtureDef1.shape = rectangle;
        fixtureDef1.density = 1f; // Densité de l’objet
        fixtureDef1.restitution = 0f; // Restitution de  l’objet
        fixtureDef1.friction = 0f; // Friction de  l’objet
        //

        //Met en place la fixture sur le body
        body.setFixedRotation(true);
        body.createFixture(fixtureDef1); // Association à l’objet

        rectangle.dispose();

        attaqueJoueur = new Attaque[4]; //GAUCHE, DROITE, HAUT, BAS
        attaqueJoueur[Orientation.GAUCHE.getIndice()] = new Attaque(world, new Vector2(-1f/16f, 0), 6f/16f, 8f/16f);
        attaqueJoueur[Orientation.DROITE.getIndice()] = new Attaque(world, new Vector2(13f/16f, 0), 6f/16f, 8f/16f);
        attaqueJoueur[Orientation.HAUT.getIndice()] = new Attaque(world, new Vector2(5f/16f, 7f/16f), 8f/16f, 6f/16f);
        attaqueJoueur[Orientation.BAS.getIndice()] = new Attaque(world, new Vector2(5f/16f, -5f/16f), 8f/16f, 6f/16f);

        for (Attaque attaque : attaqueJoueur) {
            RevoluteJointDef rjd = new RevoluteJointDef();
            rjd.initialize(body, attaque.getBody(), new Vector2(9f / 16f, 4f / 16f));

            world.getWorld().createJoint(rjd);
        }

        onCoolDown = false;
        attaqueMaintenant = false;
        currentTime = 0f;
        coolDownTime = 0.8f;

        //creer les animations
        direction = Orientation.NO_ORIENTATION;
        lastDirection = Orientation.NO_ORIENTATION;
        idleAnimation = new Animation(TextureFactory.getInstance().getJoueurIdleSpriteSheet(),6,0.8f);
        runningAnimation = new Animation(TextureFactory.getInstance().getJoueurRunningSpriteSheet(),6,0.8f);
    }

    @Override
    public void setDirection(Orientation direction) {
        currentTime += Gdx.graphics.getDeltaTime();
        if(onCoolDown && currentTime > coolDownTime) {
            currentTime = 0;
            onCoolDown = false;
        }
        if(attaqueMaintenant && currentTime > attaqueJoueur[this.lastDirection.getIndice()].getAttaqueTime()-0.1f){
            attaqueMaintenant = false;
            for(Attaque attaque : attaqueJoueur){
                attaque.reset();
            }
        }

        this.direction = direction;

        if(direction != Orientation.NO_ORIENTATION){
            if(!attaqueMaintenant){
                lastDirection = direction;
            }
            if(!onCoolDown){
                currentTime = 0;
                attaqueMaintenant = true;
                onCoolDown = true;
            }
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

        if(attaqueMaintenant){
            attaqueJoueur[lastDirection.getIndice()].drawAnimation(listeAffImg, lastDirection == Orientation.GAUCHE || lastDirection == Orientation.BAS, lastDirection == Orientation.BAS || lastDirection == Orientation.HAUT);
        }

        //Si on est proche de l'arret (0 déplacement du joueur)
        //Alors on met à jour l'animation et on l'affiche
        if(body.getLinearVelocity().isZero(0.1f)){
            idleAnimation.update();
            listeAffImg.draw(idleAnimation.getFrameFlipX(lastDirection == Orientation.GAUCHE), getX(), getY(), 1, 1);
        }//Sinon on met à jour l'animation du run (en faisant attention si on est sur la gauche ou droite)
        else{
            runningAnimation.update();
            listeAffImg.draw(runningAnimation.getFrameFlipX(lastDirection == Orientation.GAUCHE), getX(), getY(), 1, 1);
        }
    }

    public int getPointDeVie() {
        return pointDeVie;
    }

    public int getPointdeVieMax() {
        return pointdeVieMax;
    }
}
