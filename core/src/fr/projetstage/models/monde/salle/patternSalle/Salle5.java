package fr.projetstage.models.monde.salle.patternSalle;

import com.badlogic.gdx.math.Vector2;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.entites.TypeEntite;
import fr.projetstage.models.entites.ennemis.boss.Necromancer;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.monde.TypeSalle;
import fr.projetstage.models.monde.salle.Salle;

public class Salle5 extends Salle {
    public Salle5(GameWorld world) {
        super(world, 16, 10);
    }

    @Override
    public void genererSalle() {
        genererSolsEtMurs();

        ennemis.put(nbEnnemis, new Necromancer(world, new Vector2(7, 7), new Type(TypeEntite.ENNEMI, nbEnnemis++),this));
    }


    @Override
    public TypeSalle getType() {
        return TypeSalle.BOSS;
    }
}
