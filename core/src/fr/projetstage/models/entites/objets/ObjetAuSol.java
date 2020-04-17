package fr.projetstage.models.entites.objets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Entite;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.entites.TypeEntite;
import fr.projetstage.models.monde.GameWorld;

public abstract class ObjetAuSol implements Entite {

    private final Body body;
    protected GameWorld world;

    protected boolean detruit;
    private boolean touche;

    public ObjetAuSol(GameWorld world, Vector2 position, Vector2 posShape, float largeur, float hauteur){
        this.world = world;

        // BodyDef
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position);
        //

        // Récupération du body dans le world
        body = world.getWorld().createBody(bodyDef);

        CircleShape circleShape = new CircleShape();
        Vector2 posShapeCircle = new Vector2(posShape.x + largeur/2f, posShape.y + hauteur/2f);
        circleShape.setPosition(posShapeCircle);
        circleShape.setRadius(largeur/2f);

        // FixtureDef
        FixtureDef fixtureDef1 = new FixtureDef();
        fixtureDef1.shape = circleShape;
        fixtureDef1.isSensor = false;
        fixtureDef1.density = 0;
        fixtureDef1.restitution = 0f;
        fixtureDef1.friction = 0f;
        fixtureDef1.filter.groupIndex = (short)-2;

        // Met en place la fixture sur le body
        body.setFixedRotation(true);
        body.createFixture(fixtureDef1); // Association à l’objet

        body.setUserData(new Type(TypeEntite.PICKUP));

        circleShape.dispose();
    }


    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(TextureFactory.getInstance().getPotionRouge(), body.getPosition().x, body.getPosition().y, 1, 1);
    }

    public abstract void applyEffect();

    public void setTouche(boolean b){
        this.touche = b;
    }
    public boolean getTouche(){
        return this.touche;
    }

    public Body getBody(){
        return body;
    }

    public void update() {
        //si le joueur est a plus d'une certaine distance on ralentit la potion
        if(Math.abs(body.getWorldCenter().x - world.getJoueur().getX()) >= 0.8f || Math.abs(body.getWorldCenter().y - world.getJoueur().getY()) >= 0.8f){
            body.setLinearVelocity(new Vector2(0.80f * body.getLinearVelocity().x,0.80f * body.getLinearVelocity().y));
        }
    }

    public boolean estDetruit() {
        return detruit;
    }
}
