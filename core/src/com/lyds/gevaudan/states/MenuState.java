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
    private Texture history;
    private Texture options;
    private Texture exit;
    private Texture score;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        //Ajout des images a dessiner
        background=new Texture("vieww.png");
        playBtn=new Texture("play-button.png");
        history = new Texture("history.png");
        options = new Texture("option.png");
        exit = new Texture("exit.png");
        score = new Texture("scores.png");
        //playBtn=new Texture("cloud.png");
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
        history.dispose();
        exit.dispose();
        options.dispose();
        score.dispose();
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
        spriteBatch.draw(background,cam.position.x-(cam.viewportWidth/2),0);
        spriteBatch.draw(playBtn,(Gevaudan.WIDTH/2)-(playBtn.getWidth()/2),Gevaudan.HEIGHT/2);
        spriteBatch.draw(history,(Gevaudan.WIDTH/8)-(history.getWidth()/8),Gevaudan.HEIGHT/4);
        spriteBatch.draw(exit,100,158);
        spriteBatch.draw(options,150,155);
        spriteBatch.draw(score,145,120);
        //spriteBatch.draw(playBtn,100,100);
        spriteBatch.end();
    }
}
