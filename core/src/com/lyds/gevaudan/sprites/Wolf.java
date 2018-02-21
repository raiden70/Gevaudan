package com.lyds.gevaudan.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.lyds.gevaudan.Gevaudan;

/**
 * Created by ami on 25/01/2018.
 */

public class Wolf {

    private static final int GRAVITY=-10;
    public int MOVEMENTS=300;
    private Vector3 position;
    private Texture wolf;
    private Vector3 velocity;

    private Animation wolfAnim;
    public Wolf(int x,int y)
    {
        position=new Vector3(x,y,0);
        velocity=new Vector3(0,0,0);
       // wolf=new Texture("wolf1.png");
        Texture texture=new Texture("wolfanime.png");
        wolfAnim=new Animation(new TextureRegion(texture),5,0.5f );
    }

    public void update(float dt)
    {
        wolfAnim.update(dt);
        if(position.y>0)
        {
        velocity.add(0, GRAVITY, 0);
        }
        velocity.scl(dt);
        position.add(MOVEMENTS*dt,velocity.y,0);
        velocity.scl(1/dt);
        if(position.y < 70)
        {
            position.y=70;
        }
        /*
        if (position.x > Gevaudan.WIDTH/2)
        {
            position.x=Gevaudan.WIDTH/2;
        }
        if(position.x<0)
        {
            position.x=0;
       }
       */
    }

    public Vector3 getPosition() {
        return position;
    }
    public TextureRegion getWolf() {
        return wolfAnim.getFrame();
    }
    public void jump()
    {
        velocity.y=(250);
    }
   /* public void accelerate() {
        velocity.x=(150);
    }
    public void deaccelerate()
    {
        velocity.x=(-150);
    }*/
}
