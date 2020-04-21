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

    public GameOverScreen(Stage stage, UserInterface userInterface){
        this.userInterface = userInterface;
        gameOver = new Text("Disappointing", 160, Color.RED,new Vector2((Gdx.graphics.getWidth())/2f,2*(Gdx.graphics.getHeight()/3f)),true);
        mainMenu = new MenuButton(stage,this, new Vector2(3.75f*(Gdx.graphics.getWidth()/10f),(Gdx.graphics.getHeight()/10f)),Gdx.graphics.getWidth()/4f,Gdx.graphics.getHeight()/6f,"Main Menu");
        mainMenu.displayBtn(false);
    }

    public void draw(Batch batch){
        mainMenu.displayBtn(userInterface.isGameOver());
        batch.draw(TextureFactory.getInstance().getBackground(),5,5,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        gameOver.draw(batch);
    }

    public void dispose() {
        gameOver.dispose();
        mainMenu.dispose();
    }

    @Override
    public void onClick(String btnText) {
        if(btnText.equals("Main Menu")){
            userInterface.setGoToMainMenu();
        }

    }
}
