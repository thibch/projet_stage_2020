package fr.projetstage.models.monde.salle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Entite;
import fr.projetstage.models.Orientation;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.entites.TypeEntite;
import fr.projetstage.models.entites.ennemis.Ennemi;
import fr.projetstage.models.entites.ennemis.Slime;
import fr.projetstage.models.entites.objets.ObjetAuSol;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.monde.salle.meubles.Biblio;
import fr.projetstage.models.monde.salle.meubles.GrandeTable;
import fr.projetstage.models.monde.salle.meubles.PetiteTable;
import fr.projetstage.models.monde.salle.solEtMurs.*;

import java.util.ArrayList;
import java.util.Iterator;

public class Salle {

    private int largeur;
    private int hauteur;

    private ArrayList<Entite> tileMap;
    private ArrayList<Prop> props;
    private ArrayList<Entite> meubles;
    private ArrayList<Ennemi> ennemis;
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

    public void update(){
        int i = 0;
        while(i < ennemis.size()){
            if(ennemis.get(i).getTouche()){
                world.getWorld().destroyBody(ennemis.get(i).getBody());
                ennemis.remove(i);
            }
            i++;
        }
    }

    public void draw(SpriteBatch listeAffImg) {

        Texture tmpWallCorner = TextureFactory.getInstance().getMurAngle();
        Texture tmpWallBorder = TextureFactory.getInstance().getBordureMur();
        Texture tmpWallBorderCorner = TextureFactory.getInstance().getBordureMurAngle();

        // dessine mur et sol
        for(Entite tile : tileMap){
            tile.draw(listeAffImg);
        }
        // objets sur les murs
        for(Prop prop : props){
            prop.draw(listeAffImg);
        }

        for(Entite meuble : meubles){
            meuble.draw(listeAffImg);
        }

        for(Entite monstre : ennemis){
            monstre.draw(listeAffImg);
        }

        for(int y = 0; y < hauteur;y++){
            // genere le mur de gauche (sur x == 1)
            listeAffImg.draw(tmpWallBorder, -1, y, 0, 0, 1, 1, 1, 1, 90, 0, 0, tmpWallBorder.getWidth(), tmpWallBorder.getHeight(), false, false);

            // genere le mur de droite (sur x == largeur - 2)
            listeAffImg.draw(tmpWallBorder,largeur+1, (1 + y), 0, 0, 1, 1, 1, 1, -90, 0, 0, tmpWallBorder.getWidth(), tmpWallBorder.getHeight(), false, false);
        }

        for (int x = 0; x < largeur; x++) {
            // genere le mur du haut (sur y == hauteur-2)
            listeAffImg.draw(tmpWallBorder, x,hauteur+1, 1, 1);

            // genere le mur du bas (sur y == 1)
            listeAffImg.draw(tmpWallBorder,(1+x), -1,0,0, 1, 1,1,1,180, 0, 0, tmpWallBorder.getWidth(), tmpWallBorder.getHeight(), false, false);
        }

        // coin haut gauche
        listeAffImg.draw(tmpWallBorderCorner, -2,(hauteur+1), 1, 1);
        listeAffImg.draw(tmpWallBorder, -1, (hauteur), 0, 0, 1, 1, 1, 1, 90, 0, 0, tmpWallBorder.getWidth(), tmpWallBorder.getHeight(), false, false);
        listeAffImg.draw(tmpWallBorder, -1,(hauteur+1), 1, 1);
        listeAffImg.draw(tmpWallCorner, -1,(hauteur), 1, 1);

        // coin haut droite
        listeAffImg.draw(tmpWallBorderCorner, (largeur+1), hauteur+2, 0, 0, 1, 1, 1, 1, -90, 0, 0, tmpWallBorderCorner.getWidth(), tmpWallBorderCorner.getHeight(), false, false);
        listeAffImg.draw(tmpWallBorder, (largeur+1), (hauteur+1), 0, 0, 1, 1, 1, 1, -90, 0, 0, tmpWallBorder.getWidth(), tmpWallBorder.getHeight(), false, false);
        listeAffImg.draw(tmpWallBorder,(largeur),(hauteur+1), 1, 1);
        listeAffImg.draw(tmpWallCorner, (largeur), (hauteur+1), 0, 0, 1, 1, 1, 1, -90, 0, 0, tmpWallBorder.getWidth(), tmpWallBorder.getHeight(), false, false);

        // coin bas droite
        listeAffImg.draw(tmpWallBorderCorner, largeur+2, -1, 0, 0, 1, 1, 1, 1, 180, 0, 0, tmpWallBorderCorner.getWidth(), tmpWallBorderCorner.getHeight(), false, false);
        listeAffImg.draw(tmpWallBorder, (largeur+1), 0, 0, 0, 1, 1, 1, 1, -90, 0, 0, tmpWallBorder.getWidth(), tmpWallBorder.getHeight(), false, false);
        listeAffImg.draw(tmpWallBorder, (largeur+1), -1, 0, 0, 1, 1, 1, 1, 180, 0, 0, tmpWallBorder.getWidth(), tmpWallBorder.getHeight(), false, false);
        listeAffImg.draw(tmpWallCorner, (largeur+1), 0, 0, 0, 1, 1, 1, 1, 180, 0, 0, tmpWallBorder.getWidth(), tmpWallBorder.getHeight(), false, false);

        // coin bas gauche
        listeAffImg.draw(tmpWallBorderCorner, -1, -2, 0, 0, 1, 1, 1, 1, 90, 0, 0, tmpWallBorderCorner.getWidth(), tmpWallBorderCorner.getHeight(), false, false);
        listeAffImg.draw(tmpWallBorder, -1, -1, 0, 0, 1, 1, 1, 1, 90, 0, 0, tmpWallBorder.getWidth(), tmpWallBorder.getHeight(), false, false);
        listeAffImg.draw(tmpWallBorder, 0, -1, 0, 0, 1, 1, 1, 1, 180, 0, 0, tmpWallBorder.getWidth(), tmpWallBorder.getHeight(), false, false);
        listeAffImg.draw(tmpWallCorner, 0, -1, 0, 0, 1, 1, 1, 1, 90, 0, 0, tmpWallBorder.getWidth(), tmpWallBorder.getHeight(), false, false);
    }

    public void genererSalle(){
        tileMap = new ArrayList<>();
        props = new ArrayList<>();

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
                    props.add(new Prop(tmp.getPos(),tmp.getOrientation(),tmpAlea));
                }
            }
        }


        // le sol
        for(int x = 0; x < largeur; x++){
            for(int y = 0; y < hauteur; y++){
                tileMap.add(new Sol(new Vector2(x,y),getRandomGround()));
            }
        }


        meubles = new ArrayList<>();

        meubles.add(new Biblio(world, new Vector2(2, hauteur-1)));
        meubles.add(new Biblio(world, new Vector2(3, hauteur-1)));
        meubles.add(new PetiteTable(world, new Vector2(5, 3)));
        meubles.add(new GrandeTable(world, new Vector2(5, 5)));

        ennemis = new ArrayList<>();

        ennemis.add(new Slime(world, new Vector2(7, 7), new Type(TypeEntite.ENNEMI, ennemis.size())));


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
    public TypeMur getRandomWall(){
        int rand = Math.abs(world.getNextRandom()%100);
        TypeMur tmp;
        if(rand <= 70){
            tmp = TypeMur.MUR1;
        }
        else if(rand <= 90){
            tmp = TypeMur.MUR2;
        }
        else if(rand <= 94){
            tmp = TypeMur.MUR3;
        }
        else{
            tmp = TypeMur.MUR4;
        }
        return tmp;
    }

    /**
     * renvoie un numero de props aleatoire basé avec des probabilités
     * @return un entier entre 0 et 3
     */
    public TypeProp getRandomProps(){
        int rand = Math.abs(world.getNextRandom()%100);
        TypeProp tmp;
        if(rand <= 85){
            tmp = null;
        }
        else if(rand <= 90){
            tmp = TypeProp.PROP_DRAPEAU_ROUGE;
        }
        else if(rand <= 95){
            tmp = TypeProp.PROP_DRAPEAU_VERT;
        }
        else{
            tmp = TypeProp.PROP_PRISONNIER;
        }
        return tmp;
    }

    /**
     * renvoie un numero de sol aleatoire basé avec des probabilités
     * @return un entier entre 1 et 10
     */
    public TypeSol getRandomGround(){
        int rand = Math.abs(world.getNextRandom()%100);
        TypeSol tmp;
        if(rand <= 55){
            tmp = TypeSol.SOL2;
        }
        else if(rand <= 60){
            tmp = TypeSol.SOL1;
        }
        else if(rand <= 65){
            tmp = TypeSol.SOL3;
        }
        else if(rand <= 70){
            tmp = TypeSol.SOL4;
        }
        else if(rand <= 75){
            tmp = TypeSol.SOL5;
        }
        else if(rand <= 80){
            tmp = TypeSol.SOL6;
        }
        else if(rand <= 85){
            tmp = TypeSol.SOL7;
        }
        else if(rand <= 90){
            tmp = TypeSol.SOL8;
        }
        else if(rand <= 95){
            tmp = TypeSol.SOL9;
        }
        else{
            tmp = TypeSol.SOL10;
        }
        return tmp;
    }

    public Iterator<Ennemi> iterator() {
        return ennemis.iterator();
    }

    public void setEnnemiTouche(boolean touche, int id) {
        ennemis.get(id).setTouche(touche);
    }
}
