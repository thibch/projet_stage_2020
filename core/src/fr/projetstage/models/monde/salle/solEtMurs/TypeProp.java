package fr.projetstage.models.monde.salle.solEtMurs;

import com.badlogic.gdx.graphics.Texture;
import fr.projetstage.dataFactories.TextureFactory;

public enum TypeProp {

    PROP_DRAPEAU_VERT(TextureFactory.getInstance().getDrapeauVert()),
    PROP_DRAPEAU_ROUGE(TextureFactory.getInstance().getDrapeauRouge()),
    PROP_PRISONNIER(TextureFactory.getInstance().getPrisoner());

    private final Texture texture;

    TypeProp(Texture prop) {
        this.texture = prop;
    }

    /**
     * Methode retournant la texture d'un prop
     * @return la texture d'un prop associé
     */
    public Texture getTexture() {
        return texture;
    }
}
