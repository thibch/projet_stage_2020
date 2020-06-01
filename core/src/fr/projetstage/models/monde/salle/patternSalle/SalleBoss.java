package fr.projetstage.models.monde.salle.patternSalle;

import com.badlogic.gdx.math.Vector2;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.entites.TypeEntite;
import fr.projetstage.models.entites.ennemis.boss.Necromancer;
import fr.projetstage.models.entites.ennemis.boss.Ogre;
import fr.projetstage.models.entites.ennemis.boss.Satan;
import fr.projetstage.models.entites.objets.Coffre;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.monde.Monde;
import fr.projetstage.models.monde.TypeSalle;
import fr.projetstage.models.monde.salle.Salle;
import fr.projetstage.models.monde.salle.solEtMurs.Escalier;

public class SalleBoss extends Salle {

    private boolean added = false;

    public SalleBoss(GameWorld world, Monde monde, int idEtage) {
        super(world, monde, idEtage, 16, 10);
    }

    @Override
    public void genererSalle() {
        genererSolsEtMurs();

        if(idEtage == 0){
            ennemis.put(nbEnnemis, new Necromancer(world, new Vector2(7, 7), new Type(TypeEntite.ENNEMI, nbEnnemis++),this));
        }else if(idEtage == 1){
            ennemis.put(nbEnnemis, new Ogre(world, new Vector2(7, 7), new Type(TypeEntite.ENNEMI, nbEnnemis++),this));
        }else{
            ennemis.put(nbEnnemis, new Satan(world, new Vector2(7, 7), new Type(TypeEntite.ENNEMI, nbEnnemis++),this));
        }
    }

    @Override
    public void update(){
        super.update();

        if(ennemis.size() <= 0 && !added){
            Coffre coffre = ajouterNouveauCoffre((largeur)/2f +2, (hauteur)/2f);
            coffre.generateBody();
            Escalier escalier = new Escalier(world, new Vector2((largeur)/2f, (hauteur)/2f));
            meubles.add(escalier);
            escalier.generateBody();
            added = true;
        }
    }


    @Override
    public TypeSalle getType() {
        return TypeSalle.BOSS;
    }
}
