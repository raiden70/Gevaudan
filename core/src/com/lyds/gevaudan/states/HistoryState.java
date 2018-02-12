package com.lyds.gevaudan.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lyds.gevaudan.Gevaudan;

/**
 * Created by root on 10/02/18.
 */

public class HistoryState extends State {
    private Texture background;

    public HistoryState(GameStateManager gsm) {

        super(gsm);

        background=new Texture("view2.png");
        cam.setToOrtho(false, Gevaudan.WIDTH,Gevaudan.HEIGHT);
    }

    @Override
    public void handleInput() {

        if (Gdx.input.justTouched()) {

        }

    }

    @Override
    public void update(float dt) {

        handleInput();

    }

    @Override
    public void render(SpriteBatch spriteBatch) {

        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.begin();
        spriteBatch.draw(background,cam.position.x-(cam.viewportWidth/2),0);
        spriteBatch.end();

    }

    @Override
    public void dispose() {

    }
}


