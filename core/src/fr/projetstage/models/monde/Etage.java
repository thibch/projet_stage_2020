package fr.projetstage.models.monde;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.reflect.ArrayReflection;
import fr.projetstage.models.Orientation;
import fr.projetstage.models.monde.salle.EtatSalle;
import fr.projetstage.models.monde.salle.Salle;
import fr.projetstage.models.monde.salle.patternSalle.*;
import fr.projetstage.models.monde.salle.patternSalle.fichiers.GenerateurSalle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
        xCourant = Math.abs(world.getNextRandom()%(largeur-2)) +1;
        yCourant = Math.abs(world.getNextRandom()%(hauteur-2)) +1;

        int nbSalleTotal = 15;

        int nbSalleCoffre = 1;

        generationEtage(generateur, nbSalleTotal, nbSalleCoffre);

        tabSalles[xCourant][yCourant].setEtat(EtatSalle.EN_COURS_DE_VISITE);
    }

    /**
     * Methode qui genere l'étage en positionnant des salles
     */
    public void generationEtage(GenerateurSalle generateur, int nbSalleTotal, int nbSalleCoffre){
        assert (nbSalleCoffre > nbSalleTotal-2):"Erreur, il ne pas peut avoir plus de salle avec Coffre que de Salle au total";
        tabSalles[xCourant][yCourant] = new Salle1(world);

        LinkedList<Vector2> queuePosi = new LinkedList<>();
        queuePosi.add(new Vector2(xCourant, yCourant));

        LinkedList<ArrayList<Orientation>> queueOrientation = new LinkedList<>();
        ArrayList<Orientation> arrayOrientation = new ArrayList<>(4);
        arrayOrientation.add(Orientation.HAUT);
        arrayOrientation.add(Orientation.BAS);
        arrayOrientation.add(Orientation.GAUCHE);
        arrayOrientation.add(Orientation.DROITE);
        queueOrientation.add(arrayOrientation);

        //Emplacement boss (sur le bout d'une branche)
        Orientation emplacementboss = arrayOrientation.get(Math.abs(world.getNextRandom()%arrayOrientation.size()));

        Salle nouvelleSalle;
        Vector2 positionCourante;
        int x;
        int y;
        int nbSalle = 0;
        while(!queuePosi.isEmpty() && !queueOrientation.isEmpty() && nbSalle < nbSalleTotal-1){ // Tant que la queue n'est pas vide

            positionCourante = queuePosi.poll(); // On récupère la position de la première salle générée

            x = (int)positionCourante.x;
            y = (int)positionCourante.y;


            arrayOrientation = queueOrientation.poll();

            for (Orientation dir : arrayOrientation) {
                switch(dir){
                    case BAS:
                        if(y-1 >= 0){ // Si on est dans les bornes
                            if(tabSalles[x][y-1] == null){
                                nouvelleSalle = getRandomSalle(generateur); // On génère une nouvelle salle
                                if(nouvelleSalle != null && nouvelleSalle.getEtat() != EtatSalle.NO_SALLE){ // Si la nouvelle salle n'est pas null
                                    tabSalles[x][y-1] = nouvelleSalle; // On ajoute la nouvelle salle au tableau
                                    queuePosi.add(new Vector2(x, y-1)); // On ajoute la position de la nouvelle salle
                                    queueOrientation.add(getRandomSplit(Orientation.BAS, Orientation.GAUCHE, Orientation.DROITE));
                                    nbSalle++;
                                }
                            }
                        }
                        break;
                    case HAUT:
                        if(y+1 < hauteur){ // Si on est dans les bornes
                            if(tabSalles[x][y+1] == null){
                                nouvelleSalle = getRandomSalle(generateur); // On génère une nouvelle salle
                                if(nouvelleSalle != null && nouvelleSalle.getEtat() != EtatSalle.NO_SALLE){ // Si la nouvelle salle n'est pas null
                                    tabSalles[x][y+1] = nouvelleSalle; // On ajoute la nouvelle salle au tableau
                                    queuePosi.add(new Vector2(x, y+1)); // On ajoute la position de la nouvelle salle
                                    queueOrientation.add(getRandomSplit(Orientation.HAUT, Orientation.DROITE, Orientation.GAUCHE));
                                    nbSalle++;
                                }
                            }
                        }
                        break;
                    case GAUCHE:
                        if(x-1 >= 0){ // Si on est dans les bornes
                            if(tabSalles[x-1][y] == null){
                                nouvelleSalle = getRandomSalle(generateur); // On génère une nouvelle salle
                                if(nouvelleSalle != null && nouvelleSalle.getEtat() != EtatSalle.NO_SALLE){ // Si la nouvelle salle n'est pas null
                                    tabSalles[x-1][y] = nouvelleSalle; // On ajoute la nouvelle salle au tableau
                                    queuePosi.add(new Vector2(x-1, y)); // On ajoute la position de la nouvelle salle
                                    queueOrientation.add(getRandomSplit(Orientation.GAUCHE, Orientation.HAUT, Orientation.BAS));
                                    nbSalle++;
                                }
                            }
                        }
                        break;
                    case DROITE:
                        if(x+1 < largeur){ // Si on est dans les bornes
                            if(tabSalles[x+1][y] == null){
                                nouvelleSalle = getRandomSalle(generateur); // On génère une nouvelle salle
                                if(nouvelleSalle != null && nouvelleSalle.getEtat() != EtatSalle.NO_SALLE){ // Si la nouvelle salle n'est pas null
                                    tabSalles[x+1][y] = nouvelleSalle; // On ajoute la nouvelle salle au tableau
                                    queuePosi.add(new Vector2(x+1, y)); // On ajoute la position de la nouvelle salle
                                    queueOrientation.add(getRandomSplit(Orientation.DROITE, Orientation.BAS, Orientation.HAUT));
                                    nbSalle++;
                                }
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        boolean salleBossPlacee = false;

        int xBoss;
        int yBoss;
        switch (emplacementboss){
            case DROITE:
            case GAUCHE:
                xBoss = emplacementboss==Orientation.DROITE?largeur-1:0;
                yBoss = 0;
                while (yBoss < hauteur && !salleBossPlacee) {
                    if(tabSalles[xBoss][yBoss] != null){
                        tabSalles[xBoss][yBoss] = new Salle5(world);
                        salleBossPlacee = true;
                    }
                    yBoss++;
                }
                break;
            case HAUT:
            case BAS:
                yBoss = emplacementboss==Orientation.HAUT?hauteur-1:0;
                xBoss = 0;
                while (xBoss < hauteur && !salleBossPlacee) {
                    if(tabSalles[xBoss][yBoss] != null){
                        tabSalles[xBoss][yBoss] = new Salle5(world);
                        salleBossPlacee = true;
                    }
                    xBoss++;
                }
                break;
        }
        int xSalle;
        int ySalle;
        xSalle = Math.abs(world.getNextRandom()%largeur);
        ySalle = Math.abs(world.getNextRandom()%hauteur);
        if(!salleBossPlacee){
            while(tabSalles[xSalle][ySalle] == null || tabSalles[xSalle][ySalle] != null && tabSalles[xSalle][ySalle].getType() != TypeSalle.NO_TYPE){
                xSalle = Math.abs(world.getNextRandom()%largeur);
                ySalle = Math.abs(world.getNextRandom()%hauteur);
            }
            tabSalles[xSalle][ySalle] = new Salle5(world);
            salleBossPlacee = true;
        }

        for (int i = 0; i < nbSalleCoffre; i++) {
            while(tabSalles[xSalle][ySalle] == null || tabSalles[xSalle][ySalle] != null && tabSalles[xSalle][ySalle].getType() != TypeSalle.NO_TYPE){
                xSalle = Math.abs(world.getNextRandom()%largeur);
                ySalle = Math.abs(world.getNextRandom()%hauteur);
            }

            tabSalles[xSalle][ySalle] = generateur.genererSalle(true);
        }

        generationPortes();
        generationSalles();
    }

    private ArrayList<Orientation> getRandomSplit(Orientation mainOrientation, Orientation possibleOrientation, Orientation otherPossibleOrientation){
        ArrayList<Orientation> orientations = new ArrayList<>();
        int rand = Math.abs(world.getNextRandom()%100);
        if(rand <= 70){
            orientations.add(mainOrientation);
        }else if(rand <= 80){
            orientations.add(mainOrientation);
            orientations.add(possibleOrientation);
        }else if (rand <= 90) {
            orientations.add(mainOrientation);
            orientations.add(otherPossibleOrientation);
        }else{
            orientations.add(mainOrientation);
            orientations.add(possibleOrientation);
            orientations.add(otherPossibleOrientation);
        }
        return orientations;
    }

    /**
     * methode qui recupere une salle aléatoire dans l'asset
     * @return une salle choisie aléatoirement
     */
    public Salle getRandomSalle(GenerateurSalle generateur){
        return generateur.genererSalle(false);
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
        }else if(direction == Orientation.BAS){
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
    public Salle[][] getMinimap(){
        Salle[][] tmp = new Salle[largeur][hauteur];

        for(int x = 0; x < 5; x++) {
            if(xCourant-2+x >= 0 && xCourant-2+x < largeur){
                for (int y = 0; y < 5; y++) {
                    if(yCourant-2+y >= 0 && yCourant-2+y < hauteur){
                        if(tabSalles[xCourant-2+x][yCourant-2+y] != null){
                            tmp[x][y] = tabSalles[xCourant-2+x][yCourant-2+y];
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
                    str.append(tabSalles[x][y].getType().getId()).append(" ");
                }else{
                    str.append("  ");
                }
            }
            str.append("\n");
        }
        return str.toString();
    }
}
