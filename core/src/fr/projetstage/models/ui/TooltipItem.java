package fr.projetstage.models.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import fr.projetstage.dataFactories.TextureFactory;

public class TooltipItem{

    private BitmapFont fontText;
    private Label.LabelStyle labelStyle;
    private TextTooltip.TextTooltipStyle tooltipStyle;
    private TextTooltip textTooltip;

    /**
     * Constructeur de la classe Text permettant d'afficher du texte à l'écran
     * @param text un String à afficher.
     */
    public TooltipItem(String text){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/BitPotionExt.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 48;
        parameter.color = Color.WHITE;
        fontText = generator.generateFont(parameter);
        fontText.getRegion().getTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        labelStyle = new Label.LabelStyle(fontText,Color.WHITE);
        tooltipStyle = new TextTooltip.TextTooltipStyle(labelStyle,new TextureRegionDrawable(TextureFactory.getInstance().getBackground()));

        textTooltip = new TextTooltip(text, tooltipStyle);
        textTooltip.getManager().instant();
    }

    public TextTooltip getTextTooltip(){
        return textTooltip;
    }

    /**
     * methode permettant de liberer la mémoire à la destruction.
     */
    public void dispose() {
        fontText.dispose();
    }
}
