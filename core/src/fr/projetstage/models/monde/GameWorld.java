package fr.projetstage.models.monde;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import fr.projetstage.models.Entite;
import fr.projetstage.models.Orientation;
import fr.projetstage.models.entites.joueur.Joueur;
import fr.projetstage.models.entites.ennemis.Ennemi;
import fr.projetstage.models.monde.salle.Salle;

import java.util.Random;

public class GameWorld {
    private final World world;

    private Salle salleSuivante; // temporaire definit une salle statique
    private Salle salleCourante; // temporaire definit une salle statique
    private Etage etage;
    private final Joueur joueur;
    private final Random random;

    private boolean estEnTransition;

    /**
     * Classe qui s'occupe de l'affichage de l'environnement
     * @param seed le graine de génération du random
     */
    public GameWorld(int seed){
        estEnTransition = false;
        // monde physique qui va gerer les collisions
        world = new World(new Vector2(0,0),true);
        // seed
        random = new Random(seed);

        etage = new Etage(this);

        // in game elements
        salleCourante = etage.start();
        salleCourante.generateBodies();
        joueur = new Joueur(this, new Vector2(4, 4));
        this.world.setContactListener(new EcouteurContact(this));
    }

    /**
     * Permet d'afficher le monde
     * @param listeAffImg liste d'affichage pour draw
     * @param x position x de la salle
     * @param y position y de la salle
     */
    public void draw(SpriteBatch listeAffImg, float x, float y){
        if(estEnTransition){
            salleSuivante.draw(listeAffImg, x, y);
        }
        salleCourante.draw(listeAffImg, 0, 0);
        joueur.draw(listeAffImg, 0, 0);
    }

    /**
     * Permet de prévenir au monde que l'on vas changer de salle (en fonction de la direciton
     * @param direction direction de la transition (en fonction de la salle courante)
     */
    public void debutTransition(Orientation direction){
        if(!estEnTransition){
            salleSuivante = etage.next();
            estEnTransition = true;
        }
    }

    /**
     * Permet de prévenir au monde que la transition est terminée
     */
    public void finTransition() {
        estEnTransition = false;
        salleCourante.destroyBodies();
        salleCourante = salleSuivante;
        salleSuivante.generateBodies();
    }

    /**
     * permet de recuperer la largeur du monde
     * @return un entier de la largeur du monde
     */
    public int getLargeur() {
        return salleCourante.getLargeur() + 4;
    }

    /**
     * permet de recuperer la hauteur du monde
     * @return un entier de la hauteur du monde
     */
    public int getHauteur() {
        return salleCourante.getHauteur() + 4;
    }

    /**
     * Permet de recuperer le monde physique
     * @return un GameWorld de libgdx
     */
    public World getWorld() {
        return world;
    }

    /**
     * Permet de récupérer le joueur dans le monde
     * @return le joueur
     */
    public Joueur getJoueur() {
        return joueur;
    }

    /**
     * Fonction qui retourne le prochain entier aléatoire selon la seed du monde
     * @return un entier aléatoire.
     */
    public int getNextRandom(){
        return random.nextInt();
    }

    /**
     * Met à jour la physique du monde
     */
    public void update() {
        salleCourante.update();
    }

    /**
     * Définit l'ennemi avec l'idMonstre en tant que touché par la source
     * @param idMonstre l'id du monstre touché
     * @param source la source du "touchage"
     */
    public void setEnnemiTouche(int idMonstre, Entite source) {
        salleCourante.setEnnemiTouche(idMonstre, source);
    }

    /**
     * Permet de récupérer l'ennemi en fonction de son id
     * @param id id de l'ennemi
     * @return l'ennemi id
     */
    public Ennemi getEnnemi(int id){
        return salleCourante.getEnnemi(id);
    }


    public void setJoueurTouche(Entite source){
        joueur.setTouche(source);
    }

    /**
     * Permet de définir un item id comme étant pris par le joueur
     * @param id l'id de l'item récupéré
     */
    public void setPickUpTaken(int id) {
        salleCourante.setPickUpTaken(id);
    }


}
