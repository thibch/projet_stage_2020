package fr.projetstage.models.monde.salle.solEtMurs;

import com.badlogic.gdx.graphics.Texture;
import fr.projetstage.dataFactories.TextureFactory;

public enum TypeMur {

    MUR1(TextureFactory.getInstance().getMur1()), MUR2(TextureFactory.getInstance().getMur1()),
    MUR3(TextureFactory.getInstance().getMur1()), MUR4(TextureFactory.getInstance().getMur1());

    private final Texture texture;

    TypeMur(Texture mur) {
        this.texture = mur;
    }

    public Texture getTexture() {
        return texture;
    }
}
