package fr.projetstage.models.monde.salle.solEtMurs;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.monde.salle.solEtMurs.meubles.Obstacle;

public abstract class NonDestructible extends Obstacle {


    protected float tailleX;
    protected float tailleY;

    public NonDestructible(GameWorld world, Vector2 position, float tailleX, float tailleY){
        super(world, position);

        this.tailleX = tailleX;
        this.tailleY = tailleY;
    }

    @Override
    public void generateBody(){
        // Création de la shape pour le héros
        Vector2 posShape = new Vector2(0, 0); // La position du shape est en fonction de la position du body
        Vector2[] vertices = new Vector2[5];
        vertices[0] = posShape;
        vertices[1] = new Vector2(posShape.x + tailleX, posShape.y);
        vertices[2] = new Vector2(posShape.x + tailleX, posShape.y + tailleY);
        vertices[3] = new Vector2(posShape.x, posShape.y + tailleY);
        vertices[4] = posShape;

        ChainShape rectangle = new ChainShape();
        rectangle.createChain(vertices);

        fixtureDef = new FixtureDef();
        fixtureDef.shape = rectangle;
        fixtureDef.density = 1f;
        fixtureDef.restitution = 0f;
        fixtureDef.friction = 0f;

        super.generateBody();

        rectangle.dispose();
    }

    @Override
    public boolean estNonDestructible() {
        return true;
    }
}
