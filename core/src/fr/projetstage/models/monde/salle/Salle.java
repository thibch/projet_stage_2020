package fr.projetstage.models.monde.salle;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.entites.Entite;
import fr.projetstage.models.entites.objets.ObjetAuSol;
import fr.projetstage.models.monde.GameWorld;

import java.util.ArrayList;

public class Salle {

    private int largeur;
    private int hauteur;

    private Entite[][] contenuSalle;
    private ArrayList<ObjetAuSol> objetAuSols;

    public Salle(){
        hauteur = 10;
        largeur = 10;
    }

    public void draw(SpriteBatch listeAffImg) {

        //https://libgdx.badlogicgames.com/ci/nightlies/docs/api/com/badlogic/gdx/graphics/g2d/SpriteBatch.html#draw-com.badlogic.gdx.graphics.Texture-float-float-float-float-float-float-float-float-float-int-int-int-int-boolean-boolean-
        TextureRegion tmpWall = new TextureRegion(TextureFactory.getInstance().getMur1());
        TextureRegion tmpWallCorner = new TextureRegion(TextureFactory.getInstance().getMurAngle());
        TextureRegion tmpWallBorder = new TextureRegion(TextureFactory.getInstance().getBordureMur());
        TextureRegion tmpWallBorderCorner = new TextureRegion(TextureFactory.getInstance().getBordureMurAngle());

        float ratioHauteur = (float) GameWorld.getHauteur()/hauteur;
        float ratioLargeur = (float) GameWorld.getLargeur()/largeur;

        for(int x = 2; x < largeur-2; x++){
            for(int y = 2; y < hauteur-2; y++){
                listeAffImg.draw(TextureFactory.getInstance().getSol2(),x*ratioLargeur,y*ratioHauteur,ratioLargeur,ratioHauteur);
            }
        }

        //genere le mur de gauche (sur x == 1)
        for(int y = 2; y < hauteur-2;y++){
            listeAffImg.draw(tmpWallBorder, ratioLargeur, y*ratioHauteur, 0, 0, ratioHauteur, ratioLargeur, 1, 1, 90);
            listeAffImg.draw(tmpWall, 2*ratioLargeur, y*ratioHauteur, 0, 0, ratioHauteur, ratioLargeur, 1, 1, 90);
        }

        //genere le mur de droite (sur x == largeur - 2)
        for(int y = 2; y < hauteur-2;y++) {
            listeAffImg.draw(tmpWallBorder, (largeur - 1)*ratioLargeur, (1 + y)*ratioHauteur, 0, 0, ratioHauteur, ratioLargeur, 1, 1, -90);
            listeAffImg.draw(tmpWall, (largeur - 2)*ratioLargeur, (1 + y)*ratioHauteur, 0, 0, ratioHauteur, ratioLargeur, 1, 1, -90);
        }

        //genere le mur du haut (sur y == hauteur-2)
        for (int x = 2; x < largeur-2; x++) {
            listeAffImg.draw(tmpWallBorder,x*ratioLargeur,(hauteur-2+1)*ratioHauteur, ratioLargeur, ratioHauteur);
            listeAffImg.draw(tmpWall,x*ratioLargeur, (hauteur-2)*ratioHauteur, ratioLargeur, ratioHauteur);
        }

        //genere le mur du bas (sur y == 1)
        for (int x = 2; x < largeur-2; x++) {
            listeAffImg.draw(tmpWallBorder,(1+x)*ratioLargeur, ratioHauteur,0,0, ratioLargeur, ratioHauteur,1,1,180);
            listeAffImg.draw(tmpWall,(1+x)*ratioLargeur,2*ratioHauteur,0,0, ratioLargeur, ratioHauteur,1,1,180);
        }


        //coin haut gauche
        listeAffImg.draw(tmpWallBorderCorner, 0,(hauteur-1)*ratioHauteur, ratioLargeur, ratioHauteur);
        listeAffImg.draw(tmpWallBorder, ratioLargeur, (hauteur-2)*ratioHauteur, 0, 0, ratioHauteur, ratioLargeur, 1, 1, 90);
        listeAffImg.draw(tmpWallBorder, ratioLargeur,(hauteur-1)*ratioHauteur, ratioLargeur, ratioHauteur);
        listeAffImg.draw(tmpWallCorner, ratioLargeur,(hauteur-2)*ratioHauteur, ratioLargeur, ratioHauteur);

        //coin haut droite
        listeAffImg.draw(tmpWallBorderCorner, (largeur-1)*ratioLargeur, hauteur*ratioHauteur, 0, 0, ratioHauteur, ratioLargeur, 1, 1, -90);
        listeAffImg.draw(tmpWallBorder, (largeur-1)*ratioLargeur, (hauteur-1)*ratioHauteur, 0, 0, ratioHauteur, ratioLargeur, 1, 1, -90);
        listeAffImg.draw(tmpWallBorder,(largeur-2)*ratioLargeur,(hauteur-1)*ratioHauteur, ratioLargeur, ratioHauteur);
        listeAffImg.draw(tmpWallCorner, (largeur-2)*ratioLargeur, (hauteur-1)*ratioHauteur, 0, 0, ratioHauteur, ratioLargeur, 1, 1, -90);

        //coin bas droite
        listeAffImg.draw(tmpWallBorderCorner, largeur*ratioLargeur, ratioHauteur, 0, 0, ratioLargeur, ratioHauteur, 1, 1, 180);
        listeAffImg.draw(tmpWallBorder, (largeur-1)*ratioLargeur, 2*ratioHauteur, 0, 0, ratioHauteur, ratioLargeur, 1, 1, -90);
        listeAffImg.draw(tmpWallBorder, (largeur-1)*ratioLargeur, ratioHauteur, 0, 0, ratioLargeur, ratioHauteur, 1, 1, 180);
        listeAffImg.draw(tmpWallCorner, (largeur-1)*ratioLargeur, 2*ratioHauteur, 0, 0, ratioLargeur, ratioHauteur, 1, 1, 180);

        //coin bas gauche
        listeAffImg.draw(tmpWallBorderCorner, ratioLargeur, 0, 0, 0, ratioHauteur, ratioLargeur, 1, 1, 90);
        listeAffImg.draw(tmpWallBorder, ratioLargeur, ratioHauteur, 0, 0, ratioHauteur, ratioLargeur, 1, 1, 90);
        listeAffImg.draw(tmpWallBorder, 2*ratioLargeur, ratioHauteur, 0, 0, ratioLargeur, ratioHauteur, 1, 1, 180);
        listeAffImg.draw(tmpWallCorner, 2*ratioLargeur, ratioHauteur, 0, 0, ratioHauteur, ratioLargeur, 1, 1, 90);
    }
}
