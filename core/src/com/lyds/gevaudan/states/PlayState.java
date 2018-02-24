package com.lyds.gevaudan.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.lyds.gevaudan.Gevaudan;
import com.lyds.gevaudan.sprites.Wolf;
import com.lyds.gevaudan.sprites.Bonus;
import com.lyds.gevaudan.sprites.Ennemy;

import java.util.Random;

/**
 * Created by ami on 25/01/2018.
 */

public class PlayState extends State {
    private static final int SPACING = 2000;
    private static final int COUNT = 10;
    private Array<Ennemy> ennemyArray;
    private Array<Bonus> bonusArray;
    private Wolf wolf;
    private Vector2 gpos1;
    private Label text;
    private String point_de_vies;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        Random rand;
        wolf = new Wolf(100,70);
        cam.setToOrtho( false, Gevaudan.WIDTH * 3 , Gevaudan.HEIGHT * 3);
        gpos1 = new Vector2(cam.position.x-cam.viewportWidth/2,0);
        point_de_vies = String.valueOf(wolf.getLifePoints());

        BitmapFont font = new BitmapFont();
        Label.LabelStyle textStyle;
        textStyle = new Label.LabelStyle();
        textStyle.font = font;
        text = new Label("", textStyle);
        text.setFontScale(4f);
        text.setText(point_de_vies);

        ennemyArray = new Array<Ennemy>();
        bonusArray = new Array<Bonus>();

        for (int i=1;i <=COUNT*2;i++)
        {
            rand = new Random();
            if ( (rand.nextInt() %2) == 0 )
                ennemyArray.add(new Ennemy(i*SPACING));
            else
                bonusArray.add(new Bonus(i*SPACING));
        }

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

    @Override
    public void update(float dt) {
        handleInput();
        wolf.update(dt);
        cam.position.x = wolf.getPosition().x + 80;
        text.setPosition(wolf.getPosition().x, 1200);

        for (Ennemy ennemy :ennemyArray){
            if ( cam.position.x-(cam.viewportWidth/2)> ennemy.getPosition().x + ennemy.width())
            {
                ennemy.reposition(ennemy.getPosition().x + ennemy.width() + SPACING*COUNT);
            }
            if (ennemy.collides(wolf.getWolf_bounds())){
                wolf.update_ennemy_lifePoints(ennemy.getDamage());
                point_de_vies = String.valueOf(wolf.getLifePoints());
                text.setText(point_de_vies);
                if ( wolf.getLifePoints() <= 0){
                    gsm.set(new PlayState(gsm));
                }
                ennemy.reposition(ennemy.getPosition().x + ennemy.width() + SPACING*COUNT);
            }
        }

        for (Bonus bonus :bonusArray){
            if ( cam.position.x-(cam.viewportWidth/2)> bonus.getPosition().x + bonus.width())
            {
                bonus.reposition(bonus.getPosition().x + bonus.width() + SPACING*COUNT);
            }
            if (bonus.collides(wolf.getWolf_bounds())) {
                wolf.update_bonus_lifePoints(bonus.getLifePoints());
                point_de_vies = String.valueOf(wolf.getLifePoints());
                text.setText(point_de_vies);
                bonus.reposition(bonus.getPosition().x + bonus.width() + SPACING * COUNT);
            }
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        text.draw(sb, 1);
        sb.draw(wolf.getWolf(), wolf.getPosition().x ,wolf.getPosition().y);

        for (Ennemy ennemy: ennemyArray ){
            sb.draw(ennemy.getTexture(), ennemy.getPosition().x, ennemy.getPosition().y);
        }

        for (Bonus bonus: bonusArray ){
            sb.draw(bonus.getTexture(), bonus.getPosition().x, bonus.getPosition().y);
        }

        sb.end();
    }

    @Override
    public void dispose() {

    }
}