package fr.projetstage.models;

public enum CollisionFilter {

    JOUEUR(0x0001), OBJET(0x0002), MONSTRESOL(0x0004), DECOR(0x0008);

    private final short category;

    /**
     * Ajoute un élément dans la liste des filtres de collision
     * @param category le bit de la categorie
     */
    CollisionFilter(int category){
        this.category = (short) category;
    }

    /**
     * Permet de recuperer la valeur de la categorie d'un type d'élément
     * @return un short de la valeur de l'élément
     */
    public short getCategory(){
        return category;
    }

}