package fr.projetstage.models.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;

public class Transition {

    private ShapeRenderer shapeRenderer;
    private Vector2 start;
    private Vector2 transi;
    private Vector2 end;
    private boolean milieuTransi;

    private boolean estEnCours;

    public Transition(Camera camera) {
        camera.update();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        reset();
    }

    public void startTransi(){
        estEnCours = true;
    }

    public void update(){
        if(estEnCours){
            transi.interpolate(end, 0.2f, Interpolation.smooth2);
            if(transi.epsilonEquals(end, 0.01f)){
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

    public void draw(SpriteBatch batch){
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, transi.y);
        shapeRenderer.rect(-2, -2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
}
