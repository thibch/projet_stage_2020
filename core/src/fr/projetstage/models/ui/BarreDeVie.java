package fr.projetstage.models.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.monde.GameWorld;

public class BarreDeVie extends Actor {

    private Stage stage;
    private GameWorld gameWorld;

    /**
     * Instancie la barre de vie du joueur au niveau de l'interface
     * @param stage le stage dans lequel afficher la barre
     * @param gameWorld le monde contenant les informations
     * @param position la position de la barre de vie
     * @param width la largeur de la barre de vie
     * @param height la hauteur de la barre de vie
     */
    public BarreDeVie(Stage stage, GameWorld gameWorld, Vector2 position, float width, float height) {
        this.stage = stage;
        this.gameWorld = gameWorld;
        this.setPosition(position.x, position.y);
        this.setWidth(width);
        this.setHeight(height);
        stage.addActor(this);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        // Affiche les PV du joueur
        int cpt = 0;
        //Coeurs plein
        for(int i = 0; i < (int)(gameWorld.getJoueur().getPointDeVie())/2; i++){
            batch.draw(TextureFactory.getInstance().getCoeurPlein(), i*getWidth() + getX(),getY(), getWidth(), getHeight());
            cpt++;
        }
        //Coeur à moitié plein
        if((int)(gameWorld.getJoueur().getPointDeVie())%2 == 1){
            batch.draw(TextureFactory.getInstance().getCoeurMoitie(), cpt*getWidth() + getX(),getY(), getWidth(), getHeight());
            cpt++;
        }
        //Coeurs vide
        for(int i = 0; i < ((gameWorld.getJoueur().getPointDeVieMax())-(int)(gameWorld.getJoueur().getPointDeVie()))/2; i++){
            batch.draw(TextureFactory.getInstance().getCoeurVide(), cpt*getWidth() + getX(),getY(), getWidth(), getHeight());
            cpt++;
        }

    }
}
