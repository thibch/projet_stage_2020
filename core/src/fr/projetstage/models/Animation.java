package fr.projetstage.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation {

    private TextureRegion[][] frames;
    private float maxFrameTime;
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
        //cree un tableau d'images à partir de la spritesheet
        int frameWidth = textureRegion.getRegionWidth()/frameCount;
        frames = textureRegion.split(frameWidth,textureRegion.getRegionHeight());

        //definit le temps d'une image
        maxFrameTime = cycleTime / frameCount;
    }

    /**
     * met à jour l'image d'animation à renvoyer
     */
    public void update(){
        currentFrameTime += Gdx.graphics.getDeltaTime();
        if(currentFrameTime > maxFrameTime){
            currentFrame = (currentFrame+1)%(frames[0].length);
            currentFrameTime = 0;
        }
    }

    /**
     * Renvoie l'image actuelle de l'animation
     * @param flipX un booleen qui si une symetrie horizontale doit être appliquée
     * @return une texture region de l'image actuelle
     */
    public TextureRegion getFrameFlipX(boolean flipX){
        return getFrame(flipX, false);
    }

    public TextureRegion getFrameFlipY(boolean flipY){
        return getFrame(false, flipY);
    }

    public TextureRegion getFrame(boolean flipX, boolean flipY){
        TextureRegion tmp = new TextureRegion(frames[0][currentFrame]);
        tmp.flip(flipX, flipY);
        return tmp;
    }

}
