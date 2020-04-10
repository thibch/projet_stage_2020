package fr.projetstage.models.entites;

import java.util.Objects;

public class Type {

    private TypeEntite type;
    private int id;


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

    public void setType(TypeEntite type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type1 = (Type) o;
        return type == type1.type;
    }
}
