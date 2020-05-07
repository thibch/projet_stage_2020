package fr.projetstage.models.monde;

import java.util.ArrayList;

public class Monde {

    private ArrayList<Etage> etages;
    private GameWorld world;
    private int courant;

    public Monde(GameWorld world){
        this.world = world;
        etages = new ArrayList<>();
        courant = 0;
        generationMonde();
    }

    private void generationMonde() {
        etages.add(new Etage(world));
        etages.add(new Etage(world));
        etages.add(new Etage(world));
    }

    public Etage getEtageSuivant(){
        if(courant >= etages.size()){
            return etages.get(etages.size()-1);
        }
        return etages.get(courant++);
    }
}
