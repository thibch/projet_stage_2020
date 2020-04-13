package fr.projetstage.models.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.monde.GameWorld;

public class UserInterface {

    private float screenOffset = 0.3f;

    private GameWorld gameWorld;
    private Stage stage;

    private PauseButton pauseBtn;

    /**
     * Classe qui génère l'UI du jeu
     */
    public UserInterface(GameWorld gameWorld){
        stage = new Stage();
        this.gameWorld = gameWorld;
        float scaleX = stage.getWidth()/gameWorld.getLargeur();
        float scaleY = stage.getHeight()/gameWorld.getHauteur();
        pauseBtn = new PauseButton(stage, new Vector2(scaleX*screenOffset,scaleY*(gameWorld.getHauteur()-2+screenOffset)),scaleX,scaleY);
    }

    /**
     * Indique ce qu'il faut dessiner dans le monde
     * @param batch la liste d'affichage
     */
    public void draw(SpriteBatch batch){
        stage.act();
        stage.draw();

        batch.begin();
        // Affiche l'arme selectionnée
        batch.draw(TextureFactory.getInstance().getEpee(),0,gameWorld.getHauteur()-3-screenOffset,1,1);


        // Affiche les PV du joueur
        int cpt = 1;
        //Coeurs plein
        for(int i = 0; i < gameWorld.getJoueur().getPointDeVie()/2; i++){
            batch.draw(TextureFactory.getInstance().getCoeurPlein(),i+1,gameWorld.getHauteur()-3-screenOffset,1,1);
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

        batch.end();
    }

    public Stage getStage() {
        return stage;
    }
}
