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
import com.lyds.gevaudan.sprites.Cloud;


/**
 * Created by ami on 25/01/2018.
 */

public class PlayState extends State {
    private static final int SPACING_BE = 2000;
    private static final int SPACING=150;
    private static final int COUNT = 10;
    private static final int COUNT_OBSTCLE=6;
    private Array<Ennemy> ennemyArray;
    private Array<Bonus> bonusArray;
    private Array<Cloud> obstacleArray;
    private Wolf wolf;
    private Texture background;
    private Texture ground;
    private Vector2 gpos1,gpos2;
    private Label text;
    private String point_de_vies;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        Random rand;
        wolf = new Wolf(100, 70);
        background = new Texture("background1.png");
        ground = new Texture("ground.png");
        cam.setToOrtho(false, Gevaudan.WIDTH, Gevaudan.HEIGHT);
        gpos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, 0);
        gpos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(), 0);
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
        obstacleArray = new Array<Cloud>();

        for (int i = 1; i <= COUNT_OBSTCLE; i++) {
            obstacleArray.add(new Cloud(i * SPACING));
        }
        for (int i = 1; i <= COUNT * 2; i++) {
            rand = new Random();
            if ((rand.nextInt() % 2) == 0)
                ennemyArray.add(new Ennemy(i * SPACING_BE));
            else
                bonusArray.add(new Bonus(i * SPACING_BE));
        }
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()||(Gdx.input.isKeyPressed(Input.Keys.UP)))
        {
            wolf.jump();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || (Gdx.input.getAccelerometerY() > 0))
        {
            wolf.MOVEMENTS=wolf.MOVEMENTS+10;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || (Gdx.input.getAccelerometerY() < 0))
        {
            wolf.MOVEMENTS=wolf.MOVEMENTS-10;
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateground();
        wolf.update(dt);
        cam.position.x=wolf.getPosition().x+300;// replace 200 by a function with dt to make the wolf move
        text.setPosition(wolf.getPosition().x, 350);

        for (Cloud cloud :obstacleArray)
        {
            if(cam.position.x-(cam.viewportWidth/2)> cloud.getPosition().x+ cloud.width())
            {
                cloud.reposition(cloud.getPosition().x+ cloud.width()+(SPACING*COUNT_OBSTCLE));
            }
        }

        for (Ennemy ennemy :ennemyArray){
            if ( cam.position.x-(cam.viewportWidth/2)> ennemy.getPosition().x + ennemy.width())
            {
                ennemy.reposition(ennemy.getPosition().x + ennemy.width() + SPACING_BE*COUNT);
            }
            if (ennemy.collides(wolf.getWolf_bounds())){
                wolf.update_ennemy_lifePoints(ennemy.getDamage());
                point_de_vies = String.valueOf(wolf.getLifePoints());
                text.setText(point_de_vies);
                if ( wolf.getLifePoints() <= 0){
                    gsm.set(new GameOverState(gsm));
                    dispose();
                }
                ennemy.reposition(ennemy.getPosition().x + ennemy.width() + SPACING_BE*COUNT);
            }
        }

        for (Bonus bonus :bonusArray){
            if ( cam.position.x-(cam.viewportWidth/2)> bonus.getPosition().x + bonus.width())
            {
                bonus.reposition(bonus.getPosition().x + bonus.width() + SPACING_BE*COUNT);
            }
            if (bonus.collides(wolf.getWolf_bounds())) {
                wolf.update_bonus_lifePoints(bonus.getLifePoints());
                point_de_vies = String.valueOf(wolf.getLifePoints());
                text.setText(point_de_vies);
                bonus.reposition(bonus.getPosition().x + bonus.width() + SPACING_BE * COUNT);
            }
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
    spriteBatch.setProjectionMatrix(cam.combined);
    spriteBatch.begin();
    spriteBatch.draw(background,cam.position.x-(cam.viewportWidth/2),cam.position.y-(cam.viewportHeight/2)-1);
    spriteBatch.draw(wolf.getWolf(),wolf.getPosition().x,wolf.getPosition().y);
    text.draw(spriteBatch, 1);

        for (Ennemy ennemy: ennemyArray ){
            spriteBatch.draw(ennemy.getTexture(), ennemy.getPosition().x, ennemy.getPosition().y);
        }

        for (Bonus bonus: bonusArray ){
            spriteBatch.draw(bonus.getTexture(), bonus.getPosition().x, bonus.getPosition().y);
        }

        for (Cloud cloud :obstacleArray)
        {
            spriteBatch.draw(cloud.getTexture(), cloud.getPosition().x, cloud.getPosition().y);
        }

        spriteBatch.draw(ground,gpos1.x,gpos1.y);
        spriteBatch.draw(ground,gpos2.x,gpos2.y);
        spriteBatch.end();
    }

    @Override
    public void dispose()
    {

    }
    private void updateground()
    {
        if(cam.position.x-(cam.viewportWidth/2)>gpos1.x+ground.getWidth())
            gpos1.add(ground.getWidth()*2,0);
        if(cam.position.x-(cam.viewportWidth/2)>gpos2.x+ground.getWidth())
            gpos2.add(ground.getWidth()*2,0);
    }
}
