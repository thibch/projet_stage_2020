package fr.projetstage;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import fr.projetstage.view.GameScreen;

public class ProjetStage extends Game implements ApplicationListener {

	private GameScreen gameScreen;

	@Override
	public void create () {
		gameScreen = new GameScreen();
		setScreen(gameScreen);
	}


	@Override
	public void dispose() {
		super.dispose();
		gameScreen.dispose();
	}
}
