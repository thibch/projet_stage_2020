package fr.projetstage.models.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.ui.menu.Menu;

public class TextInputField {

    private TextField inputText;

    private Stage stage;

    BitmapFont fontText;

    /**
     * Constructeur d'un champ de saisie utilisateur
     * @param stage le stage dans lequel le champ est acteur
     * @param menu le menu dans lequel se trouve le champ
     * @param position la position du champ à l'écran
     * @param width la largeur du champ
     * @param height la hauteur du champ
     * @param defaultText le texte dans le champ par défaut
     */
    public TextInputField(Stage stage, Menu menu, Vector2 position, float width, float height, String defaultText){
        this.stage = stage;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/BitPotionExt.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int)stage.getWidth()/13;
        fontText = generator.generateFont(parameter);
        fontText.getRegion().getTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = fontText;
        textFieldStyle.fontColor = Color.BLACK;
        textFieldStyle.background = new TextureRegionDrawable(TextureFactory.getInstance().getInputText());
        textFieldStyle.background.setLeftWidth(textFieldStyle.background.getLeftWidth()+stage.getWidth()/50);
        textFieldStyle.cursor = new TextureRegionDrawable(TextureFactory.getInstance().getTextCursor());
        textFieldStyle.selection = new TextureRegionDrawable(TextureFactory.getInstance().getTextSelect());

        inputText = new TextField(defaultText,textFieldStyle);
        inputText.setPosition(position.x, position.y);
        inputText.setSize(width,height);

        stage.addActor(inputText);
    }

    /**
     * Methode permettant d'afficher ou non l'élement à l'écran
     * @param bool un booléen, affichage si vrai.
     */
    public void display(boolean bool){
        if(bool){
            stage.addActor(inputText);
        }
        else{
            inputText.addAction(Actions.removeActor());
        }

    }

    /**
     * Methode permettant de récupérer le contenu du champ
     * @return un String du contenu du champ
     */
    public String getText(){
        return inputText.getText();
    }

    /**
     * Methode permettant d'appliquer un filtre pour controler la saisie utilisateur
     * @param filter un TextFieldFilter à appliquer au champ
     */
    public void setFilter(TextField.TextFieldFilter filter){
        inputText.setTextFieldFilter(filter);
    }

    /**
     * Methode permettant de définir le nombre maximal de caractères
     * @param max un Etnier du maximum de caractères
     */
    public void setMaxLength(int max){
        inputText.setMaxLength(max);
    }

    /**
     * methode permettant de liberer la mémoire à la destruction.
     */
    public void dispose() {
        fontText.dispose();
    }
}
