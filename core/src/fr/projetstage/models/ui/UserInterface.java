package fr.projetstage.models.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.ui.menu.GameOverScreen;
import fr.projetstage.models.ui.menu.PauseScreen;
import fr.projetstage.models.ui.menu.VictoryScreen;

public class UserInterface {

    private float screenOffset = 0.3f;

    private GameWorld gameWorld;
    private Stage stage;

    private Boolean isPaused = false;
    private PauseButton pauseBtn;
    private PauseScreen pauseScreen;
    private ImageWeapon imageArme;
    private BarreDeVie barreDeVie;
    private Minimap minimap;


    private GameOverScreen gameOverScreen;
    private VictoryScreen victoryScreen;
    private boolean isGameOver = false;

    private boolean goToMainMenu = false;

    private Text munitions;

    /**
     * Constructeur de l'interface en jeu
     * @param gameWorld avec lequel l'interface communique
     */
    public UserInterface(GameWorld gameWorld){
        stage = new Stage();
        this.gameWorld = gameWorld;
        float scaleX = stage.getWidth()/gameWorld.getLargeur();
        float scaleY = stage.getHeight()/gameWorld.getHauteur();
        pauseBtn = new PauseButton(stage, new Vector2(scaleX*screenOffset,scaleY*(gameWorld.getHauteur()-2+screenOffset)),scaleX,scaleY,this);
        imageArme = new ImageWeapon(stage, gameWorld, new Vector2(scaleX*(1-screenOffset),scaleY*(gameWorld.getHauteur()-3+screenOffset)),scaleX,scaleY, screenOffset);
        barreDeVie = new BarreDeVie(stage, gameWorld, new Vector2(scaleX*(2+screenOffset),scaleY*(gameWorld.getHauteur()-2+screenOffset)), scaleX, scaleY);

        // Image de la flèche
        Image imageArrow = new Image(TextureFactory.getInstance().getArrowUI());
        imageArrow.setBounds(scaleX*(1-screenOffset), scaleY*(gameWorld.getHauteur()-4+screenOffset), scaleX, scaleY);
        imageArrow.setOrigin(scaleX/2f, scaleY/2f);
        imageArrow.rotateBy(45);
        stage.addActor(imageArrow);
        //

        minimap = new Minimap(stage, gameWorld, new Vector2(17*scaleX, 11.5f*scaleY), scaleX/2f, scaleY/2f);


        gameOverScreen = new GameOverScreen(stage,this);
        victoryScreen = new VictoryScreen(stage,this);
        pauseScreen = new PauseScreen(stage, this, gameWorld);
        munitions = new Text("", 65, Color.WHITE, new Vector2(1.4f*(Gdx.graphics.getWidth())/16f,scaleY*(gameWorld.getHauteur()-3)), true);
    }

    /**
     * Indique ce qu'il faut dessiner dans le monde
     */
    public void draw(){
        stage.act();

        //Affiche le texte
        stage.getBatch().begin();
        munitions.setTextContent("" + gameWorld.getJoueur().getMunition());
        munitions.draw(stage.getBatch());
        stage.getBatch().end();

        //Affiche les menus
        stage.getBatch().begin();
        isGameOver = gameWorld.getJoueur().getPointDeVie() <= 0;
        if(isGameOver){
            gameOverScreen.draw(stage.getBatch());
        }else if(gameWorld.estFinDuMonde()){
            victoryScreen.draw(stage.getBatch());
        }else{
            pauseScreen.draw(stage.getBatch());
        }

        stage.getBatch().end();
        stage.draw();
    }

    /**
     * methode permettant d'obetnir le stage de l'interface
     * @return le Stage de l'interface
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Methode permettant de recuperer l'état du jeu en pause
     * @return un booléen, vrai si le jeu est en pause.
     */
    public boolean isPaused(){
        return isPaused;
    }

    /**
     * Methode permettant de recuperer l'état du jeu de gameover
     * @return un booléen, vrai si le joueur est mort.
     */
    public boolean isGameOver() { return isGameOver; }

    /**
     * Methode permettant de changer l'état de pause du jeu
     * @param bool le nouvel étatde pause, vrai = en pause
     */
    public void setPause(boolean bool){
        isPaused = bool;
    }

    /**
     * Permet au bouton "main menu" d'indiquer que le joueur veut retourner à l'écran titre
     */
    public void setGoToMainMenu(){
        goToMainMenu = true;
    }

    /**
     * Methode permettant a l'écran de jeu de savoir si le joueur à cliquer sur un bouton deretour à l'écran titre
     * @return un booléen, vrai si le joueur désire aller à l'écran titre
     */
    public boolean goToMainMenu(){
        return goToMainMenu;
    }

    /**
     * methode permettant de liberer la mémoire à la destruction.
     */
    public void dispose() {
        stage.dispose();
        gameOverScreen.dispose();
        pauseScreen.dispose();
    }
}
