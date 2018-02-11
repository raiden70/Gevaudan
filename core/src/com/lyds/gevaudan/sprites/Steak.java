package com.lyds.gevaudan.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Sacha on 22/01/2018.
 */

/* Le steack doit tomber du ciel */

public class Steak extends Bonus{

    public Steak(int x, int y){
        position = new Vector3(x,y,0);
        texture = new Texture("viande.png");
        lifePoints = 10;
    }
}
