package fr.projetstage.models.entites.joueur;

import fr.projetstage.models.entites.objets.ObjetsTousTypes;

import java.util.ArrayList;

public class Inventaire {

    private final ArrayList<ObjetsTousTypes> contenu;
    private final Joueur joueur;

    public Inventaire(Joueur joueur){
        contenu = new ArrayList<>(50);
        this.joueur = joueur;
    }


    public void add(ObjetsTousTypes obj){
        contenu.add(obj);
        obj.applyEffect();
    }

    public void remove(ObjetsTousTypes obj){
        contenu.remove(obj);
        obj.reverseEffect();
    }




}
