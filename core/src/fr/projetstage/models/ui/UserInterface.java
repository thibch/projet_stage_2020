package fr.projetstage.models.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.monde.GameWorld;

public class UserInterface {

    private float screenOffset = 0.3f;

    private GameWorld gameWorld;
    private Stage stage;

    private PauseButton pauseBtn;

    private Text gameOver;

    /**
     * Classe qui génère l'UI du jeu
     */
    public UserInterface(GameWorld gameWorld){
        stage = new Stage();
        this.gameWorld = gameWorld;
        float scaleX = stage.getWidth()/gameWorld.getLargeur();
        float scaleY = stage.getHeight()/gameWorld.getHauteur();
        pauseBtn = new PauseButton(stage, new Vector2(scaleX*screenOffset,scaleY*(gameWorld.getHauteur()-2+screenOffset)),scaleX,scaleY);

        gameOver = new Text("Disappointing", 160, Color.RED,new Vector2((Gdx.graphics.getWidth())/2f,2*(Gdx.graphics.getHeight()/3f)),true);
    }

    /**
     * Indique ce qu'il faut dessiner dans le monde
     * @param batch la liste d'affichage
     */
    public void draw(SpriteBatch batch){
        stage.act();
        stage.draw();

        //Affiche le texte
        stage.getBatch().begin();
        if(gameWorld.getJoueur().getPointDeVie() <= 0){
            gameOver.draw(stage.getBatch());
        }
        stage.getBatch().end();

        batch.begin();
        // Affiche l'arme selectionnée
        if(gameWorld.getJoueur().isSwitchedWeapon()){ // Epee
            batch.draw(TextureFactory.getInstance().getSwordUI(),-1,gameWorld.getHauteur()-4-screenOffset,1,1);
        }
        else{ // Arc
            batch.draw(TextureFactory.getInstance().getBowUI(), -1, gameWorld.getHauteur()-4-screenOffset, 1f/2f, 1f/2f, 1, 1, 1, 1, 45,0,0, TextureFactory.getInstance().getBowUI().getWidth(), TextureFactory.getInstance().getBowUI().getHeight(), false, false);
        }


        // Affiche les PV du joueur
        int cpt = 0;
        //Coeurs plein
        for(int i = 0; i < gameWorld.getJoueur().getPointDeVie()/2; i++){
            batch.draw(TextureFactory.getInstance().getCoeurPlein(), i,gameWorld.getHauteur()-3-screenOffset,1,1);
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


        batch.draw(TextureFactory.getInstance().getArrowUI(), -1, gameWorld.getHauteur()-5-screenOffset, 1f/2f, 1f/2f, 1, 1, 1, 1, 45,0,0, TextureFactory.getInstance().getBowUI().getWidth(), TextureFactory.getInstance().getBowUI().getHeight(), false, false);

        //Affichage texte


        batch.end();
    }

    public Stage getStage() {
        return stage;
    }

    public void dispose() {
        stage.dispose();
        gameOver.dispose();
    }
}
