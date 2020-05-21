package fr.projetstage.models.monde;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import fr.projetstage.models.Entite;
import fr.projetstage.models.Orientation;
import fr.projetstage.models.chargement.Chargement;
import fr.projetstage.models.entites.joueur.Joueur;
import fr.projetstage.models.entites.ennemis.Ennemi;
import fr.projetstage.models.monde.salle.EtatSalle;
import fr.projetstage.models.monde.salle.Salle;
import fr.projetstage.view.GameScreen;

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

    private boolean aFaitlaMiTransi;
    private fr.projetstage.models.chargement.Chargement chargement;
    private float xSalleSuivante;
    private float ySalleSuivante;

    /**
     * Classe qui s'occupe de l'affichage de l'environnement
     * @param seed le graine de génération du random
     */
    public GameWorld(int seed, GameScreen gameScreen){
        estEnTransition = false;
        aFaitlaMiTransi = false;
        xSalleSuivante = 0f;
        ySalleSuivante = 0f;

        chargement = new fr.projetstage.models.chargement.Chargement(this);

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
     */
    public void draw(SpriteBatch listeAffImg){
        salleCourante.draw(listeAffImg, 0, 0);
        if(estEnTransition){
            if(chargement.getDirectionTransition() == Orientation.NO_ORIENTATION){
                //chargement.draw(listeAffImg);
            }else{
                salleSuivante.draw(listeAffImg, xSalleSuivante, ySalleSuivante);
            }
        }else{
            joueur.draw(listeAffImg, 0, 0);
        }
    }

    /**
     * Permet de prévenir au monde que l'on vas changer de salle (en fonction de la direciton
     * @return le décalage à faire pour la caméra
     */
    public Vector2 getTransition(){
        Vector2 decalage = new Vector2(0, 0);
        Vector2 emplacementJoueur = new Vector2();
        switch(chargement.getDirectionTransition()){
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

        xSalleSuivante = decalage.x;
        ySalleSuivante = decalage.y;

        joueur.setPosition(emplacementJoueur);
        return decalage;
    }

    /**
     * Permet de prévenir au monde que la transition est terminée
     */
    public void finTransition() {
        estEnTransition = false;
        aFaitlaMiTransi = false;
        if(chargement.getDirectionTransition() != Orientation.NO_ORIENTATION){
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
        if(estEnTransition){
            chargement.update();
        }else{
            salleCourante.update();
        }
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
        estEnTransition = true;
        Orientation directionTransition = Orientation.getFromIndice(id);
        chargement.setDirectionTransition(directionTransition);
        if(directionTransition != Orientation.NO_ORIENTATION){
            salleSuivante = etage.next(directionTransition);
        }
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

    public Chargement getChargement() {
        return chargement;
    }

    public Ennemi getPiege(int id) {
        return salleCourante.getPiege(id);
    }
}
