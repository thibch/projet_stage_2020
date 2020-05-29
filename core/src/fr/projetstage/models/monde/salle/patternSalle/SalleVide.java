package fr.projetstage.models.monde.salle.patternSalle;

import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.monde.TypeSalle;
import fr.projetstage.models.monde.salle.EtatSalle;
import fr.projetstage.models.monde.salle.Salle;

public class SalleVide extends Salle {
    /**
     * Salle généré avec un body Static et des portes pour sortir
     * Les salles sont forcement rectangulaire
     *
     * @param world   le monde dans lequel la salle est générée
     */
    public SalleVide(GameWorld world) {
        super(world, 0, 0);

        tileMap = null;
        props = null;
        meubles = null;
        portes = null;
        pieges = null;
        ennemis = null;
        invocationWaitList = null;
        objets = null;
        etat = EtatSalle.NO_SALLE;
    }

    @Override
    public void genererSalle() {

    }

    @Override
    public TypeSalle getType() {
        return TypeSalle.NO_TYPE;
    }
}
