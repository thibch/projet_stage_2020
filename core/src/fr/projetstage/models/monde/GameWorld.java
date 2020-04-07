package fr.projetstage.models.monde;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import fr.projetstage.models.entites.Joueur;
import fr.projetstage.models.monde.salle.Salle;

public class GameWorld {
    private World world;

    private Salle salleCourante; //temporaire definit une salle statique
    private Joueur joueur;

    /**
     * Classe qui s'occupe de l'affichage de l'environnement
     */
    public GameWorld(){
        //monde physique qui va gerer les collisions
        world = new World(new Vector2(0,0),true);
        world.setGravity(new Vector2(0,0));

        salleCourante = new Salle(this);
        joueur = new Joueur(new Vector2(5, 5),this);
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
        return salleCourante.getLargeur();
    }

    /**
     * permet de recuperer la hauteur du monde
     * @return un entier de la hauteur du monde
     */
    public int getHauteur() {
        return salleCourante.getHauteur();
    }

    /**
     * Permet de recuperer le monde physique
     * @return un GameWorld de libgdx
     */
    public World getWorld() {
        return world;
    }

    public Joueur getJoueur() {
        return joueur;
    }
}
