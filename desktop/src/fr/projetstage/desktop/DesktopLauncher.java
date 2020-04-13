package fr.projetstage.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import fr.projetstage.ProjetStage;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.a = 8; // Met en place le canal alpha à 8 bits
		config.height = 720; // Taille fenêtre en hauteur
		config.width = 1024; // Taille fenêtre en largeur
		new LwjglApplication(new ProjetStage(), config);
	}
}
