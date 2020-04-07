package fr.projetstage.models.monde;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import fr.projetstage.models.monde.salle.Salle;

public class GameWorld {
    private static int largeur = 100;
    private static int hauteur = 100;

    private World world;

    private Salle laSalle; //temporaire definit une salle statique

    /**
     * Classe qui s'occupe de l'affichage de l'environnement
     */
    public GameWorld(){
        //monde physique qui va gerer les collisions
        world = new World(new Vector2(0,0),true);

        laSalle = new Salle();
    }

    /**
     * Indique ce qu'il faut dessiner dans le monde
     * @param listeAffImg la liste d'affichage
     */
    public void draw(SpriteBatch listeAffImg){
        laSalle.draw(listeAffImg);
    }

    /**
     * permet de recuperer la largeur du monde
     * @return un entier de la largeur du monde
     */
    public static int getLargeur() {
        return largeur;
    }

    /**
     * permet de recuperer la hauteur du monde
     * @return un entier de la hauteur du monde
     */
    public static int getHauteur() {
        return hauteur;
    }

    /**
     * Permet de recuperer le monde physique
     * @return un GameWorld de libgdx
     */
    public World getWorld() {
        return world;
    }
}
