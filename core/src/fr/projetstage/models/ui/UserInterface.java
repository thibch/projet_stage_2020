package fr.projetstage.models.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.ui.menu.GameOverScreen;
import fr.projetstage.models.ui.menu.PauseScreen;

public class UserInterface {

    private float screenOffset = 0.3f;

    private GameWorld gameWorld;
    private Stage stage;

    private Boolean isPaused = false;
    private PauseButton pauseBtn;
    private PauseScreen pauseScreen;

    private GameOverScreen gameOverScreen;
    private boolean isGameOver = false;

    private boolean goToMainMenu = false;

    private Text munitions;

    /**
     * Classe qui génère l'UI du jeu
     */
    public UserInterface(GameWorld gameWorld){
        stage = new Stage();
        this.gameWorld = gameWorld;
        float scaleX = stage.getWidth()/gameWorld.getLargeur();
        float scaleY = stage.getHeight()/gameWorld.getHauteur();
        pauseBtn = new PauseButton(stage, new Vector2(scaleX*screenOffset,scaleY*(gameWorld.getHauteur()-2+screenOffset)),scaleX,scaleY,this);

        gameOverScreen = new GameOverScreen(stage,this);
        pauseScreen = new PauseScreen(stage, this);
        munitions = new Text("", 65, Color.WHITE, new Vector2(1.4f*(Gdx.graphics.getWidth())/16f,13*(Gdx.graphics.getHeight()/16f)), true);
    }

    /**
     * Indique ce qu'il faut dessiner dans le monde
     * @param batch la liste d'affichage
     */
    public void draw(SpriteBatch batch){
        stage.act();

        //Affiche le texte
        stage.getBatch().begin();
        if(gameWorld.getJoueur().getPointDeVie() <= 0){
            gameOverScreen.draw(stage.getBatch());
        }
        munitions.setTextContent("" + gameWorld.getJoueur().getMunition());
        munitions.draw(stage.getBatch());
        stage.getBatch().end();

        batch.begin();
        // Affiche l'arme selectionnée
        if(gameWorld.getJoueur().isSwitchedWeapon()){ // Epee
            batch.draw(TextureFactory.getInstance().getSwordUI(),-1-screenOffset,gameWorld.getHauteur()-4-screenOffset,1,1);
        }
        else{ // Arc
            batch.draw(TextureFactory.getInstance().getBowUI(), -1-screenOffset, gameWorld.getHauteur()-4-screenOffset, 1f/2f, 1f/2f, 1, 1, 1, 1, 45,0,0, TextureFactory.getInstance().getBowUI().getWidth(), TextureFactory.getInstance().getBowUI().getHeight(), false, false);
        }


        // Affiche les PV du joueur
        int cpt = 0;
        //Coeurs plein
        for(int i = 0; i < gameWorld.getJoueur().getPointDeVie()/2; i++){
            batch.draw(TextureFactory.getInstance().getCoeurPlein(),i,gameWorld.getHauteur()-3-screenOffset,1,1);
            cpt++;
        }
        //Coeur à moitié plein
        if(gameWorld.getJoueur().getPointDeVie()%2 == 1){
            batch.draw(TextureFactory.getInstance().getCoeurMoitie(),cpt,gameWorld.getHauteur()-3-screenOffset,1,1);
            cpt++;
        }
        //Coeurs vide
        for(int i = 0; i < (gameWorld.getJoueur().getPointdeVieMax()-gameWorld.getJoueur().getPointDeVie())/2; i++){
            batch.draw(TextureFactory.getInstance().getCoeurVide(),cpt,gameWorld.getHauteur()-3-screenOffset,1,1);
            cpt++;
        }


        batch.draw(TextureFactory.getInstance().getArrowUI(), -1-screenOffset, gameWorld.getHauteur()-5-screenOffset, 1f/2f, 1f/2f, 1, 1, 1, 1, 45,0,0, TextureFactory.getInstance().getBowUI().getWidth(), TextureFactory.getInstance().getBowUI().getHeight(), false, false);



        batch.end();

        //Affiche les menus
        stage.getBatch().begin();
        isGameOver = gameWorld.getJoueur().getPointDeVie() <= 0;
        if(isGameOver){
            gameOverScreen.draw(stage.getBatch());
        }
        else{
            pauseScreen.draw(stage.getBatch());
        }

        stage.getBatch().end();
        stage.draw();
    }

    public Stage getStage() {
        return stage;
    }

    public boolean isPaused(){
        return isPaused;
    }

    public boolean isGameOver() { return isGameOver; }

    public void setPause(boolean bool){
        isPaused = bool;
    }

    public void setGoToMainMenu(){
        goToMainMenu = true;
    }

    public boolean goToMainMenu(){
        return goToMainMenu;
    }

    public void dispose() {
        stage.dispose();
        gameOverScreen.dispose();
    }
}
