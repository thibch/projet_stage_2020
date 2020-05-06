package fr.projetstage.models.entites.objets.objetsAuSol;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.monde.GameWorld;

public class PotionVieRouge extends Consommable {

    public PotionVieRouge(GameWorld world, Vector2 position, int id) {
        super(world, position, new Vector2(4f/16f,4f/16f), 7f/16f, 10f/16f, id);
    }

    @Override
    public void applyEffect() {
        if(!world.getJoueur().estMaxPointDeVie()){
            world.getJoueur().setPointDeVie(world.getJoueur().getPointDeVie()+2);
            detruit = true;
        }else{
            setTouche(false);
        }
    }

    @Override
    public Texture getTexture() {
        return TextureFactory.getInstance().getPotionRouge();
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void update(){
        super.update();
    }

    @Override
    public void draw(SpriteBatch batch, float x, float y){
        batch.draw(TextureFactory.getInstance().getPotionRouge(), x + getPosition().x, y + getPosition().y, 1, 1);
    }
}
