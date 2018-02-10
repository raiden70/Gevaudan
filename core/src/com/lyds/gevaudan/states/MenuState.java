package com.lyds.gevaudan.states;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.lyds.gevaudan.Gevaudan;

import javax.xml.soap.Text;

/**
 * Created by ami on 20/01/2018.
 */

public class MenuState extends State implements InputProcessor{

    private  Skin skin;
    private  Stage stage;
    private Table table;

    private Sprite background;
    private TextButton playButton, exitButton;
    private Sprite historyButton;
    private Sprite optionsButton;
    private Sprite scoreButton;
    private  static  final String TAG = "Lyds";
    private Vector3 touchPoint;

    public MenuState(GameStateManager gsm) {
        super(gsm);

        skin = new Skin(Gdx.files.internal("button/uiskin.json"));
        stage = new Stage(viewport);
        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.top);
        table.setPosition(0,Gevaudan.HEIGHT);

        playButton = new TextButton("Jouer",skin);
        exitButton = new TextButton("Quit Game",skin);

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Clicked button","Yep, you did");
                event.stop();
            }
        });

        table.padTop(5);
        table.add(playButton).padBottom(5).size(20,10);
        table.row();
        table.add(exitButton).size(20,10);

        stage.addActor(table);

        background =new Sprite(new Texture(Gdx.files.internal("vieww.png")));
        background.setSize(Gevaudan.WIDTH,Gevaudan.HEIGHT);
        background.setPosition(0,0);
        touchPoint= new Vector3();

        InputMultiplexer im = new InputMultiplexer(stage,this);
        Gdx.input.setInputProcessor(im);

        historyButton = new Sprite(new Texture("history.png"));
        optionsButton = new Sprite(new Texture("option.png"));
        scoreButton = new Sprite(new Texture("scores.png"));

        historyButton.setSize(20,10);
        optionsButton.setSize(20,10);
        scoreButton.setSize(20,10);

        historyButton.setPosition(Gevaudan.WIDTH/2-historyButton.getWidth()/2,Gevaudan.HEIGHT/2-historyButton.getHeight()/2);
        optionsButton.setPosition(Gevaudan.WIDTH/2-historyButton.getWidth()/2,historyButton.getY()-optionsButton.getHeight()-2);
        scoreButton.setPosition(optionsButton.getX(),optionsButton.getY()-historyButton.getHeight()-2);








        //Ajout des images a dessiner
        /*background=new Texture("vieww.png");
        playBtn=new Texture("play-button.png");
        history = new Texture("history.png");
        options = new Texture("option.png");
        exit = new Texture("exit.png");
        score = new Texture("scores.png");*/
        //cam.setToOrtho(false, Gevaudan.WIDTH,Gevaudan.HEIGHT);

    }



    @Override
    public void handleInput() {
    if (Gdx.input.justTouched())
    {

        viewport.unproject(touchPoint.set(Gdx.input.getX(),Gdx.input.getY(),0));
        if(historyButton.getBoundingRectangle().contains(touchPoint.x,touchPoint.y)){
            gsm.set(new HistoryState(gsm));
        }
        else if(optionsButton.getBoundingRectangle().contains(touchPoint.x,touchPoint.y)){
            gsm.set(new OptionsState(gsm));
        }

        else if(scoreButton.getBoundingRectangle().contains(touchPoint.x,touchPoint.y)) {
            gsm.set(new ScoresState(gsm));
        }


        //gsm.set(new PlayState(gsm));
       // dispose();
    }
    }

    @Override
    public void dispose(){

        historyButton.getTexture().dispose();
        optionsButton.getTexture().dispose();
        scoreButton.getTexture().dispose();


        /*background.dispose();
        playBtn.dispose();
        history.dispose();
        exit.dispose();
        options.dispose();
        score.dispose();*/

    }

    @Override
    public void update(float dt) {

        handleInput();
    }




    @Override
    //zone de dessin des images en definissant leur emplacement sur l'écran
    public void render(SpriteBatch spriteBatch) {

        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.begin();
        background.draw(spriteBatch);
        historyButton.draw(spriteBatch);
        optionsButton.draw(spriteBatch);
        scoreButton.draw(spriteBatch);
        spriteBatch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();


        /*spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.begin();
        spriteBatch.draw(background,cam.position.x-(cam.viewportWidth/2),0);
        spriteBatch.draw(playBtn,(Gevaudan.WIDTH/2)-(playBtn.getWidth()/2),Gevaudan.HEIGHT/2);
        spriteBatch.draw(history,(Gevaudan.WIDTH/8)-(history.getWidth()/8),Gevaudan.HEIGHT/4);
        spriteBatch.draw(exit,100,158);
        spriteBatch.draw(options,150,155);
        spriteBatch.draw(score,145,120);
        spriteBatch.end();*/
    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
