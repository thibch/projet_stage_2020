package fr.projetstage.models.monde.salle;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.entites.Entite;
import fr.projetstage.models.entites.objets.ObjetAuSol;
import fr.projetstage.models.monde.GameWorld;

import java.util.ArrayList;

public class Salle {

    private int largeur;
    private int hauteur;

    private ArrayList<Mur> contenuSalle;
    private ArrayList<ObjetAuSol> objetAuSols;

    private GameWorld world;

    public Salle(GameWorld world){
        hauteur = 10;
        largeur = 16; //TODO: faire la génération de la carte en fonction du sol
        this.world = world;

        contenuSalle = new ArrayList<>(50);

        //Mur Gauche et Droite
        /*for(int y = 2; y < hauteur-2;y++){
            contenuSalle.add(new Mur(new Vector2(1, y), world, Orientation.GAUCHE));
            contenuSalle.add(new Mur(new Vector2((largeur-2), y), world, Orientation.DROITE));
        }

        //Mur Haut et Bas
        for (int x = 2; x < largeur-2; x++) {
            contenuSalle.add(new Mur(new Vector2(x, (hauteur-2)), world, Orientation.HAUT));
            contenuSalle.add(new Mur(new Vector2(x, 1), world, Orientation.BAS));
        }*/


        //BodyDef
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(new Vector2(0, 0));
        //

        Body body;

        //Récupération du body dans le world
        body = world.getWorld().createBody(bodyDef);

        //Création de la shape pour le héros
        Vector2 posShape = new Vector2(2, 2); //La position du shape est en fonction de la position du body
        Vector2[] vertices = new Vector2[5];
        vertices[0] = posShape;
        vertices[1] = new Vector2(posShape.x + largeur-4, posShape.y);
        vertices[2] = new Vector2(posShape.x + largeur-4, posShape.y + hauteur-4);
        vertices[3] = new Vector2(posShape.x, posShape.y + hauteur-4);
        vertices[4] = new Vector2(2, 2);

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


        for(Mur mur : contenuSalle){
            mur.draw(listeAffImg);
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
            listeAffImg.draw(tmpWallBorder,x,(hauteur-1), 1, 1);
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
