package fr.projetstage.models.monde;

import fr.projetstage.models.Orientation;
import fr.projetstage.models.monde.salle.Salle;
import fr.projetstage.models.monde.salle.patternSalle.Salle1;
import fr.projetstage.models.monde.salle.patternSalle.Salle2;

public class Etage {

    private final GameWorld world;
    private Salle[][] tabSalles;

    private int largeur;
    private int hauteur;
    private int xCourant;
    private int yCourant;

    /**
     * Etage du monde
     * @param world le gameworld
     */
    public Etage(GameWorld world){
        this.world = world;
        largeur = 5;
        hauteur = 5;
        tabSalles = new Salle[largeur][hauteur];
        xCourant = 2;
        yCourant = 3;
        generationEtage();
    }

    public void generationEtage(){
        tabSalles[xCourant][yCourant] = new Salle1(world);
        tabSalles[xCourant+1][yCourant] = new Salle2(world);

        generationPortes();
        generationSalles();
    }

    public void generationPortes(){
        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                if(tabSalles[x][y] != null){
                    if(x+1 < largeur && tabSalles[x+1][y] != null){
                        tabSalles[x][y].ajouterPorte(Orientation.DROITE);
                    }
                    if(x-1 >= 0 && tabSalles[x-1][y] != null){
                        tabSalles[x][y].ajouterPorte(Orientation.GAUCHE);
                    }
                    if(y+1 < hauteur && tabSalles[x][y+1] != null){
                        tabSalles[x][y].ajouterPorte(Orientation.HAUT);
                    }
                    if(y-1 >= 0 && tabSalles[x][y-1] != null){
                        tabSalles[x][y].ajouterPorte(Orientation.BAS);
                    }
                }
            }
        }
    }

    public void generationSalles(){
        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                if(tabSalles[x][y] != null) tabSalles[x][y].genererSalle();
            }
        }

    }
    /**
     * Donne la première salle de l'étage
     * @return la première salle de l'étage
     */
    public Salle start(){
        return tabSalles[xCourant][yCourant];
    }

    /**
     * Retourne la salle suivante
     * @return la salle suivante
     */
    public Salle next(Orientation direction){
        if(direction == Orientation.DROITE){
            xCourant += 1;
        }else if(direction == Orientation.GAUCHE){
            xCourant -= 1;
        }
        if(direction == Orientation.BAS){
            yCourant -= 1;
        }else if(direction == Orientation.HAUT){
            yCourant += 1;
        }
        return tabSalles[xCourant][yCourant];
    }
}
