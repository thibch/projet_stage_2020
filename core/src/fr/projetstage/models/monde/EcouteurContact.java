package fr.projetstage.models.monde;

import com.badlogic.gdx.physics.box2d.*;
import fr.projetstage.models.entites.TypeAttaque;
import fr.projetstage.models.entites.ennemis.Ennemi;

import java.util.Iterator;

public class EcouteurContact implements ContactListener {

    private GameWorld world;

    public EcouteurContact(GameWorld world) {
        this.world = world;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureMonstre = contact.getFixtureA();
        Fixture fixtureCaC = contact.getFixtureB();

        //TODO: modifier l'accès au monstre pour faire avec son ID dans un tableau (au lieu d'un iterator)

        if(contact.getFixtureA().getBody().getUserData() != null && contact.getFixtureB().getBody().getUserData() != null){
            if(world.getJoueur().isAttacking()){
                if(contact.getFixtureA().getBody().getUserData().equals(TypeAttaque.CORPS_A_CORPS)){
                    fixtureCaC = contact.getFixtureA();
                    if(contact.getFixtureB().getBody().getUserData().equals(TypeAttaque.ENNEMI)){
                        fixtureMonstre = contact.getFixtureB();
                        System.out.println(((TypeAttaque)fixtureMonstre.getBody().getUserData()).getId());

                        for (Iterator<Ennemi> it = world.iteratorEnnemi(); it.hasNext(); ) {
                            Ennemi en = it.next();
                            if(((TypeAttaque)fixtureMonstre.getBody().getUserData()).getId() == en.getTypeAttaque().getId()){
                                en.setTouche(true);
                            }
                        }
                    }
                }else if (contact.getFixtureB().getBody().getUserData().equals(TypeAttaque.CORPS_A_CORPS)){
                    fixtureCaC = contact.getFixtureB();
                    if(contact.getFixtureA().getBody().getUserData().equals(TypeAttaque.ENNEMI)) {
                        fixtureMonstre = contact.getFixtureA();
                        System.out.println(((TypeAttaque)fixtureMonstre.getBody().getUserData()).getId());

                        for (Iterator<Ennemi> it = world.iteratorEnnemi(); it.hasNext(); ) {
                            Ennemi en = it.next();
                            if(((TypeAttaque)fixtureMonstre.getBody().getUserData()).getId() == en.getTypeAttaque().getId()){
                                en.setTouche(true);
                            }
                        }

                    }
                }
            }
        }
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
