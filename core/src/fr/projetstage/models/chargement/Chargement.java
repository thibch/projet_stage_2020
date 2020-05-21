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

    /**
     * Classe qui s'occupe de la transition entre les salles / étages
     * @param gameWorld le monde
     */
    public Chargement(GameWorld gameWorld){
        this.gameWorld = gameWorld;
        directionTransition = null;
        currentTime = 0;
        transitionTime = 1f;
        xSalle = 0;
        ySalle = 0;
        transitionCamera = new Vector2(0, 0);
        transitionEntreSalles = false;
        transitionEtage = new Transition(gameWorld);
    }

    /**
     * Methode de mise à jour du chargement
     */
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

    /**
     * Methode permettant de définir la direction de la transition à faire à l'écran
     * @param directionTransition la direction de la transition
     */
    public void setDirectionTransition(Orientation directionTransition){
        this.directionTransition = directionTransition;
    }

    /**
     * Methode retournant la direction actuelle de la transition
     * @return l'Orientation de la transition
     */
//TODO Agrandir le chargement sur l'interface
    public Orientation getDirectionTransition(){
        return this.directionTransition;
    }

    /**
     * Methode qui retourne le vecteur de transition de la caméra
     * @return un Vector2 de la transition
     */
    public Vector2 getTransitionCamera(){
        return transitionCamera;
    }

    /**
     * Methode qui dessine la transition entre les étages
     */
    public void draw() {
        if(directionTransition == Orientation.NO_ORIENTATION){
            transitionEtage.draw();
        }
    }

    /**
     * Methode permettant de définir la caméra sur lequel doit s'effectuer la transition
     * @param cam la caméra
     */
    public void setCamera(Camera cam) {
        transitionEtage.setCamera(cam);
    }
}
