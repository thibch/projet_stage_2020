package fr.projetstage;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import fr.projetstage.dataFactories.SoundFactory;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.view.GameScreen;
import fr.projetstage.view.MenuScreen;

public class ProjetStage extends Game implements ApplicationListener {

	private MenuScreen menuScreen;
	private GameScreen gameScreen;

	@Override
	public void create () {
		menuScreen = new MenuScreen(this);
		setScreen(menuScreen);
	}

	public void startGame(String name, int seed) {
		gameScreen = new GameScreen(this,name,seed);
		setScreen(gameScreen);
	}

	@Override
	public void dispose() {
		super.dispose();
		TextureFactory.getInstance().dispose();
		SoundFactory.getInstance().dispose();
		menuScreen.dispose();
		if(gameScreen != null){
			gameScreen.dispose();
		}
	}
}
