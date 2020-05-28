package fr.projetstage.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Orientation;
import sun.rmi.runtime.Log;

public class PhoneController {

    private Viewport viewport;
    private Stage stage;
    private OrthographicCamera cam;

    private Table table;

    private Touchpad.TouchpadStyle touchpadStyle;
    private Touchpad touchpad;
    private Skin touchpadSkin;

    private Orientation directionChoisie;
    private boolean keyUp;
    private boolean keyDown;
    private boolean keyLeft;
    private boolean keyRight;


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
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), cam);
        stage = new Stage(viewport);
        touchpad.setBounds(Gdx.graphics.getWidth()* 0.05f, Gdx.graphics.getHeight() * 0.07f, 200, 200);
        int buttonSize = 65;

        keyUp = false;
        keyDown = false;
        keyLeft = false;
        keyRight = false;
        directionChoisie = Orientation.NO_ORIENTATION;


        table = new Table();
        table.setBounds(Gdx.graphics.getWidth() - 250,50, 200, 200);
        table.left().top();

        //---------------------

        table.add().top().left();
        ImageButton image = new ImageButton(new TextureRegionDrawable(TextureFactory.getInstance().getKeyUpMobile()), new TextureRegionDrawable(TextureFactory.getInstance().getKeyUpMobilePressed()));
        image.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                keyUp = true;
                updateKeys();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                keyUp = false;
                updateKeys();
                super.touchUp(event, x, y, pointer, button);
            }
        });
        table.add(image).top().center().size(buttonSize);
        table.add().top().right();

        table.row();
        //---------------------

        image = new ImageButton(new TextureRegionDrawable(TextureFactory.getInstance().getKeyLeftMobile()), new TextureRegionDrawable(TextureFactory.getInstance().getKeyLeftMobilePressed()));

        image.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                keyLeft = true;
                updateKeys();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                keyLeft = false;
                updateKeys();
                super.touchUp(event, x, y, pointer, button);
            }
        });
        table.add(image).left().size(buttonSize);
        table.add().center();
        image = new ImageButton(new TextureRegionDrawable(TextureFactory.getInstance().getKeyRightMobile()), new TextureRegionDrawable(TextureFactory.getInstance().getKeyRightMobilePressed()));

        image.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                keyRight = true;
                updateKeys();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                keyRight = false;
                updateKeys();
                super.touchUp(event, x, y, pointer, button);
            }
        });
        table.add(image).right().size(buttonSize);

        table.row();
        //---------------------

        table.add().bottom().left();
        image = new ImageButton(new TextureRegionDrawable(TextureFactory.getInstance().getKeyDownMobile()), new TextureRegionDrawable(TextureFactory.getInstance().getKeyDownMobilePressed()));

        image.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                keyDown = true;
                updateKeys();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                keyDown = false;
                updateKeys();
                super.touchUp(event, x, y, pointer, button);
            }
        });
        table.add(image).bottom().center().size(buttonSize);
        table.add().bottom().right();
        //---------------------

        stage.addActor(touchpad);
        stage.addActor(table);
    }

    public void updateKeys() {
        if(keyLeft){
            directionChoisie = Orientation.GAUCHE;
        }else if(keyRight){
            directionChoisie = Orientation.DROITE;
        }else if(keyDown){
            directionChoisie = Orientation.BAS;
        }else if(keyUp){
            directionChoisie = Orientation.HAUT;
        }else{
            directionChoisie = Orientation.NO_ORIENTATION;
        }
    }


    public void draw(){
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public void resize(int width, int height){
        stage.getViewport().update(width, height, true);
    }

    public Stage getStage() {
        return stage;
    }

    public Vector2 getAcceleration() {
        return new Vector2(touchpad.getKnobPercentX(), touchpad.getKnobPercentY());
    }

    public void dispose(){
        stage.dispose();

    }

    public Orientation getDirection() {
        return directionChoisie;
    }
}
