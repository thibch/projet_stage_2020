package fr.projetstage.models.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.monde.GameWorld;

public class ImageWeapon extends Actor {

    private final float screenOffset;

    private GameWorld world;

    public ImageWeapon(Stage stage, GameWorld world, Vector2 position, float width, float height, float screenOffset) {
        super();
        this.world = world;
        this.setPosition(position.x, position.y);
        this.setWidth(width);
        this.setHeight(height);
        this.screenOffset = screenOffset;
        stage.addActor(this);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        // Affiche l'arme selectionnée
        if(world.getJoueur().isSwitchedWeapon()){ // Epee
            batch.draw(TextureFactory.getInstance().getSwordUI(),getX(), getY(),getWidth(),getHeight());
        }
        else{ // Arc
            batch.draw(TextureFactory.getInstance().getBowUI(), getX(), getY(), getWidth()/2f, getHeight()/2f, getWidth(), getHeight(), 1, 1, 45,0,0, TextureFactory.getInstance().getBowUI().getWidth(), TextureFactory.getInstance().getBowUI().getHeight(), false, false);
        }
    }
}
