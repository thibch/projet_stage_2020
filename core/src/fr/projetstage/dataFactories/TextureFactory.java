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
    private static Texture mur2;
    private static Texture mur3;
    private static Texture mur4;
    private static Texture murAngle;
    private static Texture bordureMur;
    private static Texture bordureMurAngle;
    private static Texture petiteTable;
    private static Texture grandeTable;

    private static Texture sol1;
    private static Texture sol2;
    private static Texture sol3;
    private static Texture sol4;
    private static Texture sol5;
    private static Texture sol6;
    private static Texture sol7;
    private static Texture sol8;
    private static Texture sol9;
    private static Texture sol10;
    private static Texture bibliotheque;
    private static Texture drapeauVert;
    private static Texture drapeauRouge;
    private static Texture prisoner;

    private static Texture pauseBtn;
    private static Texture pauseBtnPressed;

    private static TextureRegion joueurIdleSpriteSheet;
    private static TextureRegion joueurRunningSpriteSheet;
    private static TextureRegion torcheSpriteSheet;

    /**
     * Met en place les textures dans la banque de texures
     */
    private TextureFactory(){
        instance = this;

        mur1 = new Texture(Gdx.files.internal("tiles/wall/wall_1.png"));
        mur2 = new Texture(Gdx.files.internal("tiles/wall/wall_2.png"));
        mur3 = new Texture(Gdx.files.internal("tiles/wall/wall_3.png"));
        mur4 = new Texture(Gdx.files.internal("tiles/wall/wall_crack.png"));
        murAngle = new Texture(Gdx.files.internal("tiles/wall/wall_bottom_corner.png"));
        bordureMur = new Texture(Gdx.files.internal("tiles/wall/wall_top_1.png"));
        bordureMurAngle = new Texture(Gdx.files.internal("tiles/wall/wall_top_corner.png"));

        bibliotheque = new Texture(Gdx.files.internal("props_items/bookshelf.png"));
        petiteTable = new Texture(Gdx.files.internal("props_items/table.png"));
        grandeTable = new Texture(Gdx.files.internal("props_items/table_2.png"));
        drapeauVert = new Texture(Gdx.files.internal("props_items/flag_green.png"));
        drapeauRouge = new Texture(Gdx.files.internal("props_items/flag_red.png"));
        prisoner = new Texture(Gdx.files.internal("props_items/prisoner.png"));

        sol1 = new Texture(Gdx.files.internal("tiles/floor/floor_1.png"));
        sol2 = new Texture(Gdx.files.internal("tiles/floor/floor_2.png"));
        sol3 = new Texture(Gdx.files.internal("tiles/floor/floor_3.png"));
        sol4 = new Texture(Gdx.files.internal("tiles/floor/floor_4.png"));
        sol5 = new Texture(Gdx.files.internal("tiles/floor/floor_5.png"));
        sol6 = new Texture(Gdx.files.internal("tiles/floor/floor_6.png"));
        sol7 = new Texture(Gdx.files.internal("tiles/floor/floor_7.png"));
        sol8 = new Texture(Gdx.files.internal("tiles/floor/floor_8.png"));
        sol9 = new Texture(Gdx.files.internal("tiles/floor/floor_9.png"));
        sol10 = new Texture(Gdx.files.internal("tiles/floor/floor_10.png"));

        pauseBtn = new Texture(Gdx.files.internal("ui/pause_button.png"));
        pauseBtnPressed = new Texture(Gdx.files.internal("ui/pause_button_press.png"));

        joueurIdleSpriteSheet = new TextureRegion(new Texture(Gdx.files.internal("heroes/knight/knight_idle_spritesheet.png")));
        joueurRunningSpriteSheet = new TextureRegion(new Texture(Gdx.files.internal("heroes/knight/knight_run_spritesheet.png")));
        torcheSpriteSheet = new TextureRegion(new Texture(Gdx.files.internal("props_items/torch_spritesheet.png")));

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
     * Getter pour l'image de wall_2
     * @return image du mur 2
     */
    public Texture getMur2() {
        return mur2;
    }

    /**
     * Getter pour l'image de wall_3
     * @return image du mur 3
     */
    public Texture getMur3() {
        return mur3;
    }

    /**
     * Getter pour l'image de wall_crack
     * @return image du mur fissuré
     */
    public Texture getMur4() {
        return mur4;
    }

    /**
     * Getter pour l'image de floor_1
     * @return image du sol 1
     */
    public Texture getSol1() {
        return sol1;
    }

    /**
     * Getter pour l'image de floor_2
     * @return image du sol 2
     */
    public Texture getSol2() {
        return sol2;
    }

    /**
     * Getter pour l'image de floor_3
     * @return image du sol 3
     */
    public Texture getSol3() {
        return sol3;
    }

    /**
     * Getter pour l'image de floor_4
     * @return image du sol 4
     */
    public Texture getSol4() {
        return sol4;
    }

    /**
     * Getter pour l'image de floor_5
     * @return image du sol 5
     */
    public Texture getSol5() {
        return sol5;
    }

    /**
     * Getter pour l'image de floor_6
     * @return image du sol 6
     */
    public Texture getSol6() {
        return sol6;
    }

    /**
     * Getter pour l'image de floor_7
     * @return image du sol 7
     */
    public Texture getSol7() {
        return sol7;
    }

    /**
     * Getter pour l'image de floor_8
     * @return image du sol 8
     */
    public Texture getSol8() {
        return sol8;
    }

    /**
     * Getter pour l'image de floor_9
     * @return image du sol 9
     */
    public Texture getSol9() {
        return sol9;
    }

    /**
     * Getter pour l'image de floor_10
     * @return image du sol 10
     */
    public Texture getSol10() {
        return sol10;
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
     * Getter pour l'image de prisonnier
     * @return image de de prisonnier
     */
    public Texture getPrisoner() {
        return prisoner;
    }

    /**
     * Getter pour l'image du drapeau vert
     * @return image de drapeau vert
     */
    public Texture getDrapeauVert() {
        return drapeauVert;
    }

    /**
     * Getter pour l'image de drapeau rouge
     * @return image de drapeau rouge
     */
    public Texture getDrapeauRouge() {
        return drapeauRouge;
    }

    /**
     * Getter pour l'image de l'angle de bordure de mur
     * @return image de l'angle de bordure de mur
     */
    public Texture getBibliotheque() {
        return bibliotheque;
    }

    /**
     * Getter pour l'image de la petite table
     * @return Getter pour l'image de la petite table
     */
    public Texture getPetiteTable() {
        return petiteTable;
    }

    /**
     * Getter pour l'image de la grande table
     * @return Getter pour l'image de la grande table
     */
    public Texture getGrandeTable() {
        return grandeTable;
    }

    /**
     * Getter pour l'image de menu pause
     * @return Getter pour l'image du menu pause
     */
    public Texture getPauseBtn() {
        return pauseBtn;
    }

    /**
     * Getter pour l'image de menu pause enfoncé
     * @return Getter pour l'image du menu pause enfoncé
     */
    public Texture getPauseBtnPressed() {
        return pauseBtnPressed;
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

    /**
     * Getter pour la sprite sheet de la torche
     * @return une TextureRegion torch spritesheet
     */
    public TextureRegion getTorcheSpriteSheet() {
        return torcheSpriteSheet;
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

