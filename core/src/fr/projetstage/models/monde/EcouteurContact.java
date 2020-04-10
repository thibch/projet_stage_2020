package fr.projetstage.models.monde;

import com.badlogic.gdx.physics.box2d.*;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.entites.TypeEntite;


public class EcouteurContact implements ContactListener {

    private GameWorld world;

    public EcouteurContact(GameWorld world) {
        this.world = world;
    }

    @Override
    public void beginContact(Contact contact) {

        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if (fixtureA.getBody().getUserData() != null && fixtureB.getBody().getUserData() != null) {

            Fixture fixtureCaC = check(fixtureA, fixtureB, TypeEntite.CORPS_A_CORPS);

            if(fixtureCaC != null){
                Fixture fixtureEnnemi = check(fixtureA, fixtureB, TypeEntite.ENNEMI);
                if(fixtureEnnemi != null){
                    world.setEnnemiTouche(true, ((Type)fixtureEnnemi.getBody().getUserData()).getId());
                }
            }

            Fixture fixtureFleche = check(fixtureA, fixtureB, TypeEntite.DISTANCE);

            if(fixtureFleche != null){
                Fixture fixtureEnnemi = check(fixtureA, fixtureB, TypeEntite.ENNEMI);
                if(fixtureEnnemi != null){
                    world.setEnnemiTouche(true, ((Type)fixtureEnnemi.getBody().getUserData()).getId());
                    //Destroy fixtureFleche
                }
            }
        }else{
            //Fleches et Mur/Tables
            Fixture fixtureFleche = check(fixtureA, fixtureB, TypeEntite.DISTANCE);
            if(fixtureFleche != null){
                Fixture fixtureMur = fixtureA == fixtureFleche?fixtureB:fixtureA;
                if(fixtureMur != null){
                    //On plante la flèche dans le mur
                }
            }
        }
    }

    public Fixture check(Fixture fixtureA, Fixture fixtureB, TypeEntite entite){
        return (new Type(entite)).equals(fixtureA.getBody().getUserData())?fixtureA:(new Type(entite)).equals(fixtureB.getBody().getUserData())?fixtureB:null;
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
