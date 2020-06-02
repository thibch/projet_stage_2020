package fr.projetstage.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import fr.projetstage.ProjetStage;
import fr.projetstage.controllers.KeyboardListener;
import fr.projetstage.dataFactories.SoundFactory;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Animation;
import fr.projetstage.models.ui.menu.MainMenuScreen;

public class MenuScreen extends ScreenAdapter {

    private ProjetStage mainStage;
    private boolean musicLaunched = false;

    private Stage stage;
    private MainMenuScreen mainMenuScreen;

    private final KeyboardListener keyboardListener;

    private Animation slime;
    private Animation goblin;

    /**
     * Constructeur de l'écran titre du jeu
     * @param mainStage le stage dans lequel afficher l'écran titre
     */
    public MenuScreen(ProjetStage mainStage){
        this.mainStage = mainStage;
        stage = new Stage();

        mainMenuScreen = new MainMenuScreen(stage);

        keyboardListener = new KeyboardListener();
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(keyboardListener);
        Gdx.input.setInputProcessor(inputMultiplexer);

        if(!musicLaunched){
            SoundFactory.getInstance().loopMusic(0.1f);
            musicLaunched = true;
        }

        slime = new Animation(TextureFactory.getInstance().getSlimeIdleSpriteSheet(),6,0.8f);
        goblin = new Animation(TextureFactory.getInstance().getGoblinIdleSpriteSheet(),6,0.8f);
    }

    /**
     * Methode qui affiche à l'écran les éléments de l'écran titre
     * @param delta le temps d'actualisation du moteur
     */
    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(54/255f, 57/255f, 63/255f, 1); // background color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        stage.getBatch().draw(TextureFactory.getInstance().getTitleScreen(),0,0,stage.getWidth(),stage.getHeight());

        slime.update();
        stage.getBatch().draw(slime.getFrame(false,false),6*(stage.getWidth()/20f),11*(stage.getHeight()/14f), stage.getWidth()/20f,stage.getHeight()/14f);
        stage.getBatch().draw(slime.getFrame(true,false),17*(stage.getWidth()/20f),7*(stage.getHeight()/14f), stage.getWidth()/20f,stage.getHeight()/14f);

        goblin.update();
        stage.getBatch().draw(goblin.getFrame(false,false),9*(stage.getWidth()/20f),11.5f*(stage.getHeight()/14f), stage.getWidth()/20f,stage.getHeight()/14f);
        stage.getBatch().draw(goblin.getFrame(true,false),10*(stage.getWidth()/20f),11.5f*(stage.getHeight()/14f), stage.getWidth()/20f,stage.getHeight()/14f);


        mainMenuScreen.draw(stage.getBatch());
        stage.getBatch().end();

        stage.act();
        stage.draw();

        update();
    }

    /**
     * Methode permettant de mettre a jour les éléments non graphiques
     */
    public void update(){
        if(mainMenuScreen.isBeginClicked()){
            mainStage.startGame(mainMenuScreen.getName(),mainMenuScreen.getSeed());
        }
    }

    /**
     * Gere le changement de taille de la fenetre
     */
    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().update(width,height,true);
    }

    /**
     * Methode de libération de la mémoire
     */
    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
        mainMenuScreen.dispose();
    }
}
