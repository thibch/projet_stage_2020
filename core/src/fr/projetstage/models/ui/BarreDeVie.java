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
        for(int i = 0; i < gameWorld.getJoueur().getPointDeVie()/2; i++){
            batch.draw(TextureFactory.getInstance().getCoeurPlein(), i*getWidth() + getX(),getY(), getWidth(), getHeight());
            cpt++;
        }
        //Coeur à moitié plein
        if(gameWorld.getJoueur().getPointDeVie()%2 == 1){
            batch.draw(TextureFactory.getInstance().getCoeurMoitie(), cpt*getWidth() + getX(),getY(), getWidth(), getHeight());
            cpt++;
        }
        //Coeurs vide
        for(int i = 0; i < (gameWorld.getJoueur().getPointdeVieMax()-gameWorld.getJoueur().getPointDeVie())/2; i++){
            batch.draw(TextureFactory.getInstance().getCoeurVide(), cpt*getWidth() + getX(),getY(), getWidth(), getHeight());
            cpt++;
        }

    }
}
