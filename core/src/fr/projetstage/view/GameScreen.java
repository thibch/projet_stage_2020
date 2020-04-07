package fr.projetstage.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.projetstage.dataFactories.SoundFactory;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.monde.GameWorld;

/**
 * Classe s'occupant de l'affichage du jeu
 */
public class GameScreen extends ScreenAdapter {

    private SpriteBatch listeAffImg;
    private OrthographicCamera cameraEnv;
    private GameWorld gameWorld;

    /**
     * initialise une partie de jeu
     */
    public GameScreen(){
        listeAffImg = new SpriteBatch();
        gameWorld = new GameWorld();
    }

    /**
     * Instancie les caméras du monde et de l'UI
     */
    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        cameraEnv = new OrthographicCamera(GameWorld.getLargeur(), GameWorld.getHauteur());
        cameraEnv.position.set(GameWorld.getLargeur()/2f, GameWorld.getHauteur()/2f,0);
        cameraEnv.update();
    }

    /**
     * Methode qui affiche l'écran du jeu
     * @param delta le temps d'actualisation de l'affichage
     */
    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(54/255f, 57/255f, 63/255f, 1); //background color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        listeAffImg.setProjectionMatrix(cameraEnv.combined);
        listeAffImg.begin();
        gameWorld.draw(listeAffImg);
        update();
        listeAffImg.end();
    }

    /**
     * Met à jour le monde physique
     */
    public void update(){
        gameWorld.getWorld().step(Gdx.graphics.getDeltaTime(),6,2);
    }

    /**
     * Methode qui libere les listes d'affichages
     */
    @Override
    public void dispose() {
        super.dispose();
        listeAffImg.dispose();
        TextureFactory.getInstance().dispose();
        SoundFactory.getInstance().dispose();
    }
}
