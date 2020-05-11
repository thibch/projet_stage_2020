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
    private Stage stage;

    private MenuButton mainMenu;
    private UserInterface userInterface;

    /**
     * Constructeur de l'écran de gameover
     * @param stage le stage dans lequel afficher le gameover et ajouter ses boutons
     * @param userInterface l'UserInterface pour la communication des boutons avec le jeu
     */
    public GameOverScreen(Stage stage, UserInterface userInterface){
        this.stage =stage;
        this.userInterface = userInterface;
        gameOver = new Text("Disappointing", (int)stage.getWidth()/8, Color.RED,new Vector2((stage.getWidth())/2f,3*(stage.getHeight()/4f)),true);
        mainMenu = new MenuButton(stage,this, new Vector2(3.75f*(stage.getWidth()/10f),(stage.getHeight()/10f)),stage.getWidth()/4f,stage.getHeight()/6f,"Main Menu");
        mainMenu.displayBtn(false);
    }

    /**
     * methode permettant de dessiner les différents éléments constituant l'écran de gameover.
     * @param batch un Batch dans lequel afficher les elements
     */
    public void draw(Batch batch){
        mainMenu.displayBtn(userInterface.isGameOver());
        batch.draw(TextureFactory.getInstance().getBackground(),5,5,stage.getWidth(),stage.getHeight());
        gameOver.draw(batch);
    }

    /**
     * methode permettant de liberer la mémoire à la destruction.zzzzzzzzzzz
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
