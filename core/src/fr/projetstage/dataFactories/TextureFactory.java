package fr.projetstage.dataFactories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * @author Thibault Choné
 * Singleton : Banque de textures
 */
public class TextureFactory {

    private static TextureFactory instance;
    private static Texture imageIntro;

    /**
     * Met en place les textures dans la banque de texures
     */
    private TextureFactory(){
        instance = this;
        imageIntro = new Texture(Gdx.files.internal("images/Intro.jpg"));
    }

    /**
     * Méthode de singleton
     * @return instance du singleton
     */
    public static TextureFactory getInstance(){
        if(instance == null){
            instance = new TextureFactory();
        }
        return instance;
    }

    /**
     * Getter pour l'image de l'intro
     * @return image de l'intro
     */
    public Texture getImageIntro() {
        return imageIntro;
    }
}

