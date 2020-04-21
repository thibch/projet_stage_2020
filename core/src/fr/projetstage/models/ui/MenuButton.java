package fr.projetstage.models.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.ui.menu.Menu;

public class MenuButton {

    private ImageTextButton imageButton;
    private Stage stage;
    BitmapFont fontText;

    /**
     * Constructeur permettant de creer un bouton avec du texte
     * @param stage le stage dans lequel le bouton est acteur
     * @param menu le menu dans lequel se trouve le bouton
     * @param position un Vector2 de la position du bouton dans le menu
     * @param width un float de la largeur du bouton
     * @param height un float de la hauteur du bouton
     * @param text un String le texte dans le bouton
     */
    public MenuButton(Stage stage, Menu menu, Vector2 position, float width, float height, String text){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/BitPotionExt.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 160;
        fontText = generator.generateFont(parameter);
        fontText.getRegion().getTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        imageButton = new ImageTextButton(text,new ImageTextButton.ImageTextButtonStyle(new TextureRegionDrawable(TextureFactory.getInstance().getBtn()), new TextureRegionDrawable(TextureFactory.getInstance().getBtnPressed()),new TextureRegionDrawable(TextureFactory.getInstance().getBtnPressed()),fontText));
        imageButton.getImage().setFillParent(true);
        imageButton.setPosition(position.x, position.y);
        imageButton.setSize(width,height);

        imageButton.getLabel().setColor(Color.BLACK);
        imageButton.getLabel().setFontScale(0.5f);

        imageButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                imageButton.setChecked(false);
                menu.onClick(text);
            }
        });
        this.stage = stage;
        stage.addActor(imageButton);
    }

    /**
     * Methode permettant d'afficher ou non un bouton dans le menu
     * @param bool le booleen d'affichage vrai affiche, faux n'affiche pas.
     */
    public void displayBtn(boolean bool){
        if(bool){
            stage.addActor(imageButton);
        }
        else{
            imageButton.addAction(Actions.removeActor());
        }

    }

    /**
     * methode permettant de liberer la mémoire.
     */
    public void dispose() {
        fontText.dispose();
    }
}
