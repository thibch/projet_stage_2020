package fr.projetstage.models.monde;

public enum TypeSalle {

    BOSS(5), SPAWN(1), SALLE_COFFRE(2), NO_TYPE(0);

    private final int id;

    TypeSalle(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
