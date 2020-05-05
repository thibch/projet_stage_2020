package fr.projetstage.models.ui.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.entites.objets.ObjetsTousTypes;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.ui.MenuButton;
import fr.projetstage.models.ui.Text;
import fr.projetstage.models.ui.UserInterface;

import java.util.ArrayList;

public class PauseScreen implements Menu{

    private GameWorld gameWorld;

    private Text pause;
    private Text inventaire;
    private UserInterface userInterface;

    private MenuButton continueBtn;
    private MenuButton mainMenu;

    /**
     * Conctructeur de l'écran de pause
     * @param stage le stage dans lequel les boutons sont acteurs
     * @param userInterface l'UserInterface pour la communication des boutons avec le jeu
     * @param gameWorld
     */
    public PauseScreen(Stage stage, UserInterface userInterface, GameWorld gameWorld){
        this.userInterface = userInterface;
        this.gameWorld = gameWorld;
        pause = new Text("Pause", 160, Color.WHITE,new Vector2((Gdx.graphics.getWidth())/2f,3*(Gdx.graphics.getHeight()/4f)),true);
        inventaire = new Text("Inventory", 80, Color.WHITE,new Vector2((Gdx.graphics.getWidth())/5f,2*(Gdx.graphics.getHeight()/3f)),true);


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
            inventaire.draw(batch);

            //affiche le contenu de l'inventaire
            ArrayList<ObjetsTousTypes> tmp = gameWorld.getJoueur().getInventaire().getContenu();
            int y = 8;
            int x = 1;
            for(ObjetsTousTypes obj : tmp){
                batch.draw(obj.getTexture(),x*(Gdx.graphics.getWidth()/20f),y*(Gdx.graphics.getHeight()/16f),96,96);
                x++;
                if( x > 5){
                    y--;
                    x = 1;
                }
            }
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
