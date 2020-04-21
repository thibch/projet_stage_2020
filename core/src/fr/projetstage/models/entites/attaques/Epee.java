package fr.projetstage.models.entites.attaques;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Orientation;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.entites.TypeEntite;
import fr.projetstage.models.monde.GameWorld;

public class Epee extends Projectile{

    private final Body body;
    private final Body bodyParent;
    private final GameWorld gameWorld;

    private Orientation direction;
    private float[] angle = {-180, 20, -90, 90};

    /**
     * On génère le corps de l'épée
     * @param gameWorld le gameWorld
     * @param bodyParent le body du lanceur d'attaque
     * @param longueur la longeur de l'épée
     * @param largeur la largeur de l'épée
     */
    public Epee(GameWorld gameWorld, Body bodyParent, float longueur, float largeur, Orientation orientation){
        this.gameWorld = gameWorld;
        this.direction = orientation;
        this.bodyParent = bodyParent;
        // Make the body spawn at orientation dans rotate
        // BodyDef
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(bodyParent.getPosition().x + (9f / 16f), bodyParent.getPosition().y + (4f / 16f)));

        // Récupération du body dans le world
        body = this.gameWorld.getWorld().createBody(bodyDef);

        // Création de la shape pour l'epée
        Vector2 posShape = new Vector2(0, 0); // La position du shape est en fonction de la position du body
        Vector2[] vertices = new Vector2[4];
        vertices[0] = posShape;
        vertices[1] = new Vector2(posShape.x + longueur, posShape.y);
        vertices[2] = new Vector2(posShape.x + longueur, posShape.y+largeur);
        vertices[3] = new Vector2(posShape.x, posShape.y + largeur);

        PolygonShape rectangle = new PolygonShape();
        rectangle.set(vertices);

        // FixtureDef
        FixtureDef fixtureDef1 = new FixtureDef();
        fixtureDef1.shape = rectangle;
        fixtureDef1.isSensor = true;
        fixtureDef1.density = 0.00001f;
        fixtureDef1.restitution = 0f;
        fixtureDef1.friction = 0f;
        //

        // Met en place la fixture sur le body
        body.setFixedRotation(true);
        body.createFixture(fixtureDef1); // Association à l’objet
        body.setUserData(new Type(TypeEntite.CORPS_A_CORPS));

        rectangle.dispose();

        // On met en place les jointures entre les attaques au CaC et le joueur
        RevoluteJointDef rjd = new RevoluteJointDef();
        rjd.initialize(bodyParent, body, new Vector2(bodyParent.getPosition().x + (9f / 16f), bodyParent.getPosition().y + (4f / 16f)));

        this.gameWorld.getWorld().createJoint(rjd);
    }

    /**
     * Définit l'angle sans changer la position de l'épée
     * @param currentAngle l'angle voulus (en radians)
     */
    public void setAngle(float currentAngle) {
        body.setTransform(body.getPosition(), currentAngle);
    }

    /**
     * Lorsque l'attaque est finit on arrête (détruit le body de l'épée, l'objet est inutilisable après cette commande)
     */
    public void stop() {
        gameWorld.getWorld().destroyBody(body);
    }

    @Override
    public void draw(SpriteBatch batch, float x, float y) {
        super.draw(batch, x, y);
        batch.draw(TextureFactory.getInstance().getEpee(), x + bodyParent.getPosition().x + 0.3f, y + body.getPosition().y + 0.1f, 0.25f, 0, 0.5f, 1, 1, 1, (body.getAngle()*(180/3.1415926f))+(angle[direction.getIndice()]+direction.getRotation()),0,0,TextureFactory.getInstance().getEpee().getWidth(), TextureFactory.getInstance().getEpee().getHeight(), false, false);
    }
}
