package fr.projetstage.models.monde.salle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.entites.Entite;
import fr.projetstage.models.entites.objets.ObjetAuSol;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.monde.salle.meubles.Biblio;
import fr.projetstage.models.monde.salle.meubles.NonDestructible;
import fr.projetstage.models.monde.salle.meubles.Table;

import java.util.ArrayList;
import java.util.Random;

public class Salle {

    private int largeur;
    private int hauteur;

    private ArrayList<Entite> tileMap;
    private ArrayList<Prop> props;
    private ArrayList<Entite> meubles;
    private ArrayList<ObjetAuSol> objetAuSols;

    private GameWorld world;

    /**
     * Salle généré avec un body Static et des portes pour sortir
     * Les salles sont forcement rectangulaire
     * @param world le monde dans lequel la salle est générée
     */
    public Salle(GameWorld world, int largeur, int hauteur){
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.world = world;

        genererSalle();
    }

    public void draw(SpriteBatch listeAffImg) {

        Texture tmpWallCorner = TextureFactory.getInstance().getMurAngle();
        Texture tmpWallBorder = TextureFactory.getInstance().getBordureMur();
        Texture tmpWallBorderCorner = TextureFactory.getInstance().getBordureMurAngle();

        //dessine mur et sol
        for(Entite tile : tileMap){
            tile.draw(listeAffImg);
        }
        //objets sur les murs
        for(Prop prop : props){
            prop.draw(listeAffImg);
        }

        for(Entite meuble : meubles){
            meuble.draw(listeAffImg);
        }

        for(int y = 0; y < hauteur;y++){
            //genere le mur de gauche (sur x == 1)
            listeAffImg.draw(tmpWallBorder, -1, y, 0, 0, 1, 1, 1, 1, 90, 0, 0, tmpWallBorder.getWidth(), tmpWallBorder.getHeight(), false, false);

            //genere le mur de droite (sur x == largeur - 2)
            listeAffImg.draw(tmpWallBorder,largeur+1, (1 + y), 0, 0, 1, 1, 1, 1, -90, 0, 0, tmpWallBorder.getWidth(), tmpWallBorder.getHeight(), false, false);
        }

        for (int x = 0; x < largeur; x++) {
            //genere le mur du haut (sur y == hauteur-2)
            listeAffImg.draw(tmpWallBorder, x,hauteur+1, 1, 1);

            //genere le mur du bas (sur y == 1)
            listeAffImg.draw(tmpWallBorder,(1+x), -1,0,0, 1, 1,1,1,180, 0, 0, tmpWallBorder.getWidth(), tmpWallBorder.getHeight(), false, false);
        }

        //coin haut gauche
        listeAffImg.draw(tmpWallBorderCorner, -2,(hauteur+1), 1, 1);
        listeAffImg.draw(tmpWallBorder, -1, (hauteur), 0, 0, 1, 1, 1, 1, 90, 0, 0, tmpWallBorder.getWidth(), tmpWallBorder.getHeight(), false, false);
        listeAffImg.draw(tmpWallBorder, -1,(hauteur+1), 1, 1);
        listeAffImg.draw(tmpWallCorner, -1,(hauteur), 1, 1);

        //coin haut droite
        listeAffImg.draw(tmpWallBorderCorner, (largeur+1), hauteur+2, 0, 0, 1, 1, 1, 1, -90, 0, 0, tmpWallBorderCorner.getWidth(), tmpWallBorderCorner.getHeight(), false, false);
        listeAffImg.draw(tmpWallBorder, (largeur+1), (hauteur+1), 0, 0, 1, 1, 1, 1, -90, 0, 0, tmpWallBorder.getWidth(), tmpWallBorder.getHeight(), false, false);
        listeAffImg.draw(tmpWallBorder,(largeur),(hauteur+1), 1, 1);
        listeAffImg.draw(tmpWallCorner, (largeur), (hauteur+1), 0, 0, 1, 1, 1, 1, -90, 0, 0, tmpWallBorder.getWidth(), tmpWallBorder.getHeight(), false, false);

        //coin bas droite
        listeAffImg.draw(tmpWallBorderCorner, largeur+2, -1, 0, 0, 1, 1, 1, 1, 180, 0, 0, tmpWallBorderCorner.getWidth(), tmpWallBorderCorner.getHeight(), false, false);
        listeAffImg.draw(tmpWallBorder, (largeur+1), 0, 0, 0, 1, 1, 1, 1, -90, 0, 0, tmpWallBorder.getWidth(), tmpWallBorder.getHeight(), false, false);
        listeAffImg.draw(tmpWallBorder, (largeur+1), -1, 0, 0, 1, 1, 1, 1, 180, 0, 0, tmpWallBorder.getWidth(), tmpWallBorder.getHeight(), false, false);
        listeAffImg.draw(tmpWallCorner, (largeur+1), 0, 0, 0, 1, 1, 1, 1, 180, 0, 0, tmpWallBorder.getWidth(), tmpWallBorder.getHeight(), false, false);

        //coin bas gauche
        listeAffImg.draw(tmpWallBorderCorner, -1, -2, 0, 0, 1, 1, 1, 1, 90, 0, 0, tmpWallBorderCorner.getWidth(), tmpWallBorderCorner.getHeight(), false, false);
        listeAffImg.draw(tmpWallBorder, -1, -1, 0, 0, 1, 1, 1, 1, 90, 0, 0, tmpWallBorder.getWidth(), tmpWallBorder.getHeight(), false, false);
        listeAffImg.draw(tmpWallBorder, 0, -1, 0, 0, 1, 1, 1, 1, 180, 0, 0, tmpWallBorder.getWidth(), tmpWallBorder.getHeight(), false, false);
        listeAffImg.draw(tmpWallCorner, 0, -1, 0, 0, 1, 1, 1, 1, 90, 0, 0, tmpWallBorder.getWidth(), tmpWallBorder.getHeight(), false, false);
    }

    public void genererSalle(){
        tileMap = new ArrayList<>();
        props = new ArrayList<>();

        //Mur Gauche et Droite
        for(int y = 0; y < hauteur;y++){
            tileMap.add(new Mur(world, new Vector2(-1, y), Orientation.GAUCHE,getRandomWall()));
            tileMap.add(new Mur(world, new Vector2((largeur), y), Orientation.DROITE,getRandomWall()));
        }

        //Mur Haut et Bas
        for (int x = 0; x < largeur; x++) {
            tileMap.add(new Mur(world, new Vector2(x, (hauteur)), Orientation.HAUT,getRandomWall()));
            tileMap.add(new Mur(world, new Vector2(x, -1), Orientation.BAS,getRandomWall()));
        }
        //parcours tout les murs et ajoute aléatoirement des props si le mur est de type 1
        Mur tmp;
        int tmpAlea;
        for(Entite mur : tileMap){
            tmp = (Mur) mur;
            if(tmp.getNumMur() == 1){
                tmpAlea = getRandomProps();
                if(tmpAlea > 0){
                    props.add(new Prop(tmp.getPos(),tmp.getOrientation(),tmpAlea));
                }
            }
        }


        //le sol
        for(int x = 0; x < largeur; x++){
            for(int y = 0; y < hauteur; y++){
                tileMap.add(new Sol(new Vector2(x,y),getRandomGround()));
            }
        }


        meubles = new ArrayList<>();

        //On affiche le sol
        for (int x = 0; x < largeur-1; x++) {
            meubles.add(new Biblio(world, new Vector2(x, 1)));
            meubles.add(new Table(world, new Vector2(x, 3)));
        }

    }

    public int getLargeur() {
        return largeur;
    }
    public int getHauteur() {
        return hauteur;
    }

    /**
     * renvoie un numero de mur aleatoire basé avec des probabilités
     * @return un entier entre 1 et 4
     */
    public int getRandomWall(){
        int rand = Math.abs(world.getNextRandom()%100);
        int tmp;
        if(rand <= 62){
            tmp = 1;
        }
        else if(rand <= 78){
            tmp = 2;
        }
        else if(rand <= 94){
            tmp = 3;
        }
        else{
            tmp = 4;
        }
        return tmp;
    }

    /**
     * renvoie un numero de props aleatoire basé avec des probabilités
     * @return un entier entre 0 et 3
     */
    public int getRandomProps(){
        int rand = Math.abs(world.getNextRandom()%100);
        int tmp;
        if(rand <= 85){
            tmp = 0;
        }
        else if(rand <= 90){
            tmp = 1;
        }
        else if(rand <= 95){
            tmp = 2;
        }
        else{
            tmp = 3;
        }
        return tmp;
    }

    /**
     * renvoie un numero de sol aleatoire basé avec des probabilités
     * @return un entier entre 1 et 10
     */
    public int getRandomGround(){
        int rand = Math.abs(world.getNextRandom()%100);
        int tmp;
        if(rand <= 55){
            tmp = 2;
        }
        else if(rand <= 60){
            tmp = 1;
        }
        else if(rand <= 65){
            tmp = 3;
        }
        else if(rand <= 70){
            tmp = 4;
        }
        else if(rand <= 75){
            tmp = 5;
        }
        else if(rand <= 80){
            tmp = 6;
        }
        else if(rand <= 85){
            tmp = 7;
        }
        else if(rand <= 90){
            tmp = 8;
        }
        else if(rand <= 95){
            tmp = 9;
        }
        else{
            tmp = 10;
        }
        return tmp;
    }

}
