package fr.projetstage.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.scenes.scene2d.Stage;
import fr.projetstage.ProjetStage;
import fr.projetstage.controllers.KeyboardListener;
import fr.projetstage.models.ui.menu.MainMenuScreen;

public class MenuScreen extends ScreenAdapter {

    private ProjetStage mainStage;

    private Stage stage;
    private MainMenuScreen mainMenuScreen;

    private final KeyboardListener keyboardListener;

    public MenuScreen(ProjetStage mainStage){
        this.mainStage = mainStage;
        stage = new Stage();
        mainMenuScreen = new MainMenuScreen(stage);

        keyboardListener = new KeyboardListener();
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(keyboardListener);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(54/255f, 57/255f, 63/255f, 1); // background color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        mainMenuScreen.draw(stage.getBatch());
        stage.getBatch().end();

        stage.act();
        stage.draw();

        update();
    }

    public void update(){
        if(mainMenuScreen.isBeginClicked()){
            mainStage.startGame(mainMenuScreen.getName(),mainMenuScreen.getSeed());
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
        mainMenuScreen.dispose();
    }
}
