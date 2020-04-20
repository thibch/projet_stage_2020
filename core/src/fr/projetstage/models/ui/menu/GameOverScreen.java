package fr.projetstage.models.ui.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.ui.Text;

public class GameOverScreen implements Menu{

    private Text gameOver;

    public GameOverScreen(){
        gameOver = new Text("Disappointing", 160, Color.RED,new Vector2((Gdx.graphics.getWidth())/2f,2*(Gdx.graphics.getHeight()/3f)),true);
    }

    public void draw(Batch batch){
        batch.draw(TextureFactory.getInstance().getBackground(),5,5,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        gameOver.draw(batch);
    }

    public void dispose() {
        gameOver.dispose();
    }

    @Override
    public void onClick(String clickListener) {

    }
}
