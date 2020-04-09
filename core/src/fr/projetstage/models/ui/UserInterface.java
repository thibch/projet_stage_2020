package fr.projetstage.models.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import fr.projetstage.models.monde.GameWorld;

public class UserInterface {

    private float screenOffset = 0.3f;

    private GameWorld gameWorld;
    private Stage stage;

    private PauseButton pauseBtn;

    /**
     * Classe qui genere l'UI du jeu
     */
    public UserInterface(GameWorld gameWorld){
        stage = new Stage();
        this.gameWorld = gameWorld;
        float scaleX = stage.getWidth()/gameWorld.getLargeur();
        float scaleY = stage.getHeight()/gameWorld.getHauteur();
        System.out.println(scaleX);
        pauseBtn = new PauseButton(stage,new Vector2(scaleX*screenOffset,scaleY*(gameWorld.getHauteur()-2+screenOffset)),scaleX,scaleY);
    }

    /**
     * Indique ce qu'il faut dessiner dans le monde
     * @param batch la liste d'affichage
     */
    public void draw(SpriteBatch batch){
        stage.act();
        stage.draw();
    }

    public Stage getStage() {
        return stage;
    }
}
