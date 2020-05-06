package fr.projetstage.models.ui.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import fr.projetstage.models.ui.MenuButton;
import fr.projetstage.models.ui.Text;
import fr.projetstage.models.ui.TextInputField;

import java.util.ArrayList;
import java.util.Random;

public class MainMenuScreen implements Menu{

    private Text title;

    private MenuButton startBtn;
    private MenuButton quitBtn;

    private Text name;
    private Text seed;
    private TextInputField nameInput;
    private TextInputField seedInput;
    private MenuButton startGameBtn;

    private Text errorText;

    private ArrayList<Text> textList;

    private boolean isBeginClicked = false;

    /**
     * Constructeur du menu principal du jeu
     * @param stage le stage dans lequel afficher les éléments de l'interface.
     */
    public MainMenuScreen(Stage stage){
        textList = new ArrayList<>();
        title = new Text("Projet Stage 2020", (int)stage.getWidth()/8, Color.WHITE,new Vector2((stage.getWidth())/2f,3*(stage.getHeight()/4f)),true);
        textList.add(title);

        startBtn = new MenuButton(stage,this, new Vector2(3.75f*(stage.getWidth()/10f),4*(stage.getHeight()/10f)),stage.getWidth()/4f,stage.getHeight()/6f,"Start");
        quitBtn = new MenuButton(stage,this, new Vector2(3.75f*(stage.getWidth()/10f),(stage.getHeight()/10f)),stage.getWidth()/4f,stage.getHeight()/6f,"Quit");

        Random rand = new Random();

        name = new Text("Name: ", (int)stage.getWidth()/13, Color.WHITE,new Vector2(1.75f*(stage.getWidth()/10f),5f*(stage.getHeight()/10f)),false);
        nameInput = new TextInputField(stage,this, new Vector2(3.25f*(stage.getWidth()/10f),4.25f*(stage.getHeight()/10f)),stage.getWidth()/2f,stage.getHeight()/8f,"Reynold");
        nameInput.setMaxLength(19);
        nameInput.display(false);
        seed = new Text("Seed: ", (int)stage.getWidth()/13, Color.WHITE,new Vector2(1.75f*(stage.getWidth()/10f),3f*(stage.getHeight()/10f)),false);
        seedInput = new TextInputField(stage,this, new Vector2(3.25f*(stage.getWidth()/10f),2.25f*(stage.getHeight()/10f)),stage.getWidth()/2f,stage.getHeight()/8f,Integer.toString(Math.abs(rand.nextInt())));
        seedInput.setFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());
        seedInput.setMaxLength(10);
        seedInput.display(false);
        errorText = new Text("Your name should be 3 character minimum.", 48, Color.RED,new Vector2(3.5f*(stage.getWidth()/10f),3.85f*(stage.getHeight()/10f)),false);


        startGameBtn = new MenuButton(stage,this, new Vector2(8.2f*(stage.getWidth()/10f),(stage.getHeight()/64f)),stage.getWidth()/6f,stage.getHeight()/6f,"Begin");
        startGameBtn.displayBtn(false);
    }

    /**
     * Méthode permettant d'afficher les éléments du menu
     * @param batch un Batch pour afficher les éléments
     */
    public void draw(Batch batch){
        title.draw(batch);
        for (Text text: textList) {
            text.draw(batch);
        }
    }

    /**
     * Methode permettant de libérer la mémoire
     */
    public void dispose() {
        startBtn.dispose();
        quitBtn.dispose();
        startGameBtn.dispose();
        name.dispose();
        seed.dispose();
        title.dispose();

    }

    /**
     * Methode permettant a un bouton de signaler qu'il a été cliqué
     * @param btnText le nom du bouton qui a été cliqué
     */
    @Override
    public void onClick(String btnText) {
        if(btnText.equals("Start")){
            startBtn.displayBtn(false);
            quitBtn.displayBtn(false);

            nameInput.display(true);
            seedInput.display(true);
            startGameBtn.displayBtn(true);
            textList.add(name);
            textList.add(seed);
        }
        else if(btnText.equals("Quit")){
            Gdx.app.exit();
        }
        else if(btnText.equals("Begin")){
            if(nameInput.getText().trim().length() >= 3){
                isBeginClicked = true;
            }
            else{
                textList.add(errorText);
            }
        }
    }

    /**
     * Methode pour récupérer le nom du joueur
     * @return un String du nom du joueur
     */
    public String getName(){
        return nameInput.getText();
    }

    /**
     * Methode pour récuperer la seed entrée ou non par l'utilisateur
     * @return un entier servant de seed
     */
    public int getSeed(){
        if(seedInput.getText().isEmpty()){
            return Math.abs(new Random().nextInt());
        }
        return Integer.parseInt(seedInput.getText());
    }

    /**
     * Methode qui retourne vrai si le bouton "Begin" a été cliqué
     * @return un booleen a vrai si le bouton "Begin" a été cliqué
     */
    public boolean isBeginClicked() {
        return isBeginClicked;
    }
}
