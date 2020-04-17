package fr.projetstage.models.entites.objets.objetsPiedestal;

import com.badlogic.gdx.graphics.Texture;
import fr.projetstage.dataFactories.TextureFactory;

public class ObjetSurPiedestal{

    private String nom;
    private int rarete;

    public Texture getTexture(){
        return TextureFactory.getInstance().getPotionJaune();
    }

    public void applyEffect() {

    }
}
