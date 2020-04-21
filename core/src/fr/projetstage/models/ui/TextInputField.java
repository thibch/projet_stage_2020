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
    private Menu menu;

    BitmapFont fontText;

    public TextInputField(Stage stage, Menu menu, Vector2 position, float width, float height, String defaultText){
        this.menu = menu;
        this.stage = stage;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/BitPotionExt.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 80;
        fontText = generator.generateFont(parameter);
        fontText.getRegion().getTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = fontText;
        textFieldStyle.fontColor = Color.BLACK;
        textFieldStyle.background = new TextureRegionDrawable(TextureFactory.getInstance().getInputText());
        textFieldStyle.background.setLeftWidth(textFieldStyle.background.getLeftWidth()+20);
        textFieldStyle.cursor = new TextureRegionDrawable(TextureFactory.getInstance().getTextCursor());
        textFieldStyle.selection = new TextureRegionDrawable(TextureFactory.getInstance().getTextSelect());

        inputText = new TextField(defaultText,textFieldStyle);
        inputText.setPosition(position.x, position.y);
        inputText.setSize(width,height);

        stage.addActor(inputText);
    }

    public void display(boolean bool){
        if(bool){
            stage.addActor(inputText);
        }
        else{
            inputText.addAction(Actions.removeActor());
        }

    }

    public String getText(){
        return inputText.getText();
    }

    public void setFilter(TextField.TextFieldFilter filter){
        inputText.setTextFieldFilter(filter);
    }

    public void setMaxLength(int max){
        inputText.setMaxLength(max);
    }

    public void dispose() {
        fontText.dispose();
    }
}
