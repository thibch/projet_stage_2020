package fr.projetstage.models.monde.salle.patternSalle;

import com.badlogic.gdx.math.Vector2;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.entites.TypeEntite;
import fr.projetstage.models.entites.ennemis.ChauveSouris;
import fr.projetstage.models.entites.ennemis.Slime;
import fr.projetstage.models.entites.objets.Coffre;
import fr.projetstage.models.entites.objets.objetsAuSol.PotionVieRouge;
import fr.projetstage.models.entites.objets.objetsCoffre.Crane;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.monde.salle.Salle;
import fr.projetstage.models.monde.salle.solEtMurs.Piege;
import fr.projetstage.models.monde.salle.solEtMurs.meubles.Biblio;
import fr.projetstage.models.monde.salle.solEtMurs.meubles.GrandeTable;
import fr.projetstage.models.monde.salle.solEtMurs.meubles.PetiteTable;

public class Salle4 extends Salle {

    public Salle4(GameWorld world) {
        super(world, 16, 10);
    }

    @Override
    public void genererSalle() {
        genererSolsEtMurs();

        meubles.add(new Biblio(world, new Vector2(1, hauteur-1)));
        meubles.add(new Biblio(world, new Vector2(2, hauteur-1)));
        meubles.add(new Biblio(world, new Vector2(3, hauteur-1)));
        meubles.add(new Biblio(world, new Vector2(4, hauteur-1)));

        //piege
        ennemis.put(nbEnnemis,new Piege(world,new Vector2(7,3),new Type(TypeEntite.PIEGE, nbEnnemis++)));

        //monstres
        ennemis.put(nbEnnemis, new Slime(world, new Vector2(7, 7), new Type(TypeEntite.ENNEMI, nbEnnemis++)));
        ennemis.put(nbEnnemis, new ChauveSouris(world, new Vector2(13, 9), new Type(TypeEntite.ENNEMI, nbEnnemis++)));


        int nbObjetAuSols = 0;

        objets.put(nbObjetAuSols, new PotionVieRouge(world, new Vector2(7,7), nbObjetAuSols++));
        objets.put(nbObjetAuSols, new Coffre(world, new Vector2(11,3), new Crane(world), nbObjetAuSols++));
    }


    @Override
    public int getNumber() {
        return 4;
    }
}
