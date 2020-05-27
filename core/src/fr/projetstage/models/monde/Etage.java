package fr.projetstage.models.monde;

import com.badlogic.gdx.math.Vector2;
import fr.projetstage.models.Orientation;
import fr.projetstage.models.monde.salle.EtatSalle;
import fr.projetstage.models.monde.salle.Salle;
import fr.projetstage.models.monde.salle.patternSalle.*;
import fr.projetstage.models.monde.salle.patternSalle.fichiers.GenerateurSalle;

import java.util.Arrays;
import java.util.LinkedList;

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
    public Etage(GameWorld world, GenerateurSalle generateur){
        this.world = world;
        largeur = 5;
        hauteur = 5;
        tabSalles = new Salle[largeur][hauteur];
        xCourant = 2;
        yCourant = 3;

        generationEtage(generateur);

        tabSalles[xCourant][yCourant].setEtat(EtatSalle.EN_COURS_DE_VISITE);
    }

    /**
     * Methode qui genere l'étage en positionnant des salles
     */
    public void generationEtage(GenerateurSalle generateur){
        tabSalles[xCourant][yCourant] = new Salle1(world);

        LinkedList<Vector2> queuePosi = new LinkedList<>();
        queuePosi.add(new Vector2(xCourant, yCourant));

        Salle nouvelleSalle;
        Vector2 positionCourante;
        int x;
        int y;
        while(!queuePosi.isEmpty()){ // Tant que la queue n'est pas vide

            positionCourante = queuePosi.poll(); // On récupère la position de la première salle générée

            x = (int)positionCourante.x;
            y = (int)positionCourante.y;

            if(x+1 < largeur && tabSalles[x+1][y] == null){ // Si on est dans les bornes
                nouvelleSalle = getRandomSalle(generateur); // On génère une nouvelle salle
                if(nouvelleSalle != null){ // Si la nouvelle salle n'est pas null
                    tabSalles[x+1][y] = nouvelleSalle; // On ajoute la nouvelle salle au tableau
                    queuePosi.add(new Vector2(x+1, y)); // On ajoute la position de la nouvelle salle
                }
            }

            // Même chose pour les 3 autres directions

            if(x-1 >= 0 && tabSalles[x-1][y] == null){ // Si on est dans les bornes
                nouvelleSalle = getRandomSalle(generateur); // On génère une nouvelle salle
                if(nouvelleSalle != null){ // Si la nouvelle salle n'est pas null
                    tabSalles[x-1][y] = nouvelleSalle; // On ajoute la nouvelle salle au tableau
                    queuePosi.add(new Vector2(x-1, y)); // On ajoute la position de la nouvelle salle
                }
            }
            if(y+1 < hauteur && tabSalles[x][y+1] == null){ // Si on est dans les bornes
                nouvelleSalle = getRandomSalle(generateur); // On génère une nouvelle salle
                if(nouvelleSalle != null){ // Si la nouvelle salle n'est pas null
                    tabSalles[x][y+1] = nouvelleSalle; // On ajoute la nouvelle salle au tableau
                    queuePosi.add(new Vector2(x, y+1)); // On ajoute la position de la nouvelle salle
                }
            }
            if(y-1 >= 0 && tabSalles[x][y-1] == null){ // Si on est dans les bornes
                nouvelleSalle = getRandomSalle(generateur); // On génère une nouvelle salle
                if(nouvelleSalle != null){ // Si la nouvelle salle n'est pas null
                    tabSalles[x][y-1] = nouvelleSalle; // On ajoute la nouvelle salle au tableau
                    queuePosi.add(new Vector2(x, y-1)); // On ajoute la position de la nouvelle salle
                }
            }
        }

        generationPortes();
        generationSalles();
    }

    /**
     * methode qui recupere une salle aléatoire dans l'asset
     * @return une salle choisie aléatoirement
     */
    public Salle getRandomSalle(GenerateurSalle generateur){
        int rand = Math.abs(world.getNextRandom()%100);
        Salle newSalle;
        if(rand <= 20){
            newSalle = null;
        } else newSalle = generateur.genererSalle(false);
        /*
        else if(rand <= 30){
            newSalle = new SalleVide(world);
        }
        else if(rand <= 60){
            newSalle = new Salle2(world);
        }
        else if(rand <= 70){
            newSalle = new Salle3(world);
        }
        else if(rand <= 80){
            newSalle = new Salle4(world);
        }else{
            newSalle = new Salle5(world);
        }*/
        return newSalle;
    }

    /**
     * Methode permettant de relier les salles entre elles dans l'étage
     */
    public void generationPortes(){
        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                if(tabSalles[x][y] != null && tabSalles[x][y].getEtat() != EtatSalle.NO_SALLE){
                    if(x+1 < largeur && tabSalles[x+1][y] != null && tabSalles[x+1][y].getEtat() != EtatSalle.NO_SALLE){
                        tabSalles[x][y].ajouterPorte(Orientation.DROITE);
                    }
                    if(x-1 >= 0 && tabSalles[x-1][y] != null && tabSalles[x-1][y].getEtat() != EtatSalle.NO_SALLE){
                        tabSalles[x][y].ajouterPorte(Orientation.GAUCHE);
                    }
                    if(y+1 < hauteur && tabSalles[x][y+1] != null && tabSalles[x][y+1].getEtat() != EtatSalle.NO_SALLE){
                        tabSalles[x][y].ajouterPorte(Orientation.HAUT);
                    }
                    if(y-1 >= 0 && tabSalles[x][y-1] != null && tabSalles[x][y-1].getEtat() != EtatSalle.NO_SALLE){
                        tabSalles[x][y].ajouterPorte(Orientation.BAS);
                    }
                }
            }
        }
    }

    /**
     * Methode qui genere des salles dans l'étage
     */
    public void generationSalles(){
        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                if(tabSalles[x][y] != null && tabSalles[x][y].getEtat() != EtatSalle.NO_SALLE) tabSalles[x][y].genererSalle();
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
     * @param direction la direction dans laquel le joueur va
     * @return la salle suivante
     */
    public Salle next(Orientation direction){
        //met a jour l'état de l'ancienne salle courante
        tabSalles[xCourant][yCourant].setEtat(EtatSalle.VISITEE);

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
        //definit la nouvelle salle comme courante
        tabSalles[xCourant][yCourant].setEtat(EtatSalle.EN_COURS_DE_VISITE);

        return tabSalles[xCourant][yCourant];
    }

    /**
     * Construit et retourne la minimap de l'étage
     * @return un tableau d'etat de salle
     */
    public EtatSalle[][] getMinimap(){
        EtatSalle[][] tmp = new EtatSalle[5][5];

        for(int x = 0; x < 5; x++) {
            if(xCourant-2+x >= 0 && xCourant-2+x < largeur){
                for (int y = 0; y < 5; y++) {
                    if(yCourant-2+y >= 0 && yCourant-2+y < hauteur){
                        if(tabSalles[xCourant-2+x][yCourant-2+y] != null){
                            tmp[x][y] = tabSalles[xCourant-2+x][yCourant-2+y].getEtat();
                        }
                    }
                }
            }
        }

        return tmp;
    }


    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int y = hauteur - 1 ; y >= 0 ; y--) {
            for (int x = 0; x < largeur; x++) {
                if(tabSalles[x][y] != null){
                    str.append(tabSalles[x][y].getNumber()).append(" ");
                }else{
                    str.append("  ");
                }
            }
            str.append("\n");
        }
        return str.toString();
    }
}
