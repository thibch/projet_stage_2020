package fr.projetstage.models.monde.salle;

public enum Orientation {

    GAUCHE(90), DROITE(-90), HAUT(0), BAS(180);

    private int rotation;

    Orientation(int rotation){
        this.rotation = rotation;
    }

    public int getRotation(){
        return rotation;
    }

}
