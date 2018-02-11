package com.lyds.gevaudan.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.lyds.gevaudan.Gevaudan;

/**
 * Created by ami on 25/01/2018.
 */

public class Wolf {

    private static final int GRAVITY=-10;
    private Vector3 position;
    private Texture wolf;
    private Integer lifePoints;
    private Vector3 velocity;

    public Wolf(int x,int y)
    {
        position=new Vector3(x,y,0);
        velocity=new Vector3(0,0,0);
        lifePoints = 1000;
        wolf=new Texture("wolf1.png");
    }

    public void update(float dt)
    { if(position.y>0)
        {
        velocity.add(0, GRAVITY, 0);
        }
        velocity.scl(dt);
        position.add(velocity.x,velocity.y,0);
        velocity.scl(1/dt);
        if(position.y < 0)
        {
            position.y=0;
        }
        if (position.x > Gevaudan.WIDTH/2)
        {
            position.x=Gevaudan.WIDTH/2;
        }
        if(position.x<0)
        {
            position.x=0;
        }
    }

    public Vector3 getPosition() {
        return position;
    }
    public Texture getWolf() {
        return wolf;
    }
    public void jump()
    {
        velocity.y=(250);
    }
    public void accelerate() {
        velocity.x=(150);
    }
    public void deaccelerate()
    {
        velocity.x=(-150);
    }
}
