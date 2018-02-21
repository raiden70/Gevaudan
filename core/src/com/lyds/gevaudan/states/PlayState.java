package com.lyds.gevaudan.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.lyds.gevaudan.Gevaudan;
import com.lyds.gevaudan.sprites.Cloud;
import com.lyds.gevaudan.sprites.Wolf;
import com.badlogic.gdx.utils.Array;


/**
 * Created by ami on 25/01/2018.
 */

public class PlayState extends State{
    private static final int SPACING=150;
    private static final int COUNT_OBSTCLE=6;
    private Array<Cloud> obstacleArray;

    private Wolf wolf;
    private Texture background;
    private Texture ground;
    private Vector2 gpos1,gpos2;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        wolf=new Wolf(100,70);
        background=new Texture("background1.png");
        ground=new Texture("ground.png");
        gpos1=new Vector2(cam.position.x-cam.viewportWidth/2,0);
        gpos2=new Vector2((cam.position.x-cam.viewportWidth/2)+ground.getWidth(),0);

        cam.setToOrtho(false, Gevaudan.WIDTH,Gevaudan.HEIGHT);
        obstacleArray=new Array<Cloud>();
        for (int i=1;i <=COUNT_OBSTCLE;i++)
        {
            obstacleArray.add(new Cloud(i*SPACING));
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
    for (Cloud cloud :obstacleArray)
    {
        if(cam.position.x-(cam.viewportWidth/2)> cloud.getPosition().x+ cloud.width())
        {
            cloud.reposition(cloud.getPosition().x+ cloud.width()+(SPACING*COUNT_OBSTCLE));
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
