package fr.projetstage.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.projetstage.dataFactories.TextureFactory;

public class PhoneController{

    private Viewport viewport;
    private Stage stage;
    private OrthographicCamera cam;

    private Table table;

    private Touchpad.TouchpadStyle touchpadStyle;
    private Touchpad touchpad;
    private Skin touchpadSkin;


    public PhoneController(){
        //(Texture background, Texture knob, float deadZoneRadius, float x, float y, float width, float height)
        touchpadSkin = new Skin();
        //Set background image
        touchpadSkin.add("touchBackground", TextureFactory.getInstance().getBackgroundPad());
        //Set knob image
        touchpadSkin.add("touchKnob", TextureFactory.getInstance().getKnobPad());
        //Create TouchPad Style
        touchpadStyle = new Touchpad.TouchpadStyle();
        //Apply the Drawables to the TouchPad Style
        touchpadStyle.background = touchpadSkin.getDrawable("touchBackground");
        touchpadStyle.knob = touchpadSkin.getDrawable("touchKnob");
        //Create new TouchPad with the created style
        touchpad = new Touchpad(0.1f, touchpadStyle);
        //setBounds(x,y,width,height)
        touchpad.setBounds(50, 50, 200, 200);
        cam = new OrthographicCamera();
        viewport = new FitViewport(1024, 720, cam);
        SpriteBatch batch = new SpriteBatch();
        stage = new Stage(viewport, batch);


        /*Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setPosition(0,0);
        table.right().bottom();

        table.add(actor);

        table.setBackground(new TextureRegionDrawable(TextureFactory.getInstance().getCoffreSpriteSheet()));

        stage.addActor(table);*/
        stage.addActor(touchpad);
    }


    public void draw(){
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }


    public void resize(int width, int height){
        viewport.update(width, height);
    }

    public Stage getStage() {
        return stage;
    }

    public Vector2 getAcceleration() {
        System.out.println("{" + touchpad.getKnobPercentX() + "" + touchpad.getKnobPercentY() + "}");
        return new Vector2(touchpad.getKnobPercentX(), touchpad.getKnobPercentY());
    }
}
