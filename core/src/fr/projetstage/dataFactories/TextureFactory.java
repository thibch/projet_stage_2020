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
    private static Texture salleCoffre;
    private static Texture salleBoss;

    // Mobile
    private static Texture backgroundPad;
    private static Texture knobPad;
    private static Texture keyUpMobile;
    private static Texture keyUpMobilePressed;

    private static Texture keyDownMobile;
    private static Texture keyDownMobilePressed;

    private static Texture keyRightMobile;
    private static Texture keyRightMobilePressed;

    private static Texture keyLeftMobile;
    private static Texture keyLeftMobilePressed;

    // Armes
    private static Texture epee;
    private static Texture fleche;

    // Objets au sol
    private static Texture potionRouge;
    private static Texture potionJaune;
    private static Texture potionVerte;
    private static Texture coffreOpen;
    private static Texture crane;
    private static Texture sunglasses;

    // SpriteSheets
    // Joueur
    private static TextureRegion joueurIdleSpriteSheet;
    private static TextureRegion joueurRunningSpriteSheet;

    // Slimes
    private static TextureRegion slimeIdleSpriteSheet;
    private static TextureRegion slimeRunSpriteSheet;

    // EyeBat
    private static TextureRegion eyeBatSpriteSheet;

    // Goblins
    private static TextureRegion goblinIdleSpriteSheet;
    private static TextureRegion goblinRunSpriteSheet;

    // Skelets
    private static TextureRegion skeletIdleSpriteSheet;
    private static TextureRegion skeletRunSpriteSheet;

    // Necromancers
    private static TextureRegion necromancerIdleSpriteSheet;
    private static TextureRegion necromancerRunSpriteSheet;

    // Big Ogre
    private static TextureRegion bigOgreIdleSpriteSheet;
    private static TextureRegion bigOgreRunSpriteSheet;

    // Orc Masked
    private static TextureRegion orcMaskedIdleSpriteSheet;
    private static TextureRegion orcMaskedRunSpriteSheet;

    // Orc Shaman
    private static TextureRegion orcShamanIdleSpriteSheet;
    private static TextureRegion orcShamanRunSpriteSheet;

    // Orc Warrior
    private static TextureRegion orcWarriorIdleSpriteSheet;
    private static TextureRegion orcWarriorRunSpriteSheet;

    // Big Demon
    private static TextureRegion bigDemonIdleSpriteSheet;
    private static TextureRegion bigDemonRunSpriteSheet;

    // Imp
    private static TextureRegion impIdleSpriteSheet;
    private static TextureRegion impRunSpriteSheet;

    // Chort
    private static TextureRegion chortIdleSpriteSheet;
    private static TextureRegion chortRunSpriteSheet;

    // Wogol
    private static TextureRegion wogolIdleSpriteSheet;
    private static TextureRegion wogolRunSpriteSheet;

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
        salleCoffre = new Texture(Gdx.files.internal("ui/GPS_salle_visitee_coffre.png"));
        salleBoss = new Texture(Gdx.files.internal("ui/GPS_salle_visitee_boss.png"));

        // Mobile
        backgroundPad = new Texture(Gdx.files.internal("ui/mobile/pad_background.png"));
        knobPad = new Texture(Gdx.files.internal("ui/mobile/pad_knob.png"));

        keyUpMobile = new Texture(Gdx.files.internal("ui/mobile/key_up.png"));
        keyUpMobilePressed = new Texture(Gdx.files.internal("ui/mobile/key_up_pressed.png"));

        keyDownMobile = new Texture(Gdx.files.internal("ui/mobile/key_down.png"));
        keyDownMobilePressed = new Texture(Gdx.files.internal("ui/mobile/key_down_pressed.png"));

        keyRightMobile = new Texture(Gdx.files.internal("ui/mobile/key_right.png"));
        keyRightMobilePressed = new Texture(Gdx.files.internal("ui/mobile/key_right_pressed.png"));

        keyLeftMobile = new Texture(Gdx.files.internal("ui/mobile/key_left.png"));
        keyLeftMobilePressed = new Texture(Gdx.files.internal("ui/mobile/key_left_pressed.png"));


        // Armes
        epee = new Texture(Gdx.files.internal("weapons/weapon_sword.png"));
        fleche = new Texture(Gdx.files.internal("weapons/arrow.png"));

        // Objets au sol
        potionRouge = new Texture(Gdx.files.internal("props_items/potion_red.png"));
        potionJaune = new Texture(Gdx.files.internal("props_items/potion_yellow.png"));
        potionVerte = new Texture(Gdx.files.internal("props_items/potion_green.png"));
        coffreOpen = new Texture(Gdx.files.internal("props_items/chest_open.png"));
        crane = new Texture(Gdx.files.internal("props_items/skull.png"));
        sunglasses = new Texture(Gdx.files.internal("props_items/sunglasses.png"));

        // SpriteSheets
        // Joueur
        joueurIdleSpriteSheet = new TextureRegion(new Texture(Gdx.files.internal("heroes/knight/knight_idle_spritesheet.png")));
        joueurRunningSpriteSheet = new TextureRegion(new Texture(Gdx.files.internal("heroes/knight/knight_run_spritesheet.png")));

        // Slimes
        slimeIdleSpriteSheet = new TextureRegion(new Texture(Gdx.files.internal("enemies/normal/slime/slime_idle_spritesheet.png")));
        slimeRunSpriteSheet = new TextureRegion(new Texture(Gdx.files.internal("enemies/normal/slime/slime_run_spritesheet.png")));

        // EyeBat
        eyeBatSpriteSheet = new TextureRegion(new Texture(Gdx.files.internal("enemies/normal/flying creature/fly_anim_spritesheet.png")));

        // Goblins
        goblinIdleSpriteSheet = new TextureRegion(new Texture(Gdx.files.internal("enemies/normal/goblin/goblin_idle_spritesheet.png")));
        goblinRunSpriteSheet = new TextureRegion(new Texture(Gdx.files.internal("enemies/normal/goblin/goblin_run_spritesheet.png")));

        // Skelets
        skeletIdleSpriteSheet= new TextureRegion(new Texture(Gdx.files.internal("enemies/normal/skelet/skelet_idle_spritesheet.png")));
        skeletRunSpriteSheet = new TextureRegion(new Texture(Gdx.files.internal("enemies/normal/skelet/skelet_run_spritesheet.png")));

        // Necromancers
        necromancerIdleSpriteSheet= new TextureRegion(new Texture(Gdx.files.internal("enemies/boss/necromancer/necromancer_idle_spritesheet.png")));
        necromancerRunSpriteSheet = new TextureRegion(new Texture(Gdx.files.internal("enemies/boss/necromancer/necromancer_run_spritesheet.png")));

        // Big Ogre
        bigOgreIdleSpriteSheet= new TextureRegion(new Texture(Gdx.files.internal("enemies/boss/big_ogre/big_ogre_idle_spritesheet.png")));
        bigOgreRunSpriteSheet = new TextureRegion(new Texture(Gdx.files.internal("enemies/boss/big_ogre/big_ogre_run_spritesheet.png")));

        // Orc Masked
        orcMaskedIdleSpriteSheet= new TextureRegion(new Texture(Gdx.files.internal("enemies/normal/orc/orc_masked_idle_spritesheet.png")));
        orcMaskedRunSpriteSheet = new TextureRegion(new Texture(Gdx.files.internal("enemies/normal/orc/orc_masked_run_spritesheet.png")));

        // Orc Shaman
        orcShamanIdleSpriteSheet= new TextureRegion(new Texture(Gdx.files.internal("enemies/normal/orc/orc_shaman_idle_spritesheet.png")));
        orcShamanRunSpriteSheet = new TextureRegion(new Texture(Gdx.files.internal("enemies/normal/orc/orc_shaman_run_spritesheet.png")));

        // Orc Warrior
        orcWarriorIdleSpriteSheet= new TextureRegion(new Texture(Gdx.files.internal("enemies/normal/orc/orc_warrior_idle_spritesheet.png")));
        orcWarriorRunSpriteSheet = new TextureRegion(new Texture(Gdx.files.internal("enemies/normal/orc/orc_warrior_run_spritesheet.png")));

        // Big Demon
        bigDemonIdleSpriteSheet= new TextureRegion(new Texture(Gdx.files.internal("enemies/boss/big_demon/big_demon_idle_spritesheet.png")));
        bigDemonRunSpriteSheet = new TextureRegion(new Texture(Gdx.files.internal("enemies/boss/big_demon/big_demon_run_spritesheet.png")));

        // Imp
        impIdleSpriteSheet= new TextureRegion(new Texture(Gdx.files.internal("enemies/normal/imp/imp_idle_spritesheet.png")));
        impRunSpriteSheet = new TextureRegion(new Texture(Gdx.files.internal("enemies/normal/imp/imp_run_spritesheet.png")));

        // Chort
        chortIdleSpriteSheet= new TextureRegion(new Texture(Gdx.files.internal("enemies/normal/chort/chort_idle_spritesheet.png")));
        chortRunSpriteSheet = new TextureRegion(new Texture(Gdx.files.internal("enemies/normal/chort/chort_run_spritesheet.png")));

        // Wogol
        wogolIdleSpriteSheet= new TextureRegion(new Texture(Gdx.files.internal("enemies/normal/wogol/wogol_idle_spritesheet.png")));
        wogolRunSpriteSheet = new TextureRegion(new Texture(Gdx.files.internal("enemies/normal/wogol/wogol_run_spritesheet.png")));

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
     * Getter pour l'image de la flèche vers le haut
     * @return Getter pour l'image de la flèche vers le haut
     */
    public Texture getKeyUpMobile() {
        return keyUpMobile;
    }

    /**
     * Getter pour l'image de la flèche vers le haut appuyé
     * @return Getter pour l'image de la flèche vers le haut appuyé
     */
    public Texture getKeyUpMobilePressed() {
        return keyUpMobilePressed;
    }

    /**
     * Getter pour l'image de la flèche vers le bas
     * @return Getter pour l'image de la flèche bas le haut
     */
    public Texture getKeyDownMobile() {
        return keyDownMobile;
    }

    /**
     * Getter pour l'image de la flèche vers le bas appuyé
     * @return Getter pour l'image de la flèche vers le bas appuyé
     */
    public Texture getKeyDownMobilePressed() {
        return keyDownMobilePressed;
    }

    /**
     * Getter pour l'image de la flèche vers la gauche appuyé
     * @return Getter pour l'image de la flèche vers la gauche appuyé
     */
    public Texture getKeyRightMobile() {
        return keyRightMobile;
    }

    /**
     * Getter pour l'image de la flèche vers le bas appuyé
     * @return Getter pour l'image de la flèche vers le bas appuyé
     */
    public Texture getKeyRightMobilePressed() {
        return keyRightMobilePressed;
    }

    /**
     * Getter pour l'image de la flèche vers la droite appuyé
     * @return Getter pour l'image de la flèche vers la droite appuyé
     */
    public Texture getKeyLeftMobile() {
        return keyLeftMobile;
    }

    public Texture getKeyLeftMobilePressed() {
        return keyLeftMobilePressed;
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
     * Getter pour l'image de la salle visitee du boss pour UI minimap
     * @return Getter pour l'image de la salle visitee du boss pour UI minimap
     */
    public Texture getSalleBoss() {
        return salleBoss;
    }

    /**
     * Getter pour l'image de la salle visitee du coffre pour UI minimap
     * @return Getter pour l'image de la salle visitee du coffre pour UI minimap
     */
    public Texture getSalleCoffre() {
        return salleCoffre;
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
     * Getter pour l'image de la potion verte
     * @return Getter pour l'image de la potion verte
     */
    public Texture getPotionVerte() {
        return potionVerte;
    }

    /**
     * Getter pour l'image du crane
     * @return Getter pour l'image du crane
     */
    public Texture getCrane() {
        return crane;
    }

    /**
     * Getter pour l'image des lunettes de soleil
     * @return Getter pour l'image des lunettes de soleil
     */
    public Texture getSunglasses() {
        return sunglasses;
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
     * Getter pour la sprite sheet du goblin inactif
     * @return une TextureRegion Idle goblin spritesheet
     */
    public TextureRegion getGoblinIdleSpriteSheet(){
        return goblinIdleSpriteSheet;
    }

    /**
     * Getter pour la sprite sheet du goblin qui marche
     * @return une TextureRegion running goblin spritesheet
     */
    public TextureRegion getGoblinRunSpriteSheet(){
        return goblinRunSpriteSheet;
    }

    /**
     * Getter pour la sprite sheet du skelet inactif
     * @return une TextureRegion Idle skelet spritesheet
     */
    public TextureRegion getSkeletIdleSpriteSheet(){
        return skeletIdleSpriteSheet;
    }

    /**
     * Getter pour la sprite sheet du skelet qui marche
     * @return une TextureRegion running skelet spritesheet
     */
    public TextureRegion getSkeletRunSpriteSheet(){
        return skeletRunSpriteSheet;
    }

    /**
     * Getter pour la sprite sheet du necromancer inactif
     * @return une TextureRegion Idle necromancer spritesheet
     */
    public TextureRegion getNecromancerIdleSpriteSheet(){
        return necromancerIdleSpriteSheet;
    }

    /**
     * Getter pour la sprite sheet du necromancer qui marche
     * @return une TextureRegion running necromancer spritesheet
     */
    public TextureRegion getNecromancerRunSpriteSheet(){
        return necromancerRunSpriteSheet;
    }

    /**
     * Getter pour la sprite sheet du gros ogre inactif
     * @return une TextureRegion Idle gros ogre spritesheet
     */
    public TextureRegion getBigOgreIdleSpriteSheet(){
        return bigOgreIdleSpriteSheet;
    }

    /**
     * Getter pour la sprite sheet du gros ogre qui marche
     * @return une TextureRegion running gros ogre spritesheet
     */
    public TextureRegion getBigOgreRunSpriteSheet(){
        return bigOgreRunSpriteSheet;
    }

    /**
     * Getter pour la sprite sheet de l'orc masqué inactif
     * @return une TextureRegion Idle de l'orc masqué spritesheet
     */
    public TextureRegion getOrcMaskedIdleSpriteSheet(){
        return orcMaskedIdleSpriteSheet;
    }

    /**
     * Getter pour la sprite sheet de l'orc masqué qui marche
     * @return une TextureRegion running de l'orc masqué spritesheet
     */
    public TextureRegion getOrcMaskedRunSpriteSheet(){
        return orcMaskedRunSpriteSheet;
    }

    /**
     * Getter pour la sprite sheet de l'orc shaman inactif
     * @return une TextureRegion Idle de l'orc shaman spritesheet
     */
    public TextureRegion getOrcShamanIdleSpriteSheet(){
        return orcShamanIdleSpriteSheet;
    }

    /**
     * Getter pour la sprite sheet de l'orc shaman qui marche
     * @return une TextureRegion running de l'orc shaman spritesheet
     */
    public TextureRegion getOrcShamanRunSpriteSheet(){
        return orcShamanRunSpriteSheet;
    }

    /**
     * Getter pour la sprite sheet de l'orc guerrier inactif
     * @return une TextureRegion Idle de l'orc guerrier spritesheet
     */
    public TextureRegion getOrcWarriorIdleSpriteSheet(){
        return orcWarriorIdleSpriteSheet;
    }

    /**
     * Getter pour la sprite sheet de l'orc guerrier qui marche
     * @return une TextureRegion running de l'orc guerrier spritesheet
     */
    public TextureRegion getOrcWarriorRunSpriteSheet(){
        return orcWarriorRunSpriteSheet;
    }

    /**
     * Getter pour la sprite sheet du gros démon inactif
     * @return une TextureRegion Idle gros démon spritesheet
     */
    public TextureRegion getBigDemonIdleSpriteSheet(){
        return bigDemonIdleSpriteSheet;
    }

    /**
     * Getter pour la sprite sheet du gros démon qui marche
     * @return une TextureRegion running gros démon spritesheet
     */
    public TextureRegion getBigDemonRunSpriteSheet(){
        return bigDemonRunSpriteSheet;
    }

    /**
     * Getter pour la sprite sheet du diablotin inactif
     * @return une TextureRegion Idle du diablotin spritesheet
     */
    public TextureRegion getImpIdleSpriteSheet(){
        return impIdleSpriteSheet;
    }

    /**
     * Getter pour la sprite sheet du diablotin qui marche
     * @return une TextureRegion running du diablotin spritesheet
     */
    public TextureRegion getImpRunSpriteSheet(){
        return impRunSpriteSheet;
    }

    /**
     * Getter pour la sprite sheet de démon inactif
     * @return une TextureRegion Idle de démon spritesheet
     */
    public TextureRegion getChortIdleSpriteSheet(){
        return chortIdleSpriteSheet;
    }

    /**
     * Getter pour la sprite sheet de démon qui marche
     * @return une TextureRegion running de démon spritesheet
     */
    public TextureRegion getChortRunSpriteSheet(){
        return chortRunSpriteSheet;
    }

    /**
     * Getter pour la sprite sheet de wogol inactif
     * @return une TextureRegion Idle de wogol spritesheet
     */
    public TextureRegion getWogolIdleSpriteSheet(){
        return wogolIdleSpriteSheet;
    }

    /**
     * Getter pour la sprite sheet de wogol qui marche
     * @return une TextureRegion running de wogol spritesheet
     */
    public TextureRegion getWogolRunSpriteSheet(){
        return wogolRunSpriteSheet;
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
                potionRouge, potionJaune, potionVerte, crane, sunglasses, coffreOpen,
                joueurIdleSpriteSheet.getTexture(),
                joueurRunningSpriteSheet.getTexture(),
                torcheSpriteSheet.getTexture(),
                attaqueSpriteSheet.getTexture(),
                slimeIdleSpriteSheet.getTexture(),
                slimeRunSpriteSheet.getTexture(),
                eyeBatSpriteSheet.getTexture(),
                goblinIdleSpriteSheet.getTexture(),
                goblinRunSpriteSheet.getTexture(),
                skeletIdleSpriteSheet.getTexture(),
                skeletRunSpriteSheet.getTexture(),
                necromancerIdleSpriteSheet.getTexture(),
                necromancerRunSpriteSheet.getTexture(),
                bigOgreIdleSpriteSheet.getTexture(),
                bigOgreRunSpriteSheet.getTexture(),
                orcMaskedIdleSpriteSheet.getTexture(),
                orcMaskedRunSpriteSheet.getTexture(),
                orcShamanIdleSpriteSheet.getTexture(),
                orcShamanRunSpriteSheet.getTexture(),
                orcWarriorIdleSpriteSheet.getTexture(),
                orcWarriorRunSpriteSheet.getTexture(),
                bigDemonIdleSpriteSheet.getTexture(),
                bigDemonRunSpriteSheet.getTexture(),
                chortIdleSpriteSheet.getTexture(),
                chortRunSpriteSheet.getTexture(),
                impIdleSpriteSheet.getTexture(),
                impRunSpriteSheet.getTexture(),
                wogolIdleSpriteSheet.getTexture(),
                wogolRunSpriteSheet.getTexture(),
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

