package fr.projetstage.models.monde.salle.solEtMurs;

import com.badlogic.gdx.graphics.Texture;
import fr.projetstage.dataFactories.TextureFactory;

public enum TypeSol {

    SOL1(TextureFactory.getInstance().getSol1()),SOL2(TextureFactory.getInstance().getSol2()),
    SOL3(TextureFactory.getInstance().getSol3()),SOL4(TextureFactory.getInstance().getSol4()),
    SOL5(TextureFactory.getInstance().getSol5()),SOL6(TextureFactory.getInstance().getSol6()),
    SOL7(TextureFactory.getInstance().getSol7()),SOL8(TextureFactory.getInstance().getSol8()),
    SOL9(TextureFactory.getInstance().getSol9()),SOL10(TextureFactory.getInstance().getSol10());

    private final Texture texture;

    TypeSol(Texture sol) {
        this.texture = sol;
    }

    public Texture getTexture() {
        return texture;
    }
}
