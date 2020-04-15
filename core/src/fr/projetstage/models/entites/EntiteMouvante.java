package fr.projetstage.models.entites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import fr.projetstage.models.Entite;
import fr.projetstage.models.entites.attaques.Attaque;
import fr.projetstage.models.Orientation;
import fr.projetstage.models.monde.GameWorld;

public abstract class EntiteMouvante implements Entite {

    private boolean touche;
    protected Body body;
    protected GameWorld world;

    private int pointDeVie;
    private int pointdeVieMax;

    private int degats; //TODO : a definir par rapport a l'arme plus tard
    private float knockback = 3f;


    public void setPointDeVie(int pointDeVie) {
        this.pointDeVie = pointDeVie;
    }

    public void setPointdeVieMax(int pointdeVieMax) {
        this.pointdeVieMax = pointdeVieMax;
    }

    public void setDegats(int degats) {
        this.degats = degats;
    }

    public int getDegats() { return degats; }

    public int getPointDeVie() {
        return pointDeVie;
    }

    public int getPointdeVieMax() {
        return pointdeVieMax;
    }

    public void setTouche(Entite source) {
        EntiteMouvante sourceDmg = (EntiteMouvante)source;
        setPointDeVie(getPointDeVie()- sourceDmg.getDegats());

        //knockback
        Vector2 knockbackVector = new Vector2((body.getPosition().x - sourceDmg.body.getPosition().x), (body.getPosition().y - sourceDmg.body.getPosition().y));
        knockbackVector.setLength(knockback);
        body.applyLinearImpulse(knockbackVector, body.getPosition(), true);

        touche = getPointDeVie() <= 0;
    }

    public boolean getTouche(){
        return touche;
    }

    @Override
    public abstract void draw(SpriteBatch batch);

    public abstract void update(Orientation direction);
}
