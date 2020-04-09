package fr.projetstage.models.entites;

import fr.projetstage.dataFactories.IdFactory;

public enum TypeAttaque {

    JOUEUR(0), CORPS_A_CORPS(0), ENNEMI(IdFactory.getInstance().getId()), DISTANCE(0);

    private int id;

    TypeAttaque(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
