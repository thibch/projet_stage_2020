package fr.projetstage.models.monde.salle.patternSalle;

import com.badlogic.gdx.math.Vector2;
import fr.projetstage.models.Entite;
import fr.projetstage.models.Orientation;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.entites.TypeEntite;
import fr.projetstage.models.entites.objets.objetsAuSol.PotionVieRouge;
import fr.projetstage.models.entites.objets.Coffre;
import fr.projetstage.models.entites.objets.objetsCoffre.PotionVitesse;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.monde.salle.Salle;
import fr.projetstage.models.monde.salle.solEtMurs.meubles.Biblio;
import fr.projetstage.models.monde.salle.solEtMurs.meubles.GrandeTable;
import fr.projetstage.models.monde.salle.solEtMurs.meubles.PetiteTable;
import fr.projetstage.models.monde.salle.solEtMurs.*;

import java.util.ArrayList;

public class Salle2 extends Salle {
    /**
     * Salle généré avec un body Static et des portes pour sortir
     * Les salles sont forcement rectangulaire
     *
     * @param world   le monde dans lequel la salle est générée
     */
    public Salle2(GameWorld world) {
        super(world, 16, 10);
    }

    @Override
    public void genererSalle() {
        genererSolsEtMurs();

        meubles.add(new Biblio(world, new Vector2(2, hauteur-1)));
        meubles.add(new Biblio(world, new Vector2(3, hauteur-1)));
        meubles.add(new PetiteTable(world, new Vector2(5, 3)));
        meubles.add(new GrandeTable(world, new Vector2(5, 5)));
        meubles.add(new GrandeTable(world, new Vector2(7, 5)));
        meubles.add(new GrandeTable(world, new Vector2(9, 5)));

        int nbEnnemis = 0;
        //piege
        ennemis.put(nbEnnemis,new Piege(world,new Vector2(3,3),new Type(TypeEntite.PIEGE, nbEnnemis++)));

        //monstres


        int nbObjetAuSols = 0;

        objets.put(nbObjetAuSols, new PotionVieRouge(world, new Vector2(7,7), nbObjetAuSols++));
        objets.put(nbObjetAuSols, new Coffre(world, new Vector2(11,3), new PotionVitesse(world), nbObjetAuSols++));
        objets.put(nbObjetAuSols, new Coffre(world, new Vector2(12,3), new PotionVitesse(world), nbObjetAuSols++));
        objets.put(nbObjetAuSols, new Coffre(world, new Vector2(13,3), new PotionVitesse(world), nbObjetAuSols++));
        objets.put(nbObjetAuSols, new Coffre(world, new Vector2(14,3), new PotionVitesse(world), nbObjetAuSols++));
        objets.put(nbObjetAuSols, new Coffre(world, new Vector2(15,3), new PotionVitesse(world), nbObjetAuSols++));
    }
}
