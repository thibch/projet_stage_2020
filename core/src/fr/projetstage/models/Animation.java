package fr.projetstage.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {

    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int currentFrame;

    private TextureRegion tmp;

    public Animation(TextureRegion textureRegion, int frameCount, float cycleTime){
        currentFrame = 0;
        //cree un tableau d'images à partir de la spritesheet
        frames = new Array<>();
        int frameWidth = textureRegion.getRegionWidth()/frameCount;
        for(int i = 0; i < frameCount; i++){
            frames.add(new TextureRegion(textureRegion,i*frameWidth,0,frameWidth, textureRegion.getRegionHeight()));
        }
        //definit le temps d'une image
        maxFrameTime = cycleTime / frameCount;

    }

    public void update(){
        currentFrameTime += Gdx.graphics.getDeltaTime();
        if(currentFrameTime > maxFrameTime){
            currentFrame= (currentFrame+1)%(frames.size-1);
            currentFrameTime = 0;
        }
    }

    public TextureRegion getFrame(boolean flipX){
        tmp = new TextureRegion(frames.get(currentFrame));
        tmp.flip(flipX,false);
        return tmp;
    }
}
