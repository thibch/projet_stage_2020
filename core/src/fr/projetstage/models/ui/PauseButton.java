package fr.projetstage.models.ui;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import fr.projetstage.dataFactories.SoundFactory;
import fr.projetstage.dataFactories.TextureFactory;

public class PauseButton {

    private ImageButton imageButton;

    public PauseButton(Stage stage, Vector2 position, float width, float height){
        imageButton = new ImageButton(new TextureRegionDrawable(TextureFactory.getInstance().getPauseBtn()),new TextureRegionDrawable(TextureFactory.getInstance().getPauseBtnPressed()));
        imageButton.getImage().setFillParent(true);
        imageButton.setPosition(position.x,position.y);
        imageButton.setSize(width,height);
        imageButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                SoundFactory.getInstance().playsoundDeath(100);
            }
        });
        stage.addActor(imageButton);
    }
}
