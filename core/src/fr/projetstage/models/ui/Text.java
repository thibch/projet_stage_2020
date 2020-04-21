package fr.projetstage.models.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;

public class Text{

    private String textContent;
    private Vector2 position;

    private BitmapFont fontText;
    private GlyphLayout layout;

    /**
     * Constructeur de la classe Text permettant d'afficher du texte à l'écran
     * @param text un String à afficher.
     * @param size la taille de la police
     * @param color la couleur de la police
     * @param position la positon du texte à l'écran
     * @param centerText un booleen pour centrer le texte ou non
     */
    public Text(String text, int size, Color color, Vector2 position, boolean centerText){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/BitPotionExt.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.color = color;
        fontText = generator.generateFont(parameter);
        fontText.getRegion().getTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        layout = new GlyphLayout(fontText, text);
        textContent = text;

        this.position = position;

        if(centerText){
            this.position = new Vector2(position.x-(layout.width/2f),position.y);
        }
    }

    /**
     * Methode pour changer le texte
     * @param textContent un String du nouveau texte
     */
    public void setTextContent(String textContent){
        this.textContent = textContent;
    }

    /**
     * methode permettant de dessiner les différents éléments constituant l'écran de gameover.
     * @param batch un Batch dans lequel afficher les elements
     */
    public void draw(Batch batch){
        fontText.draw(batch, textContent, position.x, position.y);
    }

    /**
     * methode permettant de liberer la mémoire à la destruction.
     */
    public void dispose() {
        fontText.dispose();
    }
}
