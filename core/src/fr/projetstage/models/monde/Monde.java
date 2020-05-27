package fr.projetstage.models.monde;

import fr.projetstage.models.monde.salle.patternSalle.fichiers.GenerateurSalle;

import java.util.ArrayList;

public class Monde {

    private ArrayList<Etage> etages;
    private GameWorld world;
    private int courant;

    /**
     * Constructeur du monde du jeu.
     * @param world le monde de jeu dans leuqel ajouter les étages / salles
     */
    public Monde(GameWorld world){
        this.world = world;
        etages = new ArrayList<>();
        courant = 0;
        generationMonde();
    }

    private void generationMonde() {
        GenerateurSalle generateurSalle = new GenerateurSalle(world);
        etages.add(new Etage(world, generateurSalle));
        etages.add(new Etage(world, generateurSalle));
        etages.add(new Etage(world, generateurSalle));
    }

    /**
     * Methode permettant de récuperer l'étage suivant du monde
     * @return un Etage suivant
     */
    public Etage getEtageSuivant(){
        if(courant >= etages.size()){
            return etages.get(etages.size()-1);
        }
        return etages.get(courant++);
    }
}
