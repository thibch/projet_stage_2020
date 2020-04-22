package fr.projetstage.models.monde;

import fr.projetstage.models.monde.salle.Salle;
import fr.projetstage.models.monde.salle.patternSalle.Salle1;
import fr.projetstage.models.monde.salle.patternSalle.Salle2;

import java.util.ArrayList;

public class Etage {

    private ArrayList<Salle> salles;
    private int courant;

    public Etage(GameWorld world){
        salles = new ArrayList<>();
        courant = 0;
        salles.add(new Salle1(world));
        salles.add(new Salle2(world));
    }

    public Salle start(){
        courant = 0;
        return salles.get(0);
    }

    public Salle next(){
        courant = courant==0?1:0;
        return salles.get(courant);
    }




}
