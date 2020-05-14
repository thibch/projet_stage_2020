package fr.projetstage.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import fr.projetstage.ProjetStage;

public class DesktopLauncher {

	/**
	 * Constructeur pour lancer le jeu en mode Desktop
	 * @param arg inutiles.
	 */
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.a = 8; // Met en place le canal alpha à 8 bits
		config.width = 1024; // Taille fenêtre en largeur
		config.height = 720; // Taille fenêtre en hauteur
		new LwjglApplication(new ProjetStage(), config);
	}
}
