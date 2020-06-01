package fr.projetstage.models.monde;

import fr.projetstage.models.entites.objets.ObjetsTousTypes;
import fr.projetstage.models.monde.salle.patternSalle.fichiers.GenerateurSalle;

import java.util.ArrayList;

public class Monde {

    private final ArrayList<Etage> etages;
    private final GameWorld world;
    private int courant;
    private boolean finMonde;

    private ArrayList<String> spawnedObjects;

    /**
     * Constructeur du monde du jeu.
     * @param world le monde de jeu dans leuqel ajouter les étages / salles
     */
    public Monde(GameWorld world){
        this.world = world;
        etages = new ArrayList<>();
        courant = 0;
        finMonde = false;
        spawnedObjects = new ArrayList<>();
        generationMonde();
    }

    private void generationMonde() {
        GenerateurSalle generateurSalle = new GenerateurSalle(world);
        etages.add(new Etage(world, this,0, generateurSalle));
        etages.add(new Etage(world, this,1, generateurSalle));
        etages.add(new Etage(world, this,2, generateurSalle));
    }

    public void addObject(ObjetsTousTypes objet){
        spawnedObjects.add(objet.getNom());
    }

    public boolean checkIfExists(ObjetsTousTypes objet){
        return spawnedObjects.contains(objet.getNom());
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
