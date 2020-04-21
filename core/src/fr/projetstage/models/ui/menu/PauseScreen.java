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

public class PauseScreen implements Menu{

    private Text pause;
    private UserInterface userInterface;

    private MenuButton continueBtn;
    private MenuButton mainMenu;

    /**
     * Conctructeur de l'écran de pause
     * @param stage le stage dans lequel les boutons sont acteurs
     * @param userInterface l'UserInterface pour la communication des boutons avec le jeu
     */
    public PauseScreen(Stage stage, UserInterface userInterface){
        this.userInterface = userInterface;
        pause = new Text("Pause", 160, Color.WHITE,new Vector2((Gdx.graphics.getWidth())/2f,3*(Gdx.graphics.getHeight()/4f)),true);

        continueBtn = new MenuButton(stage,this, new Vector2(3.75f*(Gdx.graphics.getWidth()/10f),4*(Gdx.graphics.getHeight()/10f)),Gdx.graphics.getWidth()/4f,Gdx.graphics.getHeight()/6f,"Continue");
        continueBtn.displayBtn(false);
        mainMenu = new MenuButton(stage,this, new Vector2(3.75f*(Gdx.graphics.getWidth()/10f),(Gdx.graphics.getHeight()/10f)),Gdx.graphics.getWidth()/4f,Gdx.graphics.getHeight()/6f,"Main Menu");
        mainMenu.displayBtn(false);
    }

    /**
     * Méthode permettant d'afficher les éléments du menu
     * @param batch un Batch pour afficher les éléments
     */
    public void draw(Batch batch){
        continueBtn.displayBtn(userInterface.isPaused());
        mainMenu.displayBtn(userInterface.isPaused());
        if(userInterface.isPaused()) {
            batch.draw(TextureFactory.getInstance().getBackground(), 5, 5, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            pause.draw(batch);
        }
    }

    /**
     * Methode permettant de libérer la mémoire
     */
    public void dispose() {
        continueBtn.dispose();
        mainMenu.dispose();
        pause.dispose();
    }

    /**
     * Methode permettant a un bouton de signaler qu'il a été cliqué
     * @param btnText le nom du bouton qui a été cliqué
     */
    @Override
    public void onClick(String btnText) {
        if(btnText.equals("Continue")){
            userInterface.setPause(!userInterface.isPaused());
        }
        else if(btnText.equals("Main Menu")){
            userInterface.setGoToMainMenu();
        }
    }
}
