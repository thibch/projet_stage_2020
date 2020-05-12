package fr.projetstage.models.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import fr.projetstage.models.entites.objets.ObjetsTousTypes;

public class Item {

    private final Stage stage;

    private final Image image;
    private final TooltipItem tooltipItem;

    /**
     * Constructeur d'un Objet dans l'interface
     * @param obj un objet du monde
     * @param stage le stage dans lequel afficher l'objet
     */
    public Item(ObjetsTousTypes obj, Stage stage){
        this.stage = stage;

        tooltipItem = new TooltipItem(obj.getDescription());
        image = new Image(obj.getTexture());
        image.setSize(stage.getWidth()/13,stage.getWidth()/13);
        image.addListener(tooltipItem.getTextTooltip());
        image.setSize(80,80);
        image.addListener(event -> {
            InputEvent e = (InputEvent)event;
            switch (e.getType()){
                case enter:
                    tooltipItem.getTextTooltip().getManager().instant();
                    break;
                case mouseMoved:
                    return false;
            }
            return tooltipItem.getTextTooltip().handle(event); // TODO : Sûrement mieux à faire
        });

    }

    /**
     * Methode permettant d'afficher ou non un bouton dans le menu
     * @param bool le booleen d'affichage vrai affiche, faux n'affiche pas.
     */
    public void display(boolean bool){
        if(bool){
            stage.addActor(image);
        }else{
            image.addAction(Actions.removeActor());
        }

    }

    /**
     * Permet de definir la position de l'objert dans le stage
     * @param x la position x
     * @param y la position y
     */
    public void setPosition(float x, float y){
        image.setPosition(x,y);
    }

    /**
     * Permet de libérer la mémoire de l'objet
     */
    public void dispose(){
        tooltipItem.dispose();
    }
}
