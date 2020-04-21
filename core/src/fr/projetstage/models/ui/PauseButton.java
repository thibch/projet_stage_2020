package fr.projetstage.models.ui;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import fr.projetstage.dataFactories.TextureFactory;

public class PauseButton {

    private final ImageButton imageButton;

    /**
     * Constructeur du bouton de pause (ce bouton est une exception car il n'appartient à aucun Menu)
     * @param stage le stage dans lequel le bouton est acteur
     * @param position un Vector2 de la position du bouton
     * @param width un float definissant la largeur du bouton
     * @param height un float definissant la hauteur du bouton
     * @param userInterface l'UserInterface avec laquelle communique le bouton
     */
    public PauseButton(Stage stage, Vector2 position, float width, float height, UserInterface userInterface){
        imageButton = new ImageButton(new TextureRegionDrawable(TextureFactory.getInstance().getPauseBtn()), new TextureRegionDrawable(TextureFactory.getInstance().getPauseBtnPressed()));
        imageButton.getImage().setFillParent(true);
        imageButton.setPosition(position.x, position.y);
        imageButton.setSize(width,height);
        imageButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                userInterface.setPause(!userInterface.isPaused());
            }
        });
        stage.addActor(imageButton);
    }
}
