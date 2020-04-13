package fr.projetstage.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation {

    private final TextureRegion[][] frames;
    private final float maxFrameTime;
    private float currentFrameTime;
    private int currentFrame;

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
        frames = textureRegion.split(frameWidth,textureRegion.getRegionHeight());

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
     * Remet à zéro l'animation
     */
    public void reset() {
        currentFrameTime = 0;
        currentFrame = 0;
    }
}
