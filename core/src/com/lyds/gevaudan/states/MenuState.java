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

    //private  Skin skin;
   private  Stage stage;
    //private Table table;

    private Sprite background;
   // private TextButton playButton, exitButton;
    private Sprite StartGame;
    private Sprite quitter;
    private Sprite historyButton;
    private Sprite optionsButton;
    private Sprite scoreButton;
   // private  static  final String TAG = "Lyds";
    private Vector3 touchPoint;

    public MenuState(GameStateManager gsm) {
        super(gsm);

        //skin = new Skin(Gdx.files.internal("ui/defaultskin.json"));
        stage = new Stage(viewport);
        /*table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.top);
        table.setPosition(0,Gevaudan.HEIGHT);
*/
       /* playButton = new TextButton("Jouer",skin);
        exitButton = new TextButton("Quit Game",skin);
*/
       /* playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Clicked button","Yep, you did");
                event.stop();
            }
        });*/


/*

        table.padTop(5);
        table.add(playButton).padBottom(5).size(20,10);
        table.row();
        table.add(exitButton).size(20,10);
*/

       // stage.addActor(table);

        background =new Sprite(new Texture(Gdx.files.internal("vieww.png")));
        background.setSize(Gevaudan.WIDTH,Gevaudan.HEIGHT);
        background.setPosition(0,0);
        touchPoint= new Vector3();

        /*InputMultiplexer im = new InputMultiplexer(stage,this);
        Gdx.input.setInputProcessor(im);*/

        StartGame = new Sprite(new Texture("play-button.png"));
        quitter = new Sprite(new Texture("exit.png"));

        historyButton = new Sprite(new Texture("history.png"));
        optionsButton = new Sprite(new Texture("option.png"));
        scoreButton = new Sprite(new Texture("scores.png"));

        historyButton.setSize(30,20);
        optionsButton.setSize(30,20);
        scoreButton.setSize(30,20);

        StartGame.setSize(30,20);
        quitter.setSize(30,20);

        historyButton.setPosition(Gevaudan.WIDTH/2-historyButton.getWidth()/2,Gevaudan.HEIGHT/2-historyButton.getHeight()/2);
        optionsButton.setPosition(Gevaudan.WIDTH/2-historyButton.getWidth()/2,historyButton.getY()-optionsButton.getHeight()-2);
        StartGame.setPosition(Gevaudan.WIDTH/2-optionsButton.getWidth()/2, optionsButton.getY()-StartGame.getHeight()-2);
        quitter.setPosition(Gevaudan.WIDTH/2-StartGame.getWidth()/2,StartGame.getY()-quitter.getHeight()-2);
        scoreButton.setPosition(quitter.getX(),quitter.getY()-historyButton.getHeight()-2);

    }



    @Override
    public void handleInput() {
    if (Gdx.input.justTouched())
    {

        viewport.unproject(touchPoint.set(Gdx.input.getX(),Gdx.input.getY(),0)); //permet de repondre lorsque un bouton est cliqué
        if(historyButton.getBoundingRectangle().contains(touchPoint.x,touchPoint.y)){
            gsm.set(new HistoryState(gsm));
        }
        else if(optionsButton.getBoundingRectangle().contains(touchPoint.x,touchPoint.y)){
            gsm.set(new OptionsState(gsm));
        }

        else if(scoreButton.getBoundingRectangle().contains(touchPoint.x,touchPoint.y)) {
            gsm.set(new ScoresState(gsm));
        }
        else if(StartGame.getBoundingRectangle().contains(touchPoint.x,touchPoint.y)){
            gsm.set(new PlayState(gsm));
        }
        else if(quitter.getBoundingRectangle().contains(touchPoint.x,touchPoint.y)){
            gsm.set(new ExitState(gsm));
        }


        gsm.set(new PlayState(gsm));
        dispose();
    }

       // dispose();

    }

    @Override
    public void dispose(){

        historyButton.getTexture().dispose();
        optionsButton.getTexture().dispose();
        scoreButton.getTexture().dispose();
        StartGame.getTexture();
        quitter.getTexture();


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
        StartGame.draw(spriteBatch);
        quitter.draw(spriteBatch);
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
