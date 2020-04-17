package fr.projetstage.models.monde;

import com.badlogic.gdx.physics.box2d.*;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.entites.TypeEntite;
import fr.projetstage.models.monde.salle.solEtMurs.Piege;


public class EcouteurContact implements ContactListener {

    private final GameWorld world;

    Fixture fixtureA;
    Fixture fixtureB;

    public EcouteurContact(GameWorld world) {
        this.world = world;
    }

    @Override
    public void beginContact(Contact contact) {

        fixtureA = contact.getFixtureA();
        fixtureB = contact.getFixtureB();

        if (fixtureA.getBody().getUserData() != null && fixtureB.getBody().getUserData() != null) {

            Fixture fixtureCaC = check(fixtureA, fixtureB, TypeEntite.CORPS_A_CORPS);

            if(fixtureCaC != null){
                Fixture fixtureEnnemi = check(fixtureA, fixtureB, TypeEntite.ENNEMI);
                if(fixtureEnnemi != null){
                    world.setEnnemiTouche(((Type)fixtureEnnemi.getBody().getUserData()).getId(), world.getJoueur());
                }
            }

            Fixture fixtureFleche = check(fixtureA, fixtureB, TypeEntite.DISTANCE);

            if(fixtureFleche != null){
                Fixture fixtureEnnemi = check(fixtureA, fixtureB, TypeEntite.ENNEMI);
                if(fixtureEnnemi != null){
                    world.setEnnemiTouche(((Type)fixtureEnnemi.getBody().getUserData()).getId(), world.getJoueur());
                    // Destroy fixtureFleche
                }
            }

            Fixture fixtureJoueur = check(fixtureA, fixtureB, TypeEntite.JOUEUR);

            if(fixtureJoueur != null) {
                Fixture fixtureEnnemi = check(fixtureA, fixtureB, TypeEntite.ENNEMI);
                if (fixtureEnnemi != null) {
                    world.setJoueurTouche(world.getEnnemi((((Type) fixtureEnnemi.getBody().getUserData()).getId())));
                }
                Fixture fixturePickUp = check(fixtureA, fixtureB, TypeEntite.PICKUP);
                if (fixturePickUp != null) {
                    System.out.println("Potion.");
                    world.setPickUpTaken(((Type) fixturePickUp.getBody().getUserData()).getId());
                }
            }

            Fixture fixturePiege = check(fixtureA, fixtureB, TypeEntite.PIEGE);

            if(fixturePiege != null) {
                Fixture fixtureEnnemi = check(fixtureA, fixtureB, TypeEntite.ENNEMI);
                if(fixtureEnnemi != null){
                    world.getEnnemi((((Type)fixturePiege.getBody().getUserData()).getId())).addTarget(world.getEnnemi((((Type)fixturePiege.getBody().getUserData()).getId())));
                }

                fixtureJoueur = check(fixtureA, fixtureB, TypeEntite.JOUEUR);
                // Joueur
                if(fixtureJoueur != null){
                    world.getEnnemi((((Type)fixturePiege.getBody().getUserData()).getId())).addTarget(world.getJoueur());
                }
            }
        }else{
            // Fleche et Mur/Tables
            Fixture fixtureFleche = check(fixtureA, fixtureB, TypeEntite.DISTANCE);
            if(fixtureFleche != null){
                Fixture fixtureMur = fixtureA == fixtureFleche?fixtureB:fixtureA;
                if(fixtureMur != null){
                    // On plante la flèche dans le mur
                }
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
        fixtureA = contact.getFixtureA();
        fixtureB = contact.getFixtureB();

        Fixture fixturePiege = check(fixtureA, fixtureB, TypeEntite.PIEGE);

        if(fixturePiege != null) {
            Fixture fixtureJoueur = check(fixtureA, fixtureB, TypeEntite.JOUEUR);
            // Joueur
            if(fixtureJoueur != null){
                world.getEnnemi((((Type)fixturePiege.getBody().getUserData()).getId())).removeTarget(world.getJoueur());
            }
            Fixture fixtureEnnemi = check(fixtureA, fixtureB, TypeEntite.ENNEMI);
            if(fixtureEnnemi != null){
                world.getEnnemi((((Type)fixturePiege.getBody().getUserData()).getId())).removeTarget(world.getEnnemi((((Type)fixturePiege.getBody().getUserData()).getId())));
            }
        }
    }

    /**
     * Vérifie si la fixtureA correspond à entite, sinon vérifie si la fixtureB correspond à entite, sinon renvoie null
     * (Tout les vérifications sont faites dans le UserData du body de la fixture)
     * @param fixtureA première ficture vérifié
     * @param fixtureB seconde ficture vérifié
     * @param entite le typeEntité vérifié
     * @return la fixture qui correspond à entite
     */
    public Fixture check(Fixture fixtureA, Fixture fixtureB, TypeEntite entite){
        return (new Type(entite)).equals(fixtureA.getBody().getUserData())?fixtureA:(new Type(entite)).equals(fixtureB.getBody().getUserData())?fixtureB:null;
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
