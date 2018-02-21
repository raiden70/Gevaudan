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
    private Texture playBtn;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        background=new Texture("vieww.png");
        playBtn=new Texture("cloud.png");
        cam.setToOrtho(false, Gevaudan.WIDTH,Gevaudan.HEIGHT);
    }

    @Override
    public void handleInput() {
    if (Gdx.input.justTouched())
    {
        gsm.set(new PlayState(gsm));
        dispose();
    }
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
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
        spriteBatch.draw(playBtn,100,100);
        spriteBatch.end();
    }
}
