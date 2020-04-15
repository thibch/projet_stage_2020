package fr.projetstage.models;

public enum Orientation {

    GAUCHE(0,90), DROITE(1,-90), HAUT(2,0), BAS(3,180), NO_ORIENTATION(4,0);

    private final int rotation;
    private final int indice;

    Orientation(int indice, int rotation){
        this.rotation = rotation;
        this.indice = indice;
    }

    public int getRotation(){
        return rotation;
    }

    public int getIndice(){
        return indice;
    }

}