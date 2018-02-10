package com.lyds.gevaudan.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lyds.gevaudan.Gevaudan;

/**
 * Created by ami on 20/01/2018.
 */

public abstract class State {
    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameStateManager gsm;
    protected Viewport viewport;

    protected State(GameStateManager gsm)
    {
        this.gsm=gsm;
        cam=new OrthographicCamera();
        //rajout ici
        viewport= new ExtendViewport(Gevaudan.WIDTH,Gevaudan.HEIGHT,cam);
        viewport.apply();
        cam.position.set(Gevaudan.WIDTH/2, Gevaudan.HEIGHT/2,0);
        mouse=new Vector3();
    }
    public abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch spriteBatch);
    public abstract void dispose();
    /*public abstract void resize(int width, int height);*/
}
