package fr.projetstage.models.ui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import fr.projetstage.models.entites.objets.ObjetsTousTypes;

public class Item {

    private Stage stage;

    private Image image;
    private TooltipItem tooltipItem;

    public Item(ObjetsTousTypes obj, Stage stage){
        this.stage = stage;

        tooltipItem = new TooltipItem(obj.getDescription());
        image = new Image(obj.getTexture());
        image.setSize(80,80);
        image.addListener(tooltipItem.getTextTooltip());

    }

    /**
     * Methode permettant d'afficher ou non un bouton dans le menu
     * @param bool le booleen d'affichage vrai affiche, faux n'affiche pas.
     */
    public void display(boolean bool){
        if(bool){
            stage.addActor(image);
        }
        else{
            image.addAction(Actions.removeActor());
        }

    }

    public void setPosition(float x, float y){
        image.setPosition(x,y);
    }

    public void dispose(){
        tooltipItem.dispose();
    }
}
