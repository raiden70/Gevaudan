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
import com.lyds.gevaudan.sprites.Bonus;
import com.lyds.gevaudan.sprites.Cloud;
import com.lyds.gevaudan.sprites.Ennemy;
import com.lyds.gevaudan.sprites.LifePoints;
import com.lyds.gevaudan.sprites.Wolf;

import java.util.Random;

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
    private Texture score_title;
    private String point_de_vies;
    private LifePoints barre_points_vie;
    private String score_final;

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
        score_final = "O";
        barre_points_vie = new LifePoints();
        score_title = new Texture("score-title.png");

        BitmapFont font = new BitmapFont();
        Label.LabelStyle textStyle;
        textStyle = new Label.LabelStyle();
        textStyle.font = font;
        text = new Label("", textStyle);
        text.setFontScale(3f);
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
        {   if(wolf.getPosition().y==70)
            wolf.jump();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || (Gdx.input.getAccelerometerY() > 0))
        { if(wolf.MOVEMENTS<800)
            wolf.MOVEMENTS=wolf.MOVEMENTS+10;
        else
            wolf.MOVEMENTS=800;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || (Gdx.input.getAccelerometerY() < 0))
        {
            if(wolf.MOVEMENTS>300)
                wolf.MOVEMENTS=wolf.MOVEMENTS-10;
            else
                wolf.MOVEMENTS=300;
        }
    }

    @Override
    public void update(float dt) {
        int pv;
        int distance = (int)wolf.getPosition().x - 100;
        int score = 0;
        Texture texture;
        handleInput();
        updateground();
        wolf.update(dt);

        for (Bonus bonus:bonusArray) {
            bonus.update(dt);
        }

        for (Ennemy ennemy:ennemyArray) {
            ennemy.update(dt);
        }

        cam.position.x=wolf.getPosition().x+300;// replace 200 by a function with dt to make the wolf move
        barre_points_vie.setPositionX((int)wolf.getPosition().x-80);
        text.setPosition(wolf.getPosition().x  + 540, 390);

        score = score + (distance/40);
        score_final = String.valueOf(score);
        text.setText(score_final);

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
                pv = Integer.parseInt(point_de_vies);
                texture = barre_points_vie.check_lifePoints(pv);
                barre_points_vie.setTexture(texture);
                text.setText(point_de_vies);
                if ( wolf.getLifePoints() <= 0){
                    gsm.set(new GameOverState(gsm, score_final));
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
                pv = Integer.parseInt(point_de_vies);
                texture = barre_points_vie.check_lifePoints(pv);
                barre_points_vie.setTexture(texture);
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


        for (Ennemy ennemy: ennemyArray ){
            spriteBatch.draw(ennemy.getEnnemy(), ennemy.getPosition().x, ennemy.getPosition().y);
        }

        for (Bonus bonus: bonusArray ){
            spriteBatch.draw(bonus.getBonus(), bonus.getPosition().x, bonus.getPosition().y);
        }

        for (Cloud cloud :obstacleArray)
        {
            spriteBatch.draw(cloud.getTexture(), cloud.getPosition().x, cloud.getPosition().y);
        }
        spriteBatch.draw(barre_points_vie.getTexture(), barre_points_vie.getPosition().x, barre_points_vie.getPosition().y);
        spriteBatch.draw(score_title, barre_points_vie.getPosition().x + 490 ,barre_points_vie.getPosition().y - 110 );
        text.draw(spriteBatch, 1);
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

