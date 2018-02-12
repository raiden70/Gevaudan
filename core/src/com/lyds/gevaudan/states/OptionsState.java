package com.lyds.gevaudan.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.lyds.gevaudan.Gevaudan;

/**
 * Created by root on 10/02/18.
 */

public class OptionsState extends State implements InputProcessor {

    private Texture background;
    private Sprite luminosite;
    private Sprite volume_b;
    private Sprite volume_h;
    private Vector3 touchPoint;

    //long id = sound.play(1.0f); //initialiser le son

    public OptionsState(GameStateManager gsm) {

        super(gsm);

        background=new Texture("view2.png");
        cam.setToOrtho(false, Gevaudan.WIDTH,Gevaudan.HEIGHT);

        luminosite = new Sprite(new Texture("luminosity.png"));
        volume_b = new Sprite(new Texture("v_bas.png"));
        volume_h = new Sprite(new Texture("v_haut.png"));
        touchPoint= new Vector3();

        luminosite.setSize(30,20);
        volume_b.setSize(30,20);
        volume_h.setSize(30,20);

        luminosite.setPosition(Gevaudan.WIDTH/2-luminosite.getWidth()/2,Gevaudan.HEIGHT/2-luminosite.getHeight()/2);
        volume_b.setPosition(Gevaudan.WIDTH/2-luminosite.getWidth()/2,luminosite.getY()-volume_b.getHeight()-2);
        volume_h.setPosition(volume_b.getX(),volume_b.getY()-luminosite.getHeight()-2);
    }

    @Override
    public void handleInput() {

        if (Gdx.input.justTouched()) {

            viewport.unproject(touchPoint.set(Gdx.input.getX(),Gdx.input.getY(),0)); //permet de repondre lorsque un bouton est cliqué
            if(luminosite.getBoundingRectangle().contains(touchPoint.x,touchPoint.y)){
                gsm.set(new HistoryState(gsm));
            }
            else if(volume_b.getBoundingRectangle().contains(touchPoint.x,touchPoint.y)){
                //gsm.set(new OptionsState(gsm));
                // sound.setPan(id, 1, 1);// diminuer  le volume

            }

            else if(volume_h.getBoundingRectangle().contains(touchPoint.x,touchPoint.y)) {
               // sound.setPan(id, -1, 1);// augmenter le volume
                //sound.stop(id);
            }



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
