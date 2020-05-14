package fr.projetstage.models.monde.salle.patternSalle;

import com.badlogic.gdx.math.Vector2;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.entites.TypeEntite;
import fr.projetstage.models.entites.ennemis.ChauveSouris;
import fr.projetstage.models.entites.ennemis.Goblin;
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

public class Salle5 extends Salle {
    public Salle5(GameWorld world) {
        super(world, 16, 10);
    }

    @Override
    public void genererSalle() {
        genererSolsEtMurs();

        meubles.add(new Biblio(world, new Vector2(2, hauteur-1)));
        meubles.add(new Biblio(world, new Vector2(3, hauteur-1)));

        int nbEnnemis = 0;
        ennemis.put(nbEnnemis, new Goblin(world, new Vector2(13, 9), new Type(TypeEntite.ENNEMI, nbEnnemis++)));

    }


    @Override
    public int getNumber() {
        return 5;
    }
}
