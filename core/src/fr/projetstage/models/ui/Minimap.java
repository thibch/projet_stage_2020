package fr.projetstage.models.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.monde.salle.EtatSalle;

public class Minimap extends Actor {

    private GameWorld gameWorld;
    private float widthCase;
    private float heightCase;

    private float nbCaseLargeur;
    private float nbCaseHauteur;

    /**
     * Constructeur de la minimap 5x5 dans l'interface utilisateur
     * @param stage le stage dans lequel afficher la minimap
     * @param gameWorld le monde dans lequel se trouve les informations
     * @param position la position de la minimap à l'écran
     * @param widthCase la largeur d'une case de la minimap
     * @param heightCase la hauteur d'une case de la minimap
     */
    public Minimap(Stage stage, GameWorld gameWorld, Vector2 position, float widthCase, float heightCase) {
        super();
        this.gameWorld = gameWorld;
        this.setPosition(position.x, position.y);
        this.widthCase = widthCase;
        this.heightCase = heightCase;
        nbCaseLargeur = 5;
        nbCaseHauteur = 5;
        this.setWidth(widthCase*nbCaseLargeur);
        this.setHeight(heightCase*nbCaseHauteur);
        stage.addActor(this);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        EtatSalle[][] tmp = gameWorld.getMinimap();
        for(int x = 0; x < nbCaseLargeur; x++){
            for(int y = 0; y < nbCaseHauteur ; y++){
                if(x < tmp.length && y < tmp[0].length && tmp[x][y] != null && !tmp[x][y].equals(EtatSalle.NO_SALLE)){
                    if(tmp[x][y].equals(EtatSalle.VISITEE)){
                        batch.draw(TextureFactory.getInstance().getSalleVisitee(),getX()+(x*widthCase),getY()+(y*heightCase), widthCase, heightCase);
                    }
                    else if(tmp[x][y].equals(EtatSalle.NON_VISITE)){
                        batch.draw(TextureFactory.getInstance().getSalleNonVisitee(),getX()+(x*widthCase),getY()+(y*heightCase), widthCase, heightCase);
                    }
                    else if(tmp[x][y].equals(EtatSalle.EN_COURS_DE_VISITE)){
                        batch.draw(TextureFactory.getInstance().getSalleCourante(),getX()+(x*widthCase),getY()+(y*heightCase), widthCase, heightCase);
                    }
                }
            }
        }
    }

}
