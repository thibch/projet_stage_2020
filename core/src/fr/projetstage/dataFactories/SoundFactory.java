package fr.projetstage.dataFactories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * @author Thibault Choné
 * Singleton : Banque de sons
 */
public class SoundFactory {

    private static SoundFactory instance;
    private static Sound soundDeath;

    /**
     * Met en place les sons dans la banque de sons
     */
    private SoundFactory() {
        instance = this;
        soundDeath = Gdx.audio.newSound(Gdx.files.internal("sounds/death.mp3"));
    }

    /**
     * Méthode de singleton
     * @return instance du singleton
     */
    public static SoundFactory getInstance(){
        if(instance == null){
            instance = new SoundFactory();
        }
        return instance;
    }

    /**
     * Joue le son de mort avec le volume donné
     * @param volume volume du son joué
     */
    public void playsoundDeath(float volume) {
        soundDeath.play(volume);
    }

    public void dispose(){
        soundDeath.dispose();
    }
}
