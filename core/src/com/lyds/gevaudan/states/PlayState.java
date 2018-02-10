package com.lyds.gevaudan.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lyds.gevaudan.Gevaudan;
import com.lyds.gevaudan.sprites.Wolf;

/**
 * Created by ami on 25/01/2018.
 */

public class PlayState extends State{
    private Wolf wolf;
    private Texture background;
    public int playbtn;
    public PlayState(GameStateManager gsm) {
        super(gsm);
       wolf=new Wolf(100,100);
        background=new Texture("view2.png");
        cam.setToOrtho(false, Gevaudan.WIDTH,Gevaudan.HEIGHT);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched())
        {
            wolf.jump();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || (Gdx.input.getAccelerometerY() > 0))
        {
            wolf.accelerate();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || (Gdx.input.getAccelerometerY() < 0))
        {
            wolf.deaccelerate();
        }

    }
//hey
    @Override
    public void update(float dt) {
    handleInput();
    wolf.update(dt);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
    spriteBatch.setProjectionMatrix(cam.combined);
    spriteBatch.begin();
    spriteBatch.draw(background,cam.position.x-(cam.viewportWidth/2),0);
    spriteBatch.draw(wolf.getWolf(),wolf.getPosition().x,wolf.getPosition().y);
    spriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}
