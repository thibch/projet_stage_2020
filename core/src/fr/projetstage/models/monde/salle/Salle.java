package fr.projetstage.models.monde.salle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.entites.Entite;
import fr.projetstage.models.entites.objets.ObjetAuSol;
import fr.projetstage.models.monde.World;

import java.util.ArrayList;

public class Salle {

    private int largeur;
    private int hauteur;

    private Entite[][] contenuSalle;
    private ArrayList<ObjetAuSol> objetAuSols;

    public Salle(){
        hauteur = 10;
        largeur = 14;
    }

    public void draw(SpriteBatch listeAffImg) {
        TextureRegion tmpWall = new TextureRegion(TextureFactory.getInstance().getMur1());
        TextureRegion tmpWallCorner = new TextureRegion(TextureFactory.getInstance().getMurAngle());
        TextureRegion tmpWallBorder = new TextureRegion(TextureFactory.getInstance().getBordureMur());
        TextureRegion tmpWallBorderCorner = new TextureRegion(TextureFactory.getInstance().getBordureMurAngle());
        for(int x = 1; x < largeur-1; x++){
            for(int y = 1; y < hauteur-1; y++){
                if(x == 1 && y == hauteur-2){ //coin haut gauche
                    listeAffImg.draw(tmpWallBorderCorner,(x-1)*(float)(World.getLargeur()/largeur),(1+y)*(float)(World.getHauteur()/hauteur),(float)(World.getLargeur()/largeur),(float)(World.getHauteur()/hauteur));
                    listeAffImg.draw(tmpWallBorder, (x)*(float)(World.getLargeur()/largeur), y*(float)(World.getHauteur()/hauteur), 0, 0,(float)(World.getHauteur()/hauteur),(float)(World.getLargeur()/largeur), 1, 1, 90);
                    listeAffImg.draw(tmpWallBorder,x*(float)(World.getLargeur()/largeur),(1+y)*(float)(World.getHauteur()/hauteur),(float)(World.getLargeur()/largeur),(float)(World.getHauteur()/hauteur));
                    listeAffImg.draw(tmpWallCorner,x*(float)(World.getLargeur()/largeur),y*(float)(World.getHauteur()/hauteur),(float)(World.getLargeur()/largeur),(float)(World.getHauteur()/hauteur));
                }
                else if( x == largeur-2 && y == hauteur-2){ //coin haut droite
                    listeAffImg.draw(tmpWallBorderCorner, (1+x)*(float)(World.getLargeur()/largeur), (2+y)*(float)(World.getHauteur()/hauteur), 0, 0,(float)(World.getHauteur()/hauteur),(float)(World.getLargeur()/largeur), 1, 1, -90);
                    listeAffImg.draw(tmpWallBorder, (1+x)*(float)(World.getLargeur()/largeur), (1+y)*(float)(World.getHauteur()/hauteur), 0, 0,(float)(World.getHauteur()/hauteur),(float)(World.getLargeur()/largeur), 1, 1, -90);
                    listeAffImg.draw(tmpWallBorder,x*(float)(World.getLargeur()/largeur),(1+y)*(float)(World.getHauteur()/hauteur),(float)(World.getLargeur()/largeur),(float)(World.getHauteur()/hauteur));
                    listeAffImg.draw(tmpWallCorner, x*(float)(World.getLargeur()/largeur), (1+y)*(float)(World.getHauteur()/hauteur), 0, 0,(float)(World.getHauteur()/hauteur),(float)(World.getLargeur()/largeur), 1, 1, -90);
                }
                else if( x == largeur-2 && y == 1){ //coin bas droite
                    listeAffImg.draw(tmpWallBorderCorner, (2+x)*(float)(World.getLargeur()/largeur), (y)*(float)(World.getHauteur()/hauteur), 0, 0,(float)(World.getLargeur()/largeur),(float)(World.getHauteur()/hauteur), 1, 1, 180);
                    listeAffImg.draw(tmpWallBorder, (1+x)*(float)(World.getLargeur()/largeur), (1+y)*(float)(World.getHauteur()/hauteur), 0, 0,(float)(World.getHauteur()/hauteur),(float)(World.getLargeur()/largeur), 1, 1, -90);
                    listeAffImg.draw(tmpWallBorder, (1+x)*(float)(World.getLargeur()/largeur), (y)*(float)(World.getHauteur()/hauteur), 0, 0,(float)(World.getLargeur()/largeur),(float)(World.getHauteur()/hauteur), 1, 1, 180);
                    listeAffImg.draw(tmpWallCorner, (1+x)*(float)(World.getLargeur()/largeur), (1+y)*(float)(World.getHauteur()/hauteur), 0, 0,(float)(World.getLargeur()/largeur),(float)(World.getHauteur()/hauteur), 1, 1, 180);
                }
                else if( x == 1 && y == 1){ //coin bas gauche
                    listeAffImg.draw(tmpWallBorderCorner, (x)*(float)(World.getLargeur()/largeur), (y-1)*(float)(World.getHauteur()/hauteur), 0, 0,(float)(World.getHauteur()/hauteur),(float)(World.getLargeur()/largeur), 1, 1, 90);
                    listeAffImg.draw(tmpWallBorder, (x)*(float)(World.getLargeur()/largeur), y*(float)(World.getHauteur()/hauteur), 0, 0,(float)(World.getHauteur()/hauteur),(float)(World.getLargeur()/largeur), 1, 1, 90);
                    listeAffImg.draw(tmpWallBorder, (1+x)*(float)(World.getLargeur()/largeur), (y)*(float)(World.getHauteur()/hauteur), 0, 0,(float)(World.getLargeur()/largeur),(float)(World.getHauteur()/hauteur), 1, 1, 180);
                    listeAffImg.draw(tmpWallCorner, (1+x)*(float)(World.getLargeur()/largeur), y*(float)(World.getHauteur()/hauteur), 0, 0,(float)(World.getHauteur()/hauteur),(float)(World.getLargeur()/largeur), 1, 1, 90);
                }
                else if( x == 1){ //genere le mur de gauche
                    listeAffImg.draw(tmpWallBorder, (x)*(float)(World.getLargeur()/largeur), y*(float)(World.getHauteur()/hauteur), 0, 0,(float)(World.getHauteur()/hauteur),(float)(World.getLargeur()/largeur), 1, 1, 90);
                    listeAffImg.draw(tmpWall, (1+x)*(float)(World.getLargeur()/largeur), y*(float)(World.getHauteur()/hauteur), 0, 0,(float)(World.getHauteur()/hauteur),(float)(World.getLargeur()/largeur), 1, 1, 90);
                }
                else if(x == largeur-2){ //genere le mur de droite
                    listeAffImg.draw(tmpWallBorder, (1+x)*(float)(World.getLargeur()/largeur), (1+y)*(float)(World.getHauteur()/hauteur), 0, 0,(float)(World.getHauteur()/hauteur),(float)(World.getLargeur()/largeur), 1, 1, -90);
                    listeAffImg.draw(tmpWall, x*(float)(World.getLargeur()/largeur), (1+y)*(float)(World.getHauteur()/hauteur), 0, 0,(float)(World.getHauteur()/hauteur),(float)(World.getLargeur()/largeur), 1, 1, -90);
                }
                else if(y == hauteur-2){ //genere le mur du haut
                    listeAffImg.draw(tmpWallBorder,x*(float)(World.getLargeur()/largeur),(y+1)*(float)(World.getHauteur()/hauteur),(float)(World.getLargeur()/largeur),(float)(World.getHauteur()/hauteur));
                    listeAffImg.draw(tmpWall,x*(float)(World.getLargeur()/largeur),y*(float)(World.getHauteur()/hauteur),(float)(World.getLargeur()/largeur),(float)(World.getHauteur()/hauteur));
                }
                else if(y == 1){ //genere le mur du bas
                    listeAffImg.draw(tmpWallBorder,(1+x)*(float)(World.getLargeur()/largeur),(y)*(float)(World.getHauteur()/hauteur),0,0,(float)(World.getLargeur()/largeur),(float)(World.getHauteur()/hauteur),1,1,180);
                    listeAffImg.draw(tmpWall,(1+x)*(float)(World.getLargeur()/largeur),(1+y)*(float)(World.getHauteur()/hauteur),0,0,(float)(World.getLargeur()/largeur),(float)(World.getHauteur()/hauteur),1,1,180);
                }
                else{ //genere le sol
                    listeAffImg.draw(TextureFactory.getInstance().getSol2(),x*(float)(World.getLargeur()/largeur),y*(float)(World.getHauteur()/hauteur),(float)(World.getHauteur()/largeur),(float)(World.getHauteur()/hauteur));
                }
            }
        }
    }
}
