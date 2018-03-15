package com.lyds.gevaudan.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by ami on 31/01/2018.
 */

public class Cloud {
    private static final int FLUCTUATION=200;
    private Texture texture;
    private Vector2 position;
    private Random rand;

    /* Les nuages, qui représentent un des éléments du décor sont définis sur cette classe */
    public Cloud(float x)
    {
        String clouds[]={"cloud1.png","cloud2.png","cloud3.png","cloud4.png","cloud5.png","cloud6.png"};
        Random random=new Random();
        texture=new Texture(clouds[random.nextInt(clouds.length-1)]);
        rand=new Random();
        position=new Vector2(x,280+rand.nextInt(FLUCTUATION));
    }

    public Texture getTexture() {
        return texture;
    }
    public Vector2 getPosition() {
        return position;
    }
    public void reposition(float x )
    {
        position.set(x,280+rand.nextInt(FLUCTUATION));
    }
    public int width()
    {
        return texture.getWidth();
    }
}
