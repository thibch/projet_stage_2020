package fr.projetstage.models.entites;

public enum TypeEntite {

    JOUEUR(), CORPS_A_CORPS(), ENNEMI(), DISTANCE(), PICKUP();


    TypeEntite(){

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
