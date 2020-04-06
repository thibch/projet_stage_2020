package fr.projetstage.dataFactories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * @author Thibault Choné
 * Singleton : Banque de textures
 */
public class TextureFactory {

    private static TextureFactory instance;
    private static Texture mur1;
    private static Texture sol2;

    /**
     * Met en place les textures dans la banque de texures
     */
    private TextureFactory(){
        instance = this;
        mur1 = new Texture(Gdx.files.internal("tiles/wall/wall_1.png"));
        sol2 = new Texture(Gdx.files.internal("tiles/floor/floor_2.png"));
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
     * Getter pour l'image de wall_1
     * @return image du mur 1
     */
    public Texture getMur1() {
        return mur1;
    }

    /**
     * Getter pour l'image de floor_2
     * @return image du sol 2
     */
    public Texture getSol2() {
        return sol2;
    }

    public void dispose(){
        mur1.dispose();
    }
}

