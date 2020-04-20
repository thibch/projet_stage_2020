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

    public void setTextContent(String textContent){
        this.textContent = textContent;
    }

    public void draw(Batch batch){
        fontText.draw(batch, textContent, position.x, position.y);
    }

    public void setPosition(Vector2 position){
        this.position = position;
    }

    public void dispose() {
        fontText.dispose();
    }
}
