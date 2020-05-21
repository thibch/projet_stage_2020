package fr.projetstage.models.ui.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.entites.objets.ObjetsTousTypes;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.ui.*;

import java.util.ArrayList;

public class PauseScreen implements Menu{

    private GameWorld gameWorld;
    private Stage stage;

    private Text pause;
    private Text inventaire;
    private ArrayList<Item> inventaireUI;

    private UserInterface userInterface;

    private MenuButton continueBtn;
    private MenuButton mainMenu;

    /**
     * Conctructeur de l'écran de pause
     * @param stage le stage dans lequel les boutons sont acteurs
     * @param userInterface l'UserInterface pour la communication des boutons avec le jeu
     * @param gameWorld le monde dans lequel se trouve l'écran
     */
    public PauseScreen(Stage stage, UserInterface userInterface, GameWorld gameWorld){
        this.userInterface = userInterface;
        this.gameWorld = gameWorld;
        this.stage = stage;
        pause = new Text("Pause", (int)stage.getWidth()/8, Color.WHITE,new Vector2((stage.getWidth())/2f,3*(stage.getHeight()/4f)),true);
        inventaire = new Text("Inventory", (int)stage.getWidth()/13, Color.WHITE,new Vector2((stage.getWidth())/6f,2*(stage.getHeight()/3f)),true);


        continueBtn = new MenuButton(stage,this, new Vector2(3.75f*(stage.getWidth()/10f),4*(stage.getHeight()/10f)),stage.getWidth()/4f,stage.getHeight()/6f,"Continue");
        continueBtn.displayBtn(false);
        mainMenu = new MenuButton(stage,this, new Vector2(3.75f*(stage.getWidth()/10f),(stage.getHeight()/10f)),stage.getWidth()/4f,stage.getHeight()/6f,"Main Menu");
        mainMenu.displayBtn(false);

        inventaireUI = new ArrayList<>();
    }

    /**
     * Méthode permettant d'afficher les éléments du menu
     * @param batch un Batch pour afficher les éléments
     */
    public void draw(Batch batch){
        if(userInterface.isPaused()) {
            updateInventoryUI();
            batch.draw(TextureFactory.getInstance().getBackground(), 5, 5, stage.getWidth(), stage.getHeight());
            pause.draw(batch);
            inventaire.draw(batch);
        }

        continueBtn.displayBtn(userInterface.isPaused());
        mainMenu.displayBtn(userInterface.isPaused());
        for(Item item : inventaireUI){
            item.display(userInterface.isPaused());
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

    private void updateInventoryUI(){
        ArrayList<ObjetsTousTypes> tmp = gameWorld.getJoueur().getInventaire().getContenu();

        if(tmp.size() != inventaireUI.size()){
            for(Item item : inventaireUI){
                item.dispose();
            }
            inventaireUI.clear();

            int y = 6;
            int x = 1;
            Item tmpItem;
            for(ObjetsTousTypes obj : tmp){
                tmpItem = new Item(obj, stage);
                tmpItem.setPosition((x*(stage.getWidth()/16f))-(stage.getWidth()/50),y*(stage.getHeight()/12f));
                inventaireUI.add(tmpItem);
                x++;
                if( x > 4){
                    y--;
                    x = 1;
                }
            }
        }
    }
}
