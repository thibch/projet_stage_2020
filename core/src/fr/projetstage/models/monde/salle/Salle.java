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

import java.util.ArrayList;
import java.util.Random;

public class Salle {

    private int largeur;
    private int hauteur;

    private ArrayList<Entite> tileMap;
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

        //BodyDef
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(new Vector2(0, 0));
        //

        Body body;

        //Récupération du body dans le world
        body = world.getWorld().createBody(bodyDef);

        //Création de la shape pour la salle
        Vector2 posShape = new Vector2(0, 0); //La position du shape est en fonction de la position du body
        Vector2[] vertices = new Vector2[5];
        vertices[0] = posShape; //Bas-Gauche
        vertices[1] = new Vector2(posShape.x + this.largeur, posShape.y); //Bas-Droite
        vertices[2] = new Vector2(posShape.x + this.largeur, posShape.y + this.hauteur); //Haut-Droite
        vertices[3] = new Vector2(posShape.x, posShape.y + this.hauteur); //Haut-Gauche
        vertices[4] = posShape; //Bas-Gauche

        ChainShape rectangle = new ChainShape();
        rectangle.createChain(vertices);

        //FixtureDef
        FixtureDef fixtureDef1 = new FixtureDef();
        fixtureDef1.shape = rectangle;
        fixtureDef1.density = 1f; // Densité de l’objet
        fixtureDef1.restitution = 0f; // Restitution de  l’objet
        fixtureDef1.friction = 0f; // Friction de  l’objet
        //

        //Met en place la fixture sur le body
        body.createFixture(fixtureDef1); // Association à l’objet

        rectangle.dispose();
        genererSalle();
    }

    public void draw(SpriteBatch listeAffImg) {

        Texture tmpWallCorner = TextureFactory.getInstance().getMurAngle();
        Texture tmpWallBorder = TextureFactory.getInstance().getBordureMur();
        Texture tmpWallBorderCorner = TextureFactory.getInstance().getBordureMurAngle();

        for(Entite tile : tileMap){
            tile.draw(listeAffImg);
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

        //Mur Gauche et Droite
        for(int y = 0; y < hauteur;y++){
            tileMap.add(new Mur(new Vector2(-1, y), Orientation.GAUCHE,getRandomWall()));
            tileMap.add(new Mur(new Vector2((largeur), y), Orientation.DROITE,getRandomWall()));
        }

        //Mur Haut et Bas
        for (int x = 0; x < largeur; x++) {
            tileMap.add(new Mur(new Vector2(x, (hauteur)), Orientation.HAUT,getRandomWall()));
            tileMap.add(new Mur(new Vector2(x, -1), Orientation.BAS,getRandomWall()));
        }
        //le sol
        for(int x = 0; x < largeur; x++){
            for(int y = 0; y < hauteur; y++){
                tileMap.add(new Sol(new Vector2(x,y),getRandomGround()));
            }
        }

        
        //On affiche le sol
        for (int x = 2; x < largeur-2; x++) {
            for (int y = 2; y < hauteur-2; y++) {
                if(world.getNextRandom()%5 == 0){
                    tileMap.add(new NonDestructible(world, new Vector2(x, y)));
                }
            }
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
        int rand = world.getNextRandom()%100;
        int tmp;
        if(rand <= 52){
            tmp = 1;
        }
        else if(rand <= 68){
            tmp = 2;
        }
        else if(rand <= 84){
            tmp = 3;
        }
        else{
            tmp = 4;
        }
        return tmp;
    }

    /**
     * renvoie un numero de sol aleatoire basé avec des probabilités
     * @return un entier entre 1 et 10
     */
    public int getRandomGround(){
        int rand = world.getNextRandom()%100;
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
