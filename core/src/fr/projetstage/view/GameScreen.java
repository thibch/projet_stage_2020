package fr.projetstage.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import fr.projetstage.controllers.KeyboardListener;
import fr.projetstage.dataFactories.SoundFactory;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.ui.UserInterface;

/**
 * Classe s'occupant de l'affichage du jeu
 */
public class GameScreen extends ScreenAdapter {

    private OrthographicCamera cameraEnv;
    private SpriteBatch listeAffEnv;

    private OrthographicCamera cameraUI;
    private SpriteBatch listeAffUI;

    private Box2DDebugRenderer box2DDebugRenderer;

    private GameWorld gameWorld;
    private UserInterface userInterface;

    private KeyboardListener keyboardListener;
    /**
     * initialise une partie de jeu
     */
    public GameScreen(){
        listeAffEnv = new SpriteBatch();
        listeAffUI = new SpriteBatch();

        box2DDebugRenderer = new Box2DDebugRenderer();

        gameWorld = new GameWorld();
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
        cameraEnv.position.set(gameWorld.getLargeur()/2f -2, gameWorld.getHauteur()/2f -2,0); //-2 est le decalage pour les murs
        cameraEnv.update();

        cameraUI = new OrthographicCamera(gameWorld.getLargeur(), gameWorld.getHauteur());
        cameraUI.position.set(gameWorld.getLargeur()/2f-2, gameWorld.getHauteur()/2f-2,0); //-2 est le decalage pour les murs
        cameraUI.update();
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

        Gdx.gl.glClearColor(54/255f, 57/255f, 63/255f, 1); //background color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //affichage environnement de jeu
        listeAffEnv.begin();
        if(keyboardListener.isAfficheDebug()){
            box2DDebugRenderer.render(gameWorld.getWorld(), cameraEnv.combined); //On affiche le Debug si on a appuyé sur la touche du clavier
        }else{
            gameWorld.draw(listeAffEnv);
        }
        update();
        listeAffEnv.end();

        //affichage de l'interface
        userInterface.draw(listeAffUI);
    }

    /**
     * Met à jour le monde physique
     */
    public void update(){
        Vector2 force = new Vector2(0, 0);
        force.add(keyboardListener.getAcceleration());
        gameWorld.getJoueur().move(force);
        gameWorld.getWorld().step(Gdx.graphics.getDeltaTime(),6,2);

        gameWorld.getJoueur().setDirection(keyboardListener.getDirection());
        gameWorld.update();
    }

    /**
     * Methode qui libere les listes d'affichages
     */
    @Override
    public void dispose() {
        super.dispose();
        listeAffEnv.dispose();
        listeAffUI.dispose();
        TextureFactory.getInstance().dispose();
        SoundFactory.getInstance().dispose();
    }
}
