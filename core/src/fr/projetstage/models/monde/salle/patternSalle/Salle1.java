package fr.projetstage.models.monde.salle.patternSalle;

import com.badlogic.gdx.math.Vector2;
import fr.projetstage.models.Entite;
import fr.projetstage.models.Orientation;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.entites.TypeEntite;
import fr.projetstage.models.entites.ennemis.ChauveSouris;
import fr.projetstage.models.entites.ennemis.Slime;
import fr.projetstage.models.entites.objets.objetsAuSol.PotionVieRouge;
import fr.projetstage.models.entites.objets.Coffre;
import fr.projetstage.models.entites.objets.objetsCoffre.Crane;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.monde.salle.Salle;
import fr.projetstage.models.monde.salle.solEtMurs.meubles.Biblio;
import fr.projetstage.models.monde.salle.solEtMurs.meubles.GrandeTable;
import fr.projetstage.models.monde.salle.solEtMurs.meubles.PetiteTable;
import fr.projetstage.models.monde.salle.solEtMurs.*;

import java.util.ArrayList;

public class Salle1 extends Salle {
    /**
     * Salle généré avec un body Static et des portes pour sortir
     * Les salles sont forcement rectangulaire
     *
     * @param world   le monde dans lequel la salle est générée
     */
    public Salle1(GameWorld world) {
        super(world, 16, 10);
    }

    @Override
    public void genererSalle() {
        genererSolsEtMurs();

        meubles.add(new Biblio(world, new Vector2(2, hauteur-1)));
        meubles.add(new Biblio(world, new Vector2(3, hauteur-1)));
        meubles.add(new PetiteTable(world, new Vector2(5, 3)));
        meubles.add(new GrandeTable(world, new Vector2(5, 5)));

        //piege
        pieges.put(nbEnnemis,new Piege(world,new Vector2(7,3),new Type(TypeEntite.PIEGE, nbPieges++)));

        //monstres
        ennemis.put(nbEnnemis, new Slime(world, new Vector2(7, 7), new Type(TypeEntite.ENNEMI, nbEnnemis++)));
        ennemis.put(nbEnnemis, new Slime(world, new Vector2(10, 9), new Type(TypeEntite.ENNEMI, nbEnnemis++)));
        ennemis.put(nbEnnemis, new ChauveSouris(world, new Vector2(13, 9), new Type(TypeEntite.ENNEMI, nbEnnemis++)));

        objets.put(nbObjetAuSols, new PotionVieRouge(world, new Vector2(7,7), nbObjetAuSols++));
        objets.put(nbObjetAuSols, new Coffre(world, new Vector2(11,3), new Crane(world), nbObjetAuSols++));

        meubles.add(new Escalier(world, new Vector2(largeur-1, 0)));
    }

    @Override
    public int getNumber() {
        return 1;
    }
}
