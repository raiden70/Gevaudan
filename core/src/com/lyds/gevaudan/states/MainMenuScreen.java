package com.lyds.gevaudan.states;

/**
 * Created by root on 05/02/18.
 */


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.lyds.gevaudan.Gevaudan;


public class MainMenuScreen implements Screen{

    Skin skin;
    Stage stage;

    Gevaudan game;


    public MainMenuScreen(Gevaudan game) {
        this.game = game;
        stage=new Stage(new StretchViewport(800,480));
        Gdx.input.setInputProcessor(stage);

        skin = new Skin( Gdx.files.internal( "ui/defaultskin.json" ));
        //definition d'une table pour enregister les données de menu

        Table table=new Table();
        table.setSize(800,480);

        final TextButton startGame=new TextButton("play-button.png",skin);
        table.add(startGame).width(200).height(50);
        table.row();

        TextButton options=new TextButton("options",skin);
        table.add(options).width(150).padTop(10).padBottom(3);
        table.row();

        TextButton credits=new TextButton("history",skin);
        table.add(credits).width(150);
        table.row();

        TextButton quit=new TextButton("exit",skin);
        table.add(quit).width(100).padTop(10);
        table.row();

        TextButton score=new TextButton("score",skin);
        table.add(quit).width(100).padTop(10);
        table.row();

        TextField text=new TextField("",skin);
        table.add(text).width(100).padTop(10);
        table.row();
        // [...]
        //String value=text.getText();

        CheckBox box=new CheckBox("done",skin);
        table.add(box).width(100);
        table.row();
        // [...]
        //boolean checked=box.isChecked();

        /*String[] items={"cool","mega","awesome"};
        SelectBox selectbox=new SelectBox(items, skin);
        table.add(selectbox).width(150);*/
        // [...]
        //String selection=selectbox.getSelection();

        stage.addActor(table);

        startGame.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                startGame.addAction(Actions.fadeOut(0.7f));
                // game.setScreen(game.anotherScreen);
            }
        });

    }



    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // clear the screen
        Gdx.gl.glClearColor(1,1,1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // let the stage act and draw
        stage.act(delta);
        stage.draw();
        stage.setViewport(new StretchViewport(800, 480));

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        stage.dispose();

    }

    @Override
    public void dispose() {
        stage.dispose();

    }
}
