package com.lyds.gevaudan.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lyds.gevaudan.Gevaudan;
import com.lyds.gevaudan.sprites.Wolf;


/**
 * Created by root on 10/02/18.
 */

public class ScoresState extends State {

    private Texture background;
    private Wolf wolf;
    private int point=0;
    private int score=0;
   // private Sprite scoreButton;

    //x du loup augmente alors point=+50
    //lecture de fichier java pour la liste de scores

    public ScoresState(GameStateManager gsm) {

        super(gsm);
        point=this.point;
        score=this.score;


        background=new Texture("view2.png");
        cam.setToOrtho(false, Gevaudan.WIDTH,Gevaudan.HEIGHT);
    }


    public int Nombre_point(){

        if(wolf.getPosition().x!=0){

            point+=50;

        }

        return point;

    }

    public int NbScore(){

       // Nombre_point();
        score=point;
        return score;
    }

  /*  public String toString(){


    }
*/
    @Override
    public void handleInput() {

        if (Gdx.input.justTouched()) {
            NbScore();
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
