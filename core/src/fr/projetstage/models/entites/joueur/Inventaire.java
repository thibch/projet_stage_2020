package fr.projetstage.models.entites.joueur;

import fr.projetstage.models.entites.objets.ObjetsTousTypes;

import java.util.ArrayList;

public class Inventaire {

    private final ArrayList<ObjetsTousTypes> contenu;
    private final Joueur joueur;

    /**
     * Inventaire
     * @param joueur le joueur qui a cet inventaire
     */
    public Inventaire(Joueur joueur){
        contenu = new ArrayList<>(50);
        this.joueur = joueur;
    }


    /**
     * Ajoute un objet à la liste de l'inventaire
     * @param obj l'objet ajouté à l'inventaire
     */
    public void add(ObjetsTousTypes obj){
        contenu.add(obj);
        obj.applyEffect();
    }

    /**
     * Supprime l'objet de l'inventaire
     * @param obj l'objet de l'inventaire
     */
    public void remove(ObjetsTousTypes obj){
        contenu.remove(obj);
        obj.reverseEffect();
    }




}
