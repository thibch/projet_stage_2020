package fr.projetstage.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation {

    private final TextureRegion[][] frames;
    private final float maxFrameTime;
    private float currentFrameTime;
    private int currentFrame;

    private boolean cycleDone = false;

    /**
     * Classe animation qui s'occupe d'animer des éléments à partir d'une spritesheet
     * @param textureRegion la spritesheet à animer
     * @param frameCount le nombre d'images dans la spritesheet
     * @param cycleTime le temps d'un cycle d'animation
     */
    public Animation(TextureRegion textureRegion, int frameCount, float cycleTime){
        currentFrame = 0;

        // Créé un tableau d'images à partir de la spritesheet
        int frameWidth = textureRegion.getRegionWidth()/frameCount;
        frames = textureRegion.split(frameWidth, textureRegion.getRegionHeight());

        // Définit le temps d'une image (temps linéaire)
        maxFrameTime = (cycleTime / frameCount);
    }


    /**
     * Met à jour l'image d'animation à renvoyer
     */
    public void update(){
        currentFrameTime += Gdx.graphics.getDeltaTime(); // On regarde le temps

        //Si on a dépassé le temps d'une frame alors on passe à la suivante
        if(currentFrameTime > maxFrameTime){
            currentFrame = (currentFrame+1)%(frames[0].length);
            currentFrameTime = 0;
            cycleDone = cycleDone || currentFrame == frames[0].length -1;
        }
    }

    /**
     * Methode de mise à jour utilisée pour l'arc, permettant de le garder bandé.
     */
    public void updateLast(){
        if(!cycleDone){
            currentFrameTime += Gdx.graphics.getDeltaTime(); // On regarde le temps

            //Si on a dépassé le temps d'une frame alors on passe à la suivante
            if(currentFrameTime > maxFrameTime){
                currentFrame = (currentFrame+1);
                currentFrameTime = 0;
                cycleDone = cycleDone || currentFrame == frames[0].length -1;
            }
        }
    }

    /**
     * Renvoie l'image actuelle de l'animation
     * @param flipX un booleen qui si une symetrie horizontale doit être appliquée
     * @return La texture region de la frame actuelle
     */
    public TextureRegion getFrameFlipX(boolean flipX){
        return getFrame(flipX, false);
    }

    /**
     * Renvoie l'image actuelle de l'animation
     * @param flipX un booleen qui si une symetrie horizontale doit être appliquée
     * @param flipY un booleen qui si une symetrie verticale doit être appliquée
     * @return La texture region de la frame actuelle
     */
    public TextureRegion getFrame(boolean flipX, boolean flipY){
        TextureRegion tmp = new TextureRegion(frames[0][currentFrame]);
        tmp.flip(flipX, flipY);
        return tmp;
    }

    /**
     * Methode permettant de savoir si une animation a terminé son cycle
     * @return un booléen à vrai si une animation à été jouée intégralement
     */
    public boolean isLastFrame(){
        return cycleDone && (currentFrame == 0);
    }

    /**
     * methode permettant de savoir à quelle frame en est l'animation.
     * @return un Entier,l'index de la frame que l'animation retournerait
     */
    public int getCurrentFrameCount(){
        return currentFrame;
    }

    /**
     * Remet à zéro l'animation
     */
    public void reset() {
        currentFrameTime = 0;
        currentFrame = 0;
        cycleDone = false;
    }
}
