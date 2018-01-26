package com.lyds.gevaudan.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lyds.gevaudan.Gevaudan;

/**
 * Created by root on 27/01/18.
 */

public class playState extends State {

    private Texture wolf;
    public playState(GameStateManager gsm) {
        super(gsm);
        wolf = new Texture("wolf.png");
        cam.setToOrtho(false, Gevaudan.WIDTH/2, Gevaudan.HEIGHT/2);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(wolf,50,50);
        spriteBatch.end();
        spriteBatch.setProjectionMatrix(cam.combined);

    }

    @Override
    public void dispose() {

    }
}
