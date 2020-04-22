package fr.projetstage.models.monde.salle.patternSalle;

import com.badlogic.gdx.math.Vector2;
import fr.projetstage.models.Entite;
import fr.projetstage.models.Orientation;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.entites.TypeEntite;
import fr.projetstage.models.entites.objets.objetsAuSol.PotionRouge;
import fr.projetstage.models.entites.objets.objetsCoffre.Coffre;
import fr.projetstage.models.entites.objets.objetsCoffre.PotionJaune;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.monde.salle.Salle;
import fr.projetstage.models.monde.salle.solEtMurs.meubles.Biblio;
import fr.projetstage.models.monde.salle.solEtMurs.meubles.GrandeTable;
import fr.projetstage.models.monde.salle.solEtMurs.meubles.PetiteTable;
import fr.projetstage.models.monde.salle.solEtMurs.*;

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
        // Mur Gauche et Droite
        for(int y = 0; y < hauteur;y++){
            tileMap.add(new Mur(world, new Vector2(-1, y), fr.projetstage.models.Orientation.GAUCHE,getRandomWall()));
            tileMap.add(new Mur(world, new Vector2((largeur), y), fr.projetstage.models.Orientation.DROITE,getRandomWall()));
        }

        // Mur Haut et Bas
        for (int x = 0; x < largeur; x++) {
            tileMap.add(new Mur(world, new Vector2(x, (hauteur)), fr.projetstage.models.Orientation.HAUT,getRandomWall()));
            tileMap.add(new Mur(world, new Vector2(x, -1), Orientation.BAS,getRandomWall()));
        }

        // parcours tout les murs et ajoute aléatoirement des props si le mur est de type 1
        Mur tmp;
        TypeProp tmpAlea;
        for(Entite mur : tileMap){
            tmp = (Mur) mur;
            if(tmp.getNumMur() == TypeMur.MUR1){
                tmpAlea = getRandomProps();
                if(tmpAlea != null){
                    props.add(new Prop(tmp.getPosition(),tmp.getOrientation(),tmpAlea));
                }
            }
        }


        // le sol
        for(int x = 0; x < largeur; x++){
            for(int y = 0; y < hauteur; y++){
                tileMap.add(new Sol(new Vector2(x,y),getRandomGround()));
            }
        }



        meubles.add(new Biblio(world, new Vector2(2, hauteur-1)));
        meubles.add(new Biblio(world, new Vector2(3, hauteur-1)));
        meubles.add(new PetiteTable(world, new Vector2(5, 3)));
        meubles.add(new GrandeTable(world, new Vector2(5, 5)));

        int nbEnnemis = 0;
        //piege
        ennemis.put(nbEnnemis,new Piege(world,new Vector2(3,3),new Type(TypeEntite.PIEGE, nbEnnemis++)));

        //monstres


        int nbObjetAuSols = 0;

        objets.put(nbObjetAuSols, new PotionRouge(world, new Vector2(7,7), nbObjetAuSols++));
        objets.put(nbObjetAuSols, new Coffre(world, new Vector2(11,3), new PotionJaune(world), nbObjetAuSols++));
        objets.put(nbObjetAuSols, new Coffre(world, new Vector2(12,3), new PotionJaune(world), nbObjetAuSols++));
        objets.put(nbObjetAuSols, new Coffre(world, new Vector2(13,3), new PotionJaune(world), nbObjetAuSols++));
        objets.put(nbObjetAuSols, new Coffre(world, new Vector2(14,3), new PotionJaune(world), nbObjetAuSols++));
        objets.put(nbObjetAuSols, new Coffre(world, new Vector2(15,3), new PotionJaune(world), nbObjetAuSols++));
    }
}
