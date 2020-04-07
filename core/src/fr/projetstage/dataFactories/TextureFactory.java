package fr.projetstage.dataFactories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * @author Thibault Choné
 * Singleton : Banque de textures
 */
public class TextureFactory {

    private static TextureFactory instance;
    private static Texture mur1;
    private static Texture murAngle;
    private static Texture bordureMur;
    private static Texture bordureMurAngle;
    private static Texture sol2;
    private static TextureRegion joueurIdleSpriteSheet;
    private static TextureRegion joueurRunningSpriteSheet;

    /**
     * Met en place les textures dans la banque de texures
     */
    private TextureFactory(){
        instance = this;
        mur1 = new Texture(Gdx.files.internal("tiles/wall/wall_1.png"));
        murAngle = new Texture(Gdx.files.internal("tiles/wall/wall_bottom_corner.png"));
        sol2 = new Texture(Gdx.files.internal("tiles/floor/floor_2.png"));
        bordureMur = new Texture(Gdx.files.internal("tiles/wall/wall_top_1.png"));
        bordureMurAngle = new Texture(Gdx.files.internal("tiles/wall/wall_top_corner.png"));

        joueurIdleSpriteSheet = new TextureRegion(new Texture(Gdx.files.internal("heroes/knight/knight_idle_spritesheet.png")));
        joueurRunningSpriteSheet = new TextureRegion(new Texture(Gdx.files.internal("heroes/knight/knight_run_spritesheet.png")));
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

    /**
     * Getter pour l'image d'angle d'un mur
     * @return image de l'angle de mur
     */
    public Texture getMurAngle() {
        return murAngle;
    }

    /**
     * Getter pour l'image de bordure de mur
     * @return image de la bordure de mur
     */
    public Texture getBordureMur() {
        return bordureMur;
    }

    /**
     * Getter pour l'image de l'angle de bordure de mur
     * @return image de l'angle de bordure de mur
     */
    public Texture getBordureMurAngle() {
        return bordureMurAngle;
    }

    /**
     * Getter pour la sprite sheet du joueur inactif
     * @return une TextureRegion idle spritesheet
     */
    public TextureRegion getJoueurIdleSpriteSheet() {
        return joueurIdleSpriteSheet;
    }

    /**
     * Getter pour la sprite sheet du joueur qui cours
     * @return une TextureRegion running spritesheet
     */
    public TextureRegion getJoueurRunningSpriteSheet() {
        return joueurRunningSpriteSheet;
    }

    public void dispose(){
        mur1.dispose();
        sol2.dispose();
        murAngle.dispose();
        bordureMur.dispose();
        bordureMurAngle.dispose();
        joueurIdleSpriteSheet.getTexture().dispose();
        joueurRunningSpriteSheet.getTexture().dispose();
    }
}

