package fr.projetstage.models.monde;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import fr.projetstage.models.Entite;
import fr.projetstage.models.Orientation;
import fr.projetstage.models.entites.joueur.Joueur;
import fr.projetstage.models.entites.ennemis.Ennemi;
import fr.projetstage.models.monde.salle.EtatSalle;
import fr.projetstage.models.monde.salle.Salle;

import java.util.Random;

public class GameWorld {
    private final World world;

    private Salle salleSuivante; // temporaire definit une salle statique
    private Salle salleCourante; // temporaire definit une salle statique
    private Etage etage;
    private Monde monde;
    private final Joueur joueur;
    private final Random random;

    private boolean estEnTransition;
    private Orientation directionTransition;
    private boolean changementEtage;

    private boolean aFaitlaMiTransi;

    /**
     * Classe qui s'occupe de l'affichage de l'environnement
     * @param seed le graine de génération du random
     */
    public GameWorld(int seed){
        estEnTransition = false;
        aFaitlaMiTransi = false;
        // monde physique qui va gerer les collisions
        world = new World(new Vector2(0,0),true);
        // seed
        random = new Random(seed);
        monde = new Monde(this);
        etage = monde.getEtageSuivant();

        // in game elements
        salleCourante = etage.start();
        salleCourante.generateBodies();
        joueur = new Joueur(this, new Vector2(4, 4));
        this.world.setContactListener(new EcouteurContact(this));
        System.out.println(etage.toString());
    }

    /**
     * Permet d'afficher le monde
     * @param listeAffImg liste d'affichage pour draw
     * @param x position x de la salle
     * @param y position y de la salle
     */
    public void draw(SpriteBatch listeAffImg, float x, float y){
        salleCourante.draw(listeAffImg, 0, 0);
        if(estEnTransition && directionTransition != Orientation.NO_ORIENTATION){
            salleSuivante.draw(listeAffImg, x, y);
        }else{
            joueur.draw(listeAffImg, 0, 0);
        }
    }

    /**
     * Permet de prévenir au monde que l'on vas changer de salle (en fonction de la direciton
     * @return le décalage à faire pour la caméra
     */
    public Vector2 transition(){
        Vector2 decalage = new Vector2(0, 0);
        Vector2 emplacementJoueur = new Vector2();
        switch(directionTransition){
            case DROITE:
                emplacementJoueur.x = 0;
                emplacementJoueur.y = salleCourante.getHauteur()/2f;
                decalage.x = salleCourante.getLargeur() + 4;
                break;
            case GAUCHE:
                emplacementJoueur.x = salleSuivante.getLargeur()-1;
                emplacementJoueur.y = salleCourante.getHauteur()/2f;
                decalage.x = - salleSuivante.getLargeur() - 4;
                break;
            case HAUT:
                emplacementJoueur.x = salleSuivante.getLargeur()/2f;
                emplacementJoueur.y = 0;
                decalage.y = salleCourante.getHauteur() + 4;
                break;
            case BAS:
                emplacementJoueur.x = salleSuivante.getLargeur()/2f;
                emplacementJoueur.y = salleCourante.getHauteur()-1;
                decalage.y = - salleSuivante.getHauteur() - 4 ;
                break;
            case NO_ORIENTATION:
                emplacementJoueur = joueur.getPosition();
                break;
        }
        joueur.setPosition(emplacementJoueur);
        return decalage;
    }

    /**
     * Permet de prévenir au monde que la transition est terminée
     */
    public void finTransition() {
        estEnTransition = false;
        aFaitlaMiTransi = false;
        if(directionTransition != Orientation.NO_ORIENTATION){
            salleCourante.destroyBodies();
            salleCourante = salleSuivante;
            salleSuivante.generateBodies();
        }
    }


    public void miTransition(){
        // Changement étage
        if(!aFaitlaMiTransi){
            etage = monde.getEtageSuivant();
            System.out.println(etage.toString());
            salleCourante.destroyBodies();
            salleCourante = etage.start();
            salleCourante.generateBodies();
            joueur.setPosition(new Vector2(salleCourante.getLargeur()/2f, salleCourante.getHauteur()/2f));
            aFaitlaMiTransi = true;
        }
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
        if(changementEtage){
            directionTransition = Orientation.NO_ORIENTATION;
            //transition();
            changementEtage = false;
            estEnTransition = true;
        }
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

    /**
     * Methode qui permet de faire des degats au joueur
     * @param source la source des degats
     */
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


    /**
     * Lorsque le joueur touche une porte
     * @param id l'id de la porte
     */
    public void setPorteTouched(int id) {
        //TODO : se servir de l'id/Orientation
        if(id == Orientation.NO_ORIENTATION.getIndice()){
            changementEtage = true;
            directionTransition = Orientation.NO_ORIENTATION;
        }else{
            if(!estEnTransition){
                estEnTransition = true;
                directionTransition = Orientation.getFromIndice(id);
                salleSuivante = etage.next(directionTransition);
            }
        }
        //this.debutTransition(Orientation.HAUT);
    }

    /**
     * booleen qui indique si le monde est en transition entre 2 salles
     * @return vrai si le monde est en transition
     */
    public boolean estEnTransition() {
        return estEnTransition;
    }

    /**
     * Permet de recuperer la minimap de l'étage dans l'UI
     * @return un tableau de l'état des salles de l'étage
     */
    public EtatSalle[][] getMinimap() {
        return etage.getMinimap();
    }
}
