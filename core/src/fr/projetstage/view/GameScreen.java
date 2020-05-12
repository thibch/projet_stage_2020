package fr.projetstage.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import fr.projetstage.ProjetStage;
import fr.projetstage.controllers.KeyboardListener;
import fr.projetstage.dataFactories.SoundFactory;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.ui.UserInterface;

import java.util.Random;

/**
 * Classe s'occupant de l'affichage du jeu
 */
public class GameScreen extends ScreenAdapter {

    private ProjetStage mainStage;

    private OrthographicCamera cameraEnv;
    private final SpriteBatch listeAffEnv;

    private OrthographicCamera cameraUI;
    private final SpriteBatch listeAffUI;

    private final Box2DDebugRenderer box2DDebugRenderer;

    private final GameWorld gameWorld;
    private final UserInterface userInterface;

    private final KeyboardListener keyboardListener;

    private float currentTime;
    private final float transitionTime = 2f;
    private boolean transitionEntreSalle;
    private float xTransition = 0f;
    private float yTransition = 0f;

    //private Transition transition;


    /**
     * initialise une partie de jeu
     * @param mainStage la fenetre de jeu
     */
    public GameScreen(ProjetStage mainStage){
        this(mainStage, "Bob", new Random().nextInt());
    }

    /**
     * Initialise une partie de jeu
     * @param mainStage la fenetre de jeu
     * @param name le nom du joueur
     * @param seed la graine de generation du monde
     */
    public GameScreen(ProjetStage mainStage, String name, int seed){
        this.mainStage = mainStage;
        currentTime = 0;
        listeAffEnv = new SpriteBatch();
        listeAffUI = new SpriteBatch();

        box2DDebugRenderer = new Box2DDebugRenderer();

        gameWorld = new GameWorld(seed, this);
        userInterface = new UserInterface(gameWorld);

        keyboardListener = new KeyboardListener();

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(keyboardListener);
        inputMultiplexer.addProcessor(userInterface.getStage());
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    /**
     * Instancie les caméras du monde et de l'UI
     */
    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        cameraEnv = new OrthographicCamera(gameWorld.getLargeur(), gameWorld.getHauteur());
        cameraEnv.position.set(gameWorld.getLargeur()/2f - 2, gameWorld.getHauteur()/2f - 2,0); // -2 est le decalage pour les murs
        cameraEnv.update();

        cameraUI = new OrthographicCamera(gameWorld.getLargeur(), gameWorld.getHauteur());
        cameraUI.position.set(gameWorld.getLargeur()/2f - 2, gameWorld.getHauteur()/2f - 2,0); // -2 est le decalage pour les murs
        cameraUI.update();

        gameWorld.getChargement().setCamera(cameraUI);

        //transition = new Transition(cameraUI);

        userInterface.getStage().getViewport().update(width,height,true);
    }

    /**
     * Methode qui affiche l'écran du jeu
     * @param delta le temps d'actualisation de l'affichage
     */
    @Override
    public void render(float delta) {
        super.render(delta);
        listeAffEnv.setProjectionMatrix(cameraEnv.combined);
        listeAffUI.setProjectionMatrix(cameraUI.combined);

        Gdx.gl.glClearColor(54/255f, 57/255f, 63/255f, 1); // background color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Affichage environnement de jeu
        listeAffEnv.begin();
        if(keyboardListener.isAfficheDebug()){
            box2DDebugRenderer.render(gameWorld.getWorld(), cameraEnv.combined); // On affiche le Debug si on a appuyé sur la touche du debug
        }else{
            gameWorld.draw(listeAffEnv);
        }

        update(delta); //On met à jour la physique du monde

        listeAffEnv.end();

        // Affichage de l'interface
        userInterface.draw(listeAffUI);
    }

    /**
     * Met à jour le monde physique
     * @param delta le temps d'actualisation de l'affichage
     */
    public void update(float delta){
        if(!gameWorld.estEnTransition() && !userInterface.isGameOver() && !userInterface.isPaused()){
            Vector2 force = keyboardListener.getAcceleration();

            gameWorld.getJoueur().move(force);
            gameWorld.getWorld().step(delta,6,2);

            gameWorld.getJoueur().update(keyboardListener.getDirection());
            gameWorld.getJoueur().setWeapon(keyboardListener.isSwitchWeapon());
            gameWorld.update();
        }

        if(gameWorld.estEnTransition()){
            gameWorld.update();
            Vector2 transi = gameWorld.getChargement().getTransitionCamera();
            cameraEnv.position.set(transi.x + gameWorld.getLargeur()/2f - 2, transi.y + gameWorld.getHauteur()/2f - 2,0);
            cameraEnv.update();
        }
    }

    /**
     * Methode qui libere les listes d'affichages
     */
    @Override
    public void dispose() {
        super.dispose();
        listeAffEnv.dispose();
        listeAffUI.dispose();
        userInterface.dispose();
        TextureFactory.getInstance().dispose();
        SoundFactory.getInstance().dispose();
    }
}
