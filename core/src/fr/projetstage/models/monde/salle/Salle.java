package fr.projetstage.models.monde.salle;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
        hauteur = 7;
        largeur = 11;
    }

    public void draw(SpriteBatch listeAffImg) {
        for(int x = 0; x < largeur; x++){
            for(int y = 0; y < hauteur; y++){
                //genere le mur du haut
                if(x == 0 || y == 0 || x == largeur-1 || y == hauteur-1){
                    listeAffImg.draw(TextureFactory.getInstance().getMur1(),x*(float)(World.getHauteur()/largeur),y*(float)(World.getHauteur()/hauteur),(float)(World.getHauteur()/largeur),(float)(World.getHauteur()/hauteur));

                }
                else{
                    //genere le sol
                    listeAffImg.draw(TextureFactory.getInstance().getSol2(),x*(float)(World.getHauteur()/largeur),y*(float)(World.getHauteur()/hauteur),(float)(World.getHauteur()/largeur),(float)(World.getHauteur()/hauteur));
                }
            }
        }
    }
}
