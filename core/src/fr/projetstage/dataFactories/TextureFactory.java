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

    // Textures
    // Murs d'une salle
    private static Texture mur1;
    private static Texture mur2;
    private static Texture mur3;
    private static Texture mur4;
    private static Texture murAngle;
    private static Texture bordureMur;
    private static Texture bordureMurAngle;

    // Porte
    private static Texture porteOuverte;
    private static Texture porteFerme;
    private static Texture escalier;
    // Décors Murs
    private static Texture drapeauVert;
    private static Texture drapeauRouge;
    private static Texture prisoner;

    // Meubles
    private static Texture petiteTable;
    private static Texture grandeTable;
    private static Texture bibliotheque;

    // Sols
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

    // Interface/Boutons
    private static Texture background;
    private static Texture titleScreen;
    private static Texture pauseBtn;
    private static Texture pauseBtnPressed;
    private static Texture btn;
    private static Texture btnPressed;
    private static Texture inputText;
    private static Texture textCursor;
    private static Texture textSelect;
    private static Texture coeurPlein;
    private static Texture coeurMoitie;
    private static Texture coeurVide;
    private static Texture swordUI;
    private static Texture bowUI;
    private static Texture arrowUI;
    private static Texture salleCourante;
    private static Texture salleVisitee;
    private static Texture salleNonVisitee;
    private static Texture backgroundPad;
    private static Texture knobPad;

    // Armes
    private static Texture epee;
    private static Texture fleche;

    // Objets au sol
    private static Texture potionRouge;
    private static Texture potionJaune;
    private static Texture coffreOpen;
    private static Texture crane;

    // SpriteSheets
    // Joueur
    private static TextureRegion joueurIdleSpriteSheet;
    private static TextureRegion joueurRunningSpriteSheet;

    // Slimes
    private static TextureRegion slimeIdleSpriteSheet;
    private static TextureRegion slimeRunSpriteSheet;

    // EyeBat
    private static TextureRegion eyeBatSpriteSheet;

    // Décors
    private static TextureRegion torcheSpriteSheet;
    private static TextureRegion piegeSpriteSheet;

    // Animation Armes
    private static TextureRegion attaqueSpriteSheet;
    private static TextureRegion arcCharging;

    // Effets
    private static TextureRegion deathSpriteSheet;

    // Objets au sol
    private static TextureRegion coffreSpriteSheet;

    /**
     * Met en place les textures dans la banque de texures
     */
    private TextureFactory(){
        instance = this;

        // Textures
        // Murs d'une salle
        mur1 = new Texture(Gdx.files.internal("tiles/level1/wall/wall_1.png"));
        mur2 = new Texture(Gdx.files.internal("tiles/level1/wall/wall_2.png"));
        mur3 = new Texture(Gdx.files.internal("tiles/level1/wall/wall_3.png"));
        mur4 = new Texture(Gdx.files.internal("tiles/level1/wall/wall_crack.png"));
        murAngle = new Texture(Gdx.files.internal("tiles/level1/wall/wall_bottom_corner.png"));
        bordureMur = new Texture(Gdx.files.internal("tiles/level1/wall/wall_top_1.png"));
        bordureMurAngle = new Texture(Gdx.files.internal("tiles/level1/wall/wall_top_corner.png"));

        // Porte
        porteOuverte = new Texture(Gdx.files.internal("tiles/level1/wall/door_open.png"));
        porteFerme = new Texture(Gdx.files.internal("tiles/level1/wall/door_closed_no_lock.png"));
        escalier = new Texture(Gdx.files.internal("tiles/level1/floor/stair_nextlevel.png"));

        // Décors Murs
        drapeauVert = new Texture(Gdx.files.internal("props_items/flag_green.png"));
        drapeauRouge = new Texture(Gdx.files.internal("props_items/flag_red.png"));
        prisoner = new Texture(Gdx.files.internal("props_items/prisoner.png"));

        // Meubles
        petiteTable = new Texture(Gdx.files.internal("props_items/table.png"));
        grandeTable = new Texture(Gdx.files.internal("props_items/table_2.png"));
        bibliotheque = new Texture(Gdx.files.internal("props_items/bookshelf.png"));

        // Sols
        sol1 = new Texture(Gdx.files.internal("tiles/level1/floor/floor_1.png"));
        sol2 = new Texture(Gdx.files.internal("tiles/level1/floor/floor_2.png"));
        sol3 = new Texture(Gdx.files.internal("tiles/level1/floor/floor_3.png"));
        sol4 = new Texture(Gdx.files.internal("tiles/level1/floor/floor_4.png"));
        sol5 = new Texture(Gdx.files.internal("tiles/level1/floor/floor_5.png"));
        sol6 = new Texture(Gdx.files.internal("tiles/level1/floor/floor_6.png"));
        sol7 = new Texture(Gdx.files.internal("tiles/level1/floor/floor_7.png"));
        sol8 = new Texture(Gdx.files.internal("tiles/level1/floor/floor_8.png"));
        sol9 = new Texture(Gdx.files.internal("tiles/level1/floor/floor_9.png"));
        sol10 = new Texture(Gdx.files.internal("tiles/level1/floor/floor_10.png"));

        // Interface/Boutons
        background = new Texture(Gdx.files.internal("ui/background.png"));
        titleScreen = new Texture(Gdx.files.internal("ui/title_screen.png"));
        pauseBtn = new Texture(Gdx.files.internal("ui/pause_button.png"));
        pauseBtnPressed = new Texture(Gdx.files.internal("ui/pause_button_press.png"));
        btn = new Texture(Gdx.files.internal("ui/menu_button.png"));
        btnPressed = new Texture(Gdx.files.internal("ui/menu_button_press.png"));
        inputText = new Texture(Gdx.files.internal("ui/text_input.png"));
        textCursor = new Texture(Gdx.files.internal("ui/text_cursor.png"));
        textSelect = new Texture(Gdx.files.internal("ui/text_selection.png"));
        coeurPlein = new Texture(Gdx.files.internal("ui/filled_heart.png"));
        coeurMoitie = new Texture(Gdx.files.internal("ui/half_heart.png"));
        coeurVide = new Texture(Gdx.files.internal("ui/empty_heart.png"));
        swordUI =  new Texture(Gdx.files.internal("ui/weapon_sword_1.png"));
        bowUI = new Texture(Gdx.files.internal("ui/bowUI.png"));
        arrowUI = new Texture(Gdx.files.internal("ui/arrow_UI.png"));
        salleCourante = new Texture(Gdx.files.internal("ui/GPS_salle_courante.png"));
        salleVisitee = new Texture(Gdx.files.internal("ui/GPS_salle_visitee.png"));
        salleNonVisitee = new Texture(Gdx.files.internal("ui/GPS_salle_non_visitee.png"));
        backgroundPad = new Texture(Gdx.files.internal("ui/pad_background.png"));
        knobPad = new Texture(Gdx.files.internal("ui/pad_knob.png"));

        // Armes
        epee = new Texture(Gdx.files.internal("weapons/weapon_sword.png"));
        fleche = new Texture(Gdx.files.internal("weapons/arrow.png"));

        // Objets au sol
        potionRouge = new Texture(Gdx.files.internal("props_items/potion_red.png"));
        potionJaune = new Texture(Gdx.files.internal("props_items/potion_yellow.png"));
        coffreOpen = new Texture(Gdx.files.internal("props_items/chest_open.png"));
        crane = new Texture(Gdx.files.internal("props_items/skull.png"));

        // SpriteSheets
        // Joueur
        joueurIdleSpriteSheet = new TextureRegion(new Texture(Gdx.files.internal("heroes/knight/knight_idle_spritesheet.png")));
        joueurRunningSpriteSheet = new TextureRegion(new Texture(Gdx.files.internal("heroes/knight/knight_run_spritesheet.png")));

        // Slimes
        slimeIdleSpriteSheet = new TextureRegion(new Texture(Gdx.files.internal("enemies/normal/slime/slime_idle_spritesheet.png")));
        slimeRunSpriteSheet = new TextureRegion(new Texture(Gdx.files.internal("enemies/normal/slime/slime_run_spritesheet.png")));

        // EyeBat
        eyeBatSpriteSheet = new TextureRegion(new Texture(Gdx.files.internal("enemies/normal/flying creature/fly_anim_spritesheet.png")));

        // Décors
        torcheSpriteSheet = new TextureRegion(new Texture(Gdx.files.internal("props_items/torch_spritesheet.png")));
        piegeSpriteSheet = new TextureRegion(new Texture(Gdx.files.internal("tiles/level1/floor/spikes_spritesheet.png")));

        // Animation Armes
        attaqueSpriteSheet = new TextureRegion(new Texture(Gdx.files.internal("effects/slash_effect_anim_spritesheet.png")));
        arcCharging = new TextureRegion(new Texture(Gdx.files.internal("weapons/weapon_bow.png")));

        //effets
        deathSpriteSheet = new TextureRegion(new Texture(Gdx.files.internal("effects/enemy_afterdead_explosion_anim_spritesheet.png")));
        coffreSpriteSheet = new TextureRegion(new Texture(Gdx.files.internal("props_items/chest_spritesheet.png")));
    }

    /**
     * Méthode de singleton
     *
     * @return instance du singleton
     */
    public static TextureFactory getInstance() {
        if (instance == null) {
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
     * Getter pour l'image de la porte ouverte
     * @return image de porte ouverte
     */
    public Texture getPorteOuverte() {
        return porteOuverte;
    }

    /**
     * Getter pour l'image de la porte fermée
     * @return image de porte fermée
     */
    public Texture getPorteFerme() {
        return porteFerme;
    }

    /**
     * Getter pour l'image de l'escalier
     * @return image de l'escalier
     */
    public Texture getEscalier() {
        return escalier;
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
     * Getter pour l'image de fond
     * @return Getter pour l'image de fond
     */
    public Texture getBackground() {
        return background;
    }

    /**
     * Getter pour l'image de fond du MenuScreen
     * @return Getter pour l'image de fond du MenuScreen
     */
    public Texture getTitleScreen() {
        return titleScreen;
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
    public Texture getPauseBtnPressed()
    {
        return pauseBtnPressed;
    }

    /**
     * Getter pour l'image de btn de menu
     * @return Getter pour l'image du bouton de menu
     */
    public Texture getBtn() {
        return btn;
    }

    /**
     * Getter pour l'image de bouton de menu enfoncé
     * @return Getter pour l'image de bouton de menu enfoncé
     */
    public Texture getBtnPressed() {
        return btnPressed;
    }

    /**
     * Getter pour l'image de saisie de texte
     * @return Getter pour l'image de saisie de texte
     */
    public Texture getInputText() {
        return inputText;
    }

    /**
     * Getter pour l'image de curseur de saisie de texte
     * @return Getter pour l'image de curseur de saisie de texte
     */
    public Texture getTextCursor() {
        return textCursor;
    }

    /**
     * Getter pour l'image de selection de saisie de texte
     * @return Getter pour l'image de selection de saisie de texte
     */
    public Texture getTextSelect() {
        return textSelect;
    }

    /**
     * Getter pour l'image d'un coeur plein
     * @return Getter pour l'image d'un coeur plein
     */
    public Texture getCoeurPlein() {
        return coeurPlein;
    }

    /**
     * Getter pour l'image d'une moitié de coeur
     * @return Getter pour l'image d'une moitié de coeur
     */
    public Texture getCoeurMoitie() {
        return coeurMoitie;
    }

    /**
     * Getter pour l'image d'un coeur vide
     * @return Getter pour l'image d'un coeur vide
     */
    public Texture getCoeurVide() {
        return coeurVide;
    }

    /**
     * Getter pour l'image d'epee pour UI
     * @return Getter pour l'image d'epee pour UI
     */
    public Texture getSwordUI() {
        return swordUI;
    }

    /**
     * Getter pour l'image d'arc pour UI
     * @return Getter pour l'image d'arc pour UI
     */
    public Texture getBowUI() {
        return bowUI;
    }

    /**
     * Getter pour l'image de la flèche pour UI
     * @return Getter pour l'image de la flèche pour UI
     */
    public Texture getArrowUI() {
        return arrowUI;
    }

    /**
     * Getter pour l'image du background du pad tactile
     * @return Getter pour l'image du background du pad tactile
     */
    public Texture getBackgroundPad() {
        return backgroundPad;
    }

    /**
     * Getter pour l'image du knob du pad tactile
     * @return Getter pour l'image du knob du pad tactile
     */
    public Texture getKnobPad(){
        return knobPad;
    }

    /**
     * Getter pour l'image de la salle courante pour UI minimap
     * @return Getter pour l'image de la salle courante pour UI minimap
     */
    public Texture getSalleCourante() {
        return salleCourante;
    }

    /**
     * Getter pour l'image de la salle visitee pour UI minimap
     * @return Getter pour l'image de la salle visitee pour UI minimap
     */
    public Texture getSalleVisitee() {
        return salleVisitee;
    }

    /**
     * Getter pour l'image de la salle non visitee pour UI minimap
     * @return Getter pour l'image de la salle non visitee pour UI minimap
     */
    public Texture getSalleNonVisitee() {
        return salleNonVisitee;
    }

    /**
     * Getter pour l'image de l'epee
     * @return Getter pour l'image de l'epee
     */
    public Texture getEpee() {
        return epee;
    }

    /**
     * Getter pour l'image de la flèche
     * @return Getter pour l'image de la flèche
     */
    public Texture getFleche() {
        return fleche;
    }

    /**
     * Getter pour l'image de la potion rouge
     * @return Getter pour l'image de la potion rouge
     */
    public Texture getPotionRouge() {
        return potionRouge;
    }

    /**
     * Getter pour l'image de la potion jaune
     * @return Getter pour l'image de la potion jaune
     */
    public Texture getPotionJaune() {
        return potionJaune;
    }

    /**
     * Getter pour l'image du crane
     * @return Getter pour l'image du crane
     */
    public Texture getCrane() {
        return crane;
    }

    /**
     * Getter pour l'image du coffre ouvert
     * @return Getter pour l'image du coffre ouvert
     */
    public Texture getCoffreOpen(){
        return coffreOpen;
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

    /**
     * Getter pour la sprite sheet de la torche
     * @return une TextureRegion torch spritesheet
     */
    public TextureRegion getAttaqueSpriteSheet() {
        return attaqueSpriteSheet;
    }

    /**
     * Getter pour la sprite sheet de l'arc qui charge
     * @return une TextureRegion charging bow spritesheet
     */
    public TextureRegion getArcCharging(){
        return arcCharging;
    }


    /**
     * Getter pour la sprite sheet du slime inactif
     * @return une TextureRegion Idle slime spritesheet
     */
    public TextureRegion getSlimeIdleSpriteSheet(){
        return slimeIdleSpriteSheet;
    }

    /**
     * Getter pour la sprite sheet du slime qui marche
     * @return une TextureRegion running slime spritesheet
     */
    public TextureRegion getSlimeRunSpriteSheet(){
        return slimeRunSpriteSheet;
    }

    /**
     * Getter pour la sprite sheet de l'oeil volant
     * @return une TextureRegion fly creature spritesheet
     */
    public TextureRegion getEyeBatSpriteSheet(){
        return eyeBatSpriteSheet;
    }

    /**
     * Getter pour la sprite sheet de mort
     * @return une TextureRegion de spritesheet de mort
     */
    public TextureRegion getDeathSpriteSheet(){
        return deathSpriteSheet;
    }

    /**
     * Getter pour la sprite sheet du piege
     * @return une TextureRegion de la spritesheet du piege
     */
    public TextureRegion getPiegeSpriteSheet(){
        return piegeSpriteSheet;
    }

    /**
     * Getter pour la sprite sheet du coffre qui scintille
     * @return une TextureRegion de la spritesheet du coffre
     */
    public TextureRegion getCoffreSpriteSheet() {
        return coffreSpriteSheet;
    }

    /**
     * Efface toute les Textures de la Factory
     */
    public void dispose() {
        dispose(mur1, mur2, mur3, mur4, murAngle,
                bordureMur, bordureMurAngle,
                petiteTable, grandeTable,
                bibliotheque,
                sol1, sol2, sol3, sol4, sol5, sol6, sol7, sol8, sol9, sol10,
                drapeauVert, drapeauRouge, prisoner,
                background, titleScreen, pauseBtn, pauseBtnPressed, btn, btnPressed, inputText, textCursor, textSelect, coeurPlein, coeurMoitie, coeurVide, bowUI, swordUI,
                salleCourante, salleNonVisitee, salleVisitee,
                epee, fleche,
                potionRouge, potionJaune, crane, coffreOpen,
                joueurIdleSpriteSheet.getTexture(),
                joueurRunningSpriteSheet.getTexture(),
                torcheSpriteSheet.getTexture(),
                attaqueSpriteSheet.getTexture(),
                slimeIdleSpriteSheet.getTexture(),
                slimeRunSpriteSheet.getTexture(),
                eyeBatSpriteSheet.getTexture(),
                deathSpriteSheet.getTexture(),
                piegeSpriteSheet.getTexture(),
                coffreSpriteSheet.getTexture()
        );
    }

    /**
     * Efface toute les Textures en paramètre
     * @param textures Tableau de toute les textures à effacer
     */
    private void dispose(Texture... textures) {
        for (Texture text : textures) {
            text.dispose();
        }
    }
}

