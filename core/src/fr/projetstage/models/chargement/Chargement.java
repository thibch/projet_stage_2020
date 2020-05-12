package fr.projetstage.models.chargement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.projetstage.models.Orientation;
import fr.projetstage.models.monde.GameWorld;

public class Chargement {

    private GameWorld gameWorld;
    private float currentTime;
    private float transitionTime;
    private Vector2 transitionCamera;
    private Orientation directionTransition;

    private boolean transitionEntreSalles;
    private Transition transitionEtage;
    private float xSalle;
    private float ySalle;

    public Chargement(GameWorld gameWorld){
        this.gameWorld = gameWorld;
        directionTransition = null;
        currentTime = 0;
        transitionTime = 2f;
        xSalle = 0;
        ySalle = 0;
        transitionCamera = new Vector2(0, 0);
        transitionEntreSalles = false;
        transitionEtage = new Transition(gameWorld);
    }


    public void update() {
        if (gameWorld.estEnTransition()) {
            if(gameWorld.estEnTransition() && !transitionEntreSalles && !transitionEtage.estEnCours()){
                Vector2 transi = gameWorld.getTransition(); // On dit au monde de mettre à jour la salle suivante en fonction de l'orientation
                if(directionTransition == Orientation.NO_ORIENTATION){
                    transitionEtage.startTransi();
                }else{
                    xSalle = transi.x; //Position à faire en fonction de l'orientation
                    ySalle = transi.y;
                    transitionEntreSalles = true;
                }
            }

            if(directionTransition == Orientation.NO_ORIENTATION){
                transitionEtage.update();
                if(transitionEtage.isFinished()){
                    gameWorld.finTransition();
                    transitionEtage.reset();
                }else if(transitionEtage.estEnCours()){
                    if(transitionEtage.estMilieuTransi()){
                        gameWorld.miTransition();
                    }
                }
            }else{
                float delta = Gdx.graphics.getDeltaTime();
                currentTime += delta;
                if (currentTime < transitionTime) {
                    transitionCamera.x += xSalle * (delta / transitionTime);
                    transitionCamera.y += ySalle * (delta / transitionTime);
                }else{
                    transitionCamera.x = 0;
                    transitionCamera.y = 0;
                    transitionEntreSalles = false;
                    currentTime = 0;
                    gameWorld.finTransition();
                }
            }

        }
    }

    public void setDirectionTransition(Orientation directionTransition){
        this.directionTransition = directionTransition;
    }

    public Orientation getDirectionTransition(){
        return this.directionTransition;
    }

    public Vector2 getTransitionCamera(){
        return transitionCamera;
    }

    public void draw() {
        if(directionTransition == Orientation.NO_ORIENTATION){
            transitionEtage.draw();
        }
    }

    public void setCamera(Camera cam) {
        transitionEtage.setCamera(cam);
    }
}
