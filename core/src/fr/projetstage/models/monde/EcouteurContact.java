package fr.projetstage.models.monde;

import com.badlogic.gdx.physics.box2d.*;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.entites.TypeEntite;


public class EcouteurContact implements ContactListener {

    private final GameWorld world;

    /**
     * Ecouteur de contact entre deux body
     * @param world le gameworld
     */
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
                    try{
                        world.getEnnemi((((Type)fixtureEnnemi.getBody().getUserData()).getId())).addTarget(world.getJoueur());
                    }
                    catch(Exception e){
                        System.out.println("Collision Error !"); //TODO : a voir pk ça fait ça
                    }
                }
                Fixture fixturePickUp = check(fixtureA, fixtureB, TypeEntite.PICKUP);
                if (fixturePickUp != null) {
                    world.setPickUpTaken(((Type) fixturePickUp.getBody().getUserData()).getId());
                }
                Fixture fixturePorte = check(fixtureA, fixtureB, TypeEntite.PORTE);
                if (fixturePorte != null) {
                    world.setPorteTouched(((Type) fixturePorte.getBody().getUserData()).getId());
                }
            }

            //debut de contact entre piege et joueur / ennemi
            Fixture fixturePiege = check(fixtureA, fixtureB, TypeEntite.PIEGE);
            if(fixturePiege != null) {

                Fixture fixtureEnnemi = check(fixtureA, fixtureB, TypeEntite.ENNEMI);
                if(fixtureEnnemi != null){
                    world.getPiege((((Type)fixturePiege.getBody().getUserData()).getId())).addTarget(world.getEnnemi((((Type)fixtureEnnemi.getBody().getUserData()).getId())));
                }

                fixtureJoueur = check(fixtureA, fixtureB, TypeEntite.JOUEUR);
                // Joueur
                if(fixtureJoueur != null){//TODO: Corriger bug piège
                    world.getPiege((((Type)fixturePiege.getBody().getUserData()).getId())).addTarget(world.getJoueur());
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
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        //fin de contact entre piege et joueur / ennemi
        Fixture fixturePiege = check(fixtureA, fixtureB, TypeEntite.PIEGE);
        Fixture fixtureJoueur = check(fixtureA, fixtureB, TypeEntite.JOUEUR);
        Fixture fixtureEnnemi = check(fixtureA, fixtureB, TypeEntite.ENNEMI);
        if(fixturePiege != null) {
            if(fixtureJoueur != null){
                world.getPiege((((Type)fixturePiege.getBody().getUserData()).getId())).removeTarget(world.getJoueur());
            }

            if(fixtureEnnemi != null){
                world.getPiege((((Type)fixturePiege.getBody().getUserData()).getId())).removeTarget(world.getEnnemi((((Type)fixtureEnnemi.getBody().getUserData()).getId())));
            }
        }

        if(fixtureJoueur != null) {
            if(fixtureEnnemi != null){
                world.getEnnemi((((Type)fixtureEnnemi.getBody().getUserData()).getId())).removeTarget(world.getJoueur());
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
