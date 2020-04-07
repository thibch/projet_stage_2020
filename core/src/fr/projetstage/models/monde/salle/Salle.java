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

    private GameWorld world;

    public Salle(GameWorld world){
        hauteur = 10;
        largeur = 16; //TODO: faire la génération de la carte en fonction du sol
        this.world = world;
    }

    public void draw(SpriteBatch listeAffImg) {

        //https://libgdx.badlogicgames.com/ci/nightlies/docs/api/com/badlogic/gdx/graphics/g2d/SpriteBatch.html#draw-com.badlogic.gdx.graphics.Texture-float-float-float-float-float-float-float-float-float-int-int-int-int-boolean-boolean-
        TextureRegion tmpWall = new TextureRegion(TextureFactory.getInstance().getMur1());
        TextureRegion tmpWallCorner = new TextureRegion(TextureFactory.getInstance().getMurAngle());
        TextureRegion tmpWallBorder = new TextureRegion(TextureFactory.getInstance().getBordureMur());
        TextureRegion tmpWallBorderCorner = new TextureRegion(TextureFactory.getInstance().getBordureMurAngle());

        for(int x = 2; x < largeur-2; x++){
            for(int y = 2; y < hauteur-2; y++){
                listeAffImg.draw(TextureFactory.getInstance().getSol2(),x,y,1,1);
            }
        }

        //genere le mur de gauche (sur x == 1)
        for(int y = 2; y < hauteur-2;y++){
            listeAffImg.draw(tmpWallBorder, 1, y, 0, 0, 1, 1, 1, 1, 90);
            listeAffImg.draw(tmpWall, 2, y, 0, 0, 1, 1, 1, 1, 90);
        }

        //genere le mur de droite (sur x == largeur - 2)
        for(int y = 2; y < hauteur-2;y++) {
            listeAffImg.draw(tmpWallBorder, (largeur - 1), (1 + y), 0, 0, 1, 1, 1, 1, -90);
            listeAffImg.draw(tmpWall, (largeur - 2), (1 + y), 0, 0, 1, 1, 1, 1, -90);
        }

        //genere le mur du haut (sur y == hauteur-2)
        for (int x = 2; x < largeur-2; x++) {
            listeAffImg.draw(tmpWallBorder,x,(hauteur-2+1), 1, 1);
            listeAffImg.draw(tmpWall,x, (hauteur-2), 1, 1);
        }

        //genere le mur du bas (sur y == 1)
        for (int x = 2; x < largeur-2; x++) {
            listeAffImg.draw(tmpWallBorder,(1+x), 1,0,0, 1, 1,1,1,180);
            listeAffImg.draw(tmpWall,(1+x),2,0,0, 1, 1,1,1,180);
        }


        //coin haut gauche
        listeAffImg.draw(tmpWallBorderCorner, 0,(hauteur-1), 1, 1);
        listeAffImg.draw(tmpWallBorder, 1, (hauteur-2), 0, 0, 1, 1, 1, 1, 90);
        listeAffImg.draw(tmpWallBorder, 1,(hauteur-1), 1, 1);
        listeAffImg.draw(tmpWallCorner, 1,(hauteur-2), 1, 1);

        //coin haut droite
        listeAffImg.draw(tmpWallBorderCorner, (largeur-1), hauteur, 0, 0, 1, 1, 1, 1, -90);
        listeAffImg.draw(tmpWallBorder, (largeur-1), (hauteur-1), 0, 0, 1, 1, 1, 1, -90);
        listeAffImg.draw(tmpWallBorder,(largeur-2),(hauteur-1), 1, 1);
        listeAffImg.draw(tmpWallCorner, (largeur-2), (hauteur-1), 0, 0, 1, 1, 1, 1, -90);

        //coin bas droite
        listeAffImg.draw(tmpWallBorderCorner, largeur, 1, 0, 0, 1, 1, 1, 1, 180);
        listeAffImg.draw(tmpWallBorder, (largeur-1), 2, 0, 0, 1, 1, 1, 1, -90);
        listeAffImg.draw(tmpWallBorder, (largeur-1), 1, 0, 0, 1, 1, 1, 1, 180);
        listeAffImg.draw(tmpWallCorner, (largeur-1), 2, 0, 0, 1, 1, 1, 1, 180);

        //coin bas gauche
        listeAffImg.draw(tmpWallBorderCorner, 1, 0, 0, 0, 1, 1, 1, 1, 90);
        listeAffImg.draw(tmpWallBorder, 1, 1, 0, 0, 1, 1, 1, 1, 90);
        listeAffImg.draw(tmpWallBorder, 2, 1, 0, 0, 1, 1, 1, 1, 180);
        listeAffImg.draw(tmpWallCorner, 2, 1, 0, 0, 1, 1, 1, 1, 90);
    }

    public int getLargeur() {
        return largeur;
    }
    public int getHauteur() {
        return hauteur;
    }

}
