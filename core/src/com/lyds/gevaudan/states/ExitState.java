package com.lyds.gevaudan.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lyds.gevaudan.Gevaudan;

/**
 * Created by root on 11/02/18.
 */

public class ExitState extends State {
    private Texture background;

    public ExitState(GameStateManager gsm) {
        super(gsm);
        background=new Texture("view2.png");
        cam.setToOrtho(false, Gevaudan.WIDTH,Gevaudan.HEIGHT);
    }

    @Override
    public void handleInput() {

        if (Gdx.input.justTouched()) {
            Gdx.app.exit();

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
