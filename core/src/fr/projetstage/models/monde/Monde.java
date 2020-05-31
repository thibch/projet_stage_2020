package fr.projetstage.models.monde;

import fr.projetstage.models.monde.salle.patternSalle.fichiers.GenerateurSalle;

import java.util.ArrayList;

public class Monde {

    private final ArrayList<Etage> etages;
    private final GameWorld world;
    private int courant;
    private boolean finMonde;

    /**
     * Constructeur du monde du jeu.
     * @param world le monde de jeu dans leuqel ajouter les étages / salles
     */
    public Monde(GameWorld world){
        this.world = world;
        etages = new ArrayList<>();
        courant = 0;
        finMonde = false;
        generationMonde();
    }

    private void generationMonde() {
        GenerateurSalle generateurSalle = new GenerateurSalle(world);
        etages.add(new Etage(world, 0, generateurSalle));
        etages.add(new Etage(world, 1, generateurSalle));
        etages.add(new Etage(world, 2, generateurSalle));
    }

    public boolean estFinMonde(){
        return finMonde;
    }

    /**
     * Methode permettant de récuperer l'étage suivant du monde
     * @return un Etage suivant
     */
    public Etage getEtageSuivant(){
        if(courant >= etages.size()){
            courant--;
            finMonde = true;
        }
        return etages.get(courant++);
    }
}
