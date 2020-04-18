package fr.projetstage.models.entites;

public class Type {

    private final TypeEntite type;
    private final int id;


    public Type(TypeEntite type, int id){
        this.type = type;
        this.id = id;
    }

    public Type(TypeEntite type) {
        this.type = type;
        this.id = 0;
    }

    public TypeEntite getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type1 = (Type) o;
        return type == type1.type;
    }

    @Override
    public String toString() {
        return "Type{" +
                "type=" + type +
                ", id=" + id +
                '}';
    }
}
