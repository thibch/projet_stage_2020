package fr.projetstage.models.ui.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.ui.MenuButton;
import fr.projetstage.models.ui.Text;
import fr.projetstage.models.ui.UserInterface;

public class GameOverScreen implements Menu{

    private Text gameOver;

    private MenuButton mainMenu;
    private UserInterface userInterface;

    /**
     * Constructeur de l'écran de gameover
     * @param stage le stage dans lequel afficher le gameover et ajouter ses boutons
     * @param userInterface l'UserInterface pour la communication des boutons avec le jeu
     */
    public GameOverScreen(Stage stage, UserInterface userInterface){
        this.userInterface = userInterface;
        gameOver = new Text("Disappointing", 160, Color.RED,new Vector2((Gdx.graphics.getWidth())/2f,2*(Gdx.graphics.getHeight()/3f)),true);
        mainMenu = new MenuButton(stage,this, new Vector2(3.75f*(Gdx.graphics.getWidth()/10f),(Gdx.graphics.getHeight()/10f)),Gdx.graphics.getWidth()/4f,Gdx.graphics.getHeight()/6f,"Main Menu");
        mainMenu.displayBtn(false);
    }

    /**
     * methode permettant de dessiner les différents éléments constituant l'écran de gameover.
     * @param batch un Batch dans lequel afficher les elements
     */
    public void draw(Batch batch){
        mainMenu.displayBtn(userInterface.isGameOver());
        batch.draw(TextureFactory.getInstance().getBackground(),5,5,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        gameOver.draw(batch);
    }

    /**
     * methode permettant de liberer la mémoire à la destruction.
     */
    public void dispose() {
        gameOver.dispose();
        mainMenu.dispose();
    }

    /**
     * Methode permettant a un bouton de signaler qu'il a été cliqué
     * @param btnText le nom du bouton qui a été cliqué
     */
    @Override
    public void onClick(String btnText) {
        if(btnText.equals("Main Menu")){
            userInterface.setGoToMainMenu();
        }
    }
}
