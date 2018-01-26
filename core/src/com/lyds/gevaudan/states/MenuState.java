package com.lyds.gevaudan.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lyds.gevaudan.Gevaudan;

/**
 * Created by ami on 20/01/2018.
 */

public class MenuState extends State {

    private Texture background;
    private Texture playbtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
    background = new Texture("background.jpeg");
    playbtn = new Texture("play.jpeg");



    }
    @Override
    public void handleInput() {

        if(Gdx.input.justTouched()){
            gsm.set(new playState(gsm));
            dispose();
        }

    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch spriteBatch) {

        spriteBatch.begin();
        spriteBatch.draw(background,0,0, Gevaudan.WIDTH, Gevaudan.HEIGHT);
        spriteBatch.draw(playbtn,(Gevaudan.WIDTH/2)-(playbtn.getWidth()/2),Gevaudan.HEIGHT/2);
        spriteBatch.end();



    }

    @Override
    public void dispose() {
        background.dispose();
        playbtn.dispose();
    }
}
