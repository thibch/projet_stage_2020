package fr.projetstage.models.chargement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import fr.projetstage.models.monde.GameWorld;

public class Transition {

    private ShapeRenderer shapeRenderer;
    private Vector2 start;
    private Vector2 transi;
    private Vector2 end;
    private boolean milieuTransi;

    private boolean estEnCours;
    private GameWorld gameWorld;
    private Camera camera;

    public Transition(GameWorld gameWorld) {
        shapeRenderer = new ShapeRenderer();
        this.gameWorld = gameWorld;
        reset();
    }

    public void startTransi(){
        estEnCours = true;
    }

    public void update(){
        if(estEnCours){
            transi.interpolate(end, 0.02f, Interpolation.linear);
            if(transi.epsilonEquals(end, 0.005f)){
                transi = new Vector2(end);
                end = new Vector2(start);
                start = new Vector2(transi);
                estEnCours = !milieuTransi;
                milieuTransi = true;
            }
        }
    }

    public boolean estEnCours(){
        return estEnCours;
    }

    public boolean estMilieuTransi(){
        return milieuTransi;
    }

    public boolean isFinished(){
        return !estEnCours && milieuTransi;
    }

    public void draw(){
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, transi.y);
        shapeRenderer.rect(-2, -2, gameWorld.getLargeur(), gameWorld.getLargeur());
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    public void reset() {
        start = new Vector2(0, 0);
        transi = new Vector2(start);
        end = new Vector2(1, 1);
        milieuTransi = false;
        estEnCours = false;
    }

    public void setCamera(Camera camera){
        this.camera = camera;
    }
}
