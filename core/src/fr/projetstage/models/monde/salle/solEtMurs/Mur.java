package fr.projetstage.models.monde.salle.solEtMurs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.Orientation;

public class Mur extends NonDestructible {

    private Orientation orientation;
    private TypeMur numMur;

    /**
     * Constructeur d'un mur dans le monde
     * @param world le monde dans lequel se trouve le mur
     * @param position la position du mur dans le monde
     * @param orientationMur l'orientation du mur dans le monde
     * @param wallNumberTexture le numero de texture associé au mur
     */
    public Mur(GameWorld world, Vector2 position, Orientation orientationMur, TypeMur wallNumberTexture){
        super(world, position, 1f, 1f);
        this.orientation = orientationMur;
        numMur = wallNumberTexture;
    }

    public void draw(SpriteBatch batch, float x, float y){
        batch.draw(numMur.getTexture(), x + getPosition().x, y + getPosition().y, tailleX/2f, tailleY/2f,
                tailleX, tailleY, 1, 1, orientation.getRotation(),0,0, numMur.getTexture().getWidth(), numMur.getTexture().getHeight(), false, false);
    }

    /**
     * Methode permettant de récuperer le numéro de mur
     * @return un TypeMur contenant le numero de mur
     */
    public TypeMur getNumMur() {
        return numMur;
    }

    /**
     * Methode permettant de récuperer l'orientation du mur dans le monde
     * @return un objet Orientation indiquant l'angle du mur dans le monde
     */
    public Orientation getOrientation() {
        return orientation;
    }
}
