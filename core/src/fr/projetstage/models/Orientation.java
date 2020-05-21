package fr.projetstage.models;

public enum Orientation {

    GAUCHE(0,90), DROITE(1,-90), HAUT(2,0), BAS(3,180), NO_ORIENTATION(4,0);

    private final int rotation;
    private final int indice;

    Orientation(int indice, int rotation){
        this.rotation = rotation;
        this.indice = indice;
    }

    /**
     * Permet de récuperer l'angle de rotation à faire pour une orientation donnée
     * @return un angle en degré de l'orientation
     */
    public int getRotation(){
        return rotation;
    }

    /**
     * Methode retournant l'indice d'une orientation
     * @return un entier definissant l'indice de l'Orientation
     */
    public int getIndice(){
        return indice;
    }

    /**
     * Methode permettant de recuperer le type d'orientation selon l'indice
     * @param indice l'indice d'une orientation
     * @return le type D'orientation selon l'indice
     */
    public static Orientation getFromIndice(int indice){
        switch (indice){
            case 0:
                return GAUCHE;
            case 1:
                return DROITE;
            case 2:
                return HAUT;
            case 3:
                return BAS;
            default:
                return NO_ORIENTATION;
        }
    }
}