package fr.projetstage.models.monde;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import fr.projetstage.models.entites.Joueur;
import fr.projetstage.models.entites.ennemis.Ennemi;
import fr.projetstage.models.monde.salle.Salle;

import java.util.Iterator;
import java.util.Random;

public class GameWorld {
    private final World world;

    private final Salle salleCourante; // temporaire definit une salle statique
    private final Joueur joueur;
    private final Random random;

    /**
     * Classe qui s'occupe de l'affichage de l'environnement
     */
    public GameWorld(){
        // monde physique qui va gerer les collisions
        world = new World(new Vector2(0,0),true);
        // seed
        random = new Random();
        // in game elements
        salleCourante = new Salle(this,16,10);
        joueur = new Joueur(this, new Vector2(0, 0));
        this.world.setContactListener(new EcouteurContact(this));
    }

    /**
     * Indique ce qu'il faut dessiner dans le monde
     * @param listeAffImg la liste d'affichage
     */
    public void draw(SpriteBatch listeAffImg){
        salleCourante.draw(listeAffImg);
        joueur.draw(listeAffImg);
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


    public Iterator<Ennemi> iteratorEnnemi(){
        return salleCourante.iterator();
    }

    public void update() {
        salleCourante.update();
    }

    public void setEnnemiTouche(boolean touche, int id) {
        salleCourante.setEnnemiTouche(touche, id);
    }
}
