package fr.projetstage.models.monde.salle.patternSalle;

import com.badlogic.gdx.math.Vector2;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.entites.TypeEntite;
import fr.projetstage.models.entites.ennemis.ChauveSouris;
import fr.projetstage.models.entites.ennemis.Goblin;
import fr.projetstage.models.entites.ennemis.OrcShaman;
import fr.projetstage.models.entites.ennemis.Slime;
import fr.projetstage.models.entites.objets.objetsAuSol.PackDeFleches;
import fr.projetstage.models.entites.objets.objetsAuSol.PotionVieRouge;
import fr.projetstage.models.entites.objets.Coffre;
import fr.projetstage.models.entites.objets.objetsCoffre.Crane;
import fr.projetstage.models.entites.objets.objetsCoffre.PotionForce;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.monde.Monde;
import fr.projetstage.models.monde.TypeSalle;
import fr.projetstage.models.monde.salle.Salle;
import fr.projetstage.models.monde.salle.solEtMurs.meubles.Biblio;
import fr.projetstage.models.monde.salle.solEtMurs.meubles.GrandeTable;
import fr.projetstage.models.monde.salle.solEtMurs.meubles.PetiteTable;
import fr.projetstage.models.monde.salle.solEtMurs.*;

public class SalleSpawn extends Salle {
    /**
     * Salle généré avec un body Static et des portes pour sortir
     * Les salles sont forcement rectangulaire
     *
     * @param world   le monde dans lequel la salle est générée
     */
    public SalleSpawn(GameWorld world, int idEtage, Monde monde) {
        super(world, monde, idEtage, 16, 10);
    }

    @Override
    public void genererSalle() {
        genererSolsEtMurs();

        if(idEtage == 0){
            meubles.add(new Biblio(world, new Vector2(2, hauteur-1)));
            meubles.add(new Biblio(world, new Vector2(3, hauteur-1)));
            meubles.add(new PetiteTable(world, new Vector2(5, 3)));
            meubles.add(new GrandeTable(world, new Vector2(5, 5)));
/*
            meubles.add(new Escalier(world, new Vector2(4, 5)));
            objets.put(nbObjetAuSols, new Coffre(world, new Vector2(3, 5), new PotionForce(world), nbObjetAuSols));
            nbObjetAuSols++;
*/
        }else if (idEtage == 1){
            meubles.add(new Biblio(world, new Vector2(12, 5)));
            meubles.add(new PetiteTable(world, new Vector2(5, 3)));
            meubles.add(new GrandeTable(world, new Vector2(10, 5)));
        }else{
            meubles.add(new Biblio(world, new Vector2(6, hauteur-2)));
            meubles.add(new Biblio(world, new Vector2(7, hauteur-2)));
            meubles.add(new Biblio(world, new Vector2(8, hauteur-2)));
        }

        //monstres
        objets.put(nbObjetAuSols, new PotionVieRouge(world, new Vector2(7,7), nbObjetAuSols++));
    }

    @Override
    public TypeSalle getType() {
        return TypeSalle.SPAWN;
    }
}
