package fr.projetstage.models.entites.joueur;

import fr.projetstage.models.entites.objets.ObjetsTousTypes;

import java.util.ArrayList;

public class Inventaire {

    private final ArrayList<ObjetsTousTypes> contenu;

    /**
     * Inventaire
     */
    public Inventaire(){
        contenu = new ArrayList<>(50);
    }


    /**
     * Ajoute un objet à la liste de l'inventaire
     * @param obj l'objet ajouté à l'inventaire
     */
    public void add(ObjetsTousTypes obj){
        if(!obj.getClass().getSimpleName().equals("Coeur") && !obj.getClass().getSimpleName().equals("PotionVitesse")){
            contenu.add(obj);
        }
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

    /**
     * Permet de recuperer le contenu de l'inventaire pour l'afficher
     * @return l'arraylist de l'inventaire
     */
    public ArrayList<ObjetsTousTypes> getContenu(){
        return contenu;
    }


}
